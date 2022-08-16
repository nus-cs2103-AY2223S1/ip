import java.util.Scanner;

public class Duke {

    private static void greet () {
        String str = "Hello! I'm Duke\n" +
                "What can I do for you?\n";
        System.out.println(str);
    }

    private static void echo () {
        Scanner sc = new Scanner(System.in);
        String str = sc.nextLine();

        if (str.equals("bye")) {
            exit();
        } else {
            System.out.println(str);
            echo();
        }
    }

    private static void exit() {
        String str = "Bye. Hope to see you again soon!";
        System.out.println(str);
    }

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";

        greet();
        echo();
    }
}
