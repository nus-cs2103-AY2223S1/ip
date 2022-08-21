import java.time.format.DateTimeParseException;
import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    private ArrayList<Task> tasks;
    private boolean end;

    public Duke() {
        this.tasks = Storage.Read();
        this.end = false;
    }

    private void run() {
        String command;
        Scanner sc = new Scanner(System.in);

        Ui.greet();
        while(!this.end) {
            command = sc.nextLine();
            try {
                this.handler(command);
            } catch (DukeException e) {
                Ui.printException(e);
            }
        }
    }

    // handler method handles user input and outputs accordingly
    private void handler(String input) throws DukeException {
        String[] args = input.split(" ", 2);

        switch(args[0]) {
            case "list":
                list();
                break;
            case "todo":
            case "deadline":
            case "event":
                try {
                    listAdd(args[0], args[1]);
                } catch (ArrayIndexOutOfBoundsException e) {
                    throw new DukeMissingInputException(args[0]);
                }
                break;
            case "delete":
                try {
                    listDelete(args[1]);
                } catch (ArrayIndexOutOfBoundsException e) {
                    throw new DukeMissingInputException(args[0]);
                }
                break;
            // mark is implemented as a toggle. note this.
            case "mark":
                try {
                    listToggle(args[1]);
                } catch (ArrayIndexOutOfBoundsException e) {
                    throw new DukeMissingInputException(args[0]);
                }
                break;
            case "bye":
                this.end = true;
                Ui.exit();
                break;
            default:
                throw new DukeUnknownInputException(args[0]);
        }
    }

    // sub methods associated with handler() for each user input case


    private void list() {
        Ui.listPrint(tasks);
    }

    // atodo, deadline and event breaks if no input is entered after each command (1 for atodo, 2 for others)
    // atodo creates an empty task if no input after command (unresolved)
    private void listAdd(String type, String item) throws DukeException {
        Task currTask;
        String[] args;
        switch(type) {
            case "todo":
                currTask = new Todo(item);
                tasks.add(currTask);
                Ui.addTask("todo", currTask, tasks.size());
                Storage.Save(tasks);
                break;
            case "deadline":
                args = item.split("/by ");
                try{
                    currTask = new Deadline(args[0], args[1]);
                    tasks.add(currTask);
                    Ui.addTask("deadline", currTask, tasks.size());
                    Storage.Save(tasks);
                } catch (ArrayIndexOutOfBoundsException e) {
                    throw new DukeMissingInputException(type);
                } catch (DateTimeParseException e) {
                    throw new DukeUnknownDateException(type);
                }
                break;
            case "event":
                args = item.split("/at ");
                try{
                    currTask = new Event(args[0], args[1]);
                    tasks.add(currTask);
                    Ui.addTask("event", currTask, tasks.size());
                    Storage.Save(tasks);
                } catch (ArrayIndexOutOfBoundsException e) {
                    throw new DukeMissingInputException(type);
                } catch (DateTimeParseException e) {
                    throw new DukeUnknownDateException(type);
                }
                break;
        }
    }

    // breaks if no input is entered after mark, or input isn't int, or index out of range
    private void listDelete(String indexString) throws DukeException {
        int index = 0;
        try {
            index = Integer.parseInt(indexString) - 1;
        } catch (NumberFormatException e) {
            throw new DukeWrongInputException("delete");
        }
        if (index >= tasks.size() || index < 0) {
            throw new DukeListOOBException(index + 1);
        }
        Task currTask = tasks.remove(index);
        Ui.deleteTask(currTask, tasks.size());
        Storage.Save(tasks);
    }

    // breaks if no input is entered after mark, or input isn't int, or index out of range
    private void listToggle(String indexString) throws DukeException{
        int index = 0;
        try {
            index = Integer.parseInt(indexString) - 1;
        } catch (NumberFormatException e) {
            throw new DukeWrongInputException("mark");
        }
        if (index >= tasks.size() || index < 0) {
            throw new DukeListOOBException(index + 1);
        }
        Task currTask = tasks.get(index);
        currTask.completeToggle();
        Ui.toggleTask(currTask);
        Storage.Save(tasks);
    }

    public static void main(String[] args) {
        Duke instance = new Duke();
        instance.run();
    }
}
