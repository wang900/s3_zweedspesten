package model.algorithm;

import model.interfaces.ICard;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class DeckShuffler {

    public static List<ICard> shuffle(List<ICard> cards) {
        List<ICard> shuffledCards = new ArrayList<>(cards);
        Random rand = new Random();
        int n = shuffledCards.size();

        for (int i = 0; i < n; i++) {
            // Random for remaining positions.
            int r = i + rand.nextInt(n - i);

            //swapping the elements
            ICard temp = shuffledCards.get(r);
            shuffledCards.set(r, shuffledCards.get(i));
            shuffledCards.set(i, temp);
        }
        return shuffledCards;
    }
}
