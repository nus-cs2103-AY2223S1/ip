import java.time.LocalDate;

class Todo extends Task{

    public Todo(String itself) {
        super(itself);
    }

    public boolean isOnDate(LocalDate lc) {
        return false;
    }

    @Override
    public String writeToFile() {
        return "T|" + super.writeToFile();
    }
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
