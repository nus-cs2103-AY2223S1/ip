import java.util.ArrayList;

public class TaskList {
    ArrayList<Task> taskList;

    TaskList() {
        this.taskList = new ArrayList<>();
    }

    TaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }

    public void addTask(String input, Commands type) throws Duke.DukeException {
        Task t;
        switch (type) {
        case TODO: {
            t = handleTodo(input);
            break;
        }
        case DEADLINE: {
            t = handleDeadline(input);
            break;
        }
        case EVENT: {
            t = handleEvent(input);
            break;
        }
        default:
            throw new IllegalArgumentException("Invalid task type entered");
            // this should only be seen by developer
        }
        this.taskList.add(t);
        line();
        System.out.println("Got it. I've added this task:");
        System.out.println(t);
        printArraySize();
        line();
    }

    public void deleteTask(int index) throws Duke.DukeException {
        int numOfTasks = this.taskList.size();
        if (index < 1) {
            throw new Duke.DukeException("Hey there! Are you sure you are referring to a correct task? " +
                    " It definitely has to be at least 1!");
        }
        if (index > numOfTasks) {
            throw new Duke.DukeException(String.format("That's magical! You only have %d task(s) at hand!", numOfTasks));
        }
        if (numOfTasks == 0) {
            throw new Duke.DukeException("You cant delete anything yet! Try creating some tasks first!");
        }
        Task t = getTask(index);
        int indexInList = index - 1;
        this.taskList.remove(indexInList);
        line();
        System.out.println("Noted. I've removed this task:");
        System.out.println(t);
        printArraySize();
        line();
    }

    public Task getTask(int index) throws Duke.DukeException {
        int numOfTasks = this.taskList.size();
        if (numOfTasks == 0) {
            throw new Duke.DukeException("Unfortunately, you do not have any tasks at hand." +
                    " Try creating some first.");
        }
        if (index > numOfTasks) {
            throw new Duke.DukeException(String.format("That's magical! You only have %d task(s) at hand!", numOfTasks));
        }
        if (index < 1) {
            throw new Duke.DukeException("Hey there! Are you sure you are referring to a correct task? " +
                    "It definitely has to be at least 1!");
        }
        return this.taskList.get(index - 1);
    }

    public void printArraySize() {
        System.out.println("Now you have " + this.taskList.size() + " tasks in the list.");
    }
}
