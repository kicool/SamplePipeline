import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.commons.pipeline.Pipeline;
import org.apache.commons.pipeline.driver.ThreadPoolStageDriverFactory;
import org.apache.commons.pipeline.stage.HttpFileDownloadStage;
import org.apache.commons.pipeline.stage.LogStage;
import org.apache.commons.pipeline.stage.PipelineShutdownStage;
import org.apache.commons.pipeline.stage.URLToInputStreamStage;

/**
 * Created with IntelliJ IDEA.
 * User: kicoolzhang
 * Date: 8/6/13
 * Time: 6:08 PM
 * To change this template use File | Settings | File Templates.
 */
public class main {
    private static Log log = LogFactory.getLog(main.class);

    public static void main(String[] argv) {
        String url = "http://mobiledev.englishtown.com/services/school/query?q=activity!88750.activityContent";
        String workdir = "/Users/kicoolzhang/Repos/workdir";

        SimplePipeline2(url, workdir);
    }

    private static void SimplePipeline(String url, String workdir) {
        Pipeline pipeline = new Pipeline();

        HttpFileDownloadStage s = new HttpFileDownloadStage(workdir);
        PipelineShutdownStage pipelineShutdownStage = new PipelineShutdownStage(5);

        ThreadPoolStageDriverFactory factory = new ThreadPoolStageDriverFactory();

        log.info("OK.");

        try {
            pipeline.addStage(new MyLogStage("1"), factory);
            pipeline.addStage(s, factory);
            pipeline.addStage(new MyLogStage("2"), factory);
            pipeline.setTerminalFeeder(new TFeeder());
            //pipeline.addStage(pipelineShutdownStage, factory);


            pipeline.start();
            for (int i = 0; i < 1; i++) {
                pipeline.getSourceFeeder().feed(url);
            }
            pipeline.finish();

            // StageDriver stageDriver = factory.createStageDriver(s, pipeline);
            //stageDriver.getFeeder().feed(url);
            //stageDriver.start();
            //stageDriver.finish();
        } catch (Exception e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }

        log.info("88.");
    }

    private static void SimplePipeline2(String url, String workdir) {
        ThreadPoolStageDriverFactory factory = new ThreadPoolStageDriverFactory();

        Pipeline pipeline = new Pipeline();

        log.info("OK.");

        try {

            pipeline.addStage(new MyLogStage("1"), factory);
            pipeline.addStage(new URLToInputStreamStage(), factory);
            pipeline.addStage(new MyLogStage("2"), factory);
            pipeline.addStage(new InputStreamHashStage("MD5"), factory);
            pipeline.addStage(new MyLogStage("3"), factory);
            pipeline.setTerminalFeeder(new TFeeder());

            pipeline.start();

            pipeline.getSourceFeeder().feed(url);

            pipeline.finish();

            // StageDriver stageDriver = factory.createStageDriver(s, pipeline);
            //stageDriver.getFeeder().feed(url);
            //stageDriver.start();
            //stageDriver.finish();
        } catch (Exception e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }

        log.info("88.");
    }
}
