package model;

import model.enums.CardValue;
import model.enums.SuitType;
import model.interfaces.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class HandTest {

    private IHand hand;
    private IBanishedCards banishedCards;
    private IPlayedCards playedCards;
    private IPlayer player;
    private IDeck deck;

    @BeforeEach
    void initialize(){
        banishedCards = new BanishedCards();
        playedCards = new PlayedCards(banishedCards);
        player = new Player("123", "test");
        hand = new Hand(player, playedCards);
        deck = new Deck();
        deck.createDeck();
    }

    @Test
    void getConstructorData() {
        List<ICard> cards = new ArrayList<>();

        cards.add(deck.getCards().get(0));
        cards.add(deck.getCards().get(1));
        cards.add(deck.getCards().get(2));

        deck.drawCards(hand, 3);

        assertEquals(player, hand.getPlayer());
        assertEquals(playedCards, hand.getPlayedCards());
        assertEquals(cards, hand.getCards());
    }

    @Test
    void placeOneCard() {
        deck.drawCards(hand, 3);
        ICard card = hand.getCards().get(0);
        assertTrue(hand.placeCard(card));
        assertEquals(card, playedCards.getCards().get(0));
    }

    @Test
    void placeMultipleDifferentCards() {
        List<ICard> cards = new ArrayList<>();
        ICard card1 = new Card(CardValue.ACE, SuitType.CLUBS);
        ICard card2 = new Card(CardValue.EIGHT, SuitType.CLUBS);
        cards.add(card1);
        cards.add(card2);
        hand.addCards(cards);
        assertFalse(hand.placeCards(cards));
        assertTrue(playedCards.getCards().isEmpty());
    }

    @Test
    void placeMultipleCorrectCards() {
        List<ICard> cards = new ArrayList<>();
        ICard card1 = new Card(CardValue.ACE, SuitType.CLUBS);
        ICard card2 = new Card(CardValue.ACE, SuitType.HEARTS);
        cards.add(card1);
        cards.add(card2);
        hand.addCards(cards);
        assertTrue(hand.placeCards(cards));
        assertEquals(cards, playedCards.getCards());
        assertFalse(playedCards.getCards().isEmpty());
    }

    @Test
    void placeMultipleHigherCardsOnOtherPlayedCard() {
        List<ICard> cards = new ArrayList<>();
        ICard card1 = new Card(CardValue.ACE, SuitType.CLUBS);
        ICard card2 = new Card(CardValue.ACE, SuitType.HEARTS);
        cards.add(card1);
        cards.add(card2);
        List<ICard> playingCards = new ArrayList<>();
        playingCards.add(new Card(CardValue.EIGHT, SuitType.HEARTS));
        playedCards.addCards(playingCards);
        hand.addCards(cards);
        assertTrue(hand.placeCards(cards));
        assertEquals(3, playedCards.getCards().size());
    }

    @Test
    void placeMultipleEqualCardsOnOtherPlayedCard() {
        List<ICard> cards = new ArrayList<>();
        ICard card1 = new Card(CardValue.EIGHT, SuitType.CLUBS);
        ICard card2 = new Card(CardValue.EIGHT, SuitType.DIAMONDS);
        cards.add(card1);
        cards.add(card2);
        List<ICard> playingCards = new ArrayList<>();
        playingCards.add(new Card(CardValue.EIGHT, SuitType.HEARTS));
        playedCards.addCards(playingCards);
        hand.addCards(cards);
        assertTrue(hand.placeCards(cards));
        assertEquals(3, playedCards.getCards().size());
    }

    @Test
    void placeMultipleLowerValueCardsOnOtherPlayedCard() {
        List<ICard> cards = new ArrayList<>();
        ICard card1 = new Card(CardValue.SEVEN, SuitType.CLUBS);
        ICard card2 = new Card(CardValue.SEVEN, SuitType.HEARTS);
        cards.add(card1);
        cards.add(card2);
        List<ICard> playingCards = new ArrayList<>();
        playingCards.add(new Card(CardValue.EIGHT, SuitType.HEARTS));
        playedCards.addCards(playingCards);
        hand.addCards(cards);
        assertFalse(hand.placeCards(cards));
        assertEquals(1, playedCards.getCards().size());
    }

    @Test
    void placeOneHigherValueCardOnSeven() {

        playedCards.addCard(new Card(CardValue.SEVEN, SuitType.HEARTS));

        ICard card = new Card(CardValue.NINE, SuitType.CLUBS);
        hand.addCard(card);

        assertFalse(hand.placeCard(card));
        assertEquals(1, playedCards.getCards().size());
    }

    @Test
    void placeOneEqualValueCardOnSeven() {

        playedCards.addCard(new Card(CardValue.SEVEN, SuitType.HEARTS));

        ICard card = new Card(CardValue.SEVEN, SuitType.CLUBS);
        hand.addCard(card);

        assertTrue(hand.placeCard(card));
        assertEquals(2, playedCards.getCards().size());
    }

    @Test
    void placeOneLowerValueCardOnSeven() {

        playedCards.addCard(new Card(CardValue.SEVEN, SuitType.HEARTS));

        ICard card = new Card(CardValue.SIX, SuitType.CLUBS);
        hand.addCard(card);

        assertTrue(hand.placeCard(card));
        assertEquals(2, playedCards.getCards().size());
    }

    @Test
    void placeOneHigherValueCardOnThreeCopySeven() {

        List<ICard> playingCards = new ArrayList<>();
        playingCards.add(new Card(CardValue.SEVEN, SuitType.HEARTS));
        playingCards.add(new Card(CardValue.THREE, SuitType.HEARTS));
        playedCards.addCards(playingCards);

        ICard card = new Card(CardValue.NINE, SuitType.CLUBS);
        hand.addCard(card);

        assertFalse(hand.placeCard(card));
        assertEquals(2, playedCards.getCards().size());
    }

    @Test
    void placeOneEqualValueCardOnThreeCopySeven() {

        List<ICard> playingCards = new ArrayList<>();
        playingCards.add(new Card(CardValue.SEVEN, SuitType.HEARTS));
        playingCards.add(new Card(CardValue.THREE, SuitType.HEARTS));
        playedCards.addCards(playingCards);

        ICard card = new Card(CardValue.SEVEN, SuitType.CLUBS);
        hand.addCard(card);

        assertTrue(hand.placeCard(card));
        assertEquals(3, playedCards.getCards().size());
    }

    @Test
    void placeOneLowerValueCardOnThreeCopySeven() {

        List<ICard> playingCards = new ArrayList<>();
        playingCards.add(new Card(CardValue.SEVEN, SuitType.HEARTS));
        playingCards.add(new Card(CardValue.THREE, SuitType.HEARTS));
        playedCards.addCards(playingCards);

        ICard card = new Card(CardValue.SIX, SuitType.CLUBS);
        hand.addCard(card);

        assertTrue(hand.placeCard(card));
        assertEquals(3, playedCards.getCards().size());
    }

    @Test
    void placeOneHigherValueCardOnThreeCopyNone() {

        List<ICard> playingCards = new ArrayList<>();
        playingCards.add(new Card(CardValue.THREE, SuitType.HEARTS));
        playedCards.addCards(playingCards);

        ICard card = new Card(CardValue.SIX, SuitType.CLUBS);
        hand.addCard(card);

        assertTrue(hand.placeCard(card));
        assertEquals(2, playedCards.getCards().size());
    }

    @Test
    void placeOneHigherValueCardOnThreeCopyThree() {

        List<ICard> playingCards = new ArrayList<>();
        playingCards.add(new Card(CardValue.THREE, SuitType.HEARTS));
        playingCards.add(new Card(CardValue.THREE, SuitType.CLUBS));
        playedCards.addCards(playingCards);

        ICard card = new Card(CardValue.SIX, SuitType.CLUBS);
        hand.addCard(card);

        assertTrue(hand.placeCard(card));
        assertEquals(3, playedCards.getCards().size());
    }

    @Test
    void placeTenOnSeven() {
        playedCards.addCard(new Card(CardValue.SEVEN, SuitType.HEARTS));

        ICard card = new Card(CardValue.TEN, SuitType.HEARTS);
        hand.addCard(card);

        assertTrue(hand.placeCard(card));
        assertEquals(0, playedCards.getCards().size());
        assertEquals(2, banishedCards.getCards().size());
    }

    @Test
    void placeMultipleTensOnAce() {
        playedCards.addCard(new Card(CardValue.ACE, SuitType.HEARTS));

        ICard card1 = new Card(CardValue.TEN, SuitType.HEARTS);
        ICard card2 = new Card(CardValue.TEN, SuitType.DIAMONDS);
        List<ICard> cards = new ArrayList<>();
        cards.add(card1);
        cards.add(card2);
        hand.addCards(cards);

        assertTrue(hand.placeCards(cards));
        assertEquals(0, playedCards.getCards().size());
        assertEquals(3, banishedCards.getCards().size());
    }

    @Test
    void placeMultipleTensWithTwoOnAce() {
        playedCards.addCard(new Card(CardValue.ACE, SuitType.HEARTS));

        ICard card1 = new Card(CardValue.TEN, SuitType.HEARTS);
        ICard card2 = new Card(CardValue.TEN, SuitType.DIAMONDS);
        ICard card3 = new Card(CardValue.TWO, SuitType.DIAMONDS);
        List<ICard> cards = new ArrayList<>();
        cards.add(card1);
        cards.add(card2);
        cards.add(card3);
        hand.addCards(cards);

        assertFalse(hand.placeCards(cards));
        assertEquals(1, playedCards.getCards().size());
        assertEquals(0, banishedCards.getCards().size());
    }
}
