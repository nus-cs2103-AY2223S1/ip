import java.util.*;

public class Duke {

    public static void main(String[] args) {
        List<Task> list = new ArrayList<>(100);

        System.out.println(Duke.start());
        Scanner sc = new Scanner(System.in);

        while (true) {
            String command = sc.nextLine();
            if (command.equals("list")) {
                System.out.println(Duke.list(list.toArray(new Task[0])));
            } else if (command.equals("bye")) {
                System.out.println(Duke.bye());
                break;
            } else {
                if (command.length() == 6) {
                    if (command.substring(0, 4).equals("mark")) {
                        int toMark = Character.getNumericValue(command.charAt(5));
                        System.out.print(Duke.mark(list.get(toMark - 1)));
                    }
                } else if (command.length() == 8) {
                    if (command.substring(0, 6).equals("unmark")) {
                        int toMark = Character.getNumericValue(command.charAt(7));
                        System.out.print(Duke.unmark(list.get(toMark - 1)));
                    }
                } else {
                    list.add(new Task(list.size() + 1, command));
                    System.out.println(Duke.replyFormat("added: " + command));
                }
            }
        }
    }

    private static String replyFormat(String... messages) {
        String line = "     ____________________________________________________________\n";
        String indent = "     ";
        StringBuilder res = new StringBuilder(line);
        for (String message : messages) {
            res.append(indent).append(message).append("\n");
        }
        return res + line;
    }

    private static String list(Task... tasks) {
        String[] messages = new String[tasks.length + 1];
        messages[0] = "Here are the tasks in your list: ";
        for (int i = 1; i < tasks.length + 1; i++) {
            messages[i] = tasks[i - 1].toStringWithIndex();
        }
        return Duke.replyFormat(messages);
    }

    private static String bye() {
        return Duke.replyFormat("Bye.", ">:)");
    }

    private static String start() {
        return Duke.replyFormat("Oi! I'm Dook", "What's up?");
    }

    private static String mark(Task task) {
        task.mark();
        return Duke.replyFormat("Nice! I've marked this task as done:", task.toString());
    }

    private static String unmark(Task task) {
        task.unmark();
        return Duke.replyFormat("OK, I've marked this task as not done yet:", task.toString());
    }

}
