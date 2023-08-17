package py.edu.fpuna.par.parusrmcs.util;


import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class PasswordUtils {

    public static String encryptPassword(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] messageDigest = md.digest(password.getBytes());
            BigInteger number = new BigInteger(1, messageDigest);
            String encryptedPassword = number.toString(16);

            while (encryptedPassword.length() < 32) {
                encryptedPassword = "0" + encryptedPassword;
            }

            return encryptedPassword;
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Error encrypting password", e);
        }
    }

    public static boolean verifyPassword(String password, String encryptedPassword) {
        String encryptedInput = encryptPassword(password);
        return encryptedInput.equals(encryptedPassword);
    }
}
