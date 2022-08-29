import task.Task;

public class Ui {
    private static final String LINE = "    ____________________________________________________________";
    private static final String INDENTATION = "     ";

    public String printMessage(String message) {
        return LINE + "\n" + message + "\n" + LINE + "\n";
    }

    public String makeIndent(String message) {
        return INDENTATION + message;
    }

    public String displayGreeting() {
        return printMessage(makeIndent("Hi, how are you doing?! I'm JRH2000\n") +
                makeIndent("How can I help you?\n"));
    }

    public String displayBye() {
        return printMessage(makeIndent("Sigh...abandoned again. See you again next time :("));
    }

    public String displayMark(Task markedTask) {
        return printMessage(makeIndent("Alright then! This task is marked as done: \n") +
                makeIndent(markedTask.toString()) + "\n");
    }

    public String displayUnmark(Task unmarkedTask) {
        return printMessage(makeIndent("Oh OK, this task is now marked as not done yet: \n") +
                makeIndent(unmarkedTask.toString()) + "\n");
    }

    public String displayDelete(Task deletedTask) {
        return printMessage(makeIndent("Fine. I've removed this task: \n") +
                makeIndent(deletedTask.toString()) + "\n");
    }

    public String displayAdd(Task addedTask, int size) {
        return printMessage(makeIndent("Sure thing! I've added this task: \n") +
                makeIndent(addedTask.toString()) + "\n" +
                makeIndent("Now you have " + size + " task(s) in the list.\n"));
    }
}
