package dto;

/**
 * Hand with possible hand combinations
 *
 * @author Edgaras
 */
public enum Hand {

    HIGH_CARD(14, "High card"),
    PAIR(15, "One pair"),
    TWO_PAIR(16, "Two pair"),
    THREEOFTHEKIND(17, "Three of the kind"),
    STRAIGHT(18, "Straight"),
    FLUSH(19, "Flush"),
    FULL_HOUSE(20, "Full house"),
    FOUR_OF_A_KIND(21, "Four of the kind"),
    STRAIGHT_FLUSH(22, "Straight flush"),
    ROYAL_FLUSH(23, "Royal flush");

    /**
     * Holds hand combination rank
     *
     * @authoe Edgaras
     */
    private final int value;

    /**
     * Holds hand description
     *
     * @authoe Edgaras
     */
    private final String description;

    Hand(int value, String description) {
        this.value = value;
        this.description = description;
    }

    public int getValue() {
        return value;
    }

    public String getDescription() {
        return description;
    }
}
