package model;

import model.enums.CardValue;
import model.enums.SuitType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class CardTest {
    private Card card;
    private CardValue value = CardValue.ACE;
    private SuitType type = SuitType.CLUBS;

    @BeforeEach
    void initialize()
    {
        card = new Card(value, type);
    }

   @Test
   void getConstructorData() {
       assertEquals(value, card.getCardValue());
       assertEquals(type, card.getSuitType());
   }

   @Test
    void setWatchable(){
        boolean expected = true;

        card.setWatchable(expected);

        assertTrue(card.isWatchable());
   }

   @Test
    void toStringTest()
   {
       String expected = "ace of clubs";

       assertEquals(expected, card.toString());
   }
}
