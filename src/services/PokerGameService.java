package services;

import dto.Card;
import dto.Player;

import java.util.List;

/**
 * Allows to create deck, create player hand and evaluate players hand.
 *
 * @author Edgaras
 */
public interface PokerGameService {

    /**
     * Creates poker deck.
     *
     * @return list of {@link Card} objects which makes a poker deck
     *
     * @author Edgaras
     */
    List<Card> createDeck();

    /**
     * Evaluates players hand combination
     *
     * @param cards is use to determine players hand combination.
     * @return Player with hand combination and highest high card
     *
     * @author Edgaras
     */
    Player evaluateHand(List<Card> cards);

    /**
     * Creates poker hand which holds 5 different cards.
     *
     * @param deck is use to determine which cards player gets.
     * @return list of {@link Card} which holds 5 different {@link Card} objects
     *
     * @author Edgaras
     */
    List<Card> createPlayerHand(List<Card> deck);
}
