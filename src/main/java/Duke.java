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
        String[] arr = new String[100];
        int index = 0;
        while (!input.equals("bye")) {
            Scanner scan = new Scanner(System.in);
            input = scan.nextLine();
            if (input.equals("bye")) {
                System.out.println(line +
                        "Bye. Hope to see you again soon!\n" +
                        line);
            } else if (input.equals("list")) {
                String list = line;
                for (int i = 0; i < index; i++) {
                    list += i + 1;
                    list += ". ";
                    list += arr[i];
                    list += "\n";
                }
                System.out.println(list + line);
            } else {
                arr[index] = input;
                index ++;
                System.out.println(line + "added: " + input + "\n" + line);
            }
        }
    }
}
