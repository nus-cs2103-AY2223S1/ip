public class OutOfRangeException extends Exception{

    public OutOfRangeException(int num) {
        super("Task " + num + " is out of range! Please try again!");
    }

}
