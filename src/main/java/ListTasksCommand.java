import java.util.List;

public class ListTasksCommand implements DukeCommand {

    public static String listTask(List<Task> taskList) {
        if (taskList.size() == 0) {
            return "No tasks added yet!\n";
        }

        StringBuilder fullList = new StringBuilder();
        for (int i = 0; i < taskList.size(); i++) {
            fullList.append(i + 1).append(". ").append(taskList.get(i));
        }
        return fullList.toString();
    }

    public String run (List<Task> taskList, String content) {
        return listTask(taskList);
    }
}
