import java.util.Scanner;

public class Duke {


    public static void main(String[] args) {

        Scanner userCommand = new Scanner(System.in);
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        while (!userCommand.hasNext("bye")) {
            String text = userCommand.nextLine();
            System.out.println("--------------------------------------");
            System.out.println("    " + text);
            System.out.println("--------------------------------------");
        }

        System.out.println("--------------------------------------");
        System.out.println("   Bye. Hope to see you again soon!");
        System.out.println("--------------------------------------");




    }
}
