public class WarGame {
    private Player player1 = new Player("");
    private Player player2 = new Player("");
    private Deck temp1;
    private Deck temp2;

    public Player getPlayer1() {
        return player1;
    }

    public Player getPlayer2() {
        return player2;
    }

    public Deck getTemp1() {
        return temp1;
    }

    public Deck getTemp2() {
        return temp2;
    }

    public void setPlayer1(Player player1) {
        this.player1 = player1;
    }

    public void setPlayer2(Player player2) {
        this.player2 = player2;
    }

    public void setTemp1(Deck temp1) {
        this.temp1 = temp1;
    }

    public void setTemp2(Deck temp2) {
        this.temp2 = temp2;
    }

    public WarGame(String name1, String name2) {
        this.player1.setName(name1);
        this.player2.setName(name2);
        this.temp1 = new Deck(false);
        this.temp2 = new Deck(false);
    }

    public void initializeGame() {
        Deck deck = new Deck(true);
        deck.shuffle();
        Player[] orderOfPlay = firstPlayerToPlay(this.player1, this.player2);
        while (!deck.isEmpty()) {
            orderOfPlay[0].addCardToPlayingDeck(deck.removeTopCard());
            orderOfPlay[1].addCardToPlayingDeck(deck.removeTopCard());
        }
    }

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

    public String start() {
        System.out.println("Initializing the game...");
        initializeGame();
        Player[] orderOfPlay = firstPlayerToPlay(this.player1, this.player2);

        int i = 1;
        boolean isWar = false;

        while (!this.player1.outOfCards() && !this.player2.outOfCards()) { // loops only if both players have cards remaining
            System.out.println("------------------------- Round number " + i + " -------------------------");

            temp1.addCard(orderOfPlay[0].drawCard()); //draw card for the first player
            temp2.addCard(orderOfPlay[1].drawCard()); //draw card for the second player

            if (temp1.getTopCard().getValue() != temp2.getTopCard().getValue()) {
                playTurn(orderOfPlay, temp1, temp2, isWar);

            } else {
                isWar = true;

                while (temp1.getTopCard().getValue() == temp2.getTopCard().getValue()) {

                    System.out.println("Starting a war...");
                    int j = 0;

                    while (j < 2) {
                        if (this.player1.outOfCards() || this.player2.outOfCards()) {
                            break;
                        }
                        temp1.addCard(orderOfPlay[0].drawCard());
                        System.out.println(orderOfPlay[0].toString() + " drew a war card...");
                        temp2.addCard(orderOfPlay[1].drawCard());
                        System.out.println(orderOfPlay[1].toString() + " drew a war card...");
                        j++;
                    }
                    if (this.player1.outOfCards() || this.player2.outOfCards()) {
                        break;
                    }
                    temp1.addCard(orderOfPlay[0].drawCard());
                    temp2.addCard(orderOfPlay[1].drawCard());
                    if (temp1.getTopCard().getValue() == temp2.getTopCard().getValue()) {
                        continue;
                    } else {
                        playTurn(orderOfPlay, temp1, temp2, isWar);
                    }
                    isWar = false;
                    break;
                }
            }
            i++;
        }
        return playerWon(orderOfPlay);
    }

    public void playTurn(Player[] order, Deck temp1, Deck temp2, boolean isWar) {

        drewCard(order[0], order[1], temp1.getTopCard().toString(), temp2.getTopCard().toString());

        if (temp1.getTopCard().getValue() > temp2.getTopCard().getValue()) {
            clearTableAfterTurn(order[0], isWar);
        } else {
            clearTableAfterTurn(order[1], isWar);
        }

    }

    public void clearTableAfterTurn(Player playerWonRound, boolean isWar) {
        while (!temp2.isEmpty()) {
            playerWonRound.addCardToWinningDeck(temp2.removeTopCard()); // take all cards from second's deck and add them to winner's winning deck
        }
        while (!temp1.isEmpty()) {
            playerWonRound.addCardToWinningDeck(temp1.removeTopCard()); // take all cards from first's deck and add them to winner's winning deck
        }
        if (isWar) {
            System.out.println(playerWonRound.toString() + " won the war"); // if in war then print this statement
        } else {
            System.out.println(playerWonRound.toString() + " won");
        }
    }

    public void drewCard(Player first, Player second, String card1, String card2) {
        System.out.println(first.getName() + " drew " + card1);
        System.out.println(second.getName() + " drew " + card2);
    }

    public String playerWon(Player[] order) {
        if (order[0].outOfCards()) {
            return order[1].toString();
        }
        return order[0].toString();
    }

}
