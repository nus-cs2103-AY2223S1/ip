import java.util.ArrayList;
import java.util.Scanner;
import java.util.Collections;

/**
 *  Duke Class
 *  The main class
 *
 * @author Kang Qiao
 */

public class Duke {

    private static ArrayList<String> inputs = new ArrayList<String>();



    private static String answer(String msg) {
        return "_______________________________________________________" +
                "\n" + msg + "\n" +
                "_______________________________________________________";
    }

    private static String addition(String msg) {
        return "_______________________________________________________" +
                "\n" + "added: " + msg + "\n" +
                "_______________________________________________________";
    }

    private static String all() {
        String userInputs = "";
        for (int i = 0; i < inputs.size(); i++)
        {
            int index = i + 1;
            userInputs += "\n" + index + ". " + inputs.get(i) + "\n";

        }
        return userInputs;
    }

    public static void main(String[] args) {

        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";

        System.out.println("Hello I'm\n" + logo + "What can I do for you?\n");
        Scanner sc = new Scanner(System.in);
        String str = sc.nextLine();


        while(!str.equals("bye"))
        {
            if (str.equals("list"))
            {
                System.out.println("_______________________________________________________" +
                        "\n" + all() + "\n" +
                        "_______________________________________________________");
            }
            else
            {
                inputs.add(str);
                System.out.println(addition(str));
            }
            str = sc.nextLine();
        }
        System.out.println("_______________________________________________________" +
                "\n" + "Bye. Hope to see you again soon!");
    }
}
