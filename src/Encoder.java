import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class Encoder {
    private static long N_ENCODER = 0;
    private static long WILDCARD = 9999;

    private long encoder_id; // use as seed for random object in generating keys.

    private BigInteger private_key;
    private BigInteger public_key;
    private BigInteger mod;

    Encoder() {
        encoder_id = ++N_ENCODER;

        KeyGenerator gen = new KeyGenerator(encoder_id);

        private_key = gen.getPrivate_key();
        public_key = gen.getPublic_key();
        mod = gen.getMod();
    }

    public List<BigInteger> encode(StringBuilder string) {
        List<BigInteger> res = new ArrayList<BigInteger>();

        while (string.length() % 32 != 0) string.append((char) WILDCARD);

        BigInteger shift = new BigInteger("65536"); // 512bit = 16char and 2^16 = 65536

        for (int i = 0; i < string.length() / 32; i++) {
            BigInteger block_val = BigInteger.ZERO;

            for (int j = 0; j < 32; j++) {
                Integer foo = (int) string.charAt(i * 32 + j);
                BigInteger bigInteger_foo = new BigInteger(foo.toString());

                block_val = block_val.multiply(shift).add(bigInteger_foo);
            }

            BigInteger encoded_block_val = block_val.modPow(private_key, mod);

            res.add(encoded_block_val);
        }

        return res;
    }

    public BigInteger getMod() {
        return mod;
    }

    public BigInteger getPublic_key() {
        return public_key;
    }

}
