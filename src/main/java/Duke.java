import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        System.out.println(reply("Hello! I'm Duke. What can I do for you?"));
        Scanner userInput = new Scanner(System.in);
        while (true) {
            String userText = userInput.nextLine();
            if (userText.equals("bye")) {
                System.out.println(reply("Bye. Hope to see you again soon!"));
                break;
            }
            System.out.println(reply(userText));
        }
    }

    public static String reply(String s) {
        String horizontalLine = "____________________________________________________________\n";
        return horizontalLine + s +'\n' + horizontalLine;
    }
}
