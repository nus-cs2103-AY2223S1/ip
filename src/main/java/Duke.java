import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;
public class Duke {
    private static final String greetMessage = "Hello! I'm Duke \nWhat can I do for you?";
    private static final String byeMessage = "Bye. Hope to see you again soon!";
    private static final String listMessage = "Here are the tasks in your list: \n";
    private static final String markText = "Nice! I've marked this task as done: \n";
    private static final String unmarkText = "OK, I've marked this task as not done yet: \n";
    private static ArrayList<Task> userTasks = new ArrayList<>();
    private static void wrapText(String content) {
        System.out.println("-".repeat(57));
        System.out.println(content);
        System.out.println("-".repeat(57));
    }

    private static void appendArray(String inputString) {
        userTasks.add(new Task(inputString));
    }

    private static String generateList() {
        String listInString = listMessage;
        int index = userTasks.size();
        for (int i = 0; i < index; i++) {
            listInString += String.valueOf(i + 1) + ". " + userTasks.get(i).returnDescription();
            if(i != index - 1) {
                listInString += "\n";
            }
        }
        return listInString;
    }

    private static String generateMark(String taskNumber) {
        int index = Integer.parseInt(taskNumber) - 1;
        String markInText = markText;
        Task currentTask = userTasks.get(index);
        currentTask.markTask();
        markInText += currentTask.returnDescription();
        return markInText;
    }

    private static String generateUnmark(String taskNumber) {
        int index = Integer.parseInt(taskNumber) - 1;
        String unmarkInText = unmarkText;
        Task currentTask = userTasks.get(index);
        currentTask.unmarkTask();
        unmarkInText += currentTask.returnDescription();
        return unmarkInText;
    }
    private static void handleUserInputs(Scanner scanner) {
        while(scanner.hasNext()) {
            String inputString = scanner.nextLine();
            String[] inputs = inputString.split(" ");
            String input = inputs[0];
            if (input.equals("bye")) {
                wrapText(byeMessage);
                break;
            } else if (input.equals("list")) {
                wrapText(generateList());
            } else if (input.equals("mark")) {
                wrapText(generateMark(inputs[1]));
            } else if (input.equals("unmark")) {
                wrapText(generateUnmark(inputs[1]));
            }
            else {
                appendArray(inputString);
                wrapText("added: " + inputString);
            }
        }
    }
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        wrapText(greetMessage);
        Scanner scanner = new Scanner(System.in);
        handleUserInputs(scanner);
    }
}
