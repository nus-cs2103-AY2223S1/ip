import java.util.ArrayList;

public class Ui {
    private TaskList tasks;
    private final static String horLine = "------------------------------------------------------";
    public Ui() {

    }

    public void showLoadingError() {
        String msg = ("No saved tasks were found.");
        System.out.println(msg);
    }

    public void tasksLoadedMsg(int size) {
        String msg = (size + " tasks were loaded.");
        System.out.println(msg);
    }
    public void welcomeMessage() {
        String msg = ("Hello! I'm Duke\n" +
                "What can I do for you?");
        printMsgWithLine(msg);
    }
    public void exitMessage() {
        String msg = ("\tBye. Hope to see you again soon!");
        printMsgWithLine(msg);
    }

    public void deleteTaskMsg(Task task,int size) {
        String msg = ("\tNoted, I have removed this task: \n" +
                "\t\t" + task.toString() + "\n" + "\tNow you have " +
                size + " tasks in the list.");
        printMsgWithLine(msg);
    }

    public void taskDoneMsg(Task task) {
        String msg = ("\tNice! I've marked this task as done:\n" +
                "\t\t" + task.toString());
        printMsgWithLine(msg);
    }

    public void taskUndoneMsg(Task task) {
        String msg = ("\tOK, I've marked this task as not done yet:\n" +
                "\t\t" + task.toString());
        printMsgWithLine(msg);
    }

    public void viewListMsg(ArrayList<Task> tasks) {
        String msg = ("\tHere are the tasks in your list:\n");
        for(int i = 0; i<tasks.size(); i++) {
            int num = i+1;
            msg += ("\t" + num + "." + tasks.get(i).toString() + "\n");
        }
        printMsgWithLine(msg);
    }

    public void taskAddMsg(Task task,int size) {
        String msg = ("\tGot it. I've added this task:\n" + "\t" + task.toString() +
        "\n\tNow you have " + size + " tasks in the list.");
        printMsgWithLine(msg);
    }


    public static void drawLine() {
        System.out.println(horLine);
    }

    public static void printMsgWithLine(String msg) {
        drawLine();
        System.out.println("\n" + msg + "\n");
        drawLine();
    }


}
