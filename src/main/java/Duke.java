import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
//        String logo = " ____        _        \n"
//                + "|  _ \\ _   _| | _____ \n"
//                + "| | | | | | | |/ / _ \\\n"
//                + "| |_| | |_| |   <  __/\n"
//                + "|____/ \\__,_|_|\\_\\___|\n";
//        System.out.println("Hello from\n" + logo);
        System.out.println("Hello! I'm Duke\nWhat can I do for you?");

        Scanner sc = new Scanner(System.in);
        String[] storedItems = new String[100];
        int index = 0;
        while (true) {
            String str = sc.nextLine();
            if ("bye".equals(str)) {
                System.out.println("Bye! Hope to see you again soon");
                break;
            } else if ("list".equals(str)) {
                int p = 0;
                while (p < 100 && storedItems[p] != null) {
                    System.out.printf("%d. %s\n", p + 1, storedItems[p++]);
                }
            } else {
                storedItems[index++] = str;
                System.out.println("Added: " + str);
            }
        }
    }
}
