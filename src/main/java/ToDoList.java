import java.util.ArrayList;

public class ToDoList {
    ArrayList<Task> list;

    ToDoList() {
        list = new ArrayList<>();
    }

    /* Sets status of task at index to be complete
     *
     * @param index
     * @throws IndexOutOfBoundsException
     */
    public void complete(int index) throws IndexOutOfBoundsException {
        if (index >= list.size()) {
            throw new IndexOutOfBoundsException();
        }
        list.get(index).mark();
    }

    /* Sets status of task at index to be incomplete
     *
     * @param index
     * @throws IndexOutOfBoundsException
     */
    public void incomplete(int index) throws IndexOutOfBoundsException {
        if (index >= list.size()) {
            throw new IndexOutOfBoundsException();
        }
        list.get(index).unmark();
    }

    /* Add task to todoList
     *
     * @param task
     */
    public void addTask(Task task) {
        list.add(task);
        System.out.println("\tGot it. I've added this task:");
        System.out.println("\t  " + task);
    }

    // Add task to todoList
    public void listTasks() {
        if (list.size() < 1) {
            System.out.println("\tYou don't have any pending tasks.");
            return;
        }

        System.out.println("\tHere are the tasks in your list:");
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i) != null){
                System.out.printf("\t%d. %s\n", i + 1, list.get(i));
            }
        }
    }

    // Returns size of current list
    public int getSize() {
        return list.size();
    }

    /* Deletes a Task
     *
     * @param index
     * @Throws IndexOutOfBoundsException
     */
    public void delete(int index) throws IndexOutOfBoundsException {
        if (index >= list.size()) {
            throw new IndexOutOfBoundsException();
        }
        else {
            Task tempTask = list.get(index);
            list.remove(index);
            System.out.println("\tNoted. I've removed this task:");
            System.out.println("\t  " + tempTask);
        }
    }
}
