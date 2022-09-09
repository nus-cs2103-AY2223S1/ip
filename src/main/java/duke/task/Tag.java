package duke.task;

public class Tag {
    private final String TAG_NAME;
    protected static final Tag NO_TAG = new Tag(null);

    public Tag(String TAG_NAME) {
        this.TAG_NAME = TAG_NAME;
    }

    public String toString() {
        if (this.equals(NO_TAG)) {
            return "";
        }
        return String.format("#%s", TAG_NAME);
    }
}
