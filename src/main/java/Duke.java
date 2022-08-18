import java.util.Scanner;

public class Duke {
    private static String reply(String message) {
        return "    ____________________________________________________________\n" +
                "     " + message + "\n" +
                "    ____________________________________________________________";
    }
    public static void main(String[] args) {
//        String logo = " ____        _        \n"
//                + "|  _ \\ _   _| | _____ \n"
//                + "| | | | | | | |/ / _ \\\n"
//                + "| |_| | |_| |   <  __/\n"
//                + "|____/ \\__,_|_|\\_\\___|\n";
//        System.out.println("Hello from\n" + logo);
        Scanner scan = new Scanner(System.in);
        System.out.println(reply("Hello! I'm Duke\n" +
                "     What can I do for you?"));
        boolean conversation = true;
        while (conversation) {
            String response = scan.nextLine().toString();
            if (response.equals("bye")) {
                System.out.println(reply("Bye. Hope to see you again soon!"));
                conversation = false;
            } else {
                System.out.println(reply(response));
            }
        }
    }
}
