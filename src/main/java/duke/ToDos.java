package duke;

public class ToDos extends Task {

    public ToDos(String description) {
        super(description);
    }

    public String savedData() {
        return "T | " + super.savedData() + "\n";
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
