public class ToDo extends Task {

    public ToDo(String input) {
        super(input);
    }

    @Override
    public String toString() {
        return String.format("[T][%s] %s",this.getDone() ? "X" : " ", this.getTask());
    }
}
