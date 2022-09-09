package duke.items;

/**
 * Type of Items.
 */
public enum ItemTypes {
    TODO,
    DEADLINE,
    EVENT;

    @Override
    public String toString() {
        switch(this) {
        case TODO:
            return "[T]";
        case DEADLINE:
            return "[D]";
        case EVENT:
            return "[E]";
        default:
            return null;
        }
    }
}
