import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Duke {
    private static Task[] tasks = new Task[100];
    private static int arrPointer = 0;

    public static void main(String[] args) throws IOException {
        InputStreamReader inputStreamReader = new InputStreamReader(System.in);
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
        System.out.println("\tHello! I'm Duke\n\tWhat can I do for you?");
        boolean inProgress = true;

        while (inProgress) {
            String userInput = bufferedReader.readLine();
            String[] userInputArr = userInput.split(" ", 2);
            String[] taskDetailsArr;
            switch (userInputArr[0]) {
                case "list":
                    listArrItems();
                    break;
                case "bye":
                    System.out.println("\tBye. Hope to see you again soon!");
                    inProgress = false;
                    break;
                case "mark":
                    markItem(Integer.parseInt(userInputArr[1]));
                    break;
                case "unmark":
                    unmarkItem(Integer.parseInt(userInputArr[1]));
                    break;
                case "todo":
                    addToTasks(new ToDo(userInputArr[1]));
                    break;
                case "deadline":
                    taskDetailsArr = userInputArr[1].split(" /by ");
                    addToTasks(new Deadline(taskDetailsArr[0], taskDetailsArr[1]));
                    break;
                case "event":
                    taskDetailsArr = userInputArr[1].split(" /at ");
                    addToTasks(new Event(taskDetailsArr[0], taskDetailsArr[1]));
                    break;
            }
        }
        bufferedReader.close();
        inputStreamReader.close();
    }

    private static void listArrItems() {
        // Corner case with empty list
        if (arrPointer == 0) {
            System.out.println("\t" + "list is empty");
            return;
        }

        // Usual Path
        System.out.println("\tHere are the tasks in your list:");
        for (int i = 0; i < arrPointer; i++) {
            System.out.println("\t" + (i + 1) + "." + tasks[i]);
        }
    }

    private static void addToTasks(Task task) {
        tasks[arrPointer++] = task;
        System.out.println("\tGot it. I've added this task:\n" +
                "\t\t"+ task + "\n" +
                "\tNow you have " + arrPointer + " tasks in your list.");
    }

    private static void markItem(int index) {
        if (index > arrPointer || index < 1) {
            System.out.println("\tI can't mark this task. It's not in the list.");
            return;
        }
        tasks[index - 1].isDone = true;
        System.out.println("\tNice! I've marked this task as done:\n\t\t" + tasks[index - 1]);
    }

    private static void unmarkItem(int index) {
        if (index > arrPointer || index < 1) {
            System.out.println("\tI can't unmark this task. It's not in the list.");
            return;
        }
        tasks[index - 1].isDone = false;
        System.out.println("\tOK, I've marked this task as not done yet:\n\t\t" + tasks[index - 1]);
    }
}
