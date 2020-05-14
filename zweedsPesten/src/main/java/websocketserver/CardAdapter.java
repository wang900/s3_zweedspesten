package websocketserver;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import model.Card;
import model.enums.CardValue;
import model.enums.SuitType;
import model.interfaces.ICard;

import java.io.IOException;

public class CardAdapter extends TypeAdapter<ICard> {
    @Override
    public void write(JsonWriter out, ICard value) throws IOException {
        out.beginObject();
        out.name("CardValue").value(value.getCardValue().getValue());
        out.name("SuitType").value(value.getSuitType().name().toUpperCase());
        out.endObject();
    }

    @Override
    public ICard read(JsonReader in) throws IOException {
        ICard card = new Card();
        in.beginObject();
        while (in.hasNext()) {
            String name = in.nextName();
            if (name.equals("CardValue")) {
                card.setCardValue(CardValue.values()[in.nextInt()]);
            } else if (name.equals("SuitType")) {
                SuitType type;

                switch (in.nextString()) {
                    case "CLUBS":
                        type = SuitType.CLUBS;
                        break;
                    case "DIAMONDS":
                        type = SuitType.DIAMONDS;
                        break;
                    case "HEARTS":
                        type = SuitType.HEARTS;
                        break;
                    case "SPADES":
                        type = SuitType.SPADES;
                        break;
                    default:
                        type = null;
                        break;
                }
                card.setSuitType(type);
            } else {
                in.skipValue();
            }
        }
        in.endObject();
        return card;
    }
}
