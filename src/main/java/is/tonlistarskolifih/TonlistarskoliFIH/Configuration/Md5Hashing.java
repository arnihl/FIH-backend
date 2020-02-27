package is.tonlistarskolifih.TonlistarskoliFIH.Configuration;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Md5Hashing {

    public static String md5(String password){
        if(password == null){
            return null;
        }
        String md5 = null;
        String salt = "!2#4Salting#445%&&3#4$$4#4NdP33PP3R1N6";
        password += salt;


        try {
            // create message digest object for Md5
            MessageDigest md = MessageDigest.getInstance("MD5");

            // Update password string in message digest
            md.update(password.getBytes(), 0, password.length());

            // Convert message digest value in base 16(hex)
            md5 = new BigInteger(1, md.digest()).toString(16);

        } catch (NoSuchAlgorithmException e){

        }
        return md5;
    }
}
