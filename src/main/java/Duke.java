import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        /*
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
         */
        System.out.println("Hello! I'm Duke\nWhat can I do for you?");
        String[] store = new String[100];
        int count = 0;
        while (true) {
            Scanner sc = new Scanner(System.in);
            String s = sc.nextLine();
            if (s.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                break;
            } else if (s.equals("list")) {
                for (int a = 0; a < count; a++) {
                    System.out.println((a + 1) + ". " + store[a]);
                }
            } else {
                store[count] = s;
                count++;
                System.out.println("added: " + s);
            }
        }
    }
}
