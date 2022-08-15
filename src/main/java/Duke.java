import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class Duke {
    private static final String LINE = "____________________________________________________________";
    private static final String GREETING = LINE + "\n" + "Hello! I am Duke\n" + "What can I do for you?\n" + LINE;
    private static final String GOODBYE = LINE + "\n" + "Bye. Hope to see you again soon!" + "\n" + LINE;
    private static final List<Task> taskList = new ArrayList<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println(GREETING);

        while (scanner.hasNext()) {
            String input = scanner.nextLine();
            String[] inputArr = input.split(" ", 2);
            String command = inputArr[0];
            switch (command) {
                case "bye":
                    scanner.close();
                    System.out.println(GOODBYE);
                    break;
                case "list":
                    System.out.println(LINE + "\n");
                    for (int i = 0; i < taskList.size(); i++) {
                        System.out.println((i + 1) + ". " + taskList.get(i) + "\n");
                    }
                    System.out.println(LINE + "\n");
                    break;
                case "mark": {
                    int index = Integer.parseInt(inputArr[1]) - 1;
                    if (index < 0 || index >= taskList.size()) {
                        System.out.println(LINE + "\n"
                                + "Invalid task!" + "\n"
                                + LINE + "\n");
                    } else {
                        Task task = taskList.get(index);
                        task.markDone();
                        System.out.println(LINE + "\n"
                                + "Nice! I've marked this task as done:" + "\n"
                                + task + "\n"
                                + LINE + "\n");
                    }
                    break;
                }
                case "unmark": {
                    int index = Integer.parseInt(inputArr[1]) - 1;
                    if (index < 0 || index >= taskList.size()) {
                        System.out.println(LINE + "\n"
                                + "Invalid task!" + "\n"
                                + LINE + "\n");
                    } else {
                        Task task = taskList.get(index);
                        task.markUndone();

                        System.out.println(LINE + "\n"
                                + "OK, I've marked this task as not done yet:" + "\n"
                                + task + "\n"
                                + LINE + "\n");
                    }
                    break;
                }
                default:
                    taskList.add(new Task(input));
                    System.out.println(LINE + "\n" + "added: " + input + "\n" + LINE);
                    break;
            }
        }
    }
}