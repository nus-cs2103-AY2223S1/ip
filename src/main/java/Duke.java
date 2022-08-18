import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    private static final String LINE = "\n----------------------------------------------------------------\n";
    public static void main(String[] args) {
        ArrayList<Task> taskList = new ArrayList<Task>(100);
        System.out.println(LINE + "Hello! I'm Duke\n" + "What can I do for you?" + LINE + "\n");
        Scanner command = new Scanner(System.in);
        while (true) {
            String temp = command.nextLine();
            String input = temp.split(" ")[0];

            if (input.equals("bye")) {
                System.out.println(LINE + "Bye. Hope to see you again!" + LINE);
                command.close();
                break;
            }
            else if (input.equals("list")) {
                System.out.println("Here are the tasks in your list:\n");
                for (int j = 0; j < taskList.size(); j++) {
                    System.out.println("" + String.valueOf(j + 1) + ". " + taskList.get(j));
                }
            }

            else if (input.equals("mark")) {
                Integer taskNo = Integer.parseInt(temp.split(" ")[1]) - 1;
                taskList.get(taskNo).markAsDone();
                System.out.println(LINE+ "Nice! I've marked this task as done:\n"
                        + taskList.get(taskNo) + LINE);
            }

            else if (input.equals("unmark")) {
                Integer taskNo = Integer.parseInt(temp.split(" ")[1]) - 1;
                taskList.get(taskNo).markAsUndone();
                System.out.println(LINE + "OK, I've marked this task as not done yet:\n"
                        + taskList.get(taskNo) + LINE);
            }

            else {
                System.out.println(LINE + "added: " + temp + LINE);
                taskList.add(new Task(input));
            }

        }
    }
}
