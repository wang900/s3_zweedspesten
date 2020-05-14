package communication.messaging;

import communication.messaging.interfaces.IMessageHandler;
import serialization.SerializationProvider;
import serialization.interfaces.ISerializer;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

public abstract class MessageHandlerBase<T> implements IMessageHandler {

    public void handleMessage(String data, String sessionId) {
        ISerializer<String> ser = SerializationProvider.getSerializer();
        Type type = ((ParameterizedType)getClass().getGenericSuperclass()).getActualTypeArguments()[0];
        T msg =  ser.deserialize(data, type);
        handleMessageInternal(sessionId, msg);
    }

    public abstract void handleMessageInternal(String sessionId, T message);
}
