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
        String userInput = sc.next();
        String output;

        while (!(userInput.equals("Bye") || userInput.equals("bye"))) {
            if (userInput.equals("List")) {
                output = listTask();
                System.out.print(separator + "List all tasks:\n" + output + separator);
            } else if (userInput.equals("mark")) {
                Integer taskNum = Integer.valueOf(sc.next());
                taskList[taskNum - 1].markAsDone();
                System.out.print(separator + "Good job on completing the task:\n" + taskList[taskNum - 1] + separator);
            } else if (userInput.equals("unmark")) {
                Integer taskNum = Integer.valueOf(sc.next());
                taskList[taskNum - 1].markAsUndone();
                System.out.print(separator + "This task is not done yet:\n" + taskList[taskNum - 1] + separator);
            } else if (userInput.equals("deadline")) {
                String task = sc.nextLine();
                String[] content = task.split("/by");
                Deadline ddl = new Deadline(content[0], content[1]);
                taskList[numOfTasks++] = ddl;
                System.out.print(separator + "Deadline added: " + ddl + separator);
            } else if (userInput.equals("event")) {
                String task = sc.nextLine();
                String[] content = task.split("/at");
                Event ev = new Event(content[0], content[1]);
                taskList[numOfTasks++] = ev;
                System.out.print(separator + "Event added: " + ev + separator);
            } else if (userInput.equals("todo")) {
                String task = sc.nextLine();
                Todo todo = new Todo(task);
                taskList[numOfTasks++] = todo;
                System.out.print(separator + "Todo added: " + todo + separator);
            }
            userInput = sc.next();
        }
    }

    public static void main(String[] args) {
        System.out.println("Hello from\n" + logo);
        System.out.print("Tell me what you need\n");
        chat();
        System.out.print("Goodbye!");
    }
}
