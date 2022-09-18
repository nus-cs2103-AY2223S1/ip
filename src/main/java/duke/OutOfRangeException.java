package duke;

public class OutOfRangeException extends Exception{

    public OutOfRangeException(int num) {
        super("duke.Task " + num + " is out of range! Please try again!");
    }

}
