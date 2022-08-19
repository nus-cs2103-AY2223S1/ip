public class ToDo extends Task {

    public ToDo(String taskText) {
        super(taskText);
    }

    @Override
    public String toString(){
        return "[T] " + super.toString();
    }
}
