package duke;

import java.util.Scanner;

public class Ui {

    private final String GREETING = "HELLO MY BROTHER! HOW CAN I HELP YOU?";
    private final String BYE = "GOOD BYE! SEE YOU IN WHILE!";
    private final String DIVIDER = "===================================================";

    private Scanner sc;

    public Ui() {
        this.sc = new Scanner(System.in);
    }

    public void showWelcome() {
        System.out.println(GREETING);
    }

    public void showBye() {
        System.out.println(BYE);
    }

    public void showLine() {
        System.out.println(DIVIDER);
    }

    public void showError(String errMessage) {
        System.out.println(errMessage);
    }

    public String readCommand() {
        return sc.nextLine();
    }

    public void echoCommand(String echo) {
        System.out.println(echo);
    }

    public void addSuccess(Task task, String numOfTask) {
        System.out.println("Added: " + task.toString() +"\n" + numOfTask);
    }

    public void showList(TaskList taskList) {
        System.out.println(taskList.toString());
    }

    public void showToggleSuccess(Task task) {
        if (!task.isDone()) {
            System.out.println("MISTAKES WERE MADE!\nTHAT'S WHY YOU ARE HERE:\n" + task.toString());
        } else {
            System.out.println("GOOD JOB BROTHER!\n" + task.toString());
        }
    }
}