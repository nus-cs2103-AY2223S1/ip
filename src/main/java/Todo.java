import java.time.LocalDate;

class Todo extends Task{

    public Todo(String itself) {
        super(itself);
    }

    public boolean isOnDate() {
        return false;
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
