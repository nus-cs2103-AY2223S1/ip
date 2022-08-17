import java.util.ArrayList;

/**
 * Object to represent a list of tasks created based on input and to print the list as output.
 *
 */
public class ToDoList {
    private Integer totalTasks = 0;
    private ArrayList<Task> complete = new ArrayList<>();
    private ArrayList<Task> incomplete = new ArrayList<>();
    private ArrayList<Task> allTaskList = new ArrayList<>();

    private String notDoneSymbol = "[Zzzzz]";
    private String doneSymbol    = "[/ᐠ｡ꞈ｡ᐟ\\]";

    public Integer tasksLeft() {
        return incomplete.size();
    }

    public Integer tasksDone() {
        return complete.size();
    }

    public void addTask(String description) {
        totalTasks++;
        Task task = new Task(description);
        allTaskList.add(task);

        if (task.status()) {
            complete.add(task);
        } else {
            incomplete.add(task);
        }

        System.out.println("added: " + task.describe());
    }

    public void mark(Integer rank) {
        String msg;
        if (rank <= 0 || rank > totalTasks) {
            msg = "There are NYA tasks hereeeee";
        } else {
            Task t = allTaskList.get(rank - 1);
            msg = "Nyace! One step closer to nap!\n"
                + "    "    + doneSymbol + " " + t;
            t.markAsDone();;
        }
        System.out.println(msg);
    }

    public void unMark(Integer rank) {
        String msg;
        if (rank <= 0 || rank > totalTasks) {
            msg = "There are NYA tasks hereeeee";
        } else {
            Task t = allTaskList.get(rank - 1);
            msg = "You nyapped for too long!\n"
                    + "    "    + notDoneSymbol + " " + t;
            t.markAsNotDone();
        }
        System.out.println(msg);
    }
    public void printList() {
        String intro;
        if (totalTasks == 0) {
            intro = "NYAAA! 00 Tasks means nap time.\n";
        } else {
            intro = "Here nya the tasks in your list:\n";
        }
        System.out.println(intro);

        for(int i = 1; i <= totalTasks; i++) {
            Task t = allTaskList.get(i - 1);
            String symbol = notDoneSymbol;
            if (t.status()) {
                symbol = doneSymbol;
            }
            System.out.println(i + "." + symbol + " " + t + "\n");
        }
    }
}
