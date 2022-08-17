class Todo extends Task {
    private static String extractName(String input) throws CarbonException {
        int len = input.length();
        int requiredLen = "todo ".length();
        if (len <= requiredLen) {
            CarbonException invalidParam = new InvalidParamException(input);
            throw invalidParam;
        } else {
            String name = input.substring("todo ".length());
            return name;
        }
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
