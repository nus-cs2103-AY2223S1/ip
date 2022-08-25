package duke;

import duke.task.Task;

import java.util.*;
import java.util.stream.Collectors;

public class TaskList {
    private final List<Task> userTasks;
    private final Storage storage;

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
        storage.writeToDisk(getStorageRepresentationOfAllTasks());
    }

    public void markTaskAsDone(int index) {
        userTasks.get(index).setTaskAsDone();
        storage.writeToDisk(getStorageRepresentationOfAllTasks());
    }

    public void markTaskAsNotDone(int index) {
        userTasks.get(index).setTaskAsNotDone();
        storage.writeToDisk(getStorageRepresentationOfAllTasks());
    }

    public void removeTask(int index) {
        userTasks.remove(index);
        storage.writeToDisk(getStorageRepresentationOfAllTasks());
    }

    public String getTaskString(int index) {
        return userTasks.get(index).toString();
    }

    // use LinkedHashMap to guarantee order of elements is insertion order
    private LinkedHashMap<Integer, Task> getMappedIndexToUserText(List<Task> taskList) {
        return taskList.stream().collect(LinkedHashMap::new,
                (hashMap, streamElement) -> hashMap.put(hashMap.size() + 1, streamElement), HashMap::putAll);
    }

    private String convertTaskListToString(List<Task> taskListToConvert) {
        // Idea below of iterating with indices in streams adapted from
        // https://stackoverflow.com/a/42616742
        StringBuilder listOfUserText = getMappedIndexToUserText(taskListToConvert).entrySet().stream().reduce(
                new StringBuilder(), (stringToBuild, currentEntry) -> stringToBuild.append("\n      ")
                        .append(currentEntry.getKey()).append(".")
                        .append(currentEntry.getValue().toString()), StringBuilder::append);
        return listOfUserText.toString();
    }

    public String getTextRepresentationOfKeywordTasks(String keyword) {
        return convertTaskListToString(userTasks.stream().filter(task -> task.isMatchingKeywordInDescription(keyword))
                .collect(Collectors.toList()));
    }

    public String getTextRepresentationOfAllTasks() {
        return convertTaskListToString(userTasks);
    }

    private String getStorageRepresentationOfAllTasks() {
        StringBuilder listOfUserText = getMappedIndexToUserText(userTasks).entrySet().stream().reduce(
                new StringBuilder(), (stringToBuild, currentEntry) -> stringToBuild.append(
                        currentEntry.getValue().getFileStorageString(currentEntry.getKey())), StringBuilder::append);
        return listOfUserText.toString();
    }
}
