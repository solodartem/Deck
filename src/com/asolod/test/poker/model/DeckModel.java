package com.asolod.test.poker.model;

import java.util.*;

/**
 * Created by asolod on 16.04.17.
 */
public class DeckModel {

    public static final int POKER_DECK_SIZE = 54;
    private String[] suits = new String[]{"clubs", "diamonds", "hearts", "spades"};
    private String[] values = new String[]{"2", "3", "4", "5", "6", "7", "8", "9", "10", "jack", "queen", "king", "ace"};

    private LinkedList<String> cards;

    public DeckModel() {
        Set uniqueCards = new HashSet();
        for (String value : values) {
            for (String suit : suits) {
                uniqueCards.add(value + "_of_" + suit);
            }
        }

        uniqueCards.add("red_joker");
        uniqueCards.add("black_joker");

        if (uniqueCards.size() != POKER_DECK_SIZE) {
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
        for (int i = 0; i < POKER_DECK_SIZE; i++) {
            System.out.println(deckModel.popRandomCard());
        }
    }
}
