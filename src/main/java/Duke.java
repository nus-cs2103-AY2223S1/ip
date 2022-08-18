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

            else if (input.equals("todo")) {

                StringBuilder description = new StringBuilder();
                String[] words = temp.split("\\s");
                for (int i = 1; i < words.length; i++) {
                    description.append(words[i]);
                    if (i != words.length - 1) {
                        description.append(" ");
                    }
                }
                Task todo = new Todos(description.toString());
                taskList.add(todo);
                System.out.println(LINE + "Got it. I've added this task:\n" + todo + "\nNow you have " +
                        String.valueOf(taskList.size()) + " tasks in the list." + LINE);
            }

            else if (input.equals("deadline")) {
                String[] words = temp.split(" /by", 2);
                Task deadline = new Deadlines(words[0], words[1]);
                taskList.add(deadline);
                System.out.println(LINE + "Got it. I've added this task:\n" + deadline + "\nNow you have " +
                        String.valueOf(taskList.size()) + " tasks in the list." + LINE);
            }

            else if (input.equals("event")) {
                String[] words = temp.split(" /at", 2);
                Task event = new Event(words[0], words[1]);
                taskList.add(event);
                System.out.println(LINE + "Got it. I've added this task:\n" + event + "\nNow you have " +
                        String.valueOf(taskList.size()) + " tasks in the list." + LINE);
            }
            else {
                System.out.println(LINE + "added: " + temp + LINE);
                taskList.add(new Task(input));
            }

        }
    }
}
