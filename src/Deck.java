import java.util.ArrayList;

public class Deck {
    public ArrayList<Card> deckOfCards = new ArrayList<>();

    public Deck(boolean isFullDeck) {
        Shape[] shapeArray = {Shape.SPADES, Shape.DIAMONDS, Shape.CLUBS, Shape.HEARTS};
        if (isFullDeck) {
            for (Shape shape : shapeArray) {
                for (int i = 1; i <= 13; i++) {
                    Card card = new Card(shape, i);
                    this.deckOfCards.add(card);
                }
            }
        }
    }

    /**
     * @param card top card in the deck.
     */
    public void addCard(Card card) {
        this.deckOfCards.add(card);
    }

    public Card removeTopCard() {
        Card card = this.deckOfCards.get(this.deckOfCards.size() - 1);
        this.deckOfCards.remove(this.deckOfCards.size() - 1);
        return card;
    }

    /**
     * @return boolean value, true if empty, false if not.
     */
    public boolean isEmpty() {
        return this.deckOfCards.isEmpty();
    }

    public void shuffle() {
        for (int i = 0; i < 50; i++) { // repeat 50 times, swap between 2 cards at random indexes.
            int firstIndex = Main.rnd.nextInt(this.deckOfCards.size()); // a randomized index
            int secondIndex = Main.rnd.nextInt(this.deckOfCards.size()); // a randomized index
            Card tempCard = this.deckOfCards.get(firstIndex);
            this.deckOfCards.set(firstIndex, this.deckOfCards.get(secondIndex));
            this.deckOfCards.set(secondIndex, tempCard);
        }
    }

    /**
     * @return the top card of the deck.
     */
    public Card getTopCard() {
        return this.deckOfCards.get(this.deckOfCards.size() - 1);
    }
}
