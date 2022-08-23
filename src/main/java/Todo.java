public class Todo extends Task {
    private Todo(boolean isDone, String text, boolean isPrinting) {
        super(isDone, text, isPrinting);
        if (isPrinting) {
            System.out.println(this.toString());
        }
    }

    public static Todo of(String command, boolean isPrinting) throws IllegalArgumentException {
        boolean isDone = command.contains("/done");
        if (isDone) {
            command = command.replace("/done", "");
        }

        String text = command.replaceFirst("todo", "").strip();

        if (text.isEmpty()) {
            throw new IllegalArgumentException("🙁 OOPS!!! The description of a todo cannot be empty.\n");
        } else {
            return new Todo(isDone, text, isPrinting);
        }
    }

    @Override
    public String toString() {
        return "[T] " + super.toString();
    }
}
