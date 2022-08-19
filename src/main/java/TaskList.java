import java.util.ArrayList;

/**
 * Represents a list of tasks created based on input and to print the list as output.
 *
 * @author WR3nd3
 */
public class TaskList {
    private Integer totalTasks = 0;

    private ArrayList<Task> allTaskList = new ArrayList<>();

    /**
     * Returns a message reporting the number of tasks in list.
     *
     * @return String containing number of tasks in list.
     */
    public String tasksLeft() {
        String str;
        if (totalTasks > 1) {
            str = "tasks in the list";
        } else {
            str = "task in the list";
        }
        return "Nyaw you have " + Math.max(totalTasks, 0) + " " + str;
    }

    /**
     * Creates Todo task representing the description and adds it to the list.
     * Prints message indicating completion of the action.
     *
     * @param description String representing the description of the task.
     */
    public void addTodo(String description) {
        totalTasks++;
        Task task = new Todo(description);
        allTaskList.add(task);
        System.out.println("Meow! I'm a cat. I've added this task:\n"
                            + task + "\n"
                            + tasksLeft());
    }

    /**
     * Creates Event task representing the description and adds it to the list.
     * Prints message indicating completion of the action.
     *
     * @param description String representing the description of the task.
     * @param date String representing the time of the event.
     */
    public void addEvent(String description, String date) {
        totalTasks++;
        Task task = new Event(description, date);
        allTaskList.add(task);
        System.out.println("Moo! I'm a cat. I've added this task:\n"
                + task + "\n"
                + tasksLeft());
    }

    /**
     * Creates Deadline task representing the description and adds it to the list.
     * Prints message indicating completion of the action.
     *
     * @param description String representing the description of the task.
     * @param date String representing the deadline of the event.
     */
    public void addDeadline(String description, String date) {
        totalTasks++;
        Task task = new Deadline(description, date);
        allTaskList.add(task);
        System.out.println("Woof! I'm a cat. I've added this task:\n"
                + task + "\n"
                + tasksLeft());
    }

    /**
     * Marks the task represented by the rank in the list as complete.
     *
     * @param rank Integer indicating the position of the task relative in the list.
     */
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

    /**
     * Marks the task represented by the rank in the list as incomplete.
     *
     * @param rank Integer indicating the position of the task relative in the list.
     */
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

    /**
     * Deletes the task represented by the rank in the list from the list.
     *
     * @param rank Integer indicating the position of the task relative in the list.
     */
    public void delete(Integer rank) {
        String msg;
        if (rank <= 0 || rank > totalTasks) {
            msg = "There are NYA tasks hereeeee";
        } else {
            totalTasks--;
            Task t = allTaskList.get(rank - 1);
            msg = "It's dead!! It's deadsss!\n"
                    + "    "
                    + t + "\n"
                    + tasksLeft();
        }
        System.out.println(msg);
    }

    /**
     * Prints the status of the list to the command line interface.
     */
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
