class ToDo extends Task {

    ToDo(String description) {
        super(description);
    }

    ToDo(String description, boolean isDone) {

        super(description, isDone);
    }

    @Override
    public String toFileString() {

        return "T | " + (this.isDone ? 1 : 0) + " | " +
                this.description;
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

}
