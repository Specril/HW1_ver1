public class Card {
    private Shape shape;
    private int value;

    public Card(Shape shape, int value) {
        this.shape = shape;
        this.value = value;
    }

    public Shape getShape() {
        return shape;
    }

    public int getValue() {
        return value;
    }

    public void setShape(Shape shape) {
        this.shape = shape;
    }

    public void setValue(int value) {
        this.value = value;
    }

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
                valueToReturn = ((Integer)this.value).toString();
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
