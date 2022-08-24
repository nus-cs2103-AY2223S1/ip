public class ToDo extends Task {
    public ToDo(String desc) throws MissingDescriptionException {
        super(desc);
        if (desc.isBlank()) {
            throw new MissingDescriptionException(Duke.commandGuide("todo", Command.TODO));
        }
    }

    public ToDo(String desc, boolean isDone) {
        super(desc, isDone);
    }

    @Override
    public String toSaveFormat() {
        return "T " + super.toSaveFormat();
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
