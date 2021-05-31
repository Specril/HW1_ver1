import java.util.ArrayList;
import java.util.Collection;
import java.util.Random;

public class Deck {
    public ArrayList<Card> deckOfCards = new ArrayList<Card>();

    public Deck(boolean isFullDeck){
        Shape[] shapeArray = {Shape.SPADES, Shape.DIAMONDS, Shape.CLUBS, Shape.HEARTS};
        if(isFullDeck){
            for(Shape shape: shapeArray){
                for (int i = 1; i <= 13; i++) {
                    Card card = new Card(shape,i);
                    this.deckOfCards.add(card);
                }
            }
        }
    }
    public void addCard(Card card){
        this.deckOfCards.add(card);
    }
    public Card removeTopCard(){
        Card card = this.deckOfCards.get(this.deckOfCards.size()-1);
        this.deckOfCards.remove(this.deckOfCards.size()-1);
        return card;
    }
    public boolean isEmpty(){
        return this.deckOfCards.isEmpty();
    }
    public void shuffle(){
        //Main.rnd= new Random();
        for (int i = 0; i < 50; i++) {
            int firstIndex = Main.rnd.nextInt(this.deckOfCards.size());
            int secondIndex = Main.rnd.nextInt(this.deckOfCards.size());
            Card tempCard = this.deckOfCards.get(firstIndex);
            this.deckOfCards.set(firstIndex,this.deckOfCards.get(secondIndex));
            this.deckOfCards.set(secondIndex,tempCard);
        }
    }
    public Card getTopCard(){
        return this.deckOfCards.get(this.deckOfCards.size()-1);
    }
    /*
     public Card removeBottomCard(){
        Card card = this.deckOfCards.get(0);
        this.deckOfCards.remove(0);
        return card;
        }
     */
}
