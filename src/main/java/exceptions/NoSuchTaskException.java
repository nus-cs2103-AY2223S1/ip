package exceptions;

public class NoSuchTaskException extends Exception {

    public NoSuchTaskException(String type) {
        super("I'm sorry, there is no task of type " + type);
    }

}
