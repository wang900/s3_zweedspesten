package model.algorithm;

import model.Card;
import model.enums.CardValue;
import model.enums.SuitType;
import model.interfaces.ICard;

import java.util.ArrayList;
import java.util.List;

public class DeckGenerator {

    public static List<ICard> generateDeck() {
        List<ICard> cards = new ArrayList<>();
        int x = 0;
        for(int i = 0; i < 52; i++)
        {
            if(x == 13)
            {
                x = 0;
            }
            if(i <= 12)
            {
                cards.add(new Card(CardValue.values()[x], SuitType.CLUBS));
            }
            else if(i <= 25)
            {
                cards.add(new Card(CardValue.values()[x], SuitType.DIAMONDS));
            }
            else if(i <= 38)
            {
                cards.add(new Card(CardValue.values()[x], SuitType.HEARTS));
            }
            else
            {
                cards.add(new Card(CardValue.values()[x], SuitType.SPADES));
            }
            cards.get(i).setWatchable(false);
            x++;
        }
        return cards;
    }
}
