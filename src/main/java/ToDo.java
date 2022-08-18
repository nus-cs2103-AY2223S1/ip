public class ToDo extends Task {
    public ToDo(String input) {
        super(input, "");
    }

    public ToDo(String input, boolean done, String dummy) {
        super(input, done, "");
    }

    public ToDo markDone() {
        return new ToDo(this.getVal(), true, "");
    }

    public ToDo markUndone() {
        return new ToDo(this.getVal(), false, "");
    }
    @Override
    public String toString() {
        if(this.getDone()) {
            return String.format("[T][X] %s", this.getVal());
        }
        else {
            return String.format("[T][ ] %s", this.getVal());
        }
    }
}
