package functor;

import java.io.InputStream;
import java.security.MessageDigest;

/**
 * User: kicoolzhang
 * Date: 8/8/13
 * Time: 6:09 PM
 */
public class InputStreamToHash implements Functor {

    private InputStream is;
    private String algorithm;


    public InputStreamToHash(InputStream is, String algorithm) {
        this.is = is;
        this.algorithm = algorithm;
    }

    @Override
    public Object call() throws Exception {
        MessageDigest md = MessageDigest.getInstance(algorithm);

        byte[] dataBytes = new byte[Contants.BUF_SIZE];

        int nread = 0;
        while ((nread = is.read(dataBytes)) != -1) {
            md.update(dataBytes, 0, nread);
        }

        byte[] mdbytes = md.digest();

        // convert the byte to hex format
        StringBuffer sb = new StringBuffer("");
        for (int i = 0; i < mdbytes.length; i++) {
            sb.append(Integer.toString((mdbytes[i] & 0xff) + 0x100, 16)
                    .substring(1));
        }

        return sb.toString();
    }
}
