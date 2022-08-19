public class ToDos extends Task{

    public ToDos(String name) {
        super(name);
    }

    @Override
    public String toString() {
        if (this.isDone()) {
            return "[T][X] " + getName();
        } else {
            return "[T][ ] " + getName();
        }
    }
}
