public class Todo extends Task{

    public Todo(String description) {
        super(description);
    }

    @Override
    public String parseTask() {
        return "T" + super.parseTask();
    }

    @Override
    public String toString(){
        return "[T]" + super.toString();
    }
}
