public class ToDos extends Task {

    public ToDos(String s) {
        super(s);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
