package duke.storage;

import duke.commands.UserCommandList;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.TaskList;
import duke.task.ToDo;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class FileDecoder {
    /**
     * Decode the tasks from file and store in task list. Finally, return the task list.
     *
     * @param dataFile File to be read from.
     * @return Task list containing tasks.
     * @throws FileNotFoundException If file not found when trying to read from the said file.
     */
    static TaskList decodeFile(File dataFile) throws FileNotFoundException {
        TaskList taskList = new TaskList(new ArrayList<>());
        Scanner fileReader = new Scanner(dataFile);
        decodeLine(taskList, fileReader);
        return taskList;
    }

    /**
     * Decode line by line in file and store the task in task list.
     *
     * @param taskList Task list to store the decoded task.
     * @param fileReader Line read by scanner.
     */
    private static void decodeLine(TaskList taskList, Scanner fileReader) {
        while (fileReader.hasNextLine()) {
            fileReader.useDelimiter(" \\| ");
            UserCommandList command = UserCommandList.valueOf(fileReader.next().toUpperCase());
            Task task;
            switch (command) {
            case TODO:
                String toDoDescription = fileReader.next();
                task = new ToDo(toDoDescription);
                taskList.add(task);
                break;
            case DEADLINE:
                String deadlineDescription = fileReader.next();
                String deadlineBy = fileReader.next();
                task = new Deadline(deadlineDescription, deadlineBy);
                taskList.add(task);
                break;
            case EVENT:
                String eventDescription = fileReader.next();
                String eventAt = fileReader.next();
                task = new Event(eventDescription, eventAt);
                taskList.add(task);
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + command);
            }
            String isDone = fileReader.reset().skip(" \\| ").nextLine();
            if (isDone.equals("true")) {
                task.markAsDoneWithoutMessage();
            }
        }
    }
}
