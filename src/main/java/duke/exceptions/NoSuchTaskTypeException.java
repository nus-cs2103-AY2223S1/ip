package duke.exceptions;

public class NoSuchTaskTypeException extends Exception {

    public NoSuchTaskTypeException(String type) {
        super("I'm sorry, there is no task of type " + type);
    }

}
