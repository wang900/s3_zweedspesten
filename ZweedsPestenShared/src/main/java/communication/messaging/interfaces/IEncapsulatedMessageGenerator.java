package communication.messaging.interfaces;

import communication.messaging.EncapsulatedMessage;

public interface IEncapsulatedMessageGenerator {
    EncapsulatedMessage generateMessage(Object content);

    String generateMessageString(Object content);

}
