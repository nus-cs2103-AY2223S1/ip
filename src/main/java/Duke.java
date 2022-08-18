import java.util.Scanner;

public class Duke {

    public static String breaker = "____________________________________________________________\n";

    public static void main(String[] args) {


        String start = "Hello! I'm Duke\nWhat can I do for you?";
        String end = "Bye. Hope to see you again soon!";
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        msg(start);

        String text = "";
        while (!"bye".equals(text)) {
            Scanner reader = new Scanner(System.in);  // Reading from System.in
            text = reader.nextLine();
            if ("bye".equals(text)) {
                msg(end);
                reader.close();
            } else {
                msg(text);
            }
        }
    }

    public static void msg(String s) {
        System.out.println(breaker + s + "\n" + breaker);
    }
}
