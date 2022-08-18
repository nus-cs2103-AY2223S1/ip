public class Add extends Task {

    public Add(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[ ] " + this.description;
    }
}
