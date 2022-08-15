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
    private static void display(ArrayList<String> ls) {
        for (int i = 1; i <= ls.size(); i++) {
            System.out.println(i + ". " + ls.get(i - 1));
        }
    }
    public static void main(String[] args) {
        System.out.println(line);
        System.out.println(greetings);
        System.out.println(line);
        Scanner sc = new Scanner(System.in);
        String exit_command = "bye";
        String list_command = "list";
        boolean flag = true;
        while (flag) {
            String response = sc.nextLine();
            System.out.println(line);
            if (response.toLowerCase().equals(exit_command)) {
                flag = false;
                System.out.println(exit_message);
            } else if (response.toLowerCase().equals(list_command)) {
                display(ls);
            }else {
                addToList(response);
            }
            System.out.println(line);
        }
    }
}
