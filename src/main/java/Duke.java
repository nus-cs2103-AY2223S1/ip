import java.util.*;

public class Duke {
    private static List<Task> list = new ArrayList<>(100);
    public static void main(String[] args) {
        System.out.println(Duke.start());
        Scanner sc = new Scanner(System.in);

        while (sc.hasNext()) {
            String input = sc.nextLine();
            if (input.equals("list")) {
                System.out.println(Duke.list());
            } else if (input.equals("bye")) {
                System.out.println(Duke.bye());
                sc.close();
                break;
            } else if (input.startsWith("mark") && input.length() >= 6) {
                System.out.print(Duke.mark(input));
            } else if (input.startsWith("unmark") && input.length() >= 8) {
                System.out.print(Duke.unmark(input));
            } else {
                System.out.println(Duke.addTask(input));
            }
        }
    }

    private static String list() {
        String[] lines = new String[list.size() + 1];
        lines[0] = "Here are the tasks in your list: ";
        for (int i = 1; i < list.size() + 1; i++) {
            lines[i] = list.get(i - 1).toStringWithIndex(i);
        }
        return Duke.replyFormat(lines);
    }

    private static String bye() {
        return Duke.replyFormat("Bye.", ">:)");
    }

    private static String start() {
        return Duke.replyFormat("Oi! I'm Dook", "What's up?");
    }

    private static String mark(String input) {
        int toMark = Character.getNumericValue(input.charAt(5));
        Task task = list.get(toMark - 1);
        task.mark();
        return Duke.replyFormat("Nice! I've marked this task as done:",
                "  " + task.toString());
    }

    private static String unmark(String input) {
        int toMark = Character.getNumericValue(input.charAt(7));
        Task task = list.get(toMark - 1);
        task.unmark();
        return Duke.replyFormat("OK, I've marked this task as not done yet:",
                "  " + task.toString());
    }

    private static String addTask(String input) {
        Task task;
        if (input.startsWith("todo")) {
            task = new ToDo(input.substring(5));
        } else {
            int slash = input.indexOf('/');
            String time = input.substring(slash + 4);
            if (input.startsWith("event")) {
                task = new Event(input.substring(6, slash - 1), time);
            } else if (input.startsWith("deadline")) {
                task = new Deadline(input.substring(9, slash - 1), time);
            } else {
                task = new Task(input);
            }
        }
        list.add(task);
        return Duke.replyFormat("Got it. I've added this task:",
                "   " + task.toString(),
                String.format("Now you have %d tasks in the list.", list.size()));
    }

    private static String replyFormat(String... lines) {
        String separator = "     ____________________________________________________________\n";
        String indent = "     ";
        StringBuilder res = new StringBuilder(separator);
        for (String line : lines) {
            res.append(indent).append(line).append("\n");
        }
        res.append(separator);
        return res.toString();
    }

}
