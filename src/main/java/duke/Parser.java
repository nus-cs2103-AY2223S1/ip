package duke;
import java.io.IOException;
import java.util.List;

public class Parser {
    private TaskList tasks;
    private Storage storage;
    String dummyString;
    Task dummyTask;
    int counter;
    int start;
    int end;

    public Parser(TaskList tasks, Storage storage) {
        this.tasks = tasks;
        this.storage = storage;
    }

    public void parse(String input) throws IOException {
        try {
            if (input.startsWith("mark")) {
                dummyString = input.substring(5); //get number of task
                counter = Integer.parseInt(dummyString) - 1;//convert to index of task (int)
                tasks.get(counter).mark();
                System.out.println("Nice! I've marked this task as done:\n" +
                        "[" + tasks.get(counter).getStatusIcon() + "] " + tasks.get(counter).getDescription());
                storage.saveFile(tasks);

            } else if (input.startsWith("unmark")) {
                dummyString = input.substring(7); //get number of task
                counter = Integer.parseInt(dummyString) - 1; //convert to index of task (int)
                tasks.get(counter).unmark();
                System.out.println("OK, I've marked this task as not done yet:\n" +
                        "[" + tasks.get(counter).getStatusIcon() + "] " + tasks.get(counter).getDescription());
                storage.saveFile(tasks);

            } else if (input.startsWith("find")) {
                System.out.println("Here are the matching tasks in your list:");
                String descriptionToFind = input.substring(5);
                List<Integer> searchResults = tasks.findTasks(descriptionToFind);
                int counter = 1;
                for (int index : searchResults) {
                    System.out.println((counter) + "." + tasks.get(index).toString());
                    counter += 1;
                }

            } else if (input.equals("list")) {
                System.out.println("Here are the tasks in your list:");
                for (int i = 0; i < tasks.size(); i++) { //iterate through all tasks
                    System.out.println( (i+1) + "." + tasks.get(i).toString());
                }

            } else if (input.startsWith("delete")) {
                if (input.equals("delete")) {
                    throw new DukeException("Please specify which item is to be deleted.");
                }
                dummyString = input.substring(7); //get item number to be deleted
                counter = Integer.parseInt(dummyString) - 1;//convert to index of task (int)
                dummyTask = tasks.get(counter);
                tasks.delete(counter);
                System.out.println("Noted. I've removed this task:");
                System.out.println("  " + dummyTask.toString());
                System.out.println("Now you have " + tasks.size() + " tasks in the list.");
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
                System.out.println("Now you have " + tasks.size() + " tasks in the list.");
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
                System.out.println("  " + tasks.get(tasks.size() - 1).toString());
                System.out.println("Now you have " + tasks.size() + " tasks in the list.");
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
                System.out.println("  " + tasks.get(tasks.size() - 1).toString());
                System.out.println("Now you have " + tasks.size() + " tasks in the list.");
                storage.saveFile(tasks);

            } else { //random input
                throw new DukeException("OOPS!!! I'm sorry, but I don't know what that means :-(");
            }

        } catch (DukeException e){
            System.out.println(e.toString().substring(15));
        }
    }
}
