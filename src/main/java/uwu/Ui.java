package uwu;

import java.util.Scanner;
import uwu.task.Task;
import uwu.task.TaskList;

public class Ui {
    Scanner scanner = new Scanner(System.in);

    public String readCommand() {
        String userCommand = scanner.nextLine().toLowerCase().trim();
        return userCommand;
    }

    public void showLine() {
        String horizontalLine ="----------------------------------------------------------------------";
        System.out.println(horizontalLine);
    }

    public void greetUsers() {
        String greetings = "\thellu!" +
                           "\n\ti am oo woo <:" +
                           "\n\thow can i be of service today?";
        System.out.println(greetings);
    }

    public void leaveChat() {
        String farewellWords = "\tgood work today!" +
                                "\n\thope to see you again soon~";
        System.out.println(farewellWords);
    }

    public void addTask(Task task, int tasksLength) {
        String addToDoString = "\too new task! ^^" +
                "\n\t\tadded:\t" + task.toString() +
                "\n\tyou have " + String.valueOf(tasksLength) + " task(s) <:";
        System.out.println(addToDoString);
    }

    public void listTasks(TaskList tasks) {
        if (tasks.size() == 0) {
            System.out.println("\tlooks like there are no tasks on your list uwu" +
                                "\n\tfeed me a task to get started~ <:");
        } else {
            System.out.println("\there are your tasks~ you've got this!" + tasks.taskListToString());
        }
    }

    public void markTask(Task task) {
        String markedAsDone = "\tyey! good job~ keep it up <3";
        System.out.println(markedAsDone + "\n\t\t" + task.toString());
    }

    public void unmarkTask(Task task) {
        String unmarked = "\tkeep going~";
        System.out.println(unmarked + "\n\t\t" + task.toString());
    }

    public void deleteTask(Task task, int tasksLength) {
        System.out.println("\tremoving this task from your list...\n\t\t" +
                task.toString() +
                "\n\ttask removed~! now you have " + String.valueOf(tasksLength) + " task(s) <:");
    }

    public void showError(String e) {
        System.out.println(e);
    }
}
