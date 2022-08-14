import java.sql.SQLOutput;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String line = "_______________________________\n";
        System.out.println(line +
                "Hello I'm Duke\n" +
                "What can I do for you?\n" +
                line
        );
        String input = "";
        while (!input.equals("bye")) {
            Scanner scan = new Scanner(System.in);
            input = scan.nextLine();
            if (input.equals("bye")) {
                System.out.println(line +
                        "Bye. Hope to see you again soon!\n" +
                        line);
            } else {
                System.out.println(line + input + "\n" + line);
            }
        }
    }
}
