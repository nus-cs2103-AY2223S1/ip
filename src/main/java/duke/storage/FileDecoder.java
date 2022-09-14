package duke.storage;

import duke.commands.Command;
import duke.parser.Parser;
import duke.task.TaskList;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class FileDecoder {
    /**
     * Decode the tasks from file and store in the given task list. Finally, return the task list.
     *
     * @param dataFile File to be read from.
     * @return Task list containing tasks.
     */
    static TaskList decodeFile(File dataFile, TaskList taskList) {
        try {
            Scanner fileReader = new Scanner(dataFile);
            decodeLine(taskList, fileReader);
        } catch (FileNotFoundException fnfe) {
            throw new AssertionError("Should not reach this stage.");
        }
        return taskList;
    }

    /**
     * Decode the file line by line and store the decoded tasks in task list.
     *
     * @param taskList Task list to store the decoded tasks.
     * @param fileReader Line read by scanner.
     */
    private static void decodeLine(TaskList taskList, Scanner fileReader) {
        while (fileReader.hasNextLine()) {
            Command command = Parser.parseFileInput(fileReader);
            command.execute(taskList);
        }
    }
}
