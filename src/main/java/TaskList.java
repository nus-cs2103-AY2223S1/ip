import java.util.ArrayList;

class TaskList {
    ArrayList<Task> list;

    public TaskList() {
        this.list = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> list) {
        this.list = list;
    }

    public int getSize() {
        return list.size();
    }

    public void addTask(Task task) {
        list.add(task);
    }

    public Task deleteTask(int index) throws WrongMessageException {
        int size = list.size();
        if (size == 0) {
            throw new WrongMessageException("You don't have task now");
        }
        if (index > size) {
            throw new WrongMessageException(String.format("?? You only have %d task(s) at hand eh?", size));
        }
        if (index < 1) {
            throw new WrongMessageException("ARE YOU KIDDING ME??????");
        }
        Task task = list.get(index - 1);
        int indexReal = index - 1;
        this.list.remove(indexReal);
        return task;
    }

    public Task getTask(int index) throws WrongMessageException {
        int size = this.list.size();
        if (size == 0) {
            throw new WrongMessageException("You don't have task now");
        }
        if (index > size) {
            throw new WrongMessageException(String.format("?? You only have %d task(s) at hand eh?", size));
        }
        if (index < 1) {
            throw new WrongMessageException("ARE YOU KIDDING ME??????");
        }
        return this.list.get(index - 1);
    }

    public ArrayList<Task> getTaskList() {
        return list;
    }
}