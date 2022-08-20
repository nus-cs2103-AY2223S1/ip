import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Accesses a file for tasks.
 */
public class TasksFileState {

    /**
     * Gets ArrayList of previously saved tasks.
     * @return ArrayList of tasks.
     */
    public static ArrayList<Task> getTasks() {
        FileState fileState = FileState.getFileState("./duke.txt");
        ArrayList<Task> tasks = new ArrayList<>();
        for (String[] line : fileState.getLines()) {
            TaskFactory.constructOptionalTask(line).ifPresent((task) -> tasks.add(task));
        }
        return tasks;
    }

    /**
     * Saves a list of tasks to the default file.
     * @param tasks List of tasks.
     */
    public static void saveTasks(List<Task> tasks) {
        List<String[]> lines = tasks.stream().map(Task::getAsStringArray).collect(Collectors.toList());
        FileState fileState = FileState.getFileState("./duke.txt");
        fileState.saveLines((String[][]) lines.toArray(new String[][]{}));
    }
}
