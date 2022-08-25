
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DukeHandler {
    private TaskList tasks;
    private TaskStorage storage;

    public DukeHandler(String filePath) {
        this.storage = new TaskStorage(filePath);
        this.tasks = storage.loadTask();
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
                    storage.saveTask(tasks);
                }
                if (inputParts.get(0).equals("unmark")) {
                    tasks.unmark(Integer.parseInt(inputParts.get(1)));
                    storage.saveTask(tasks);
                }
            }
            else if (input.matches("delete +\\d+")) {
                tasks.delete(Integer.parseInt(inputParts.get(1)));
                storage.saveTask(tasks);
            }
            else if (inputParts.get(0).equals("todo")) {
                tasks.addTask(new Todo(inputParts.get(1), false));
                storage.saveTask(tasks);
            }
            else if (inputParts.get(0).equals("deadline")) {
                String[] deadlineParts = inputParts.get(1).split("/by", 2);
                if (deadlineParts.length < 2) {
                    new Deadline(deadlineParts[0], deadlineParts[0], false);
                }
                Deadline deadlineTask = new Deadline(deadlineParts[0], deadlineParts[1], false);
                tasks.addTask(deadlineTask);
                storage.saveTask(tasks);
            } else if (inputParts.get(0).equals("event")) {
                String[] eventParts = inputParts.get(1).split("/at", 2);
                if (eventParts.length < 2) {
                    new Event(eventParts[0], eventParts[0], false);
                }
                Event newEvent = new Event(eventParts[0], eventParts[1], false);
                tasks.addTask(newEvent);
                storage.saveTask(tasks);
            } else {
                throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
            }
        }
        catch (DukeException e) {
            System.err.println(e.getMessage());
        }
    }

}
