public class WarGame {
    private final Player player1 = new Player("");
    private final Player player2 = new Player("");
    private final Deck temp1;
    private final Deck temp2;

    public WarGame(String name1, String name2) {
        this.player1.setName(name1);
        this.player2.setName(name2);
        this.temp1 = new Deck(false);
        this.temp2 = new Deck(false);
    }

    /**
     * @param player1 get a player.
     * @param player2 get a player.
     * @return an array which holds both players, the first slot will be occupied by the player who goes first, this will be determined lexicographically.
     */

    public Player[] firstPlayerToPlay(Player player1, Player player2) {
        Player[] order = new Player[2];
        if (player1.getName().compareTo(player2.getName()) < 0) {
            order[0] = player1;
            order[1] = player2;
            return order;
        }
        order[0] = player2;
        order[1] = player1;
        return order;
    }

    public void initializeGame() {
        Deck deck = new Deck(true); // create a new full deck.
        deck.shuffle(); // shuffle the new deck randomly.
        Player[] orderOfPlay = firstPlayerToPlay(this.player1, this.player2);
        while (!deck.isEmpty()) { // distribute cards evenly between both players, starting with the first player (according to our orderOfPlay).
            orderOfPlay[0].addCardToPlayingDeck(deck.removeTopCard());
            orderOfPlay[1].addCardToPlayingDeck(deck.removeTopCard());
        }
    }

    /**
     * The winner takes all cards and the method prints out his name.
     *
     * @param playerWonRound get the player with the highest value card.
     * @param isWar          which kind of round was played.
     */

    public void clearTableAfterTurn(Player playerWonRound, boolean isWar) {
        while (!temp2.isEmpty() && !temp1.isEmpty()) {
            playerWonRound.addCardToWinningDeck(temp2.removeTopCard()); // take all cards from second's deck and add them to winner's winning deck.
            playerWonRound.addCardToWinningDeck(temp1.removeTopCard()); // take all cards from first's deck and add them to winner's winning deck.
        }
        if (isWar) {
            System.out.println(playerWonRound.toString() + " won the war"); // if in war then print this statement.
        } else {
            System.out.println(playerWonRound.toString() + " won");
        }
    }

    /**
     * Prints out what each player drew this round.
     *
     * @param first  get the first player to play
     * @param second get the second player to play
     * @param card1  card of the first player
     * @param card2  card of the second player
     */
    public void drewCard(Player first, Player second, String card1, String card2) {
        System.out.println(first.getName() + " drew " + card1);
        System.out.println(second.getName() + " drew " + card2);
    }

    /**
     * This method uses drewCard and clearTableAfterTurn in order to finish a round.
     *
     * @param order array containing both players..
     * @param temp1 place for first player's cards in each round.
     * @param temp2 place for second player's cards in each round.
     * @param isWar a flag to state if both players are currently at war.
     */
    public void playTurn(Player[] order, Deck temp1, Deck temp2, boolean isWar) {

        drewCard(order[0], order[1], temp1.getTopCard().toString(), temp2.getTopCard().toString());

        if (temp1.getTopCard().getValue() > temp2.getTopCard().getValue()) {
            clearTableAfterTurn(order[0], isWar);
        } else {
            clearTableAfterTurn(order[1], isWar);
        }

    }

    /**
     * @param order gets the order of play.
     * @return the name of the winning player.
     */
    public String playerWon(Player[] order) {
        if (order[0].outOfCards()) {
            return order[1].toString();
        }
        return order[0].toString();
    }

    /**
     * @return the name of the winning player.
     */
    public String start() {
        System.out.println("Initializing the game...");
        initializeGame();
        Player[] orderOfPlay = firstPlayerToPlay(this.player1, this.player2); // set the order of play.

        int i = 1; // index for the number of current round.
        boolean isWar = false; // a flag to state if we are at war.

        while (!this.player1.outOfCards() && !this.player2.outOfCards()) { // loops only if both players have cards remaining.
            System.out.println("------------------------- Round number " + i + " -------------------------");

            temp1.addCard(orderOfPlay[0].drawCard()); //draw card for the first player.
            temp2.addCard(orderOfPlay[1].drawCard()); //draw card for the second player.

            if (temp1.getTopCard().getValue() != temp2.getTopCard().getValue()) { // if we are not at war.
                playTurn(orderOfPlay, temp1, temp2, isWar);

            } else {
                isWar = true; // we are at war.

                while (temp1.getTopCard().getValue() == temp2.getTopCard().getValue()) { // if we are still at war, continue looping.
                    drewCard(orderOfPlay[0], orderOfPlay[1], temp1.getTopCard().toString(), temp2.getTopCard().toString());
                    System.out.println("Starting a war...");
                    int j = 0; // index for drawing 2 cards.

                    while (j < 2) { // draw exactly two cards if possible.
                        if (this.player1.outOfCards() || this.player2.outOfCards()) { // check if one of the players is out of cards.
                            break; // break out of the loop and return the winner.
                        }
                        temp1.addCard(orderOfPlay[0].drawCard()); // draw the war card.
                        System.out.println(orderOfPlay[0].toString() + " drew a war card");
                        temp2.addCard(orderOfPlay[1].drawCard()); // draw the war card.
                        System.out.println(orderOfPlay[1].toString() + " drew a war card");
                        j++; // increment j.
                    }
                    if (this.player1.outOfCards() || this.player2.outOfCards()) { // check again if anyone is out of cards.
                        break;
                    }
                    temp1.addCard(orderOfPlay[0].drawCard()); // draw last card of the war.
                    temp2.addCard(orderOfPlay[1].drawCard()); // draw last card of the war.
                    if (temp1.getTopCard().getValue() == temp2.getTopCard().getValue()) { // if both cards have equal values, start another war.
                        continue;
                    } else {
                        playTurn(orderOfPlay, temp1, temp2, isWar);
                    }
                    isWar = false; // we are out of the war loop.
                    break;
                }
            }
            i++; // increment round.
        }
        return playerWon(orderOfPlay);
    }


}
