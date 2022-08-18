import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {

    static List<Task> list = new ArrayList<>();

    public static void main(String[] args) {

        printWelcomeMsg();

        //interaction with user
        while(true) {

            Scanner scanner = new Scanner(System.in);
            String userInput = scanner.nextLine();
            String[] tokens = userInput.split("\\s+");
            String firstWord = tokens[0];

            if(firstWord.equals("mark") || firstWord.equals("unmark")) {

                int taskNumber = Integer.parseInt(tokens[1]);
                markingOfTask(firstWord,taskNumber); //and print

            } else if(userInput.equals("bye")){

                printEndingMsg();
                break;

            } else if(userInput.equals("list")){

                displayTasks(list);

            } else {

                Task task = new Task(userInput);
                addTaskToList(task); //and print

            }
        }
    }

    private static void addTaskToList(Task task) {
        list.add(task);
        printWithFormat("added: " + task.getName());
    }

    private static void printWelcomeMsg() {
        String welcomeMsg = "Hello! I'm Duke\n     What can I do for you?";
        printWithFormat(welcomeMsg);
    }

    private static void printEndingMsg() {
        String endingMsg = "Bye. Hope to see you again soon!";
        printWithFormat(endingMsg);
    }

    private static void markingOfTask(String firstWord, int taskNumber) {
        Task task = list.get(taskNumber - 1);
        if(firstWord.equals("mark")) {
            task.setDone(true);
            printWithFormat("Nice! I've marked this task as done:\n     " + task);
        } else {
            task.setDone(false);
            printWithFormat("OK, I've marked this task as not done yet:\n     " + task);
        }
    }

    private static void printWithFormat(String text) {

        String startHorizontalLines = "     ________________________________________\n";
        String endHorizontalLines = "\n     ________________________________________\n";

        System.out.println(startHorizontalLines + "     " + text + endHorizontalLines);
    }

    private static void displayTasks(List<Task> list) {

        String text = "Here are the tasks in your list:\n     ";

        for(int i = 0; i < list.size(); i++) {

            if(i == list.size() - 1) {
                text += i + 1 + ". " + list.get(i);
            } else {
                text += i + 1 + ". " + list.get(i) +"\n     ";
            }
        }

        printWithFormat(text);
    }
}
