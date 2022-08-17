import java.util.ArrayList;

/**
 * Object to represent a list of tasks created based on input and to print the list as output.
 *
 */
public class TaskList {
    private Integer totalTasks = 0;

    private ArrayList<Task> allTaskList = new ArrayList<>();


    public String tasksLeft() {
        return "Nyaw you have " + totalTasks + " tasks in the list";
    }

    public void addTodo(String description) {
        totalTasks++;
        Task task = new Todo(description);
        allTaskList.add(task);
        System.out.println("Meow! I'm a cat. I've added this task:\n"
                            + task + "\n"
                            + tasksLeft());
    }

    public void mark(Integer rank) {
        String msg;
        if (rank <= 0 || rank > totalTasks) {
            msg = "There are NYA tasks hereeeee";
        } else {
            Task t = allTaskList.get(rank - 1);
            t.markAsDone();
            msg = "Nyace! One step closer to nap!\n"
                + "    " + t;
        }
        System.out.println(msg);
    }

    public void unMark(Integer rank) {
        String msg;
        if (rank <= 0 || rank > totalTasks) {
            msg = "There are NYA tasks hereeeee";
        } else {
            Task t = allTaskList.get(rank - 1);
            t.markAsNotDone();
            msg = "You nyapped for too long!\n"
                    + "    " + t;
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
            System.out.println(i + "." + t + "\n");
        }
    }
}
