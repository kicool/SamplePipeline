package functor;

/**
 * User: kicoolzhang
 * Date: 8/8/13
 * Time: 5:50 PM
 */
public class FuncActivityIdToUrl implements Functor {

    private static final String HOST = "http://mobiledev.englishtown.com/services/school/query?q=activity!";
    private static final String SUFFIX = ".activityContent";
    private int aid;

    FuncActivityIdToUrl(int aid) {
        this.aid = aid;
    }

    @Override
    public Object call() {
        return HOST + this.aid + SUFFIX;
    }
}
