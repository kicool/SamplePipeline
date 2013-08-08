import org.apache.commons.logging.LogFactory;
import org.apache.commons.pipeline.StageException;

/**
 * Created with IntelliJ IDEA.
 * User: kicoolzhang
 * Date: 8/8/13
 * Time: 10:48 AM
 * To change this template use File | Settings | File Templates.
 */
public final class BranchStage extends BranchBaseStage {
    static {
        log = LogFactory.getLog(BranchStage.class);
    }

    public BranchStage(String branch) {
        addBranch(branch);
    }

    /*
     * emit same objects to all branches
     */
    @Override
    public void process(Object obj) throws StageException {
        super.process(obj);

        emitToBranches(obj);
    }
}
