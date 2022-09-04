package duke.chatbot.util;

import static duke.chatbot.common.Message.MESSAGE_ADDED_TASK;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import duke.chatbot.command.Command;
import duke.chatbot.command.CommandResult;
import duke.chatbot.data.task.TaskList;

public class ParserTest {
    @Test
    public void parseCommand_validToDo_commandExecuted() {
        TaskList taskList = new TaskList();
        String userInput = "todo Walk the dog";
        Command command = assertDoesNotThrow(() -> Parser.parseCommand(userInput));
        command.initData(taskList);
        CommandResult commandResult = assertDoesNotThrow(() -> command.execute());
        int taskListSize = taskList.size();
        assertEquals(1, taskListSize);
        String expected = MESSAGE_ADDED_TASK + "\n" + "[T][ ] Walk the dog";
        String actual = commandResult.getMessage();
        assertEquals(expected, actual);
    }
}
