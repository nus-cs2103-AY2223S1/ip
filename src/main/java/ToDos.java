public class ToDos extends Task{

    public ToDos(String todo) {
        super(todo);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
