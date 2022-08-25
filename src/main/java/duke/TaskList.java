package duke;

import duke.task.Task;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Scanner;

/**
 * Represents a {@code List} of the {@code Tasks}
 */
public class TaskList {
    private final ArrayList<Task> userTasks;
    private final Storage storage;

    /**
     * Reads an existing file if it exists, else uses an empty {@code List}.
     * @param storage A {@code Storage} instance to take in
     */
    public TaskList(Storage storage) {
        this.storage = storage;
        userTasks = new ArrayList<>();
        Parser parser = new Parser(this);
        try {
            Scanner fileScanner = storage.getScannerForTasksFile();
            while (fileScanner.hasNextLine()) {
                parser.parseUserCommand(fileScanner.nextLine(), parser.breakLoopIndicator);
                if (parser.breakLoopIndicator.getIsExitCommand()) break;
            }
            fileScanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("No existing file found");
        } catch (CustomMessageException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Returns the number of {@code Tasks}
     * @return The number of {@code Tasks}
     */
    public int sizeOfList() {
        return userTasks.size();
    }

    /**
     * Adds a new {@code Task}
     * @param newTask The {@code Task} to be added
     */
    public void addToTaskList(Task newTask) {
        userTasks.add(newTask);
        storage.writeToDisk(getTasksListForStorage());
    }

    /**
     * Marks as the {@code Task} as done.
     * @param index The index of the {@code Task}
     */
    public void markTaskAsDone(int index) {
        userTasks.get(index).setTaskAsDone();
        storage.writeToDisk(getTasksListForStorage());
    }

    /**
     * Marks as the {@code Task} as not done.
     * @param index The index of the {@code Task}
     */
    public void markTaskAsNotDone(int index) {
        userTasks.get(index).setTaskAsNotDone();
        storage.writeToDisk(getTasksListForStorage());
    }

    /**
     * Deletes the specified {@code Task}
     * @param index The index of the {@code Task}
     */
    public void removeTask(int index) {
        userTasks.remove(index);
        storage.writeToDisk(getTasksListForStorage());
    }

    /**
     * Gets a {@code String} representation of the {@code Task}
     * @param index The index of the {@code Task}
     * @return A {@code String} representation
     */
    public String getTaskString(int index) {
        return userTasks.get(index).toString();
    }

    // use LinkedHashMap to guarantee order of elements is insertion order
    private LinkedHashMap<Integer, Task> getMappedIndexToUserText() {
        return userTasks.stream().collect(LinkedHashMap::new,
                (hashMap, streamElement) -> hashMap.put(hashMap.size() + 1, streamElement), HashMap::putAll);
    }

    /**
     * Returns a {@code String} representation of the {@code Tasks}
     * @return A {@code String} representation of the {@code Tasks}
     */
    public String getTasksListsForUser() {
        // Idea below of iterating with indices in streams adapted from
        // https://stackoverflow.com/a/42616742
        StringBuilder listOfUserText = getMappedIndexToUserText().entrySet().stream().reduce(
                new StringBuilder(), (stringToBuild, currentEntry) -> stringToBuild.append("\n      ")
                        .append(currentEntry.getKey()).append(".")
                        .append(currentEntry.getValue().toString()), StringBuilder::append);
        return listOfUserText.toString();
    }

    private String getTasksListForStorage() {
        StringBuilder listOfUserText = getMappedIndexToUserText().entrySet().stream().reduce(
                new StringBuilder(), (stringToBuild, currentEntry) -> stringToBuild.append(
                        currentEntry.getValue().getFileStorageString(currentEntry.getKey())), StringBuilder::append);
        return listOfUserText.toString();
    }
}
