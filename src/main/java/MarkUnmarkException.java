/**
 * the class of exceptions related to the mark
 * and unmark functions of Rabbit
 */
public class MarkUnmarkException extends RabbitException{
    private Type type;
    public enum Type {
        INDEX, MARKFORMAT, MARKREPEAT,
        UNMARKFORMAT, UNMARKREPEAT;
    }
    public MarkUnmarkException(Type type) {
        this.type = type;
    };

    @Override
    public String toString() {
        switch (this.type) {
            case INDEX:
                return "Hey, be careful.\n"
                        + "The index must be between 1 and the size of the list, alright?";
            case MARKFORMAT:
                return "Type 'mark + the index of the task' "
                        + "if that's what you want.";
            case MARKREPEAT:
                return "What do you mean? This task is already marked as done.";
            case UNMARKFORMAT:
                return "Type 'unmark + the index of the task' if that's what you want.";
            case UNMARKREPEAT:
                return "What do you mean? This task is not done in the first place.";

        }
        return "An Unknown exception related to mark and unmark functions occurred.";
    }
}
