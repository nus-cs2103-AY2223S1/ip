import java.time.LocalDate;

public class Todo extends Task{


    public Todo(String name) {
        super(name);
    }

    public void setTime(String time) {
        return;
    }

    public void setDate(LocalDate date) {
        return;
    }
    @Override
    public String toString() {
        return "[T] " + (super.isCompleted() ? "[X] " : "[ ] ") + super.getTaskName();
    }
}
