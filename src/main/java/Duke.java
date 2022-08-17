import java.util.Scanner;

public class Duke {
    private static final String line = "____________________________________________________________\n";

    public static void greeting() {
        String initMessage = "Hello! I'm Duke\n" + "What can I do for you?\n";
        System.out.println(line + initMessage + line);
    }

    public static void exit() {
        String goodbyeMessage = "Bye. Hope to see you again soon!\n";
        System.out.println(line + goodbyeMessage + line);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println(logo);
        greeting();

        while (true) {
            String input = sc.nextLine();
            if (input.equals("bye")) {
                exit();
                break;
            } else {
                System.out.println(line  + input + "\n" + line);
            }
        }
    }

}
