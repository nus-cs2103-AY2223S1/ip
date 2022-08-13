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
        final String COMMAND_EXIT = "bye";
        final String COMMAND_LIST = "list";
        final String COMMAND_MARK = "mark";
        Task[] tasks = new Task[100];
        int tasksCount = 0;

        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            String input = sc.nextLine();
            String[] splitInput = input.split(" ", 2);

            if (input.equals(COMMAND_EXIT)) {
                System.out.println("Bye, until next time...");
                break;
            } else if (input.equals(COMMAND_LIST)) {
                for (int i = 0; i < tasksCount; i++) {
                    StringBuilder sb = new StringBuilder();
                    Task task = tasks[i];
                    sb.append(i + 1);
                    sb.append(".[");
                    sb.append(task.getStatusIcon());
                    sb.append("] ");
                    sb.append(task);
                    System.out.println(sb);
                }
            } else if (splitInput[0].equals(COMMAND_MARK) && splitInput.length == 2) {
                 int taskNumber = Integer.parseInt(splitInput[1]) - 1;
                 Task task = tasks[taskNumber];
                 task.markAsDone();

                 StringBuilder sb = new StringBuilder("marked: ");
                 sb.append("[");
                 sb.append(task.getStatusIcon());
                 sb.append("] ");
                 sb.append(task);
                 System.out.println(sb);
            } else {
                tasks[tasksCount] = new Task(input);
                tasksCount++;
                System.out.println("added: " + input);
            }
        }
        sc.close();
    }
}