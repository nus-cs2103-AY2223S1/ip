package duke;

import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class Parser {
    private TaskList taskList;
    private Scanner scanner;
    private boolean isScanning;


    public Parser(TaskList taskList) {
        this.taskList = taskList;
        this.scanner = new Scanner(System.in);
        this.isScanning = true;
    }

    public boolean isScanning() {
        return this.isScanning;
    }

    public void parse() throws DukeException {
        String line = scanner.nextLine();

        if (line.equals("bye")) {
            this.isScanning = false;
            scanner.close();
            Storage.save(this.taskList);
            Ui.showGoodbye();
        } else if (line.equals("list")) {
            taskList.showList();
        } else if (line.startsWith("unmark")) {
            if (taskList.isEmpty()) {
                throw new DukeException("OOPS!!! Cannot unmark when list is empty");
            }
            if (line.length() <= 7) {
                throw new DukeException("OOPS!!! Please enter a number after unmark");
            }
            int index = Integer.parseInt(line.replaceAll("[^0-9]", "")); // regex gotten from https://stackoverflow.com/a/14974126
            taskList.get(index - 1).markAsUndone();
            Ui.show("\tNice! I've marked this task as not done yet:");
            Ui.show("\t" + taskList.get(index - 1));
        } else if (line.startsWith("mark")) {
            if (taskList.isEmpty()) {
                throw new DukeException("OOPS!!! Cannot mark when list is empty");
            }
            if (line.length() <= 5) {
                throw new DukeException("OOPS!!! Please enter a number after mark");
            }
            int index = Integer.parseInt(line.replaceAll("[^0-9]", ""));
            taskList.get(index - 1).markAsDone();
            Ui.show("\tNice! I've marked this task as done:");
            Ui.show("\t" + taskList.get(index - 1));
        } else if (line.startsWith("todo")) {
            if (line.length() <= 5) {
                throw new DukeException("OOPS!!! The description of a todo cannot be empty.");
            }
            Todo todo = new Todo(line.replace("todo ", ""));
            taskList.add(todo);
            Ui.showLine();
            Ui.show("\tGot it. I've added this task:");
            Ui.show("\t\t" + todo);
            Ui.show("\tNow you have " + taskList.size() + " tasks in the list.");
            Ui.showLine();
        } else if (line.startsWith("deadline")) {
            if (line.length() <= 9) {
                throw new DukeException("OOPS!!! The description of a deadline cannot be empty.");
            }
            int idxOfBy = line.indexOf("/by");
            if (idxOfBy == -1) {
                throw new DukeException("OOPS!!! The description of a deadline must include /by");
            }
            try {
                new Deadline(line.substring(9, idxOfBy), line.substring(idxOfBy + 4));
            } catch (DateTimeParseException e) {
                throw new DukeException("Error! Please enter deadlines in this format:\t\ndeadline TASK /by DD-MM-YYYY");
            }
            Deadline deadline = new Deadline(line.substring(9, idxOfBy), line.substring(idxOfBy + 4));
            taskList.add(deadline);
            Ui.showLine();
            Ui.show("\tGot it. I've added this task:");
            Ui.show("\t\t" + deadline);
            Ui.show("\tNow you have " + taskList.size() + " tasks in the list.");
            Ui.showLine();
        } else if (line.startsWith("event")) {
            if (line.length() <= 6) {
                throw new DukeException("OOPS!!! The description of a event cannot be empty.");
            }
            int idxOfAt = line.indexOf("/at");
            if (idxOfAt == -1) {
                throw new DukeException("OOPS!!! The description of a event must include /at");
            }
            Event event = new Event(line.substring(6, idxOfAt), line.substring(idxOfAt + 4));
            taskList.add(event);
            Ui.showLine();
            Ui.show("\tGot it. I've added this task:");
            Ui.show("\t\t" + event);
            Ui.show("\tNow you have " + taskList.size() + " tasks in the list.");
            Ui.showLine();
        } else if (line.startsWith("delete")) {
            if (line.length() <= 7) {
                throw new DukeException("OOPS!!! Please enter a number after delete");
            }
            int index = Integer.parseInt(line.replaceAll("[^0-9]", ""));
            if (index > taskList.size() || index < 0) {
                throw new DukeException("OOPS!!! Invalid number to delete");
            }
            Ui.showLine();
            Ui.show("\tNoted. I've removed this task:");
            Ui.show("\t\t" + taskList.get(index - 1));
            taskList.remove(index - 1);
            Ui.show("\tNow you have " + taskList.size() + " tasks in the list.");
            Ui.showLine();
        } else if (line.startsWith("save")) {
            Ui.show("Saving progress...");
            Storage.save(taskList);
            Ui.show("Successfully saved!");
        } else {
            Ui.show("OOPS!!! I'm sorry, but I don't know that that means :(");
        }
    }

}
