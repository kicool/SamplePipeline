import functor.CloneInputStream;
import org.apache.commons.logging.LogFactory;
import org.apache.commons.pipeline.StageException;

import java.io.InputStream;

/**
 * Created with IntelliJ IDEA.
 * User: kicoolzhang
 * Date: 8/7/13
 * Time: 5:59 PM
 * To change this template use File | Settings | File Templates.
 */
public class InputStreamHashStage extends BranchBaseStage {
    static {
        log = LogFactory.getLog(InputStreamHashStage.class.getSimpleName());
    }

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

        InputStream is = null;
        try {
            if (obj instanceof InputStream) {

                is = (InputStream) obj;
                is.reset();


            } else if (obj instanceof CloneInputStream) {

                is = (InputStream) ((CloneInputStream) obj).call();

            } else {
                throw new StageException(this);
            }

            if (is != null) {
                String digest = (String) (new functor.InputStreamToHash(is, algorithm).call());

                log.info("Digest " + algorithm + " (in hex format):: " + digest);

                emitToBranches(digest);
            }
        } catch (Exception e) {
            throw new StageException(this, e);
        }
    }
}
