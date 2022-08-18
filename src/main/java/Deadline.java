public class Deadline extends Task {
    private final String due;

    public Deadline(String desc, int id, char taskType) {
        super(desc, id, taskType);
        this.due = getDue(desc);
    }

    private String getDue(String desc) {
        int index = desc.indexOf('/');
        return desc.substring(index + 1);
    }
}
