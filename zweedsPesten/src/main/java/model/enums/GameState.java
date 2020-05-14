package model.enums;

import com.google.gson.annotations.SerializedName;

public enum GameState {
    WAITING_FOR_PLAYERS,
    GAME_SESSION_ACTIVE,
    GAME_SESSION_RESULT,
    FINISHED
}
