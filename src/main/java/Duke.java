import java.io.FileNotFoundException;
import java.util.Scanner;

public class Duke {

    public static void handleInput(String userCommand, String userAction, Tasks tasks) {
        try {
            if (userCommand.equals("list")) {
                tasks.getList();
            } else if (userCommand.equals("mark")) {
                tasks.markMessage(userAction);
            } else if (userCommand.equals("unmark")) {
                tasks.unmarkMessage(userAction);
            } else if (userCommand.equals("todo")) {
                tasks.todoMessage(userAction);
            } else if (userCommand.equals("event")) {
                tasks.eventMessage(userAction);
            } else if (userCommand.equals("deadline")) {
                tasks.deadlineMessage(userAction);
            } else if (userCommand.equals("delete")) {
                tasks.deleteMessage(userAction);
            } else {
                throw new DukeException("I'm sorry, but I don't know what that means :-(");
            }
        } catch (DukeException e) {
            DukeMessage.sendMessage(e.getMessage());
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        DukeMessage.startMessage();
        Tasks tasks = new Tasks(100);

        while (scanner.hasNextLine()) {
            String userCommand = scanner.next();
            String userAction = scanner.nextLine().stripLeading();
            if (userCommand.equals("bye")) {
                DukeMessage.endMessage();
                scanner.close();
                System.exit(0);
            }
            handleInput(userCommand, userAction, tasks);
        }
    }
}