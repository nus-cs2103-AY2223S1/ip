class Deadline extends Task {

    Deadline(String description) {
        super(description);
    }

    public String toString() {
        return "[D]" + super.toString();
    }

}