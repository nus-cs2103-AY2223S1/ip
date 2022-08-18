
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DukeHandler {
    private TaskList tasks;

    public DukeHandler() {
        this.tasks = new TaskList();
    }

    public void handleResponse(String input) {
        if (input.equals("bye")) {
            System.out.println("Bye. Hope to see you again soon!");
            System.exit(0);
        }
        String[] temp = input.split(" ", 2);
        List<String> inputParts = new ArrayList<String>(Arrays.asList(temp));
        try {
            if (inputParts.size() < 2) {
                inputParts.add("");
            }
            if (input.equals("list")) {
                tasks.listTasks();
            }
            else if (input.matches("mark +\\d+") || input.matches("unmark +\\d+")) {
                if (inputParts.get(0).equals("mark")) {
                    tasks.mark(Integer.parseInt(inputParts.get(1)));
                }
                if (inputParts.get(0).equals("unmark")) {
                    tasks.unmark(Integer.parseInt(inputParts.get(1)));
                }
            }
            else if (input.matches("delete +\\d+")) {
                tasks.delete(Integer.parseInt(inputParts.get(1)));
            }
            else if (inputParts.get(0).equals("todo")) {
                tasks.addTask(new Todo(inputParts.get(1)));
            }
            else if (inputParts.get(0).equals("deadline")) {
                String[] deadlineParts = inputParts.get(1).split("/by", 2);
                if (deadlineParts.length < 2) {
                    new Deadline(deadlineParts[0], deadlineParts[0]);
                }
                Deadline deadlineTask = new Deadline(deadlineParts[0], deadlineParts[1]);
                tasks.addTask(deadlineTask);
            } else if (inputParts.get(0).equals("event")) {
                String[] eventParts = inputParts.get(1).split("/at", 2);
                if (eventParts.length < 2) {
                    new Event(eventParts[0], eventParts[0]);
                }
                Event newEvent = new Event(eventParts[0], eventParts[1]);
                tasks.addTask(newEvent);
            } else {
                throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
            }
        }
        catch (DukeException e) {
            System.err.println(e.getMessage());
        }
    }

}
