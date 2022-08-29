import java.util.ArrayList;

class TaskList {

    private ArrayList<Task> listOfTasks = new ArrayList<>();
    public void addTask (Task task) {
        listOfTasks.add(task);
    }

    public int getSize() {
        return listOfTasks.size();
    }

    public void deleteTask (int i) throws DukeException {
        if (listOfTasks.size() <= i) {
            throw DukeException.idTooBig;
        }

        listOfTasks.remove(i);
    }

    public Task getTask (int i) throws DukeException {
        if (listOfTasks.size() <= i) {
            throw DukeException.idTooBig;
        }

        return listOfTasks.get(i);
    }

    @Override
    public String toString() {
        String res = "";
        for (int i = 0; i < getSize(); i++) {
            res += (i + 1) + ". " + listOfTasks.get(i);
            if (i != getSize() - 1) {
                res += "\n";
            }
        }
        return res;
    }
}
