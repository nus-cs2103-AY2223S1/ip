package duke.task;

public class Tag {
    private String label;

    public Tag(String label) {
        this.label = label;
    }

    @Override
    public String toString() {
        return "#" + label;
    }
}
