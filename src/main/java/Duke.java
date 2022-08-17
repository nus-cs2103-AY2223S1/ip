import java.util.*;

public class Duke {

    public static void main(String[] args) {
        List<String> list = new ArrayList<>(100);

        String start = Duke.replyFormat("Oi! I'm Dook", "What's up?");
        String end = Duke.replyFormat("Bye.", ">:)");

        System.out.println(start);
        Scanner sc = new Scanner(System.in);

        while (true) {
            String command = sc.nextLine();
            if (command.equals("list")) {
                System.out.println(Duke.replyFormat(list.toArray(new String[0])));
                continue;
            }
            if (command.equals("bye")) {
                System.out.println(end);
                break;
            }
            list.add(list.size() + 1 + ": " + command);
            System.out.println(Duke.replyFormat("added: " + command));
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


}
