package serialization;

import serialization.interfaces.ISerializer;

public class SerializationProvider {

    private static ISerializer serializer;

    public static ISerializer getSerializer()
    {
        if(serializer == null) serializer = new GsonSerializer();
        return serializer;
    }
}
