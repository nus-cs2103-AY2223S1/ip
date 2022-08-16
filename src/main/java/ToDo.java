public class ToDo extends Task{

    public ToDo(String name) {
        super(name);

    }

    public String tag() {
        return "T";
    }

    @Override
    public String toString() {
        return String.format("[%s] %s", tag(), super.toString());
    }

}
