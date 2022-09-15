package rabbit.exception;

public class EditTaskException extends RabbitException {
    public enum Type {
        WRONGTYPE, INDEX, FORMAT;
    }

    private Type type;

    public EditTaskException(Type type) {
        this.type = type;
    }

    @Override
    public String toString() {
        switch(this.type) {
        case WRONGTYPE:
            return "Only events and deadlines have types, got it?";
        case INDEX:
            return "You can't edit a task that's not in the list.\n"
        + "Unless you think I can use magic?";
        case FORMAT:
            return "Do I need to teach you the format of editing a task again?\n";
        }
        return "";
    }
}
