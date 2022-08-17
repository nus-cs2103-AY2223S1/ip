import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
//        String logo = " ____        _        \n"
//                + "|  _ \\ _   _| | _____ \n"
//                + "| | | | | | | |/ / _ \\\n"
//                + "| |_| | |_| |   <  __/\n"
//                + "|____/ \\__,_|_|\\_\\___|\n";
//        System.out.println("Hello from\n" + logo);
        System.out.println("Hello! I'm Edric \nWhat can I do for you?");
        Scanner sc = new Scanner(System.in);
        // while loop keeps grabbing input from user, unless user inputs bye to break the loop
        while (true) {
            String parrot = sc.nextLine();
            if (parrot.equals("bye")) {
                break;
            }
            System.out.println(parrot);
        }
        System.out.println("Bye. Hope to see you again soon!");
    }
}
