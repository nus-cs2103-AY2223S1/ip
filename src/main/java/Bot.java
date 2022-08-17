import java.util.ArrayList;
import java.util.HashMap;

public class Bot {

    protected final String name;
    protected final TaskList taskList;
    protected static final String BORDER = "____________________________________________________________";

    public Bot() {
        this.name = "Bocil";
        this.taskList = new TaskList();
    }

    public void introduce() {
        String line1 = String.format("Hello! I'm %s", this.name);
        String line2 = String.format("What can I do for you?");
        System.out.println(String.format("%s\n%s\n%s\n%s\n", BORDER, line1, line2, BORDER));
    }

    public String answer(String input) {
        String[] split = input.split("\\s");
        String command = split[0];

        String response = "";
        try {
            if (input.equals("")) {
                throw DukeException.DukeEmptyInputException();
            } else if (command.equals("todo") | command.equals("event") | command.equals("deadline")) {
                Task task = null;
                String name = "";
                for (int i = 1; i < split.length; i++) {
                    name = String.format("%s%s ", name, split[i]);
                }
                if (name.equals("")) {
                    throw DukeException.DukeEmptyNameException();
                } else if (command.equals("todo")) {
                    task = new Todo(name);
                } else if (command.equals("deadline")) {
                    String[] details = name.split("\\s/by\\s");
                    task = new Deadline(details[0], details[1]);
                } else if (command.equals("event")) {
                    String[] details = name.split("\\s/at\\s");
                    task = new Event(details[0], details[1]);
                }
                this.taskList.addTask(task);
                String header = "Got it. I've added this task:";
                String line = String.format("  %s", task.toString());
                String footer = String.format("Now you have %s task in the list", this.taskList.getSize());
                response = String.format("%s\n%s\n%s", header, line, footer);
            } else if (command.equals("mark")) {
                try {
                    int index = Integer.parseInt(split[1]);
                    Task task = this.taskList.getTask(index);
                    task.mark();
                    String header = "Nice! I've marked this task as done:";
                    String line = String.format("  %s", task.toString());
                    response = String.format("%s\n%s", header, line);
                } catch (NumberFormatException e) {
                    throw DukeException.DukeInvalidIndexException();
                } catch (IndexOutOfBoundsException e) {
                    throw DukeException.DukeInvalidIndexException();
                }
            } else if (command.equals("unmark")) {
                try {
                    int index = Integer.parseInt(split[1]);
                    Task task = this.taskList.getTask(index);
                    task.unmark();
                    String header = "OK, I've marked this task as not done yet:";
                    String line = String.format("  %s", task.toString());
                    response = String.format("%s\n%s", header, line);
                } catch (NumberFormatException e) {
                    throw DukeException.DukeInvalidIndexException();
                } catch (IndexOutOfBoundsException e) {
                    throw DukeException.DukeInvalidIndexException();
                }
            } else if (command.equals("bye")) {
                if (input.equals("bye")) {
                    String header = "Bye. Hope to see you again soon!";
                    response = String.format("%s", header);
                } else {
                    throw DukeException.DukeUnknownCommandException();
                }
            } else if (command.equals("list")) {
                if (input.equals("list")) {
                    String header = "Here are the tasks in your list";
                    response = String.format("%s\n%s", header, taskList.toString());
                } else {
                    throw DukeException.DukeUnknownCommandException();
                }
            } else {
                throw DukeException.DukeUnknownCommandException();
            }
            return String.format("%s\n%s\n%s\n", BORDER, response, BORDER);
        } catch (DukeException e) {
            return String.format("%s\n%s\n%s\n", BORDER, e.getMessage(), BORDER);
        }
    }
}
