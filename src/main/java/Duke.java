import java.util.Scanner;

/**
 *  Duke Class
 *  The main class
 *
 * @author Kang Qiao
 */

public class Duke {

    public static String answer(String msg) {
        return "_______________________________________________________" +
                "\n" + msg + "\n" +
                "_______________________________________________________";
    }

    public static void main(String[] args) {

        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  /\n"
                + "|____/ \\,_|_|\\_\\___|\n";

        System.out.println("Hello from\n" + logo + "What can I do for you?\n");
        Scanner sc = new Scanner(System.in);
        String str = sc.nextLine();

        while(!str.equals("bye"))
        {
            System.out.println(answer(str));
            str = sc.nextLine();
        }
        System.out.println("_______________________________________________________" +
                "\n" + "Bye. Hope to see you again soon!");
    }
}