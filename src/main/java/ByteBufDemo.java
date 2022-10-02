import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;

public class ByteBufDemo {

    public static void main(String[] args) {
        ByteBuf buf = ByteBufAllocator.DEFAULT.buffer(9, 100); // capacity  和  maxCapacity
        buf.writeBytes(new byte[] {1,2,3,4,5});
        buf.writeInt(100);
        System.out.println(buf); // PooledUnsafeDirectByteBuf(ridx: 0, widx: 9, cap: 9/100)
        buf.writeInt(101);
        System.out.println(buf); // PooledUnsafeDirectByteBuf(ridx: 0, widx: 13, cap: 16/100)  发生了扩容

    }

}


