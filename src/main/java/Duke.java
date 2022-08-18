import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        /*
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);*/

        Scanner sc = new Scanner(System.in);
        System.out.println("-----------------------------\n" +
                "Hello! I'm Duke\n" +
                "What can I do for you\n" +
                "-----------------------------\n");

        while (true) {
            String answer = sc.nextLine();
            if (!answer.equals("bye")) {
                System.out.println("-----------------------------\n" + answer + "\n" +
                        "-----------------------------\n");
            } else {
                System.out.println("-----------------------------\n" + "Bye. Hope to see you again soon!" + "\n" +
                        "-----------------------------\n");
                break;
            }
        }
    }
}
