import java.util.Scanner;

public class Todo extends Task {

    public Todo(String task) {
        super(task.substring(5));
    }

    public String toString() {
        return "[T]" + super.toString();
    }
}
