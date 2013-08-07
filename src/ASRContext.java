import org.apache.commons.pipeline.Feeder;
import org.apache.commons.pipeline.Stage;
import org.apache.commons.pipeline.StageContext;
import org.apache.commons.pipeline.StageEventListener;

import java.util.Collection;
import java.util.EventObject;

/**
 * Created with IntelliJ IDEA.
 * User: kicoolzhang
 * Date: 8/6/13
 * Time: 6:15 PM
 * To change this template use File | Settings | File Templates.
 */
public class ASRContext implements StageContext {

    @Override
    public void registerListener(StageEventListener stageEventListener) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public Collection<StageEventListener> getRegisteredListeners() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void raise(EventObject eventObject) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public Feeder getBranchFeeder(String s) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public Feeder getDownstreamFeeder(Stage stage) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public Object getEnv(String s) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }
}
