public class InvalidIndexException extends Exception {
    public InvalidIndexException() {
        super("____________________________________________________________\n" +
                "The task you are referring to doesn't exist!!\n" +
                "____________________________________________________________");
    }
}
