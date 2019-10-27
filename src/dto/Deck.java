package dto;

import java.util.List;

/**
 * Poker deck which holds ranks and suits
 *
 * @author Edgaras
 */
public class Deck {

    /**
     * Holds values of suits
     *
     * @author Edgaras
     */
    private List<String> suits = List.of("diamonds", "spades", "hearts", "clubs");

    /**
     * Holds values of ranks
     *
     * @author Edgaras
     */
    private List<Integer> ranks = List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13);

    public List<String> getSuits() {
        return suits;
    }

    public List<Integer> getRanks() {
        return ranks;
    }
}
