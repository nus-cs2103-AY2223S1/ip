import java.util.Scanner;
public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        // prompt user
        System.out.println("Where would you like to go next?");

        Scanner in = new Scanner(System.in);
        while(true) {
            String s = in.nextLine();
            if (s.equals("bye")) {
                //exit
                System.out.println("Thank you for swinging by :)");
                System.exit(0);
            } else {
                //echo request
                System.out.println(s);
            }

        }


    }
}
