public class WrongMessageException extends Exception{

    public WrongMessageException() {
        super("☹ OOPS!!! Please check whether you type correctly");
    }

    public WrongMessageException(String s) {
        super(s);
    }

}
