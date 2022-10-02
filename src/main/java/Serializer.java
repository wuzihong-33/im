public interface Serializer {
//    Serializer JSON = new JSONSerializer();
    Serializer DEFAULT = new JSONSerializer();

    byte getSerializerAlgorithm();

    byte[] serialize(Object obj);

    <T> T deserialize(Class<T> clazz, byte[] bytes);
}
