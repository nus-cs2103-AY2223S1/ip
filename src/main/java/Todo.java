class Todo extends Task {
    Todo(String description) {
        super(description);
    }

    static Todo createTodo(ParsedData data) throws EmptyDescriptionException {
        /*
         * TODO
         * Add error checking here
         */
        if (data.description.length() == 0) {
            throw new EmptyDescriptionException("todo");
        }
        return new Todo(data.description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
