import java.util.*;
public class Duke {
    public static void main(String[] args) {
//        String logo = " ____        _        \n"
//                + "|  _ \\ _   _| | _____ \n"
//                + "| | | | | | | |/ / _ \\\n"
//                + "| |_| | |_| |   <  __/\n"
//                + "|____/ \\__,_|_|\\_\\___|\n";
//        System.out.println("Hello from\n" + logo);
        // create scanner to receive user input
        Scanner sc = new Scanner(System.in);
        System.out.println("Hello! I'm Kuke\n" +
                "What can I do for you?");
        String a = sc.next();
        // if input received is anything but "bye" system will output what the user
        // inputted

        String[] arr = new String[100];
        int pos = 0;
        while (!a.equals("bye")) {
            if (a.equals("list")) {
                for (int i = 1; i <= pos; i++) {
                    System.out.println(i + ". " + arr[i - 1]);
                }
                a = sc.next();
            } else {
                arr[pos] = a;
                pos++;
                System.out.println("added: " + a);
                a = sc.next();
            }
        }
        System.out.println("Bye. Hope to see you again soon!");
    }

}

