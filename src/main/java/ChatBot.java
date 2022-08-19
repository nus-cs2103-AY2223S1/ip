import java.util.*;

/**
 * This class encapsulates the behaviors of a chatbot
 */
public class ChatBot {
    private final ChatBotController controller = new ChatBotController();

    /**
     * The main control flow of the chatbot's behaviour.
     */
    public void launch() {
        controller.startGreeting();

        while (true) {
            try {
                int command = controller.userCommand();
                switch (command) {
                    case 1:
                        System.out.println("Please type in your ToDo task:");
                        String todo = controller.inputFromUser();
                        if (!controller.checkContent(todo)) {
                            throw new EmptyContentException("ERROR: No empty task is allowed! Please try again.");
                        }
                        Task todoTask = controller.addToList(todo, null, "todo");
                        controller.display(todoTask.toString(), false, false, false, false);
                        break;

                    case 2:
                        System.out.println("Please type in your Event:");
                        String event = controller.inputFromUser();
                        if (!controller.checkContent(event)) {
                            throw new EmptyContentException("ERROR: The content of your task is empty! ");
                        }
                        System.out.println("Please type in the time (D/M/Y H:M:S):");
                        String eventTime = controller.inputFromUser();
                        if (!controller.checkTime(eventTime)) {
                            throw new InvalidTimeException("ERROR: The format of time you input is invalid!");
                        }
                        Task eventTask = controller.addToList(event, eventTime, "event");
                        controller.display(eventTask.toString(), false, false, false, false);
                        break;

                    case 3:
                        System.out.println("Please type in your Deadline task:");
                        String ddl = controller.inputFromUser();
                        if (!controller.checkContent(ddl)) {
                            throw new EmptyContentException("ERROR: The content of your task is empty!");
                        }
                        System.out.println("Please type in deadline (D/M/Y H:M:S):");
                        String ddlTime = controller.inputFromUser();
                        if (!controller.checkTime(ddlTime)) {
                            throw new InvalidTimeException("ERROR: The format of time you input is invalid!");
                        }
                        Task ddlTask = controller.addToList(ddl, ddlTime, "deadline");
                        controller.display(ddlTask.toString(), false, false, false, false);
                        break;

                    case 4:
                        controller.display(null, true, false, false, false);
                        break;

                    case 5:
                        System.out.println("Please type in the task index that you want to mark:");
                        int taskIndexToMark = controller.userTask() - 1;
                        controller.changeTaskStatus(taskIndexToMark, true);
                        String replyMark = controller.getTask(taskIndexToMark).toString();
                        controller.display(replyMark, false, true, false, false);
                        break;

                    case 6:
                        System.out.println("Please type in the task index that you want to unmark:");
                        int taskIndexToUnmark = controller.userTask() - 1;
                        controller.changeTaskStatus(taskIndexToUnmark, false);
                        String replyUnmark = controller.getTask(taskIndexToUnmark).toString();
                        controller.display(replyUnmark, false, false, true, false);
                        break;

                    case 7:
                        System.out.println("Please type in the task index that you want to delete:");
                        int taskIndexToDelete = controller.userTask() - 1;
                        controller.deleteFromList(taskIndexToDelete);
                        controller.display(null, false, false, false, true);
                        break;
                    case 8:
                        controller.sayBye();
                        System.exit(0);
                }
            } catch (DukeExceptions e) {
                System.out.println("#".repeat(80));
                System.out.println(e.getMessage());
                System.out.println("Please try again");
                System.out.println("#".repeat(80));
            } finally {
                controller.showCommandList();
            }
        }
    }

}
