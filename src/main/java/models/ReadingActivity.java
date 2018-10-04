package models;

public class ReadingActivity extends Activity {

    private int fromPage;
    private int toPage;
    private int pageProgression;

    public ReadingActivity(String name, int fromPage, int toPage, int pageProgression) {
        super(name);
        this.fromPage = fromPage;
        this.toPage = toPage;
        this.pageProgression = pageProgression;
    }

    private int getFromPage() {
        return this.fromPage;
    }

    public void setFromPage(int fromPage) {
        this.fromPage = fromPage;
    }

    public int getToPage() {
        return this.toPage;
    }

    public void setToPage(int toPage) {
        this.toPage = toPage;
    }

    public int getPageProgression() {
        return this.pageProgression;
    }

    public void setPageProgression(int pageProgression) {
        this.pageProgression = pageProgression;
    }

}
