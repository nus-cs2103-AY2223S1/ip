import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Duke {
    private static Task[] arr = new Task[100];
    private static int arrPointer = 0;

    public static void main(String[] args) throws IOException {
        InputStreamReader inputStreamReader = new InputStreamReader(System.in);
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
        System.out.println("\tHello! I'm Duke\n\tWhat can I do for you?");
        boolean inProgress = true;

        while (inProgress) {
            String userInput = bufferedReader.readLine();
            String[] userInputArr = userInput.split(" ");
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
                case "":
                    break;
                default:
                    addArrItem(userInput);
                    System.out.println("\tadded: " + userInput);
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
            System.out.println("\t" + (i + 1) + "." + arr[i]);
        }
    }

    private static void addArrItem(String taskDescription) {
        arr[arrPointer++] = new Task(taskDescription);
    }

    private static void markItem(int index) {
        if (index > arrPointer || index < 1) {
            System.out.println("\tI can't mark this task. It's not in the list.");
            return;
        }
        arr[index - 1].isDone = true;
        System.out.println("\tNice! I've marked this task as done:\n\t\t" + arr[index - 1]);
    }

    private static void unmarkItem(int index) {
        if (index > arrPointer || index < 1) {
            System.out.println("\tI can't unmark this task. It's not in the list.");
            return;
        }
        arr[index - 1].isDone = false;
        System.out.println("\tOK, I've marked this task as not done yet:\n\t\t" + arr[index - 1]);
    }
}
