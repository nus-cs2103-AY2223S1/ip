import java.util.List;

public class DeleteTaskCommand implements DukeCommand {
    public String run (List<Task> taskList, String content) {
        try {
            Integer taskNum = Integer.valueOf(content);
            return "Task deleted:\n" + taskList.remove(taskNum - 1);
        } catch (NumberFormatException e) {
            return "Task index is not a number!\n";
        } catch (IndexOutOfBoundsException e) {
            return "Task index out of bounds!\n";
        }
    }
}
