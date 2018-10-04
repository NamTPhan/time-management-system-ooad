package models;

public class WritingActivity extends Activity {

    private int words;
    private int wordProgression;

    public WritingActivity(String name, int words, int wordProgression) {
        super(name);
        this.words = words;
        this.wordProgression = wordProgression;
    }

    public int getWords() {
        return this.words;
    }

    public void setWords(int words) {
        this.words = words;
    }

    public int getWordProgression() {
        return wordProgression;
    }

    public void setWordProgression(int wordProgression) {
        this.wordProgression = wordProgression;
    }

}
