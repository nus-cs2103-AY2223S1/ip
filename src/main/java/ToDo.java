class ToDo extends Task {

    ToDo(String description) {
        super(description);
    }

    ToDo(String description, boolean isDone) {

        super(description, isDone);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

}
