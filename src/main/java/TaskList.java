import java.util.ArrayList;

/**
 * Object to represent a list of tasks created based on input and to print the list as output.
 *
 */
public class TaskList {
    private Integer totalTasks = 0;

    private ArrayList<Task> allTaskList = new ArrayList<>();


    public String tasksLeft() {
        String str;
        if (totalTasks > 1) {
            str = "tasks in the list";
        } else {
            str = "task in the list";
        }
        return "Nyaw you have " + Math.max(totalTasks, 0) + " " + str;
    }

    public void addTodo(String description) {
        totalTasks++;
        Task task = new Todo(description);
        allTaskList.add(task);
        System.out.println("Meow! I'm a cat. I've added this task:\n"
                            + task + "\n"
                            + tasksLeft());
    }

    public void addEvent(String description, String date) {
        totalTasks++;
        Task task = new Event(description, date);
        allTaskList.add(task);
        System.out.println("Moo! I'm a cat. I've added this task:\n"
                + task + "\n"
                + tasksLeft());
    }

    public void addDeadline(String description, String date) {
        totalTasks++;
        Task task = new Deadline(description, date);
        allTaskList.add(task);
        System.out.println("Woof! I'm a cat. I've added this task:\n"
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
            System.out.println(i + "." + t);
        }
    }
}
