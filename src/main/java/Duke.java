import java.util.InputMismatchException;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        System.out.println("Hello! I'm Duke\n" +
                "What can I do for you?");

        Scanner sc = new Scanner(System.in);
        while (true) {
            try {
                String str1 = sc.nextLine();
                if (str1.equals("bye")) {
                    System.out.println("Bye, Hope to see you again soon!");
                    break;
                } else {
                    System.out.println(str1);
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input");
            }
        }


//        String logo = " ____        _        \n"
//                + "|  _ \\ _   _| | _____ \n"
//                + "| | | | | | | |/ / _ \\\n"
//                + "| |_| | |_| |   <  __/\n"
//                + "|____/ \\__,_|_|\\_\\___|\n";
//        System.out.println("Hello from\n" + logo);
    }
}
