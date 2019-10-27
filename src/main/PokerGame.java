package main;

import dto.Card;
import dto.Player;
import services.PokerGameServiceImpl;

import java.io.Console;
import java.util.List;

/**
 * Five cards hand poker game application,
 * allows to play poker for two players decides which player wins by their hand combination.
 *
 * @author Edgaras
 */
public class PokerGame {

    public static void main(String[] args) {

        Console console = System.console();
        String readLine = console.readLine("Play poker game Y/N: ");

        while (readLine.equalsIgnoreCase("y")) {
            PokerGameServiceImpl pokerGameService = new PokerGameServiceImpl();
            List<Card> deck = pokerGameService.createDeck();

            List<Card> playerOneHand = pokerGameService.createPlayerHand(deck);
            List<Card> playerTwoHand = pokerGameService.createPlayerHand(deck);
            Player playerOneEvaluatedHand = pokerGameService.evaluateHand(playerOneHand);
            Player playerTwoEvaluatedHand = pokerGameService.evaluateHand(playerTwoHand);

            checkWinner(playerOneEvaluatedHand, playerTwoEvaluatedHand);
            readLine = console.readLine("\nPlay again Y/N: ");
        }
    }

    /**
     * Checks which player won the game, by comparing their hand combination,
     * if their hand combinations are equal then checks by their high card,
     * else we have a draw and players split the pot.
     *
     * @param playerOneEvaluatedHand player one hand combination
     * @param playerTwoEvaluatedHand player two hand combination
     *
     * @author Edgaras
     */
    private static void checkWinner(Player playerOneEvaluatedHand, Player playerTwoEvaluatedHand) {
        if (isHandCombinationEqual(playerOneEvaluatedHand, playerTwoEvaluatedHand)) {
            if (playerOneEvaluatedHand.getHighCard().getRank() > playerTwoEvaluatedHand.getHighCard().getRank()) {
                whichPlayerWinsWithHigherHighCard(playerOneEvaluatedHand, playerTwoEvaluatedHand, "Player 1 wins with ", " because Player 2 only had ");
            } else if (playerOneEvaluatedHand.getHighCard().getRank() < playerTwoEvaluatedHand.getHighCard().getRank()) {
                whichPlayerWinsWithHigherHighCard(playerTwoEvaluatedHand, playerOneEvaluatedHand, "Player 2 wins with ", " because Player 1 only had ");
            } else {
                System.out.println("Split the pot, it's a tie");
            }
        } else if (playerOneHandIsStronger(playerOneEvaluatedHand, playerTwoEvaluatedHand)) {
            whichPlayerWins(playerOneEvaluatedHand, playerTwoEvaluatedHand, "Player 1 wins with ", " because Player 2 only had ");
        } else {
            whichPlayerWins(playerTwoEvaluatedHand, playerOneEvaluatedHand, "Player 2 wins with ", " because Player 1 only had ");
        }
    }

    /**
     * Prints which player is a winner and has a higher high card.
     *
     * @param playersDescriptionOne winning players hand description
     * @param playersDescriptionTwo losing players hand description
     * @param winningPlayer String which player wins
     * @param losingPlayer String which player loses
     *
     * @author Edgaras
     */
    private static void whichPlayerWinsWithHigherHighCard(Player playersDescriptionOne, Player playersDescriptionTwo, String winningPlayer, String losingPlayer) {
        System.out.println(winningPlayer + playersDescriptionOne.getHand().getDescription() + " and higher high card " + playersDescriptionOne.getHighCard() + losingPlayer + playersDescriptionTwo.getHand().getDescription() + " and high card " + playersDescriptionTwo.getHighCard());
    }

    /**
     * Prints which player is a winner.
     *
     * @param playersDescriptionOne winning players hand description
     * @param playersDescriptionTwo losing players hand description
     * @param winningPlayer String which player wins
     * @param losingPlayer String which player loses
     *
     * @author Edgaras
     */
    private static void whichPlayerWins(Player playersDescriptionOne, Player playersDescriptionTwo, String winningPlayer, String losingPlayer) {
        System.out.println(winningPlayer + playersDescriptionOne.getHand().getDescription() + losingPlayer + playersDescriptionTwo.getHand().getDescription());
    }

    /**
     * Determines if player one has stronger hand then player two.
     *
     * @param playerOneEvaluatedHand player one hand, which is used to get hand value
     * @param playerTwoEvaluatedHand player two hand, which is used to get hand value
     * @return true if player one hand combination is stronger, otherwise false
     *
     * @author Edgaras
     */
    private static boolean playerOneHandIsStronger(Player playerOneEvaluatedHand, Player playerTwoEvaluatedHand) {
        return playerOneEvaluatedHand.getHand().getValue() > playerTwoEvaluatedHand.getHand().getValue();
    }

    /**
     * Checks if hand combinations are equal.
     *
     * @param playerOneEvaluatedHand player one hand, which is used to get hand value
     * @param playerTwoEvaluatedHand player two hand, which is used to get hand value
     * @return true if hand combinations are equal between player one and two, otherwise false
     *
     * @author Edgaras
     */
    private static boolean isHandCombinationEqual(Player playerOneEvaluatedHand, Player playerTwoEvaluatedHand) {
        return playerOneEvaluatedHand.getHand().getValue() == playerTwoEvaluatedHand.getHand().getValue();
    }
}
