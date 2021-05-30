public class WarGame {
    private Player player1;
    private Player player2;
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
        while (!this.player1.outOfCards() || !this.player2.outOfCards()) {
            System.out.println("------------------------- Round number " + i + " -------------------------");
            temp1.addCard(orderOfPlay[0].drawCard());
            temp2.addCard(orderOfPlay[1].drawCard());
            if (temp1.getTopCard().getValue() != temp2.getTopCard().getValue()) {
                System.out.println(orderOfPlay[0].getName() + " drew" + temp1.deckOfCards.get(0).toString());
                System.out.println(orderOfPlay[1].getName() + " drew" + temp2.deckOfCards.get(0).toString());
                playTurn(orderOfPlay, temp1, temp2);
            } else {
                isWar = true;
                while (isWar) {
                    System.out.println("Starting a war...");
                    for (int i = 0; i < 3; i++) {
                        temp1.addCard(orderOfPlay[0].drawCard());
                        System.out.println(orderOfPlay[0].toString() + " drew a war card...");
                        temp2.addCard(orderOfPlay[1].drawCard());
                        System.out.println(orderOfPlay[0].toString() + " drew a war card...");
                    }
                    if (temp1.getTopCard().getValue() != temp2.getTopCard().getValue()) {
                        isWar = false;
                    }

                }

            }
            i++;
        }

    }

    public void playTurn(Player[] order, Deck temp1, Deck temp2) {
        if (temp1.getTopCard().getValue() > temp2.getTopCard().getValue()) {
            while (!temp1.isEmpty() && !temp2.isEmpty()) {
                order[0].addCardToWinningDeck(temp1.removeTopCard());
                order[0].addCardToWinningDeck(temp2.removeTopCard());
            }
            System.out.println(order[0].toString() + " won");
        } else {
            while (!temp1.isEmpty() && !temp2.isEmpty()) {
                order[1].addCardToWinningDeck(temp1.removeTopCard());
                order[1].addCardToWinningDeck(temp2.removeTopCard());
            }
            System.out.println(order[1].toString() + " won");
        }
    }

}
