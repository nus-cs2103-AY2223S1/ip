import java.time.LocalDate;
import java.util.ArrayList;

public class DateCommand extends Command {

    private LocalDate date;
    private ArrayList<Task> matchingTasks;

    public DateCommand(LocalDate date) {
        this.date = date;
        this.matchingTasks = new ArrayList<>();
    }

    @Override
    public void execCommand(TaskList list, Save save) {
        for (int i = 0; i < list.getSize(); i++) {
            Task task = list.getTask(i);
            if (task.getTime().equals(date)) {
                matchingTasks.add(task);
            }
        }
        if (matchingTasks.size() != 0) {
            System.out.println("Here are the tasks on that date:");
            for (int i = 0; i < matchingTasks.size(); i++) {
                System.out.println(i + 1 + ". " + matchingTasks.get(i));
            }
        } else {
            System.out.println("You have no tasks on that date.");
        }
    }
}
