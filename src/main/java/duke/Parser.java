package duke;
import java.io.IOException;
import java.util.List;

/**
 * The Parser class parses user commands and executes the command.
 */
public class Parser {
    private TaskList tasks;
    private Storage storage;
    Task dummyTask;
    int counter;
    int start;
    int end;

    /**
     * Creates a new Parser which will parse user commands and execute the command.
     *
     * @param tasks The current list of tasks.
     * @param storage The location where the files will be saved to and loaded from.
     */
    public Parser(TaskList tasks, Storage storage) {
        this.tasks = tasks;
        this.storage = storage;
    }

    /**
     * Parses the input provided and executes it.
     *
     * @param input The input provided by user to parser.
     * @throws IOException When system I/O fails.
     */
    public String parse(String input) throws IOException {

        try {

            if (input.startsWith("mark")) {
                return mark(input);

            } else if (input.startsWith("unmark")) {
                return unmark(input);

            } else if (input.startsWith("find")) {
                return find(input);

            } else if (input.equals("list")) {
                return list();

            } else if (input.startsWith("delete")) {
                return delete(input);

            } else if (input.startsWith("todo")) {
                return todo(input);

            } else if (input.startsWith("deadline")) {
                return deadline(input);

            } else if (input.startsWith("event")) {
                return event(input);

            }else if (input.equals("reminder")) {
                return reminder();

            } else { //random input
                throw new DukeException("OOPS!!! I'm sorry, but I don't know what that means :-(");
            }

        } catch (DukeException e){
            System.out.println(e.toString().substring(15));
        }
        return "OOPS!!! I'm sorry, but I don't know what that means :-(";
    }

    private String event(String input) throws DukeException, IOException {
        String responseString;
        if (input.equals("event")) {
            throw new DukeException("OOPS!!! The description of an event cannot be empty.");
        }
        counter = input.indexOf("/");
        start = 6;
        end = counter - 1;
        tasks.add(new Event(input.substring(start, end), input.substring(counter + 4)));
        assert(input.substring(start, end) != null);
        assert(input.substring(counter + 4) != null);
        responseString = "Got it. I've added this task:\n";
        responseString += String.format("  %s\n", tasks.getTask(tasks.getSize() - 1).toString());
        responseString += String.format("Now you have %s tasks in the list.", tasks.getSize());
        storage.saveFile(tasks);
        return responseString;
    }

    private String deadline(String input) throws DukeException, IOException {
        String responseString;
        if (input.equals("deadline")) {
            throw new DukeException("OOPS!!! The description of a deadline cannot be empty.");
        }
        counter = input.indexOf("/");
        start = 9;
        end = counter - 1;
        tasks.add(new Deadline(input.substring(start, end), input.substring(counter + 4)));
        assert(input.substring(start, end) != null);
        assert(input.substring(counter + 4) != null);
        responseString = ("Got it. I've added this task:\n");
        responseString += String.format("  %s\n", tasks.getTask(tasks.getSize() - 1).toString());
        responseString += String.format("Now you have %s tasks in the list.", tasks.getSize());
        storage.saveFile(tasks);
        return responseString;
    }

    private String todo(String input) throws DukeException, IOException {
        String responseString;
        if (input.equals("todo")) {
            throw new DukeException("OOPS!!! The description of a todo cannot be empty.");
        }
        start = 5;
        dummyTask = new Todo(input.substring(start));
        tasks.add(dummyTask);
        assert(input.substring(start) != null);
        responseString = "Got it. I've added this task:\n";
        responseString += String.format(" %s\n", dummyTask);
        responseString += String.format("Now you have %s tasks in the list.\n", tasks.getSize());
        storage.saveFile(tasks);
        return responseString;
    }

    private String delete(String input) throws DukeException, IOException {
        if (input.equals("delete")) {
            throw new DukeException("Please specify which item is to be deleted.");
        }
        String dummyString = input.substring(7); //get item number to be deleted
        counter = Integer.parseInt(dummyString) - 1;//convert to index of task (int)
        dummyTask = tasks.getTask(counter);
        tasks.delete(counter);
        String responseString = "Noted. I've removed this task:\n";
        responseString += String.format("  %s\n", dummyTask.toString());
        responseString += String.format("Now you have %s tasks in the list.\n", tasks.getSize());
        storage.saveFile(tasks);
        return responseString;
    }

    private String list() {
        String responseString = String.format("Here are the tasks in your list:\n");
        for (int i = 0; i < tasks.getSize(); i++) { //iterate through all tasks
            responseString += String.format("%s.%s\n", (i+1), tasks.getTask(i).toString());
        }
        return responseString;
    }

    private String find(String input) {
        String responseString = String.format("Here are the matching tasks in your list:\n");
        String descriptionToFind = input.substring(5);
        List<Integer> searchResults = tasks.findTasks(descriptionToFind);
        assert(descriptionToFind != null);
        int counter = 1;
        for (int index : searchResults) {
            responseString += String.format("%s.%s\n",counter, tasks.getTask(index).toString());
            counter += 1;
        }
        return responseString;
    }

    private String mark(String input) throws IOException {
        String markString = input.substring(5); //get number of task
        counter = Integer.parseInt(markString) - 1;//convert to index of task (int)
        tasks.getTask(counter).mark();
        storage.saveFile(tasks);
        return String.format("Nice! I've marked this task as done:\n" +
                "[" + tasks.getTask(counter).getStatusIcon() + "] " + tasks.getTask(counter).getDescription());
    }

    private String unmark(String input) throws IOException {
        String dummyString;
        dummyString = input.substring(7); //get number of task
        counter = Integer.parseInt(dummyString) - 1; //convert to index of task (int)
        tasks.getTask(counter).unmark();
        storage.saveFile(tasks);
        return String.format("OK, I've marked this task as not done yet:\n" +
                "[" + tasks.getTask(counter).getStatusIcon() + "] " + tasks.getTask(counter).getDescription());
    }

    private String reminder() {
        String responseString = String.format("Reminder for tasks in the upcoming week:\n");
        int counter = 1;
        List<Integer> searchResults = tasks.getUpcomingTasks();
        for (int index : searchResults) {
            responseString += String.format("%s.%s\n",counter, tasks.getTask(index).toString());
            counter += 1;
        }
        return responseString;
    }
}
