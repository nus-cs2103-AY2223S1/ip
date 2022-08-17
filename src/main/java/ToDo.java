public class ToDo extends Task {

    public ToDo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        String output = String.format("[T][%s] %s", this.getStatusIcon(), this.description);
        return output;
    }
}
