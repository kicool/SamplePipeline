import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.commons.pipeline.Feeder;

/**
 * Created with IntelliJ IDEA.
 * User: kicoolzhang
 * Date: 8/7/13
 * Time: 12:17 PM
 * To change this template use File | Settings | File Templates.
 */
public class TFeeder implements Feeder {
    private Log log = LogFactory.getLog(TFeeder.class);

    @Override
    public void feed(Object obj) {

        if (obj instanceof String) {
            String str = (String) obj;
            log.info("Terminal. String:" + str);
        } else {
            log.info("Terminal. Obj class type:" + obj.getClass().getCanonicalName());
        }

    }
}