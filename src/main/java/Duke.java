import java.util.Scanner;
public class Duke {
    private static String lb(String msg) {
        String line = "____________________________________________________________________________________\n";
        return line + msg + "\n" + line;
    }
    private static final String greetings = "Hello! I'm Duke\n" + "What can I do for you?";
    private static final String exit_message = "Bye. Hope to see you again soon!";
    public static void main(String[] args) {
        System.out.println(lb(greetings));
        Scanner sc = new Scanner(System.in);
        String exit_command = "bye";
        boolean flag = true;
        while (flag) {
            String response = sc.nextLine();
            if (response.toLowerCase().equals(exit_command)) {
                flag = false;
                System.out.println(lb(exit_message));
            } else {
                System.out.println(lb(response));
            }
        }
    }
}
