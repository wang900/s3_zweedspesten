package communication.websockets;

import communication.messaging.EncapsulatedMessageGenerator;
import communication.messaging.interfaces.IEncapsulatedMessageGenerator;
import serialization.interfaces.ISerializer;
import serialization.SerializationProvider;

public abstract class WebSocketBase {

    public IEncapsulatedMessageGenerator getEncapsulatedMessageGenerator() {
        return encapsulatedMessageGenerator;
    }

    private IEncapsulatedMessageGenerator encapsulatedMessageGenerator = new EncapsulatedMessageGenerator();

    public WebSocketBase()
    {

    }
    public abstract void start();

    public abstract void stop();

    public ISerializer<String> getSerializer() {
        return SerializationProvider.getSerializer();
    }
}
