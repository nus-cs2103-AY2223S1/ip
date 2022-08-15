class Todo extends Task {
    public static String extractName(String input) {
        String name = input.substring("todo ".length());
        return name;
    }

    public Todo(String input) {
        super(Todo.extractName(input));
    }

    @Override
    public String toString() {
        String type = "\u001B[35m(TODO)\u001B[0m";
        return String.format("%s %s !", type, super.toString());
    }
}
