import com.alibaba.fastjson.JSON;

public class JSONSerializer implements Serializer {
    public byte getSerializerAlgorithm() {
        return SerializerAlgorithm.JSON;
    }

    public byte[] serialize(Object obj) {
        return JSON.toJSONBytes(obj);
    }

    public <T> T deserialize(Class<T> clazz, byte[] bytes) {
        return JSON.parseObject(bytes, clazz);
    }
}
