package duke;

public class DukeException extends Exception{

    private String message;

    public DukeException(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return this.message;
    }

    public static String EmptyTaskException() {
        return "Please input the details of your task! It cannot be empty.";
    }

    public static String UnmarkIndexEmptyException() {
        return "Please input the index to unmark!";
    }

    public static String MarkIndexEmptyException() {
        return "Please input the index to mark as completed!";
    }


}
