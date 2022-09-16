package duke.chatbot.commandmanager.commands;

import duke.chatbot.ChatBot;
import duke.chatbot.commandmanager.commands.exceptions.EmptyTaskException;
import duke.chatbot.taskmanager.task.ToDoTask;

/**
 * To Do Task Command Handler that adds a new To Do task to the list of task
 * managed by the task manager.
 * Responds with the confirmation message stating that a new task is added.
 */
public class TodoTaskCommandHandler implements Command {
    private final ChatBot chatBot;
    /**
     * Creates a new handler for the to do task command with a reference to the chatbot.
     *
     * @param chatBot a reference to the chatbot
     */
    public TodoTaskCommandHandler(ChatBot chatBot) {
        this.chatBot = chatBot;
    }

    /**
     * Adds a new to do task to the task manager with the given attributes and responds with a confirmation message.
     *
     * @param arguments string of the task name
     * @return the string of the response stating the successful addition of the new to do task
     * @throws EmptyTaskException thrown when the task name is empty
     */
    @Override
    public String execute(String arguments) throws EmptyTaskException {
        String todoTaskName = arguments.strip();
        if (todoTaskName.length() == 0) {
            throw new EmptyTaskException(this.chatBot.getPersonality());
        }

        String taskAdded = this.chatBot.getTaskManager().addTask(new ToDoTask(todoTaskName));
        return this.chatBot.getPersonality().formulateResponse("add_task", taskAdded);
    }

    @Override
    public boolean isValid() {
        return true;
    }
}
