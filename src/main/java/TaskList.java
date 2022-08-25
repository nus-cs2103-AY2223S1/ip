import java.util.ArrayList;

/**
 * Represents a list of tasks created based on input and to print the list as output.
 *
 * @author WR3nd3
 */
public class TaskList {

    private ArrayList<Task> allTaskList = new ArrayList<>();
    private ListLoader updater = new ListLoader(this);


    public int tasksLeft() {
        return allTaskList.size();
    }

    public boolean isValidPosition(int position) {
        return !(position <= 0 || position > tasksLeft());
    }


//    /**
//     * Creates Todo task representing the description and adds it to the list.
//     * Prints message indicating completion of the action.
//     *
//     * @param description String representing the description of the task.
//     * @param isCompleted Boolean representing whether the task is completed.
//     * @param isNewTask Boolean representing whether to save task to data list.
//     * @return String representation of addition of task and list status.
//     */
    public void addTask(Task task) {
        allTaskList.add(task);

    }

    public Task retrieveRank(int rank) {
        return allTaskList.get(rank - 1);
    }
//
//    /**
//     * Creates Event task representing the description and adds it to the list.
//     * Prints message indicating completion of the action.
//     *
//     * @param description String representing the description of the task.
//     * @param date String representing the time of the event.
//     * @param isCompleted Boolean representing whether the task is completed.
//     * @param isNewTask Boolean representing whether to save task to data list.
//     * @return String representation of addition of task and list status.
//     */
//    public String addEvent(String description, String date, Boolean isCompleted, Boolean isNewTask) {
//        Task task = new Event(description, date, isCompleted);
//        allTaskList.add(task);
//        if (isNewTask) {
//            updater.appendToList(task.summary());
//        }
//        return "Moo! I'm a cat. I've added this task:\n"
//                + task + "\n"
//                + tasksLeft();
//    }
//
//    /**
//     * Creates Deadline task representing the description and adds it to the list.
//     * Prints message indicating completion of the action.
//     *
//     * @param description String representing the description of the task.
//     * @param date String representing the deadline of the event.
//     * @param isCompleted Boolean representing whether the task is completed.
//     * @param isNewTask Boolean representing whether to save task to data list.
//     * @return String representation of addition of task and list status.
//     */
//    public String addDeadline(String description, String date, Boolean isCompleted, Boolean isNewTask) {
//        totalTasks++;
//        Task task = new Deadline(description, date, isCompleted);
//        allTaskList.add(task);
//        if (isNewTask) {
//            updater.appendToList(task.summary());
//        }
//        return "Woof! I'm a cat. I've added this task:\n"
//                + task + "\n"
//                + tasksLeft();
//    }

    /**
     * Marks the task represented by the rank in the list as complete.
     *
     * @param rank Integer indicating the position of the task relative in the list.
     */
    public void mark(Integer rank) {
        retrieveRank(rank).markAsDone();
    }

    /**
     * Marks the task represented by the rank in the list as incomplete.
     *
     * @param rank Integer indicating the position of the task relative in the list.
     */
    public void unmark(Integer rank) {
        retrieveRank(rank).markAsNotDone();
    }

    /**
     * Deletes the task represented by the rank in the list from the list.
     *
     * @param rank Integer indicating the position of the task relative in the list.
     */
    public void delete(Integer rank) {
        allTaskList.remove(allTaskList.get(rank - 1));
    }

    /**
     * Prints the status of the list to the command line interface.
     */
    public String[] giveList() {
        String[] list = new String[tasksLeft()];
        for(int i = 0; i < tasksLeft(); i++) {
            Task t = allTaskList.get(i);
            list[i] = i + 1 + "." + t;
        }
        return list;
    }


}
