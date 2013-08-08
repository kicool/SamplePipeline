import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.commons.pipeline.StageException;
import org.apache.commons.pipeline.stage.BaseStage;

import java.io.BufferedInputStream;
import java.io.InputStream;

/**
 * Created with IntelliJ IDEA.
 * User: kicoolzhang
 * Date: 8/8/13
 * Time: 2:11 PM
 * To change this template use File | Settings | File Templates.
 */
public class MarkInputStreamStage extends BaseStage {
    private final Log log = LogFactory.getLog(MarkInputStreamStage.class);

    @Override
    public void process(Object obj) throws StageException {
        if (obj instanceof InputStream) {
            InputStream is = (InputStream) obj;
            if (is.markSupported()) {
                is.mark(Integer.MAX_VALUE);

                log.info(obj.getClass().getSimpleName() + " supports mark().");
                emit(is);
            } else {
                BufferedInputStream bis = new BufferedInputStream(is);
                if (bis.markSupported()) {
                    bis.mark(Integer.MAX_VALUE);

                    log.info("Using BufferedInputStream wrapper to support mark().");
                    emit(bis);
                }   else  {
                    log.error("can not be used multi user.");
                }
            }

        }
    }
}
