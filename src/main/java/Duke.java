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
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";

        System.out.println("Hello from\n" + logo + "What can I do for you?\n");
        Scanner sc = new Scanner(System.in);
        String str = sc.nextLine();

        while(true)
        {
            if(!str.equals("bye"))
            {
                 System.out.println(answer(str));
                 str = sc.nextLine();
            }
            else
            {
                System.out.println("_______________________________________________________" +
                        "\n" + "Bye. Hope to see you again soon!" + "\n" +
                        "_______________________________________________________");
                System.exit(1);
            }
        }
    }
}
