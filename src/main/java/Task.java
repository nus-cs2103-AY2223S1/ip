public class Task {
    private final String description;
    private final CheckBox checkBox;

    public Task(String name) {
        this.description = name;
        this.checkBox = new CheckBox(false);
    }

    public void mark(boolean isCompleted) {
        this.checkBox.setCheckBox(isCompleted);
    }

    public String toString() {
        return this.checkBox + " " + this.description;
    }
}
