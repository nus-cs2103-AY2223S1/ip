import java.util.Scanner;

public class Duke {

    public static void main(String[] args) {
        boolean bye = false;
        Scanner sc = new Scanner(System.in);
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("HELLO!");

        while (!bye) {
            String input = sc.nextLine();
            if (input.equals("bye")) {
                bye = true;
                System.out.println("Bye Bye!");
            } else {
                System.out.println(input);
            }
        }

    }
}
