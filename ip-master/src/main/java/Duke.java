import java.util.Scanner;
/*
Duke IP for CS2103T by Yuvaraj Kumaresan
 */
public class Duke {
    /*
    Method echo
    Description: Asks for user input using the scanner utility,
                 Redisplay input if input is not bye,
                 if input is bye exit message is displayed and program exits.
     */
    public static void echo() {
        Scanner input = new Scanner(System.in);
        String text = input.nextLine();
        if (text.equalsIgnoreCase("bye")) {
            System.out.println("Bye. Hope to see you again soon!\n");
        } else {
            System.out.println(text + "\n");
            echo();
        }
    }

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello I am \n" + logo + "\n What can I do for you?\n");
        echo();
    }
}

