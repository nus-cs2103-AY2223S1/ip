public class Todo extends Task {

    Todo(String title, boolean isCompleted) {
        super(title, isCompleted);
    }

    Todo(String title) {
        super(title);
    }

    public static Todo fromSaveFormat(String saveFormat) throws IllegalArgumentException {
        final String[] args = saveFormat.split(";;");
        if (args.length != 2) {
            throw new IllegalArgumentException("Incorrect save format: " + saveFormat);
        }
        return new Todo(args[1], args[0].equals("1"));
    }

    @Override
    public String toSaveFormat() {
        return String.format("T;;%s", super.toSaveFormat());
    }

    @Override
    public String toString() {
        return String.format("📜 %s", super.toString());
    }
}
