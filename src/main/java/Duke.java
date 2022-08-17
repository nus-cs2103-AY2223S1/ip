import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    private static final String LINE = "    ____________________________________________________________";
    private static final String INDENTATION = "     ";
    private static ArrayList<Task> taskList = new ArrayList<>();

    private static boolean isDone = false;

    public static void main(String[] args) {
        greet();
        Scanner sc = new Scanner(System.in);

        while (!isDone) {
            String command = sc.nextLine();
            String[] splitCommandSpace = command.split("\\s+");

            if (command.equals("bye")) {
                bye();
            } else if (command.equals("list")) {
                displayList();
            } else if (splitCommandSpace[0].equals("mark")) {
                String markItem = splitCommandSpace[1];
                mark(markItem);
            } else if (splitCommandSpace[0].equals("unmark")) {
                String unmarkItem = splitCommandSpace[1];
                unmark(unmarkItem);
            } else {
                printMessage(INDENTATION + "added: " + command);
                Task currentTask = new Task(command);
                addList(currentTask);
            }
        }
    }

    private static void exitProgram() {
        isDone = true;
    }

    private static void displayLine() {
        System.out.println(LINE);
    }


    private static void printMessage(String message) {
        displayLine();
        System.out.println(message);
        displayLine();
    }

    private static void greet() {
        String greetingMessage = INDENTATION + "Hello! I'm Duke\n" +
                INDENTATION + "What can I do for you?\n";
        printMessage(greetingMessage);
    }

    private static void bye() {
        String byeMessage = INDENTATION + "Bye. Hope to see you again soon!\n";
        printMessage(byeMessage);
        exitProgram();
    }

    private static void addList(Task input) {
        taskList.add(input);
    }

    private static void displayList() {
        displayLine();
        String listMessage = INDENTATION + "Here are the tasks in your list:";
        System.out.println(listMessage);
        int len = taskList.size();
        for (int i = 0; i < len; i++) {
            int orderList = i + 1;
            String message = INDENTATION + orderList + ". " + taskList.get(i).toString();
            System.out.println(message);
        }
        displayLine();
    }

    private static void mark(String item) {
        int itemNumber = Integer.parseInt(item);
        int index = itemNumber - 1;
        Task markedTask = taskList.get(index);
        markedTask.markAsDone();
        displayLine();
        String markMessage = INDENTATION + "Nice! I've marked this task as done:";
        System.out.println(markMessage);
        System.out.println(INDENTATION + markedTask.toString());
        displayLine();
    }

    private static void unmark(String item) {
        int itemNumber = Integer.parseInt(item);
        int index = itemNumber - 1;
        Task unmarkedTask = taskList.get(index);
        unmarkedTask.unmarkAsDone();
        displayLine();
        String unmarkMessage = INDENTATION + "OK, I've marked this task as not done yet:";
        System.out.println(unmarkMessage);
        System.out.println(INDENTATION + unmarkedTask.toString());
        displayLine();
    }
}
