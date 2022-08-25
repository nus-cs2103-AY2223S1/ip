import java.util.List;

public class FileFormatter {
    public String formatTask(Task task) {
        return String.format("%s|%s|%s|%s|%s\n",
                task.getTypeLetter(),
                task.getStatusLetter(),
                task.getDescription(),
                task.getDuring(),
                task.getTimeText());
    }

    public String formatTaskList(List<Task> taskList) {
        StringBuilder result = new StringBuilder();
        for (Task task : taskList) {
            result.append(formatTask(task));
        }
        return result.toString();
    }
}
