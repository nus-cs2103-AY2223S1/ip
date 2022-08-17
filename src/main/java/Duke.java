import java.util.Scanner;

public class Duke {
    private static final String logo =
            " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";
    private static final String separator = "––––––––––––––––––––––––––––––––––––––––\n";
    public static Task[] taskList = new Task[100];
    public static Integer numOfTasks = 0;

    public static String listTask() {
        StringBuilder fullList = new StringBuilder();
        for (int i = 1; i <= numOfTasks; i++) {
            fullList.append(i).append(". ").append(taskList[i - 1]);
        }
        return fullList.toString();
    }

    public static void chat () {
        Scanner sc = new Scanner(System.in);
        String userInput = sc.nextLine();
        String output;

        while (!(userInput.equals("Bye") || userInput.equals("bye"))) {
            if (userInput.equals("List")) {
                output = listTask();
                System.out.print(separator + "List all tasks:\n" + output + separator);
            } else {
                Task task = new Task(userInput);
                taskList[numOfTasks++] = task;
                output = task.toString();
                System.out.print(separator + "Task added: " + output + separator);
            }
            userInput = sc.nextLine();
        }
    }

    public static void main(String[] args) {
        System.out.println("Hello from\n" + logo);
        System.out.print("Tell me what you need\n");
        chat();
        System.out.print("Goodbye!");
    }
}
