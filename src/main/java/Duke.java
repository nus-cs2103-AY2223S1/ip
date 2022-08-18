import java.util.Scanner;
import java.util.ArrayList;

public class Duke {


    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("Hello! I'm Duke\n What can I do for you?");

        ArrayList<String> list = new ArrayList<>();

        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();


        if (input.equals("bye")) {
            System.out.println("Bye. Hope to see you again soon!");
        }

        else {
            list.add(input);
            input = sc.nextLine();
        }



    }
}
