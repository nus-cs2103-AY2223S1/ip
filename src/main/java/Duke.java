import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    private static String CMD_LIST = "list";
    private static ArrayList<Task> allTasks = new ArrayList<>();

    private static void greet() {
        String LOGO = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + LOGO);
        System.out.println("What can I do for you?");
    }

    private static void listTasks() {
        String output = "";
        for (int i = 0; i < Duke.allTasks.size(); i++) {
            output += "\n" + (i + 1) + ". " + Duke.allTasks.get(i).toString();
        }
        System.out.println(output);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Duke.greet();
        while (true) {
            System.out.println("------------------------------");
            System.out.print(": ");
            String userInput = scanner.nextLine();
            String[] inputArray = userInput.split(" ");
            String cmd = inputArray[0];
            try {
                switch (cmd) {
                    case "list":
                        Duke.listTasks();
                        break;
                    case "mark":
                        int i = Integer.parseInt(inputArray[1]) - 1;
                        Duke.allTasks.get(i).markAsDone();
                        break;
                    case "unmark":
                        int j = Integer.parseInt(inputArray[1]) - 1;
                        Duke.allTasks.get(j).unmark();
                        break;
                    default:
                        Task newTask = new Task(userInput);
                        Duke.allTasks.add(newTask);
                }
            } catch (ClassCastException e) {
                System.out.println(e.getMessage());
            }

            System.out.println("------------------------------\n");
        }
    }
}
