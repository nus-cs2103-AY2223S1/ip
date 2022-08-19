public class ToDo extends Task {

    ToDo(String name) {
        super(name);
    }

    @Override
    public String toString() {
        String out = "[T]";
        out += super.toString();
        return out;
    }
}
