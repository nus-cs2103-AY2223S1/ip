import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Jude {
    private static List<String> taskList = new ArrayList<>();

    /**
     * Runs the chatbot.
     *
     * @param args not used for now
     */
    public static void main(String[] args) {
        System.out.println("Hello! I'm Jude.");
        System.out.println("What can I do for you?");

        // Solution below adapted from
        // https://stackoverflow.com/questions/5287538/how-to-get-the-user-input-in-java
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println();
            System.out.print("> ");
            String str = sc.nextLine();
            if (str.equals("list")) {
                for (int i = 0; i < taskList.size(); i++) {
                    String task = taskList.get(i);
                    System.out.printf("%d. %s\n", i, task);
                }
            } else if (str.equals("bye")) {
                System.out.println("Goodbye! Have a nice day!");
                break;
            } else {
                System.out.println("added: " + str);
                taskList.add(str);
            }
        }
    }
}
