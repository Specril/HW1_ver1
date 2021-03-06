public class Card {
    private final Shape shape;
    private final int value;

    public Card(Shape shape, int value) {
        this.shape = shape;
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    /**
     * @param other card of opposing player.
     * @return -1 if current card value is lower than other's , 0 if values are equal and 1 if the current value is higher than other's.
     */
    public int compare(Card other) {
        if (this.value > other.getValue()) {
            return 1;
        }
        if (this.value == other.getValue()) {
            return 0;
        }
        return -1;
    }

    @Override
    public String toString() {
        String valueToReturn;
        switch (this.value) {
            case 1:
                valueToReturn = "Ace";
                break;
            case 11:
                valueToReturn = "Jack";
                break;
            case 12:
                valueToReturn = "Queen";
                break;
            case 13:
                valueToReturn = "King";
                break;
            default:
                valueToReturn = ((Integer) this.value).toString();
        }
        switch (this.shape) {
            case SPADES:
                return valueToReturn + " of ♠";
            case DIAMONDS:
                return valueToReturn + " of ♦";
            case CLUBS:
                return valueToReturn + " of ♣";
            case HEARTS:
                return valueToReturn + " of ♥";
            default:
                return "Card does not exist...";
        }
    }
}
