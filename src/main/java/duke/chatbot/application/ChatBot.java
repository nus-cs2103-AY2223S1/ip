package duke.chatbot.application;

import duke.chatbot.command.Command;
import duke.chatbot.command.CommandResult;
import duke.chatbot.common.MessageConstants;
import duke.chatbot.data.exception.InvalidInputException;
import duke.chatbot.data.task.TaskList;
import duke.chatbot.storage.TaskFileManager;

import java.util.Scanner;


public class ChatBot {
    private final TaskFileManager fileManager = TaskFileManager.of("duke.txt");
    private final TaskList taskList = fileManager.getTaskList();

    private void displayMessage(String message) {
        System.out.println("\t____________________________________________________________");
        System.out.print(message);
        System.out.println("\t____________________________________________________________");
    }

    private void displayMessage(CommandResult result) {
        System.out.println("\t____________________________________________________________");
        for (String line : result.getMessage()) {
            System.out.println("\t" + line);
        }
        System.out.println("\t____________________________________________________________");
    }

    private void handleGreet() {
        this.displayMessage(MessageConstants.MESSAGE_WELCOME);
    }

    private void handleSave() {
        fileManager.save(taskList);
    }

    public void start() {
        this.handleGreet();

        Scanner input = new Scanner(System.in);

        Command command = null;
        while (!Command.isExit(command)) {
            try {
                command = CommandParser.parseCommand(input.nextLine());
                command.initData(taskList);
                CommandResult result = command.execute();
                displayMessage(result);
            } catch (InvalidInputException e) {
            }
        }
        handleSave();
    }
}
