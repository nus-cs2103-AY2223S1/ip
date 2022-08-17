import java.util.ArrayList;

public class ToDoList {
    ArrayList<Task> list;

    ToDoList() {
        list = new ArrayList<>();
    }

    /* Sets status of task at index to be complete
     *
     * @param index
     */
    public void complete(int index) throws IndexOutOfBoundsException {
        if (index >= list.size()) {
            throw new ArrayIndexOutOfBoundsException();
        }
        else {
            list.get(index).mark();
        }
    }

    /* Sets status of task at index to be incomplete
     *
     * @param index
     */
    public void incomplete(int index) throws IndexOutOfBoundsException {
        if (index >= list.size()) {
            throw new ArrayIndexOutOfBoundsException();
        }
        else {
            list.get(index).unmark();
        }
    }

    /* Add task to todoList
     *
     * @param task
     */
    public void addTask(Task task) {
        list.add(task);
        System.out.println("\tadded: " + task);
    }

    // Add task to todoList
    public void listTasks() {
        System.out.println("\tHere are the tasks in your list:");
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i) != null){
                System.out.printf("\t%d. %s\n", i + 1, list.get(i).listFormat());
            }
        }
    }
}
