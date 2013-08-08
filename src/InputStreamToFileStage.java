import org.apache.commons.logging.LogFactory;

import java.io.*;

/**
 * Created with IntelliJ IDEA.
 * User: kicoolzhang
 * Date: 8/8/13
 * Time: 9:53 AM
 * To change this template use File | Settings | File Templates.
 */
public class InputStreamToFileStage extends BranchBaseStage {
    static {
        log = LogFactory.getLog(BranchStage.class);
    }

    private static final int BUF_SIZE = 1024;

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

        if (obj instanceof InputStream) {
            InputStream is = (InputStream) obj;
            if (is instanceof BufferedInputStream) {
                try {
                    is.reset();
                } catch (IOException e) {
                    e.printStackTrace();
                    log.error("Abort:", e);
                    return;
                }
            } else {
                log.error("No reset()!");
            }

            FileOutputStream fos;
            File f = genFile();

            try {
                fos = new FileOutputStream(f);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                log.error("Abort:", e);
                return;
            }

            int read = 0;
            byte[] bytes = new byte[BUF_SIZE];

            try {
                while ((read = is.read(bytes)) != -1) {
                    fos.write(bytes, 0, read);
                }
            } catch (IOException e) {
                e.printStackTrace();
                log.error("Abort:", e);
                return;
            }

            log.info("InputStream to file:" + f.getAbsolutePath());
            emitToBranches(f);
        } else {
            log.error("Invalid object type:" + obj.getClass().getSimpleName());
        }
    }
}
