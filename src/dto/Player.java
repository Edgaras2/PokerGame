package dto;

/**
 * Player who is has {@link Hand} and {@link Card}
 *
 * @author Edgaras
 */
public class Player {

    /**
     * Holds value of hand combination.
     *
     * @author Edgaras
     */
    private Hand hand;

    /**
     * Holds value of high card is used to determine who is a winner if {@link Hand} is equal.
     *
     * @author Edgaras
     */
    private Card highCard;

    public Player(Hand hand, Card highCard) {
        this.hand = hand;
        this.highCard = highCard;
    }

    public Hand getHand() {
        return hand;
    }

    public Card getHighCard() {
        return highCard;
    }
}
