import java.math.BigInteger;
import java.util.List;

public class Test {

    public static void main(String args[]) {
        Encoder a = new Encoder();
        BigInteger mod = a.getMod();
        BigInteger public_key = a.getPublic_key();

        Decoder b = new Decoder(mod, public_key);

        StringBuilder to_decode = new StringBuilder("bjklvasbjlvdsjklvdsbjlkvbsdfljkvbsajkvbasdkjlvbljkdsav");

        List<BigInteger> encoded = a.encode(to_decode);

        StringBuilder res = b.decode(encoded);

        System.out.println(res);
//        System.out.println((char) 97);

    }

}
