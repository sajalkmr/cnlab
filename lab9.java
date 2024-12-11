import java.math.BigInteger;

public class RSA {
    public static void main(String[] args) {
        BigInteger p = BigInteger.valueOf(61);
        BigInteger q = BigInteger.valueOf(53);

        BigInteger n = p.multiply(q);
        BigInteger phi = p.subtract(BigInteger.ONE).multiply(q.subtract(BigInteger.ONE));
        BigInteger e = BigInteger.valueOf(17);
        BigInteger d = e.modInverse(phi);

        System.out.println("Public Key: (" + e + ", " + n + ")");
        System.out.println("Private Key: (" + d + ", " + n + ")");

        String message = "HELLO";
        BigInteger[] ciphertext = new BigInteger[message.length()];
        for (int i = 0; i < message.length(); i++) {
            BigInteger m = BigInteger.valueOf(message.charAt(i));
            ciphertext[i] = m.modPow(e, n);
        }

        String decrypted = "";
        for (BigInteger c : ciphertext) {
            BigInteger m = c.modPow(d, n);
            decrypted += (char) m.intValue();
        }
        System.out.println("Decrypted: " + decrypted);
    }
}
