import java.util.Scanner;
public class Duke {
    public static void main(String[] args) {
        String logo = "_______     _\n" +
                   "|  ___|    | |\n" +
                   "|  |_  ____| |_____ ____  _  __\n" +
                   "|   _|/ _  \\ | ___|/  _ \\| |/  \\\n"+
                   "|  | | |_| | | |___| |_| |  / \\ |\n" +
                   "|__|  \\__|_|_|____|\\____/|_|  |_|\n";
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
