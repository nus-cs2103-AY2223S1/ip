import java.util.List;

/**
 * Contains the task list e.g., it has operations
 * to add/delete tasks in the list.
 */
public class TaskList {
    private List<Task> userTasks;

    public TaskList(List<Task> userTasks) {
        this.userTasks = userTasks;
    }

    public List<Task> getTasks() {
        return userTasks;
    }

    public int getListSize() {
        return userTasks.size();
    }

    public void fillTaskPrint(List<String> taskPrint) {
        for (int i = 1; i <= userTasks.size(); i++) {
            Task task = userTasks.get(i - 1);
            String output = String.format(" %d. %s", i, task);
            taskPrint.add(output);
        }
    }

    public Task markTask(int oneIndexedNum) throws TumuException {
        if (oneIndexedNum < 1 || oneIndexedNum > userTasks.size()) {
            //Specified index from user is out of bounds of list.
            if (userTasks.isEmpty()) throw new NoTaskException();
            else throw new OutOfBoundsException(userTasks.size());
        } else {
            Task task = userTasks.get(oneIndexedNum - 1);
            task.markDone();
            return task;
        }
    }

    public Task unmarkTask(int oneIndexedNum) throws TumuException {
        if (oneIndexedNum < 1 || oneIndexedNum > userTasks.size()) {
            //Specified index from user is out of bounds of list.
            if (userTasks.isEmpty()) throw new NoTaskException();
            else throw new OutOfBoundsException(userTasks.size());
        } else {
            Task task = userTasks.get(oneIndexedNum - 1);
            task.unmarkDone();
            return task;
        }
    }

    public Task deleteTask(int oneIndexedNum) throws TumuException {
        if (oneIndexedNum < 1 || oneIndexedNum > userTasks.size()) {
            //Specified index from user is out of bounds of list.
            if (userTasks.isEmpty()) throw new NoTaskException();
            else throw new OutOfBoundsException(userTasks.size());
        } else {
            Task removedTask = userTasks.remove(oneIndexedNum - 1);
            return removedTask;
        }
    }

    public void addTask(Task task) {
        userTasks.add(task);
    }
}
