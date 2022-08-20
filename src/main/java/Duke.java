import java.util.Scanner;

/**
 * Represents a Duke class
 *
 * @author Khor Jun Wei
 * @version CS2103T AY22/23 Sem 1
 */
public class Duke {

    /**
     * Represents a string representation of the greeting message.
     */
    private final static String greeting_message = "Hello! I'm Duke\n" +
            "     What can I do for you?";

    /**
     * Represents a string representation of the bye message.
     */
    private final static String bye_message = "Bye. Hope to see you again soon!";

    /**
     * Main method.
     *
     * @param args given arguments
     */
    public static void main(String[] args) {

        TaskManager manager = new TaskManager(100);

        Scanner scan = new Scanner(System.in);
        System.out.println(TaskManager.reply(greeting_message));
        boolean conversation = true;

        while (conversation) {
            String response = scan.nextLine();

            if (response.equals("bye")) {
                System.out.println(TaskManager.reply(bye_message));
                conversation = false;
            } else {
                try {
                    System.out.println(TaskManager.reply(manager.errorCheckerThanHandle(response)));

                } catch (DukeException e) {
                    System.out.println(TaskManager.reply(e.toString()));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
