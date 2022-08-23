import java.util.ArrayList;
import java.util.Arrays;

public class TaskList {
    public ArrayList<Task> list;

    public TaskList() {
        list = new ArrayList<>();
    }

    public void printList() {
        int i = 0;
        while (i < list.size()) {
            System.out.println(i+1 + ". " +list.get(i));
            i++;
        }
    }

    public void add(Task task) {
        this.list.add(task);
        System.out.println("Got it. I've added this task:");
        System.out.println(task);
        System.out.println("Now you have " + this.list.size() + " tasks in the list");
    }

    public Task markDone(String num) throws DukeException{
        int index = Integer.parseInt(num);
        if (index < 0 || index > list.size()) {
            throw new InvalidInputException(num, "mark");
        }
        Task task = list.get(index-1);
        task.complete();
        return task;
    }

    public Task markUndone(String num) throws DukeException{
        int index = Integer.parseInt(num);
        if (index < 0 || index > list.size()) {
            throw new InvalidInputException("unmark", num);
        }
        Task task = list.get(index-1);
        task.undo();
        return task;
    }

    public void addDeadline(String[] arr) throws DukeException {
        int i = 1;
        while (i < arr.length && !arr[i].equals("/by") ) {
            i++;
        }
        if (arr.length == 1 ) {
            throw new MissingInputException("description", arr[0]);
        } else if (arr.length - 1 == i) {
            throw new MissingInputException("date", arr[0]);
        }
        String description = String.join(" ", Arrays.copyOfRange(arr, 1, i));
        String dueDate = String.join(" ", Arrays.copyOfRange(arr, i + 1, arr.length));
        String[] command = {description, dueDate};
        Deadline deadline = new Deadline(command);
        this.add(deadline);
    }

    public void removeTask(String[] command) throws DukeException {
        if (command.length > 2) {
            throw new InvalidInputException(
                    String.join(" " , Arrays.copyOfRange(command, 1 , command.length)), command[0]);
        } else if (command.length != 2) {
            throw new MissingInputException("index", command[0]);
        } else {
            try {
                Integer index = Integer.parseInt(command[1]);
                Task task = list.get(index-1);
                System.out.println("Ok, I have removed this task:");
                System.out.println(task);
                list.remove(index-1);
                System.out.println("You now have " + list.size() + " tasks left in the list");

            } catch (NumberFormatException e){
                throw new InvalidInputException(command[1], command[0]);
            }
        }
    }

    public void addTodo(String[] arr) throws DukeException {
        if (arr.length == 1) {
            throw new MissingInputException("description", arr[0]);
        }
        Todo todo = new Todo(String.join(" ", Arrays.copyOfRange(arr, 1, arr.length)));
        this.add(todo);
    }

    public void addEvent(String[] arr) throws DukeException {
        int i = 1;
        while (i < arr.length && !arr[i].equals("/at") ) {
            i++;
        }
        if (arr.length == 1) {
            throw new MissingInputException("description", arr[0]);
        } else if (arr.length - 1 == i) {
            throw new MissingInputException("date", arr[0]);
        }
        String description = String.join(" ", Arrays.copyOfRange(arr, 1, i));
        String dueDate = String.join(" ", Arrays.copyOfRange(arr, i + 1, arr.length));
        String[] command = {description, dueDate};
        Event event = new Event(command);
        this.add(event);
    }


}
