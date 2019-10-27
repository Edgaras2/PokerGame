package dto;

/**
 * Card which holds suit and rank.
 *
 * @author Edgaras
 */
public class Card {

    /**
     * Holds value of poker card suit (e.g. 'spades', 'clubs').
     *
     * @author Edgaras
     */
    private String suit;

    /**
     * Holds value of poker card rank.
     *
     * @author Edgaras
     */
    private int rank;

    public Card(String suit, int rank) {
        this.suit = suit;
        this.rank = rank;
    }

    public Card() {
    }

    public String getSuit() {
        return suit;
    }

    public void setSuit(String suit) {
        this.suit = suit;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    @Override
    public String toString() {
        switch (rank) {
            case 1:
                return "Ace of " + suit;
            case 2:
                return "Two of " + suit;
            case 3:
                return "Three of " + suit;
            case 4:
                return "Four of " + suit;
            case 5:
                return "Five of " + suit;
            case 6:
                return "Six of " + suit;
            case 7:
                return "Seven of " + suit;
            case 8:
                return "Eight of " + suit;
            case 9:
                return "Nine of " + suit;
            case 10:
                return "Ten of " + suit;
            case 11:
                return "Jack of " + suit;
            case 12:
                return "Queen of " + suit;
            case 13:
                return "King of " + suit;
            default:
                return "Something went wrong";
        }
    }
}
