package Serializer;

public interface Serializer {
//    Serializer.Serializer JSON = new Serializer.JSONSerializer();
    Serializer DEFAULT = new JSONSerializer();

    byte getSerializerAlgorithm();

    byte[] serialize(Object obj);

    <T> T deserialize(Class<T> clazz, byte[] bytes);
}
