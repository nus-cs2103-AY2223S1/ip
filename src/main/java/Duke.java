import java.util.Scanner;
import java.util.ArrayList;
public class Duke {
    private static final String greetings = "Hello! I'm Duke\n" + "What can I do for you?";
    private static final String exit_message = "Bye. Hope to see you again soon!";
    private static final String line = "______________________________________________________________________________";
    private static final ArrayList<String> ls = new ArrayList(100);
    private static void addToList(String msg) {
        String modified = "added: " + msg;
        ls.add(msg);
        System.out.println(modified);
    }
    public static void main(String[] args) {
        System.out.println(line);
        System.out.println(greetings);
        System.out.println(line);
        Scanner sc = new Scanner(System.in);
        String exit_command = "bye";
        boolean flag = true;
        while (flag) {
            String response = sc.nextLine();
            System.out.println(line);
            if (response.toLowerCase().equals(exit_command)) {
                flag = false;
                System.out.println(exit_message);
            } else {
                addToList(response);
            }
            System.out.println(line);
        }
    }
}
