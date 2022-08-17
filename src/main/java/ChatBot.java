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
            String userInput = controller.inputFromUser();
            if (userInput.length() == 0) {
                continue;
            }
            boolean isList = controller.checkList(userInput);
            boolean isBye = controller.checkBye(userInput);
            boolean isMark = controller.checkMark(userInput);
            boolean isUnmark = controller.checkUnmark(userInput);
            if (!isBye && !isList && !isMark && !isUnmark) {
                controller.addToList(userInput);
            }
            String reply = controller.replyToUser(userInput, isBye, isList, isMark, isUnmark);
            if (isBye) {
                controller.sayBye();
                break;
            } else if (isMark) {
                int taskIndex = Integer.parseInt(reply) - 1;
                controller.changeTaskStatus(taskIndex, true);
                reply = controller.getTask(taskIndex).toString();
            } else if (isUnmark) {
                int taskIndex = Integer.parseInt(reply);
                controller.changeTaskStatus(taskIndex, false);
                reply = controller.getTask(taskIndex).toString();
            }
            controller.display(reply, isList, isMark, isUnmark);
        }
    }

}
