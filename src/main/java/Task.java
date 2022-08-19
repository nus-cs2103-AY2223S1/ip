import java.util.ArrayList;

public class Task {
    protected String description;
    protected boolean isDone;


    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public static Task of (String command, ArrayList<String> arrayList, int number) throws DukeException {
        if (command.split(" ")[0].equals("mark")) {
            int num = Integer.parseInt(command.substring(5)) - 1;
            Task task = new Mark(arrayList.get(num));
            arrayList.set(num, arrayList.get(num).substring(0, 3) + "[X]" + arrayList.get(num).substring(6));
            return task;
        }
        if (command.split(" ")[0].equals("unmark")) {
            int num = Integer.parseInt(command.substring(7)) - 1;
            Task task = new Unmark(arrayList.get(num));
            arrayList.set(num, arrayList.get(num).substring(0, 3) + "[ ]" + arrayList.get(num).substring(6));
            return task;
        }
        if (command.split(" ")[0].equals("todo")) {
            if (command.length() == 4) {
                throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
            }
            arrayList.add("[T][ ] " + command.substring(5));
            return new ToDos(command.substring(5), number);
        }
        if (command.split(" ")[0].equals("deadline")) {
            String time = command.split("/")[1].substring(3);
            arrayList.add("[D][ ] " + command.substring(9) + "(by: " + time + ")");
            return new Deadlines(command.split("/")[0].substring(9), number, time);
        }
        if (command.split(" ")[0].equals("event")) {
            String time = command.split("/")[1].substring(3);
            arrayList.add("[E][ ] " + command.substring(6) + "(at: " + time + ")");
            return new Events(command.split("/")[0].substring(6), number, time);
        }
        if (command.equals("blah")) {
            throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
        Task task = new Add(command);
        arrayList.add(number + "." + task.toString());
        return task;
    }

    public String toString() {
        return " " + description + "\n";
    }
}
