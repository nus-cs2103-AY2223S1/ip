import java.util.*;

public class Duke {
    public static void main(String[] args) {

        String start = Duke.replyFormat("Oi! I'm Dook", "What you want?");
        String end = Duke.replyFormat("Bye.", ">:)");

        System.out.println(start);
        Scanner sc = new Scanner(System.in);

        while (true) {
            String command = sc.nextLine();
            if (command.equals("bye")) {
                System.out.println(end);
                break;
            }
            System.out.println(Duke.replyFormat(command));
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
