package model.enums;

public enum GameAction {
    DRAW_CARDS_FROM_DECK,
    DRAW_CARDS_FROM_PLAYED_CARDS,
    PLACE_CARD_FROM_HAND_SUCCESS,
    PLACE_CARD_FROM_HAND_FAILURE,
    PLACE_CARD_FROM_EXTRA_HAND_SUCCESS,
    PLACE_CARD_FROM_EXTRA_HAND_FAILURE,
    SWITCH_TURN,
    CREATE_EXTRA_HAND,
    CREATE_DECK, INVALID_TURN
}
