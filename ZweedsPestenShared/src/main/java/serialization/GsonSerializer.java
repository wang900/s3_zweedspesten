package serialization;

import com.google.gson.Gson;
import serialization.interfaces.ISerializer;

import java.lang.reflect.Type;

public class GsonSerializer implements ISerializer<String> {

    private final Gson gson = new Gson();

    @Override
    public <T> T deserialize(String data, Class<T> clazz) {
        return gson.fromJson(data, clazz);
    }

    @Override
    public <T> T deserialize(String data, Type type) {
        return gson.fromJson(data, type);
    }

    @Override
    public String serialize(Object object) {
        return gson.toJson(object);
    }
}
