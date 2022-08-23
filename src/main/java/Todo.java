//@@author chengda300
//Reused from https://nus-cs2103-ay2223s1.github.io/website/schedule/week2/project.html
// with minor modifications
public class Todo extends Task {
    public Todo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
//@@author