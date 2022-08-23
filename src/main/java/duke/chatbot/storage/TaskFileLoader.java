package duke.chatbot.storage;

import duke.chatbot.data.exception.InvalidInputException;
import duke.chatbot.data.task.TaskList;
import duke.chatbot.parser.Parser;

import java.io.File;
import java.io.FileNotFoundException;

public class TaskFileLoader {
    private final File file;

    protected TaskFileLoader(File file) {
        this.file = file;
    }

    public TaskList getTaskList() throws InvalidInputException, FileNotFoundException {
        return Parser.parseFile(file);
    }
}
