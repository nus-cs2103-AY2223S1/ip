package rabbit.exception;

/**
 * the class of exceptions related to
 * add to list function of Rabbit
 */
public class AddToListException extends RabbitException{
    private Type type;
    public enum Type {
        FULL, FORMAT, TIMEFORMAT;
    }

    public AddToListException(Type type) {
        this.type = type;
    }

    @Override
    public String toString() {
        switch(this.type) {
        case FULL:
            return "There are too many tasks in the list!\n"
                    + "Don't exceed 100 please. The poor notebook is full.";
        case FORMAT:
            return "Do I have to teach you again the format of creating a task?";
        case TIMEFORMAT:
            return "Hmmmm...I'm not sure if that's a valid time. Do you live on the earth or...?";
        }
        return "";
    }

}
