import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.commons.pipeline.Pipeline;
import org.apache.commons.pipeline.stage.BaseStage;

import java.util.ArrayList;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: kicoolzhang
 * Date: 8/8/13
 * Time: 4:42 PM
 * To change this template use File | Settings | File Templates.
 */
public abstract class BranchBaseStage extends BaseStage {
    protected static Log log = LogFactory.getLog(BranchBaseStage.class.getSimpleName());

    protected ArrayList<String> branches;

    public BranchBaseStage() {
        this.branches = new ArrayList<String>();
    }

    public void addBranch(String branch) {
        this.branches.add(branch);
    }

    public void emitToBranches(Object obj) {
        for (String branch : branches) {
            if (branch.length() > 0) {

                try {
                    Map<String, Pipeline> allBranches = ((Pipeline) context).getBranches();
                    if (allBranches.get(branch) != null) {
                        log.info("emit " + obj.getClass().getSimpleName() + " to branch:" + branch);

                        emit(branch, obj);
                    }
                } catch (IllegalStateException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
