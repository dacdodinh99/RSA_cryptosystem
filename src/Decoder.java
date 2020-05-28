import java.math.BigInteger;
import java.util.List;

public class Decoder {

    public final int BLOCK_LENGTH = 32;
    private static long WILDCARD = 9999;

    private BigInteger public_key;
    private BigInteger mod;

    Decoder(BigInteger mod, BigInteger public_key) {
        this.mod = mod;
        this.public_key = public_key;
    }

    StringBuilder decode(List<BigInteger> codes) {
        StringBuilder res = new StringBuilder();

        for (BigInteger e : codes) {
            res.append(decode(e));
        }

        return res;
    }

    StringBuilder decode(BigInteger code) {
        StringBuilder res = new StringBuilder();

        BigInteger decoded = code.modPow(public_key, mod);

        BigInteger shift = new BigInteger("65536"); // 512bit = 16chars and 2^16 = 65536

        for (int i = 0; i < BLOCK_LENGTH; i++) {
            int t = (decoded.mod(shift)).intValue();
            decoded = decoded.divide(shift);

            if (t != WILDCARD) res.append((char) t);
        }

        res.reverse();

        return res;
    }

}
