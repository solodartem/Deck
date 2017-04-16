package sample.animation;

import java.util.*;

/**
 * Created by asolod on 16.04.17.
 */
public class DeckModel {

    private String[] suits = new String[]{"clubs", "diamonds", "hearts", "spades"};
    private String[] values = new String[]{"2", "3", "4", "5", "6", "7", "8", "9", "10", "jack", "queen", "king", "ace"};

    private LinkedList<String> cards;
    private Random random = new Random();

    public DeckModel() {
        Set uniqueCards = new HashSet();
        for (String value : values) {
            for (String suit : suits) {
                uniqueCards.add(value + "_of_" + suit);
            }
        }

        uniqueCards.add("red_joker");
        uniqueCards.add("black_joker");
        if (uniqueCards.size() != 54) {
            throw new RuntimeException("Wrong size of deck:" + this.cards.size());
        }

        this.cards = new LinkedList<>(uniqueCards);
    }

    public String popRandomCard() {
        Collections.shuffle(this.cards);
        return this.cards.pop();
    }

    public static void main(String[] args) {
        DeckModel deckModel = new DeckModel();
        for (int i = 0; i < 54; i++) {
            System.out.println(deckModel.popRandomCard());
        }
    }
}
