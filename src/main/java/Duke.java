import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    private static final ArrayList<Task> data = new ArrayList<>();

    // lists all the tasks
    public static String listData() {
        StringBuilder output = new StringBuilder();
        for (int i = 0; i < data.size(); i++) {
            Task task = data.get(i);
            output.append(i+1).append(". ").append(task).append("\n");
        }
        return output.substring(0, output.length()-1);
    }

    // marks task as complete or not complete
    public static String markTask(String input, boolean isComplete) {
        int target_index = Integer.parseInt(input.substring(isComplete? 4 : 6).trim()) - 1;
        Task task = data.get(target_index);
        if (isComplete) task.markDone();
        else task.markNotDone();
        return "Nice! I've marked this task as done:\n" + task;
    }

    public static String reply(String input) {
        // shows user list of all saved tasks
        if (input.equals("list")) return listData();
        // mark task as complete
        if (input.startsWith("mark")) return markTask(input, true);
        // mark task as not done
        if (input.startsWith("unmark")) return markTask(input, false);
        // adding task to data
        data.add(new Task(input));
        return "added: " + input;
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
                return;
            } else System.out.println(reply(input));
            System.out.println("____________________________________________________________");
        }
    }
}
