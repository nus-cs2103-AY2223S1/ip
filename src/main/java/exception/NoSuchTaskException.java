package exception;

public class NoSuchTaskException extends Exception {

    public NoSuchTaskException(String type) {
        super("No task of type " + type);
    }

}
