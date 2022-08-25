package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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
            for (int k = 2; k < command.split("/").length; k++) {
                time += command.split("/")[k];
            }
            LocalDate date = LocalDate.parse(time);
            String tranTime = date.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
            arrayList.add("[D][ ] " + command.split("/")[0].substring(9) + "(by: " + tranTime + ")");
            return new Deadlines(command.split("/")[0].substring(9), number, tranTime);
        }
        if (command.split(" ")[0].equals("event")) {
            String time = command.split("/")[1].substring(3);
            for (int k = 2; k < command.split("/").length; k++) {
                time += command.split("/")[k];
            }
            LocalDate date = LocalDate.parse(time);
            String tranTime = date.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
            arrayList.add("[E][ ] " + command.split("/")[0].substring(6) + "(at: " + tranTime + ")");
            return new Events(command.split("/")[0].substring(6), number, tranTime);
        }
        if (command.equals("blah")) {
            throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
        Task task = new Add(command);
        arrayList.add(number + "." + task.toString());
        return task;
    }

    public boolean AddToList() {
        return true;
    }

    public String toString() {
        return " " + description + "\n";
    }
}
