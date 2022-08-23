public class ToDo extends Task {
    public ToDo(String desc) throws MissingDescriptionException {
        super(desc);
        if (desc.isBlank()) {
            throw new MissingDescriptionException(Duke.commandGuide("todo", Command.TODO));
        }
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
