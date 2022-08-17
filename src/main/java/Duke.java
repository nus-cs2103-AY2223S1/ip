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

    private static ArrayList<Task> inputs = new ArrayList<Task>();



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
//inputs contain all the tasks
    private static String all() {
        String userInputs = "";
        for (int i = 0; i < inputs.size(); i++)
        {
            int index = i + 1;
            Task tempTask = inputs.get(i);
            userInputs += "\n" + index + "." + "[" + tempTask.getStatusIcon() +"] "
                    + tempTask.getContent() + "\n";

        }
        return userInputs;
    }

    private static String markDone(String str) {
        return "_______________________________________________________" + "\n" +
                "Nice! I've marked this task as done:" +
                "\n" + "[X] " + str + "\n" +
                "\n" + "_______________________________________________________";
    }

    private static String markUndone(String str) {
        return "_______________________________________________________" + "\n" +
                "Wow! I've marked this task as not done yet:" +
                "\n" + "[ ] " + str + "\n" +
                "\n" + "_______________________________________________________";
    }

    public static void main(String[] args) {

        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  /\n"
                + "|____/ \\,_|_|\\_\\___|\n";

        System.out.println("Hello I'm\n" + logo + "What can I do for you?\n");
        Scanner sc = new Scanner(System.in);
        String str = sc.nextLine();





        while(!str.equals("bye"))
        {
            //for listing
            if (str.equals("list"))
            {
                System.out.println("Here are all the tasks in your list\n");
                System.out.println("_______________________________________________________" +
                        "\n" + all() + "\n" +
                        "_______________________________________________________");
            }
            else
            {
                String mark = str.split(" ")[0];
                if (mark.equals("mark"))
                {
                    //this is as index is greater than array index by 1
                    int index = Integer.valueOf(str.split(" ")[1]) - 1;
                    inputs.get(index).setDone();
                    System.out.println(markDone(inputs.get(index).getContent()));
                }
                else if ((mark.equals("unmark")))
                {
                    int index = Integer.valueOf(str.split(" ")[1]) - 1;
                    inputs.get(index).setUndone();
                    System.out.println(markUndone(inputs.get(index).getContent()));
                }
                else
                {
                    Task task = new Task(str);
                    inputs.add(task);
                    System.out.println(addition(str));
                }
            }
            str = sc.nextLine();
        }
        System.out.println("_______________________________________________________" +
                "\n" + "Bye. Hope to see you again soon!");
    }
}