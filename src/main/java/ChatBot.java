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
            int command = controller.userCommand();
            switch (command) {
                case 1:
                    System.out.println("Please type in your ToDo task:");
                    String todo = controller.inputFromUser();
                    Task todoTask = controller.addToList(todo, null, "todo");
                    controller.display(todoTask.toString(), false, false, false);
                    break;

                case 2:
                    System.out.println("Please type in your Event:");
                    String event = controller.inputFromUser();
                    System.out.println("Please type in date:");
                    String eventTime = controller.inputFromUser();
                    Task eventTask = controller.addToList(event, eventTime, "event");
                    controller.display(eventTask.toString(), false, false, false);
                    break;

                case 3:
                    System.out.println("Please type in your Deadline task:");
                    String ddl = controller.inputFromUser();
                    System.out.println("Please type in deadline:");
                    String ddlTime = controller.inputFromUser();
                    Task ddlTask = controller.addToList(ddl, ddlTime, "deadline");
                    controller.display(ddlTask.toString(), false, false, false);
                    break;

                case 4:
                    controller.display(null, true, false, false);
                    break;

                case 5:
                    System.out.println("Please type in the task index that you want to mark:");
                    int taskIndexToMark = controller.userTask() - 1;
                    controller.changeTaskStatus(taskIndexToMark, true);
                    String replyMark = controller.getTask(taskIndexToMark).toString();
                    controller.display(replyMark, false, true, false);
                    break;

                case 6:
                    System.out.println("Please type in the task index that you want to unmark:");
                    int taskIndexToUnmark = controller.userTask() - 1;
                    controller.changeTaskStatus(taskIndexToUnmark, false);
                    String replyUnmark = controller.getTask(taskIndexToUnmark).toString();
                    controller.display(replyUnmark, false, false, true);
                    break;

                case 7:
                    controller.sayBye();
                    return;
            }

            controller.showCommandList();
        }
    }

}
