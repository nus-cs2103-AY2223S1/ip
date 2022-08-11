public class ToDo extends Task{

    ToDo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return String.format("%s%s", "[T]", super.toString());
    }
}
