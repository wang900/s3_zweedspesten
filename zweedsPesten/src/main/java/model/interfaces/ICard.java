package model.interfaces;

import com.google.gson.annotations.JsonAdapter;
import model.enums.CardValue;
import model.enums.SuitType;
import websocketserver.CardAdapter;

@JsonAdapter(CardAdapter.class)
public interface ICard {
    CardValue getCardValue();
    SuitType getSuitType();
    boolean isWatchable();
    void setWatchable(boolean watchable);
    void setCardValue(CardValue value);
    void setSuitType(SuitType type);
}
