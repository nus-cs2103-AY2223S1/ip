public class ToDos extends Task{
    ToDos(String name) {
        super(name);
    }

    @Override
    public String toString() {
        return String.format("[T] %s", super.toString());
    }
}
