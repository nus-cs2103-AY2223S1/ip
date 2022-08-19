import java.util.*;
import java.util.ArrayList;
import java.util.List;

public class Duke {
    private List<Task> taskList = new ArrayList<>();
    private int count = 0;

    public void addToDo(String taskName)  {
        Task newTask = new ToDo(taskName);
        addTask(newTask);
    }

    public void addDeadline(String commandInput) throws DukeException {
        String[] inputs = commandInput.split("/by");
        //no deadline provided
        if (inputs.length == 1) {
            throw new DukeException("Deadline must have a due date\n" +
                    "Include '/by' and date at the back");
        }
        String taskName = inputs[0];
        String dueDate = inputs[1];
        Task newTask = new Deadline(taskName, dueDate);
        addTask(newTask);
    }

    public void addEvent(String commandInput) throws DukeException {
        String[] inputs = commandInput.split("/at");
        //no date provided
        if (inputs.length == 1) {
            throw new DukeException("Event must have a date\n" +
                    "Include '/at' and date at the back");
        }
        String taskName = inputs[0];
        String date = inputs[1];
        Task newTask = new Event(taskName, date);
        addTask(newTask);
    }

    public void addTask(Task task) {
        count++;
        taskList.add(task);
        System.out.println("You have added: \n" + task);
        System.out.println("Now you have " + count + " tasks in your list. \n");
    }

    public void checkTask(String[] inputs, String command) throws DukeException {
        //handle the error where no task name
        if (inputs.length == 1) {
            throw new DukeException("The description of a " + command + " cannot be empty");
        }
    }

    public void checkMark(String[] inputs) throws DukeException {
        //handle error without task number
        if (inputs.length == 1) {
            throw new DukeException("You didn't put the task number at the back :(.\n" +
                    "Wanya isn't Anya. I can't read your mind!");
        }
    }

    public void showTasks() {
        if (count == 0) {
            System.out.println("List is empty! Wheee slack time!");
        }
        for (int i = 1; i <= count; i++) {
            Task task = taskList.get(i - 1);
            System.out.println(i + "." + task);
        }
        System.out.println("");
    }

    public static void main(String[] args) {
        Duke duke = new Duke();
        String startMsg = "Hello!!! My name is Wanya! \nWWaku WWaku! \nHow can I help you? \n";
        String closeMsg = "Yayyy Wanya get to slack and watch shows now. Bye bye! :)";
        Scanner sc = new Scanner(System.in);
        System.out.println(startMsg);

        while (sc.hasNext()) {
            try {
                String commandInput = sc.nextLine();
                String[] inputs = commandInput.split(" ", 2);
                String command = inputs[0];

                if (commandInput.equals("bye")) {
                    System.out.println(closeMsg);
                    break;
                } else if (commandInput.equals("list")) {
                    duke.showTasks();
                } else if (command.equals("mark")) {
                    duke.checkMark(inputs);
                    int indexToMark = Integer.parseInt(inputs[1]);
                    duke.taskList.get(indexToMark - 1).completedTask();
                } else if (command.equals("unmark")) {
                    duke.checkMark(inputs);
                    int indexToUnmark = Integer.parseInt(inputs[1]);
                    duke.taskList.get(indexToUnmark - 1).uncompletedTask();
                } else if (command.equals("todo")) {
                    duke.checkTask(inputs, command);
                    duke.addToDo(inputs[1]);
                } else if (command.equals("deadline")) {
                    duke.checkTask(inputs, command);
                    duke.addDeadline(inputs[1]);
                } else if (command.equals("event")) {
                    duke.checkTask(inputs, command);
                    duke.addEvent(inputs[1]);
                } else {
                    throw new DukeException("I am sorry. Wanya doesn't like to study " +
                            "so Wanya don't know what that means.");
                }
            } catch (DukeException e) {
                System.out.println(e.getMessage() + '\n');
            }
        }
    }
}

