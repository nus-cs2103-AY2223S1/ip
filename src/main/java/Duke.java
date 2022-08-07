import java.util.Scanner;
public class Duke {

    private static String[] dukeTasks = new String[100];
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);


        System.out.println("Hello! I'm Duke"); //Can use newline character
        System.out.println("What can I do for you?");

        Scanner sc = new Scanner(System.in);
        String input = "";
        while (!input.equals("bye")) {
            input = sc.next();
            System.out.println(input);
        }
        //At this point, input == "bye"
        //print exit stuff
        System.out.println("That's all? Hope to see you again soon!");

    }
}

