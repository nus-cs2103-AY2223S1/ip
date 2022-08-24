public class ToDos extends Task{
    ToDos(String name) {
        super(name);
    }

    ToDos(String name, boolean status) {
        super(name, status);
    }

    @Override
    public String toString() {
        return String.format("[T] %s", super.toString());
    }
}
