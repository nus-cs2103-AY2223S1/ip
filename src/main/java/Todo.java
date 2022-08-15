class Todo extends Task {
    Todo(String description) {
        super(description);
    }

    static Todo createTodo(ParsedData data) {
        /*
         * TODO
         * Add error checking here
         */

        return new Todo(data.description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
