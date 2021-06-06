
public class Player {
    private String name;
    private final Deck playingDeck;
    private final Deck winningDeck;

    public Player(String name) {
        this.name = name;
        this.playingDeck = new Deck(false);
        this.winningDeck = new Deck(false);
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void addCardToWinningDeck(Card card) {
        this.winningDeck.addCard(card);
    }

    public void addCardToPlayingDeck(Card card) {
        this.playingDeck.addCard(card);
    }

    /**
     * @return the top card of the plating deck.
     */
    public Card drawCard() {
        if (this.playingDeck.isEmpty() && !this.winningDeck.isEmpty()) { // if the playing deck is empty and the winning deck isn't, then transfer all winning deck cards into plating deck.
            this.winningDeck.shuffle();
            this.playingDeck.deckOfCards.addAll(this.winningDeck.deckOfCards);
            this.winningDeck.deckOfCards.clear();
        }

        return this.playingDeck.removeTopCard();
    }

    /**
     * @return a boolean value, true if player is out of cards in both decks, false if not.
     */
    public boolean outOfCards() {
        return this.winningDeck.isEmpty() && this.playingDeck.isEmpty();
    }

    @Override
    public String toString() {
        return this.name;
    }

}

