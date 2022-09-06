package duke;

import java.util.Scanner;

public class Ui {

    private static final String GREETING = "HELLO MY FRIEND! HOW CAN I HELP YOU?";
    private static final String BYE = "GOOD BYE! SEE YOU IN WHILE!";

    public static String showGreet() {
        return GREETING;
    }

    public String showBye() {
        return BYE;
    }

    public String showError(String errMessage) {
        assert !errMessage.isBlank(): "error message should not be blank";
        return errMessage;
    }
    public String addSuccess(Task task, String numOfTask) {
        assert !task.toString().isBlank(): "task content should not be blank";
        assert !numOfTask.isBlank(): "number of task should not be blank";

        return "Added: " + task.toString() +"\n" + numOfTask;
    }

    public String showList(TaskList taskList) {
        return taskList.toString();
    }

    public String showFind(TaskList findList) {
        return "HERE ARE THE TASKS THAT YOU REQUESTED:\n"
                + findList.toString();
    }

    public String showToggleSuccess(Task task) {
        assert !task.toString().isBlank(): "task content should not be blank";
        if (!task.isDone()) {
            return "I HAVE CHANGED:\n" + task.toString();
        } else {
            return "GOOD JOB MY FRIEND!\n" + task.toString();
        }
    }

    public String showDeleteSuccess(Task task, String numOfTask) {
        assert !task.toString().isBlank(): "task content should not be blank";
        return "Deleted: " + task.toString() + "\n" + numOfTask;
    }
}