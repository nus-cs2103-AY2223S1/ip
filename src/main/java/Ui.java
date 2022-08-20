import java.util.ArrayList;
import java.util.Scanner;

public class Ui {
    private Scanner myObj = new Scanner(System.in);

    public void showLoadingError() {
        System.out.println("There was an error loading tasks from the file");
    }

    public void printWelcomeMessage() {
        System.out.println("Hello! I'm Snoopy");
        System.out.println("What can I do for you?");
    }

    public String collectUserInput() {
        return myObj.nextLine();
    }

    public void printList(TaskList taskList) {
        ArrayList<Task> tasks = taskList.getTasks();
        int i = tasks.size();
        System.out.println("Here are the tasks in your list:");
        for (int a = 1; a <= i; a++) {
            String output = a + "." + tasks.get(a - 1).toString();
            System.out.println(output);
        }
    }

    public void printDeleteMessage(TaskList taskList, int taskNumber) {
        System.out.println("Noted. I've removed this task:");
        System.out.println(taskList.retrieveTask(taskNumber));
        int i = taskList.getListSize() - 1;
        System.out.println("Now you have " + i + " tasks in the list.");
    }

    public void printAddMessage(TaskList taskList) {
        int listSize = taskList.getListSize();
        System.out.println("Got it. I've added this task:");
        System.out.println(taskList.retrieveTask(listSize));
        System.out.println("Now you have " + listSize + " tasks in the list.");
    }

    public void printGoodbyeMessage() {
        System.out.println("Bye. Hope to see you again soon!");
    }
}
