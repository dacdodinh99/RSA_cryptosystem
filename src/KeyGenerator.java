import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class KeyGenerator {
    private static final int BITLENGTH = 513;

    private BigInteger mod;
    private BigInteger private_key;
    private BigInteger public_key;
    private BigInteger p;
    private BigInteger q;

    KeyGenerator(long seed) {
        Random random = new Random(seed);
        p = generateBigPrime(random, BITLENGTH);
        q = generateBigPrime(random, BITLENGTH + 1);
        mod = p.multiply(q);

        BigInteger to_find_keys = p.subtract(BigInteger.ONE).multiply(q.subtract(BigInteger.ONE));  // (p - 1) * (q - 1)

        public_key = generateBigPrime(random, 2 * BITLENGTH);
        public_key = public_key.mod(to_find_keys);

        try {
            private_key = public_key.modInverse(to_find_keys);
        } catch (Exception e) {
            System.out.println("Something went wrong with modInverse function");
        }
    }

    private BigInteger generateBigPrime(Random random, int bitlength) {
        return BigInteger.probablePrime(bitlength, random);
    }

    public BigInteger getMod() {
        return mod;
    }

    public BigInteger getPublic_key() {
        return public_key;
    }

    public BigInteger getPrivate_key() {
        return private_key;
    }
}
