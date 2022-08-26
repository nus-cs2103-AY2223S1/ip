import task.Deadline;
import task.Event;
import task.Task;
import task.ToDo;

import java.time.LocalDate;
import java.util.ArrayList;

public class Parser {
    private static Ui ui = new Ui();
    public static void parseInput(String input, ArrayList<Task> taskArr) throws DukeException {
        if (input.equals("list")) {
            ui.printArrAsNumberedList(taskArr);
        } else if (input.startsWith("mark")) {
            taskArr.get(Integer.parseInt(input.substring(5)) - 1).mark();
        } else if (input.startsWith("unmark")) {
            taskArr.get(Integer.parseInt(input.substring(7)) - 1).unmark();
        } else {
            // this is under task creation
            if (input.startsWith("event")) {
                String[] inputArr = input.split("/");
                taskArr.add(new Event(inputArr[0], LocalDate.parse(inputArr[1].substring(3))));
                ui.printAddTask(taskArr.get(taskArr.size() - 1).toString());
            } else if (input.startsWith("todo")) {

                if (input.substring(4).equals("")){
                    throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
                } else {
                    taskArr.add(new ToDo(input));
                    ui.printAddTask(taskArr.get(taskArr.size() - 1).toString());
                }
            } else if (input.startsWith("deadline")) {
                String[] inputArr = input.split("/");
                taskArr.add(new Deadline(inputArr[0], LocalDate.parse(inputArr[1].substring(3))));
                ui.printAddTask(taskArr.get(taskArr.size() - 1).toString());
            } else if (input.startsWith("delete")) {
                int index = Integer.parseInt(input.substring(7)) - 1;
                System.out.printf("Noted. I've removed this task:\n%s\nNow you have %s tasks in the list.\n",
                        taskArr.get(index).toString(), (taskArr.size() - 1));
                taskArr.remove(index);
            } else {
                throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
            }
        }
        Storage.saveFileData(taskArr);

    }
}
