import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.commons.pipeline.StageException;
import org.apache.commons.pipeline.stage.BaseStage;

import java.io.InputStream;
import java.security.MessageDigest;

/**
 * Created with IntelliJ IDEA.
 * User: kicoolzhang
 * Date: 8/7/13
 * Time: 5:59 PM
 * To change this template use File | Settings | File Templates.
 */
public class InputStreamHashStage extends BranchBaseStage {
    static {
        log = LogFactory.getLog(InputStreamHashStage.class);
    }

    private static final int BUF_SIZE = 1024;

    private final String algorithm;

    /**
     * Creates a new instance of InputStreamHashStage
     */
    public InputStreamHashStage(String algorithm) {
        this.algorithm = algorithm;
        addBranch(algorithm);
    }

    public void process(Object obj) throws org.apache.commons.pipeline.StageException {
        super.process(obj);

        if (obj instanceof InputStream) {
            InputStream is = (InputStream) obj;

            try {
                is.reset();

                MessageDigest md = MessageDigest.getInstance(algorithm);

                byte[] dataBytes = new byte[BUF_SIZE];

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

                String digest = sb.toString();
                log.info("Digest " + algorithm + " (in hex format):: " + digest);
                emitToBranches(digest);
            } catch (Exception e) {
                throw new StageException(this, e);
            }
        }
    }
}
