import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Duke {

    List<Task> taskList;

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        Duke duke = new Duke();
        duke.start();
    }

    public Duke() {
        taskList = new ArrayList<>();
    }

    public void start() {
        Scanner sc = new Scanner(System.in);
        System.out.println(
                "Hello! I'm Duke\n" +
                "What can I do for you?"
        );
        loop(sc);
    }

    public void loop(Scanner sc) {
        String s = sc.nextLine().trim();

        if ("bye".equals(s)) {
            System.out.println("Bye. Hope to see you again soon!");
            return;
        } else if ("list".equals(s)) {
            System.out.print(inputListToString());
        } else if (Pattern.matches("mark \\d+", s)) {
            int index = Integer.parseInt(s.split("\\s+")[1]) - 1;
            Task taskToMark = taskList.get(index);
            taskToMark.mark();
            System.out.println("Nice! I've marked this task as done:");
            System.out.println(taskToMark);
        } else if (Pattern.matches("unmark \\d+", s)) {
            int index = Integer.parseInt(s.split("\\s+")[1]) - 1;
            Task taskToUnmark = taskList.get(index);
            taskToUnmark.unmark();
            System.out.println("OK, I've marked this task as not done yet:");
            System.out.println(taskToUnmark);
        } else {
            taskList.add(new Task(s));
            System.out.println("Added: " + s);
        }

        loop(sc);
    }

    private String inputListToString() {
        StringBuilder sb = new StringBuilder();
        int i = 1;
        for (Task t : taskList) {
            sb.append(i);
            sb.append(". ");
            sb.append(t);
            sb.append("\n");
            ++i;
        }
        return sb.toString();
    }

}