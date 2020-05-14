package model.interfaces;

import java.util.List;

public interface IBanishedCards {
    List<ICard> getCards();
    void addCards(List<ICard> cards);
}
