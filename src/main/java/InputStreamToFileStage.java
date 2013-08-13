import functor.CloneInputStream;
import functor.Contants;
import org.apache.commons.logging.LogFactory;
import org.apache.commons.pipeline.StageException;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;

/**
 * Created with IntelliJ IDEA.
 * User: kicoolzhang
 * Date: 8/8/13
 * Time: 9:53 AM
 * To change this template use File | Settings | File Templates.
 */
public class InputStreamToFileStage extends BranchBaseStage {
    static {
        log = LogFactory.getLog(BranchStage.class.getSimpleName());
    }

    private String workdir;

    /**
     * Creates a new instance of InputStreamHashStage
     */
    public InputStreamToFileStage(String workdir) {
        this.workdir = workdir;
        addBranch("File");
    }

    private File genFile() {
        // TODO:
        File output = new File(workdir + File.separator + "temp");
        return output;
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

            FileOutputStream fos;
            File f = genFile();

            fos = new FileOutputStream(f);

            int read = 0;
            byte[] bytes = new byte[Contants.BUF_SIZE];
            while ((read = is.read(bytes)) != -1) {
                fos.write(bytes, 0, read);
            }

            log.info("InputStream to file:" + f.getAbsolutePath());
            emitToBranches(f);
        } catch (Exception e) {
            throw new StageException(this, e);
        }
    }
}
