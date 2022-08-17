public class ToDo extends Task{
    public ToDo(String description) {
        super(description);
    }

    public ToDo() throws DokeException {
        throw new DokeException("ToDo");
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
