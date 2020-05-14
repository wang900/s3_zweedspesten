package communication.messaging;

public class EncapsulatedMessage {

    private String messageType;

    private String messageData;

    public EncapsulatedMessage(String type, String data)
    {
        this.messageType = type;
        this.messageData = data;
    }

    public String getMessageType()
    {
        return messageType;
    }

    public String getMessageData(){
        return messageData;
    }
}
