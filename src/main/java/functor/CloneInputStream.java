package functor;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;

/**
 * User: kicoolzhang
 * Date: 8/8/13
 * Time: 6:41 PM
 */
public class CloneInputStream implements Functor {

    private ByteArrayOutputStream baos = null;
    private Exception e;

    public CloneInputStream(InputStream is) {
        try {
            baos = new ByteArrayOutputStream();
            byte[] buffer = new byte[Contants.BUF_SIZE];
            int len;
            while ((len = is.read(buffer)) > -1) {
                baos.write(buffer, 0, len);
            }
            baos.flush();
        } catch (Exception e) {
            e.printStackTrace();
            this.e = e;
        }
    }


    @Override
    public Object call() throws Exception {
        if (baos == null) {
            throw e;
        }

        return new ByteArrayInputStream(baos.toByteArray());
    }
}
