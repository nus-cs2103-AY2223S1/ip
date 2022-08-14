import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    private static final ArrayList<Task> data = new ArrayList<>();

    // lists all the tasks
    public static String listData() {
        if (data.size() == 0) return "Nothing here...";
        StringBuilder output = new StringBuilder();
        for (int i = 0; i < data.size(); i++) {
            Task task = data.get(i);
            output.append(i+1).append(". ").append(task).append("\n");
        }
        return output.substring(0, output.length()-1);
    }

    // marks task as complete or not complete
    public static String markTask(String input, boolean isComplete) throws DukeException{
        String res = input.substring(isComplete ? 4 : 6).trim();
        if (!res.matches("[0-9]+")) throw new DukeException("Input is not a number");
        int target_index = Integer.parseInt(res) - 1;
        if (target_index < 0 || target_index >= data.size()) throw new DukeException("Please input a correct number");
        Task task = data.get(target_index);
        if (isComplete) task.markDone();
        else task.markNotDone();
        return (isComplete ? "Nice! I've marked this task as done:\n" : "OK, I've marked this task as not done yet:\n")
                + task;
    }

    public static String reply(String input) throws DukeException{
        // shows user list of all saved tasks
        if (input.equals("list")) return listData();
        // mark task as complete
        if (input.startsWith("mark")) return markTask(input, true);
        // mark task as not done
        if (input.startsWith("unmark")) return markTask(input, false);
        // add todo
        if (input.startsWith("todo")) {
            String description = input.substring(4).trim();
            if (description.length() == 0) throw new DukeException("The description of a todo cannot be empty.");
            ToDo task = new ToDo(description);
            data.add(task);
            return "Got it. I've added this task:\n" + task + "\nNow you have " + data.size() + " tasks.";
        }
        // add event
        if (input.startsWith("event")) {
            String[] info = input.substring(5).split("/at");
            if (info.length != 2) throw new DukeException("Invalid Input!");
            String description = info[0].strip();
            String at = info[1].strip();
            if (description.length() == 0 || at.length() == 0)
                throw new DukeException("The description of event cannot be empty.");
            Event task = new Event(description, at);
            data.add(task);
            return "Got it. I've added this task:\n" + task + "\nNow you have " + data.size() + " tasks.";
        }
        // add deadline
        if (input.startsWith("deadline")) {
            String[] info = input.substring(8).split("/by");
            if (info.length != 2) throw new DukeException("Invalid Input!");
            String description = info[0].strip();
            String by = info[1].strip();
            if (description.length() == 0 || by.length() == 0)
                throw new DukeException("The description of deadline cannot be empty.");
            Deadline task = new Deadline(description, by);
            data.add(task);
            return "Got it. I've added this task:\n" + task + "\nNow you have " + data.size() + " tasks.";
        }
        else {
            throw new DukeException("I'm sorry, but I don't know what that means :-(");
        }
    }

    public static void main(String[] args) {
        System.out.println("Quack! I'm Duck\nWhat can I do for you?");
        Scanner sc = new Scanner(System.in);
        while (sc.hasNextLine()) {
            System.out.println("____________________________________________________________");
            System.out.println("Duck:");
            String input = sc.nextLine();
            if (input.equals("bye")) {
                System.out.println("Quack! Hope to see you again soon!");
                System.out.println("____________________________________________________________");
                return;
            } else {
                try {
                    String reply = reply(input);
                    System.out.println(reply);
                } catch (DukeException e) {
                    System.out.println(e);
                }
            }
            System.out.println("____________________________________________________________");
        }
    }
}
