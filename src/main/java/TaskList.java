import java.util.List;
import java.util.ArrayList;

public class TaskList {
    private List<Task> tasks;

    public TaskList(List<Task> tasks) {
        this.tasks = tasks;
    }

    public void addTask(String input) throws DanException {
        String description;
        String dateString;
        if (input.startsWith("todo")) {
            description = input.replace("todo", "").strip();
            if (description.isEmpty()) {
                throw new DanException("Please provide me a description for your todo item");
            }
            tasks.add(new ToDo(description));

        } else if (input.startsWith("deadline")) {
            String[] temp = input.replace("deadline","").strip().split("/by");
            if (temp.length != 2) {
                throw new DanException("Please follow the following format:\n deadline <description> /by <due date>");
            }
            description = temp[0].strip();
            dateString = temp[1].strip();
            if (description.isEmpty()) {
                throw new DanException("Please provide me a description for your deadline");
            }
            tasks.add(new Deadline(description, dateString));

        } else if (input.startsWith("event")) {
            String[] temp = input.replace("event", "").strip().split("/at");
            if (temp.length != 2) {
                throw new DanException("Please follow the following format:\n event <description> /at <time/date>");
            }
            description = temp[0].strip();
            dateString = temp[1].strip();
            if (description.isEmpty()) {
                throw new DanException("Please provide me a description for your event");
            }
            tasks.add(new Event(description, dateString));
        }
        Ui.printLine();
        Ui.printIndent("Okay okay, I'll add this task then:");
        Ui.printIndent(tasks.get(tasks.size() -1).toString());
        Ui.printIndent(String.format("You now have %d many tasks in your list", tasks.size()));
        Ui.printLine();
    }

    public void showTasks() throws DanException {
        if (tasks.isEmpty()) {
            throw new DanException("Your list is empty!");
        }
        Ui.printLine();
        Ui.printIndent("Here are the tasks in your list:");
        for (int i =1; i <= tasks.size(); i++) {
            Ui.printIndent(i + "." + tasks.get(i - 1));
        }
        Ui.printLine();
    }

    public void markTask(int index) throws DanException {
        if (index > tasks.size()) {
            Ui.printIndent("tasks.size(): " + tasks.size());
            throw new DanException("This task number doesn't exist!");
        }
        Task task = tasks.get(index - 1);
        task.setDone(true);
        Ui.printBlock(String.format("Hehe okay guess this is now done\n"
                + "  %s", task));
    }

    public void unMarkTask(int index) throws DanException {
        if (index > tasks.size()) {
            throw new DanException("This task number doesn't exist!");
        }
        Task task = tasks.get(index - 1);
        task.setDone(false);
        Ui.printBlock(String.format("Ooops, you haven't done this yet? Here ya go:\n"
                + "  %s", task));
    }

    public void deleteTask(int index) throws DanException {
        if (index > tasks.size()) {
            throw new DanException("This task number doesn't exist!");
        }
        Ui.printLine();
        Ui.printIndent("Alright then, I'll remove this task from your list:");
        Ui.printIndent(tasks.get(index - 1).toString());
        tasks.remove(index - 1);
        Ui.printIndent(String.format("You now have %d many tasks in your list", tasks.size()));
        Ui.printLine();
    }

    protected List<Task> getTasks() {
        return tasks;
    }

}
