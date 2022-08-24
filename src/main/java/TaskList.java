import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Scanner;

public class TaskList {
    private final ArrayList<Task> userTasks;
    private final Storage storage;

    public TaskList(Storage storage) {
        this.storage = storage;
        userTasks = new ArrayList<>();
        Parser parser = new Parser(this);
        try {
            Scanner fileScanner = storage.getScannerForTasksFile();
            while (fileScanner.hasNextLine()) {
                parser.parseScannerLineInput(fileScanner.nextLine(), parser.breakLoopIndicator);
                if (parser.breakLoopIndicator.getIsExitCommand()) break;
            }
            fileScanner.close();
        } catch (CustomMessageException e) {
            System.out.println(e.getMessage());
            System.out.println("Using an empty file");
        }
    }

    public int sizeOfList() {
        return userTasks.size();
    }

    public void addToTaskList(Task newTask) {
        userTasks.add(newTask);
        storage.writeToDisk(getTasksListForStorage());
    }

    public void markTaskAsDone(int index) {
        userTasks.get(index).setTaskAsDone();
        storage.writeToDisk(getTasksListForStorage());
    }

    public void markTaskAsNotDone(int index) {
        userTasks.get(index).setTaskAsNotDone();
        storage.writeToDisk(getTasksListForStorage());
    }

    public void removeTask(int index) {
        userTasks.remove(index);
        storage.writeToDisk(getTasksListForStorage());
    }

    public String getTaskString(int index) {
        return userTasks.get(index).toString();
    }

    // use LinkedHashMap to guarantee order of elements is insertion order
    public LinkedHashMap<Integer, Task> getMappedIndexToUserText() {
        return userTasks.stream().collect(LinkedHashMap::new,
                (hashMap, streamElement) -> hashMap.put(hashMap.size() + 1, streamElement), HashMap::putAll);
    }

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
