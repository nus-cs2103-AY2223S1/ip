package duke;
import java.io.IOException;

/**
 * The Parser class parses user commands and executes the command.
 */
public class Parser {
    private TaskList tasks;
    private Storage storage;
    String dummyString;
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
    public void parse(String input) throws IOException {
        try {
            if (input.startsWith("mark")) {
                dummyString = input.substring(5); //get number of task
                counter = Integer.parseInt(dummyString) - 1;//convert to index of task (int)
                tasks.getTask(counter).mark();
                System.out.println("Nice! I've marked this task as done:\n" +
                        "[" + tasks.getTask(counter).getStatusIcon() + "] " + tasks.getTask(counter).getDescription());
                storage.saveFile(tasks);

            } else if (input.startsWith("unmark")) {
                dummyString = input.substring(7); //get number of task
                counter = Integer.parseInt(dummyString) - 1; //convert to index of task (int)
                tasks.getTask(counter).unmark();
                System.out.println("OK, I've marked this task as not done yet:\n" +
                        "[" + tasks.getTask(counter).getStatusIcon() + "] " + tasks.getTask(counter).getDescription());
                storage.saveFile(tasks);

            } else if (input.equals("list")) {
                System.out.println("Here are the tasks in your list:");
                for (int i = 0; i < tasks.getSize(); i++) { //iterate through all tasks
                    System.out.println( (i+1) + "." + tasks.getTask(i).toString());
                }

            } else if (input.startsWith("delete")) {
                if (input.equals("delete")) {
                    throw new DukeException("Please specify which item is to be deleted.");
                }
                dummyString = input.substring(7); //get item number to be deleted
                counter = Integer.parseInt(dummyString) - 1;//convert to index of task (int)
                dummyTask = tasks.getTask(counter);
                tasks.delete(counter);
                System.out.println("Noted. I've removed this task:");
                System.out.println("  " + dummyTask.toString());
                System.out.println("Now you have " + tasks.getSize() + " tasks in the list.");
                storage.saveFile(tasks);

            } else if (input.startsWith("todo")) {
                if (input.equals("todo")) {
                    throw new DukeException("OOPS!!! The description of a todo cannot be empty.");
                }
                start = 5;
                dummyTask = new Todo(input.substring(start));
                tasks.add(dummyTask);
                System.out.println("Got it. I've added this task:");
                System.out.println("  " + dummyTask);
                System.out.println("Now you have " + tasks.getSize() + " tasks in the list.");
                storage.saveFile(tasks);

            } else if (input.startsWith("deadline")) {
                if (input.equals("deadline")) {
                    throw new DukeException("OOPS!!! The description of a deadline cannot be empty.");
                }
                counter = input.indexOf("/");
                start = 9;
                end = counter - 1;
                tasks.add(new Deadline(input.substring(start, end), input.substring(counter + 4)));
                System.out.println("Got it. I've added this task:");
                System.out.println("  " + tasks.getTask(tasks.getSize() - 1).toString());
                System.out.println("Now you have " + tasks.getSize() + " tasks in the list.");
                storage.saveFile(tasks);

            } else if (input.startsWith("event")) {
                if (input.equals("event")) {
                    throw new DukeException("OOPS!!! The description of an event cannot be empty.");
                }
                counter = input.indexOf("/");
                start = 6;
                end = counter - 1;
                tasks.add(new Event(input.substring(start, end), input.substring(counter + 4)));
                System.out.println("Got it. I've added this task:");
                System.out.println("  " + tasks.getTask(tasks.getSize() - 1).toString());
                System.out.println("Now you have " + tasks.getSize() + " tasks in the list.");
                storage.saveFile(tasks);

            } else { //random input
                throw new DukeException("OOPS!!! I'm sorry, but I don't know what that means :-(");
            }

        } catch (DukeException e){
            System.out.println(e.toString().substring(15));
        }
    }
}
