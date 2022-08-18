import java.util.Scanner;
import java.lang.Number;

public class Duke {
    public static void main(String[] args) {
        System.out.println("Hello! I'm Duke\n" + "What can I do for you?");
        Scanner sc= new Scanner(System.in);
        String[] strArray = new String[100];
        char[] doneArray = new char[100];
        int strCount = 0;

        while (true) {
            String str = sc.nextLine();
            if (str.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                System.exit(0);

            } else if (str.equals("list")) {
                System.out.printf("Here are the tasks in your list\n");
                for (int i = 0; i < strCount; i++) {
                    System.out.printf("%d.[%c] %s\n", i+1, doneArray[i], strArray[i]);
                }

            } else if (str.startsWith("mark")) {
                String[] word = str.split(" ");
                int taskNo = Integer.parseInt(word[1]);
                doneArray[taskNo] = 'X';
                System.out.printf("Nice! I've marked this task as done: \n" +
                                    "  [X] %s", strArray[taskNo]);

            } else if (str.startsWith("unmark")) {
                String[] word = str.split(" ");
                int taskNo = Integer.parseInt(word[1]);
                doneArray[taskNo] = ' ';
                System.out.printf("OK, I've marked this task as not done yet: \n" +
                                    "  [ ] %s", strArray[taskNo]);
            } else {
                strArray[strCount] = str;
                doneArray[strCount] = ' ';
                strCount++;
                System.out.println("added: " + str);
            }
        }

    }
}
