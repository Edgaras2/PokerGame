package services;

import dto.Card;
import dto.Deck;
import dto.Hand;
import dto.Player;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

/**
 * Implementation of {@link PokerGameService}
 *
 * @author Edgaras
 */
public class PokerGameServiceImpl implements PokerGameService {

    private int cardsInDeck = 52;
    //TODO : RENAME j
    private int cardsAfterPlayerDraw = 47;

    @Override
    public List<Card> createDeck() {
        Deck deck = new Deck();
        List<Card> playingDeck = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 13; j++) {
                playingDeck.add(new Card(deck.getSuits().get(i), deck.getRanks().get(j)));
            }
        }
        return playingDeck;
    }

    @Override
    public List<Card> createPlayerHand(List<Card> deck) {
        List<Card> playersHand = new ArrayList<>();
        for (; cardsInDeck > cardsAfterPlayerDraw; cardsInDeck--) {
            int cardInd = (int) Math.floor(Math.random() * cardsInDeck);
            playersHand.add(deck.get(cardInd));
            deck.remove(cardInd);
        }
        cardsAfterPlayerDraw = 42;
        return playersHand;
    }

    @Override
    public Player evaluateHand(List<Card> hand) {
        hand = sortedHand(hand);
        Card highCard = highCard(hand);
        if (royalFlush(hand)) {
            return new Player(Hand.ROYAL_FLUSH, highCard);
        } else if (straightFlush(hand)) {
            return new Player(Hand.STRAIGHT_FLUSH, highCard);
        } else if (fourOfaKind(hand)) {
            return new Player(Hand.FOUR_OF_A_KIND, highCard);
        } else if (fullHouse(hand)) {
            return new Player(Hand.FULL_HOUSE, highCard);
        } else if (flush(hand)) {
            return new Player(Hand.FLUSH, highCard);
        } else if (straight(hand)) {
            return new Player(Hand.STRAIGHT, highCard);
        } else if (threeOfTheKind(hand)) {
            return new Player(Hand.THREEOFTHEKIND, highCard);
        } else if (twoPairs(hand)) {
            return new Player(Hand.TWO_PAIR, highCard);
        } else if (onePair(hand)) {
            return new Player(Hand.PAIR, highCard);
        } else {
            return new Player(Hand.HIGH_CARD, highCard);
        }
    }

    private Card highCard(List<Card> hand) {
        return hand.stream()
                .max(Comparator.comparing(Card::getRank))
                .orElseThrow(NoSuchElementException::new);
    }

    private boolean twoPairs(List<Card> hand) {
        return checkPairs(hand) == 2;
    }

    private boolean threeOfTheKind(List<Card> hand) {
        return hand.stream()
                .map(Card::getRank)
                .distinct()
                .count() == 3;
    }

    private boolean straight(List<Card> hand) {
        if (checkStraight(hand)) {
            return true;
        }
        for (int j = 1; j < 4; j++) {
            if (hand.get(j).getRank() != (hand.get(j + 1).getRank() - 1)) {
                return false;
            }
        }
        return true;
    }

    private boolean fullHouse(List<Card> hand) {
        return checkPairs(hand) == 3;
    }

    private boolean fourOfaKind(List<Card> hand) {
        return hand.get(0).getRank() == hand.get(1).getRank() || hand.get(2).getRank() == hand.get(3).getRank();
    }

    private boolean straightFlush(List<Card> hand) {
        if (!flush(hand)) {
            return false;
        }
        return checkStraight(hand);
    }

    private boolean checkStraight(List<Card> hand) {
        for (int i = 0; i < 4; i++) {
            if (hand.get(i).getRank() != (hand.get(i + 1).getRank()) - 1) {
                return false;
            }
        }
        return true;
    }

    private boolean royalFlush(List<Card> hand) {
        return flush(hand) && isRoyalFlush(hand);
    }

    private boolean isRoyalFlush(List<Card> hand) {
        return hand.get(0).getRank() == 1 && hand.get(1).getRank() == 10 &&
                hand.get(2).getRank() == 11 && hand.get(3).getRank() == 12 &&
                hand.get(4).getRank() == 13;
    }


    private boolean onePair(List<Card> hand) {
        return checkPairs(hand) == 1;
    }

    private int checkPairs(List<Card> hand) {
        int counter = 0;
        for (int i = 0; i < 4; i++) {
            if (hand.get(i).getRank() == hand.get(i + 1).getRank()) {
                counter++;
            }
        }
        return counter;
    }

    private boolean flush(List<Card> hand) {
        for (int i = 0; i < 4; i++) {
            if (!hand.get(i).getSuit().equals(hand.get(i + 1).getSuit())) {
                return false;
            }
        }
        return true;
    }

    private List<Card> sortedHand(List<Card> hand) {
        return hand.stream()
                .sorted(Comparator.comparing(Card::getRank))
                .collect(Collectors.toList());
    }
}
