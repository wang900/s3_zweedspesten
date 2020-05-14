package communication.messaging;

import communication.messaging.interfaces.IEncapsulatedMessageGenerator;
import serialization.interfaces.ISerializer;
import serialization.SerializationProvider;

public class EncapsulatedMessageGenerator implements IEncapsulatedMessageGenerator {

    ISerializer<?> serializer = SerializationProvider.getSerializer();
    
    public EncapsulatedMessage generateMessage(Object content) {
        String messageForServerJson = (String) serializer.serialize(content);
        String type = content.getClass().toGenericString();
        return new EncapsulatedMessage(type, messageForServerJson);
    }

    public String generateMessageString(Object content) {
        EncapsulatedMessage msg = generateMessage(content);
        return (String) serializer.serialize(msg);
    }
}
