package duke.tasklist;

import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Scanner;

import duke.CustomMessageException;
import duke.Parser;
import duke.Storage;
import duke.command.Command;
import duke.task.Task;

/**
 * A class to serialise the {@code TaskList} to String and back
 */
public class TaskListSerialiser {
    // Uses LinkedHashMap to guarantee that the order of elements is insertion order
    private static LinkedHashMap<Integer, Task> getMappedIndexToUserText(List<Task> taskList) {
        return taskList.stream().collect(LinkedHashMap::new, (
                hashMap, streamElement) -> hashMap.put(hashMap.size() + 1, streamElement), HashMap::putAll);
    }

    /**
     * Converts the task list to a {@code String} to display to the user.
     * @param taskListToConvert The task list to convert
     * @return The serialised String
     */
    public static String serialiseTaskListForDisplay(List<Task> taskListToConvert) {
        // Idea below of iterating with indices in streams adapted from
        // https://stackoverflow.com/a/42616742
        StringBuilder listOfUserText = getMappedIndexToUserText(taskListToConvert).entrySet().stream().reduce(
                new StringBuilder(), (stringToBuild, currentEntry) -> stringToBuild.append("\n  ")
                        .append(currentEntry.getKey()).append(". ")
                        .append(currentEntry.getValue().toString()), StringBuilder::append);
        return listOfUserText.toString();
    }
    /**
     * Converts the task list to a {@code String} for storage.
     * @param taskList The tasklist to serialise for Storage
     * @return The serialised String
     */
    public static String serialiseTaskListForStorage(List<Task> taskList) {
        StringBuilder listOfUserText = getMappedIndexToUserText(taskList).entrySet().stream().reduce(
                new StringBuilder(), (stringToBuild, currentEntry) -> stringToBuild.append(
                        currentEntry.getValue().getFileStorageString(currentEntry.getKey())), StringBuilder::append);
        return listOfUserText.toString();
    }
    /**
     * Updates the TaskList
     * @param curr The TaskList object
     * @param storage The Storage object
     */
    public static void deserialiseStorageString(TaskList curr, Storage storage) {
        Parser parser = new Parser(curr);
        try {
            Scanner fileScanner = storage.getScannerForTasksFile();
            while (fileScanner.hasNextLine()) {
                Command parsedCommand = parser.parseUserCommand(fileScanner.nextLine());
                parsedCommand.execute();
            }
            fileScanner.close();
        } catch (FileNotFoundException | CustomMessageException e) {
            System.out.println("No existing data was found");
        }
    }
}
