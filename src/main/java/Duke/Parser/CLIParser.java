package Duke.Parser;

import Duke.Commands.UserCommand;

import Duke.Commands.AddTaskCommand;
import Duke.Commands.QuitCommand;


import Duke.Exceptions.DukeException;
import Duke.Exceptions.InvalidCommentException;
import Duke.Tasks.TaskList;
import Duke.Tasks.ToDo;
import Duke.Tasks.Deadline;


import javax.xml.stream.events.Comment;

public class CLIParser {

    // Like a classifier
    public UserCommand parseCommand(String command, TaskList userTasks) throws DukeException {
        int firstSpace = command.indexOf(" ");

        if (firstSpace == -1) {
            if (command.equals("bye")) {
                return new QuitCommand();
            }

            // This one means the command don't needs execute
            throw new InvalidCommentException();

        }

        String commandType = command.substring(0, firstSpace);
        String commandElse = command.substring(firstSpace).strip();

        if (commandType.equals("ToDo")) {
            return parseTodoCommand(commandElse, userTasks);
        }

        if (commandType.equals("Deadline")) {
            return parseDeadlineCommand(commandElse, userTasks);
        }
    }



    private AddTaskCommand parseTodoCommand(String taskName, TaskList userTask) {
        return new AddTaskCommand(new ToDo(taskName, false), userTask);
    }
    private AddTaskCommand parseDeadlineCommand(String commandElse, TaskList userTask) {

        try {
            String[] parts = commandElse.split("/");
            String taskName = split[0].strip();
            String[] time = parts[1].strip().split(" ");
        } catch (Exception e) {

        }
        Deadline deadline = null;

        return new AddTaskCommand(null, userTask)
    }
}
