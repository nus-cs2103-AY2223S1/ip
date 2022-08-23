public class ToDo extends Task{

    public ToDo(String description) {
        super(description);
    }

    @Override
    public String getTime() {
        return "";
    }

    @Override
    public String getType() {
        return "T";
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
