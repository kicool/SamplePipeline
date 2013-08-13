import functor.CloneInputStream;
import org.apache.commons.logging.LogFactory;
import org.apache.commons.pipeline.StageException;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created with IntelliJ IDEA.
 * User: kicoolzhang
 * Date: 8/8/13
 * Time: 7:26 PM
 * To change this template use File | Settings | File Templates.
 */
public class UrlToByteArrayOutputStreamStage extends BranchBaseStage {
    static {
        log = LogFactory.getLog(UrlToByteArrayOutputStreamStage.class.getSimpleName());
    }

    @Override
    public void process(Object obj) throws StageException {
        URL url = null;
        if (obj instanceof URL) {
            url = (URL) obj;
        } else if (obj instanceof String) {
            String urlString = (String) obj;
            try {
                url = new URL(urlString);
            } catch (MalformedURLException e) {
                throw new StageException(this, "Error converting url String:" + urlString, e);
            }
        } else {
            throw new StageException(this, "invalid object:" + obj.getClass().getName());
        }

        try {
            InputStream inputStream = url.openStream();
            CloneInputStream cloneInputStream = new CloneInputStream(inputStream);
            inputStream.close();
            log.info("enqueing input stream");
            this.emit(cloneInputStream);
        } catch (IOException e) {
            throw new StageException(this, "Error with stream from url:" + url, e);
        }
    }
}
