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

    public static Todo fromFileDescription(String input) {
        String[] strArray = input.split("\\|");
        boolean isDone;
        Todo todo = new Todo(strArray[2]);
        if (strArray[1].equals("Y")) {
            todo.donelah();
        }
        return todo;
    }
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
