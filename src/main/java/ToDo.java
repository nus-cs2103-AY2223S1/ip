class ToDo extends Task {

    ToDo(String description) {
        super(description);
    }

    public String toString() {
        return "[T]" + super.toString();
    }

    public String write() {
        return "T" + super.write();
    }

}