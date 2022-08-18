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

        String[] arr = new String[100];
        int count = 0;
        System.out.println("Hello! I'm Duke\nWhat can I do for you?");

        Scanner sc = new Scanner(System.in);

        while (true) {
            String item = sc.nextLine();
            if (item.equals("bye")) {
                break;
            } else if (item.equals("list")) {
                for (int i =0; i < count; i++) {
                    String str = String.format("%d. " + arr[i], i+1);
                    System.out.println(str);
                }
            } else {
                arr[count] = item;
                count++;
                System.out.println("Added: " + item);
            }
        }

        System.out.println("Bye. Hope to see you again soon!");

    }
}
