import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Scanner;

public class TaskList {
    private final ArrayList<Task> userTasks;

    public TaskList() {
        userTasks = new ArrayList<>();
        File tasksFile = new File("duke.txt");
        if (tasksFile.exists()) {
            try {
                InputParser inputParser = new InputParser(this);
                Scanner fileScanner = new Scanner(tasksFile);
                while (fileScanner.hasNextLine()) {
                    inputParser.parseScannerLineInput(fileScanner, inputParser.breakLoopIndicator);
                    if (inputParser.breakLoopIndicator.getIsScannerDone()) break;
                }
                fileScanner.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    public int sizeOfList() {
        return userTasks.size();
    }

    public void addToTaskList(Task newTask) {
        userTasks.add(newTask);
        writeToDisk();
    }

    public void markTaskAsDone(int index) {
        userTasks.get(index).setTaskAsDone();
        writeToDisk();
    }

    public void markTaskAsNotDone(int index) {
        userTasks.get(index).setTaskAsNotDone();
        writeToDisk();
    }

    public void removeTask(int index) {
        userTasks.remove(index);
        writeToDisk();
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

    private void writeToDisk() {
        try {
            File tasksFile = new File("duke.txt");
            if ((tasksFile.exists() && tasksFile.delete()) || !tasksFile.exists()) {
                FileWriter fileWriter = new FileWriter("duke.txt");
                try {
                    fileWriter.write(getTasksListForStorage());
                } catch (IOException e) {
                    e.printStackTrace();
                }
                fileWriter.close();
            } else {
                System.out.println("Unable to save your data");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
