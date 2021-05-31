import java.util.ArrayList;

public class Player {
    private String name;
    private Deck playingDeck;
    private Deck winningDeck;

    public Player(String name) {
        this.name = name;
        this.playingDeck = new Deck(false);
        this.winningDeck = new Deck(false);
    }

    public String getName() {
        return this.name;
    }

    public Deck getPlayingDeck() {
        return this.playingDeck;
    }

    public Deck getWinningDeck() {
        return this.winningDeck;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPlayingDeck(Deck deck) {
        this.playingDeck = deck;
    }

    public void setWinningDeck(Deck deck) {
        this.winningDeck = deck;
    }

    public void addCardToWinningDeck(Card card) {
        this.winningDeck.addCard(card);
    }

    public void addCardToPlayingDeck(Card card) {
        this.playingDeck.addCard(card);
    }

    public Card drawCard() {
        if (this.playingDeck.isEmpty() && !this.winningDeck.isEmpty()) {
            this.winningDeck.shuffle();
            this.playingDeck.deckOfCards.addAll(this.winningDeck.deckOfCards);
            this.winningDeck.deckOfCards.clear();
        }

        return this.playingDeck.removeTopCard();
    }

    public boolean outOfCards() {
        return this.winningDeck.isEmpty() && this.playingDeck.isEmpty();
    }

    @Override
    public String toString() {
        return this.name;
    }

}

