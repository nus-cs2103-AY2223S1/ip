package duke.task;

public class Tag {
    private String label;

    public Tag() {
        this.label = "";
    }

    public Tag(String label) {
        this.label = label;
    }

    public boolean isEmpty() {
        return label.trim().length() == 0;
    }

    @Override
    public String toString() {
        if (label.trim().length() == 0) {
            return "";
        }
        return "#" + label;
    }

    public String toMemoryString() {
        return label;
    }
}
