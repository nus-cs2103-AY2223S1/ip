import java.util.Scanner;

/**
 * A chatbot named Tako that
 * supports various tasks.
 *
 * @author Alvin Tan Fu Long
 */
public class Tako {
    public static void main(String[] args) {
        System.out.println("Hello! I'm Tako\nWhat do you want?");
        final String COMMAND_BYE = "bye";
        final String COMMAND_LIST = "list";
        final String COMMAND_MARK = "mark";
        final String COMMAND_TODO = "todo";
        final String COMMAND_DEADLINE = "deadline";
        final String COMMAND_EVENT = "event";
        Task[] tasks = new Task[100];
        int tasksCount = 0;

        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            String input = sc.nextLine();
            String[] splitInput = input.split(" ", 2);
            String command = splitInput[0];

            if (command.equals(COMMAND_BYE)) {
                System.out.println("Bye, until next time...");
                break;
            }

            switch (command) {
            case COMMAND_LIST:
                for (int i = 0; i < tasksCount; i++) {
                    StringBuilder sb = new StringBuilder();
                    Task task = tasks[i];
                    sb.append(i + 1);
                    sb.append(".");
                    sb.append(task);
                    System.out.println(sb);
                }
                break;
            case COMMAND_MARK:
                if (splitInput.length == 2) {
                    int taskNumber = Integer.parseInt(splitInput[1]) - 1;
                    Task task = tasks[taskNumber];
                    task.markAsDone();
                    System.out.println("marked: " + task);
                }
                break;
            case COMMAND_TODO:
                Todo todo = new Todo(splitInput[1]);
                tasks[tasksCount] = todo;
                tasksCount++;
                System.out.println("added: " + todo);
                System.out.println("Total tasks: " + tasksCount);
                break;
            case COMMAND_DEADLINE:
                String[] splitDeadline = splitInput[1].split(" /by ", 2);
                Deadline deadline = new Deadline(splitDeadline[0], splitDeadline[1]);
                tasks[tasksCount] = deadline;
                tasksCount++;
                System.out.println("added: " + deadline);
                System.out.println("Total tasks: " + tasksCount);
                break;
            case COMMAND_EVENT:
                String[] splitEvent = splitInput[1].split(" /at ", 2);
                Event event = new Event(splitEvent[0], splitEvent[1]);
                tasks[tasksCount] = event;
                tasksCount++;
                System.out.println("added: " + event);
                System.out.println("Total tasks: " + tasksCount);
                break;
            default:
                tasks[tasksCount] = new Task(input);
                tasksCount++;
                System.out.println("added: " + input);
                System.out.println("Total tasks: " + tasksCount);
            }
        }
        sc.close();
    }
}