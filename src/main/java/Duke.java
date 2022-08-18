import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    private static final String LINE = "\n----------------------------------------------------------------\n";

    public static void run(String command, ArrayList<Task> taskList) throws DukeException {
        String input = command.split(" ")[0];

        if (input.equals("list")) {
            System.out.println("Here are the tasks in your list:\n");
            for (int j = 0; j < taskList.size(); j++) {
                System.out.println("" + String.valueOf(j + 1) + ". " + taskList.get(j));
            }
        }

        else if (input.equals("mark")) {
            Integer taskNo = Integer.parseInt(command.split(" ")[1]) - 1;
            taskList.get(taskNo).markAsDone();
            System.out.println(LINE+ "Nice! I've marked this task as done:\n"
                    + taskList.get(taskNo) + LINE);
        }

        else if (input.equals("unmark")) {
            Integer taskNo = Integer.parseInt(command.split(" ")[1]) - 1;
            taskList.get(taskNo).markAsUndone();
            System.out.println(LINE + "OK, I've marked this task as not done yet:\n"
                    + taskList.get(taskNo) + LINE);
        }

        else if (input.equals("delete")) {
            Integer taskNo = Integer.parseInt(command.split(" ")[1]) - 1;
            System.out.println(LINE + "Noted. I've removed this task:\n" + taskList.get(taskNo));
            int i = taskNo;
            taskList.remove(i);
            System.out.println("\nNow you have " + String.valueOf(taskList.size()) + " tasks in the list." + LINE);

        }

        else if (input.equals("todo")) {

            StringBuilder description = new StringBuilder();
            String[] words = command.split("\\s");
            if (words.length <= 1) {
                throw new DukeException("The description of a todo cannot be empty");
            }
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
            String[] words = command.split(" /by", 2);
            Task deadline = new Deadlines(words[0], words[1]);
            taskList.add(deadline);
            System.out.println(LINE + "Got it. I've added this task:\n" + deadline + "\nNow you have " +
                    String.valueOf(taskList.size()) + " tasks in the list." + LINE);
        }

        else if (input.equals("event")) {
            String[] words = command.split(" /at", 2);
            Task event = new Event(words[0], words[1]);
            taskList.add(event);
            System.out.println(LINE + "Got it. I've added this task:\n" + event + "\nNow you have " +
                    String.valueOf(taskList.size()) + " tasks in the list." + LINE);
        }

        else {
            throw new DukeException("I'm sorry, but I don't know what that means :-(");
        }

    }

    public static void main(String[] args) {

        System.out.println(LINE + "Hello! I'm Duke\n" + "What can I do for you?" + LINE + "\n");
        Scanner command = new Scanner(System.in);
        ArrayList<Task> taskList = new ArrayList<Task>(100);
        while (true) {
            String temp = command.nextLine();
            try {
                if (temp.equals("bye")) {
                    System.out.println(LINE + "Bye. Hope to see you again!" + LINE);
                    command.close();
                    break;
                }
                run(temp, taskList);
            }
            catch (DukeException err) {
                command.close();
                System.out.println(err);
                break;
            }

        }
    }
}
