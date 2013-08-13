import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.commons.pipeline.StageException;
import org.apache.commons.pipeline.stage.BaseStage;

/**
 * Created with IntelliJ IDEA.
 * User: kicoolzhang
 * Date: 8/7/13
 * Time: 12:19 PM
 * To change this template use File | Settings | File Templates.
 */
public class MyLogStage extends BaseStage {
    private final Log log = LogFactory.getLog(MyLogStage.class);

    String s;

    public MyLogStage(String s) {
        this.s = s;
    }

    @Override
    public void process(Object obj) throws StageException {
        super.process(obj);
        log.info(s + " Processing object:<" + obj.getClass().getCanonicalName() + "> " + obj);
    }
}
