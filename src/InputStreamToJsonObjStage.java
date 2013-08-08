import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.commons.pipeline.StageException;
import org.apache.commons.pipeline.stage.BaseStage;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created with IntelliJ IDEA.
 * User: kicoolzhang
 * Date: 8/8/13
 * Time: 12:36 PM
 * To change this template use File | Settings | File Templates.
 */
public class InputStreamToJsonObjStage extends BranchBaseStage {
    static {
        log = LogFactory.getLog(InputStreamToJsonObjStage.class);
    }

    public static final String KEY_TEMPLATE_CODE = "templateCode";

    public InputStreamToJsonObjStage() {
        addBranch("Json");
    }

    @Override
    public void process(Object obj) throws StageException {
        super.process(obj);

        if (obj instanceof InputStream) {
            InputStream is = (InputStream) obj;

            try {
                is.reset();

                JSONTokener jsonTokener = new JSONTokener(is);
                JSONArray json = new JSONArray(jsonTokener);
                for (int i = 0; i < json.length(); ++i) {
                    JSONObject jsonObject = json.getJSONObject(i);
                    String template = jsonObject.optString(KEY_TEMPLATE_CODE);
                    if (template != null && template.length() > 0) {
                        emitToBranches(template);
                        return;
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            }

        } else {
            log.error("Invalid object type:" + obj.getClass().getSimpleName());
        }
    }
}
