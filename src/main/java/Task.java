import java.util.HashMap;

public class Task {
    protected String description;
    protected boolean isDone;


    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public static Task of (String command, HashMap<Integer, String> map, int number) throws DukeException {
        if (command.split(" ")[0].equals("mark")) {
            int num = Integer.parseInt(command.substring(5));
            Task task = new Mark(map.get(num));
            map.put(num, num + map.get(num).substring(1, 5) + "[X] " + map.get(num).substring(9));
            return task;
        }
        if (command.split(" ")[0].equals("unmark")) {
            int num = Integer.parseInt(command.substring(7));
            Task task = new Unmark(map.get(num));
            map.put(num, num + map.get(num).substring(1, 5) + "[ ] " + map.get(num).substring(9));
            return task;
        }
        if (command.split(" ")[0].equals("todo")) {
            if (command.length() == 4) {
                throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
            }
            map.put(number, number + ".[T][ ] " + command.substring(5));
            return new ToDos(command.substring(5), number);
        }
        if (command.split(" ")[0].equals("deadline")) {
            String time = command.split("/")[1].substring(3);
            map.put(number, number + ".[D][ ] " + command.substring(9) + "(by: " + time + ")");
            return new Deadlines(command.split("/")[0].substring(9), number, time);
        }
        if (command.split(" ")[0].equals("event")) {
            String time = command.split("/")[1].substring(3);
            map.put(number, number + ".[E][ ] " + command.substring(6) + "(at: " + time + ")");
            return new Events(command.split("/")[0].substring(6), number, time);
        }
        if (command.equals("blah")) {
            throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
        Task task = new Add(command);
        map.put(number, number + "." + task.toString());
        return task;
    }

    public String toString() {
        return " " + description + "\n";
    }
}
