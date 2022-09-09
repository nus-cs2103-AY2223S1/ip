package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Used to parse user's commands entered and execute the command
 */
public class Parser {
    private Storage s;
    private Ui ui;
    private TaskList tasks;

    public Parser(Storage s, Ui ui, TaskList taskList) {
        this.s = s;
        this.ui = ui;
        this.tasks = taskList;
    }

    public String parse(String command) throws DukeException {
        if (command.equals("bye")) {
            return executeBye();
        } else if (command.equals("list")) {
            return executeList();
        } else if (command.startsWith("done")) {
           return executeDone(command);
        } else if (command.startsWith("delete")) {
           return executeDelete(command);
        } else if (command.startsWith("find")) {
            return executeFind(command);
        } else if (command.startsWith("todo")) {
            return executeTodo(command);
        } else if (command.startsWith("event")) {
            return executeEvent(command);
        } else if (command.startsWith("deadline")) {
           return executeDeadline(command);
        } else if (command.startsWith("view")) {
            return executeViewShedule(command);
        } else {
            throw new DukeException("Sorry, I don't know what that means");
        }
    }

    public String executeBye() {
        return ui.showGoodbye();
    }

    public String executeList() {
        String result = "";
        for (int i = 0; i < tasks.getSize(); i++) {
            Task nextTask = tasks.get(i);
            result += "\n" + ((i + 1) + ". " + nextTask.toString());
        }
        return result;
    }

    public String executeDone(String command) {
        int completedIndex = Character.getNumericValue(command.charAt(5));
        Task currentTask = tasks.get(completedIndex - 1);
        currentTask.setComplete(true);
        s.writeFile(tasks);
        return("Nice! I've marked this task as done: [X] " + currentTask.getTaskName());
    }

    public String executeDelete(String command) {
        int deleteIndex = Character.getNumericValue(command.charAt(7));
        Task deletedTask = tasks.get(deleteIndex - 1);
        tasks.delete(deleteIndex - 1);
        s.writeFile(tasks);
        return("Noted. I've removed this task:" + deletedTask.toString()
                + "\nNow you have " +  tasks.getSize() +  " tasks in the list.");
    }

    public String executeFind(String command) {
        String result = "";
        result += "Here are the matching tasks in your list";
        String keyword = command.substring(5);
        for (int i = 0; i < tasks.getSize(); i++) {
            if(tasks.get(i).toString().contains(keyword)) {
                result += "\n"+ tasks.get(i).toString() ;
            }
        }
        return result;
    }

    public String executeTodo(String command) {
        try {
            if (command.length() == 4) {
                throw new DukeException("OOPS!!! The description of a todo cannot be empty.");
            }
            Task newTask = new Todo(command, false);
            tasks.add(newTask);
            return AddTask();
        } catch (DukeException e) {
            return (e.getMessage());
        }
    }

    public String executeDeadline(String command) {
        try {
            if (command.length() == 8) {
                throw new DukeException("OOPS!!! The description of a deadline cannot be empty.");
            }
            Task newTask = new Deadline(command, false);
            tasks.add(newTask);
            return AddTask();
        } catch (DukeException e) {
            return(e.getMessage());
        }
    }

    public String executeEvent(String command) {
        try {
            if (command.length() == 5) {
                throw new DukeException("OOPS!!! The description of an event cannot be empty.");
            }
            Task newTask = new Event(command, false);
            tasks.add(newTask);
           return AddTask();
        } catch (DukeException e) {
            return (e.getMessage());
        }
    }

    public String executeViewShedule(String command) {
        String dateString = command.substring(5);
        LocalDate date = LocalDate.parse(dateString);
        String formattedDate = date.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        String result = "";
        for (int i = 0; i < tasks.getSize(); i++) {
            String taskString = tasks.get(i).toString();
            if (taskString.contains(formattedDate)) {
                result += (i+1) + ". " + taskString + "\n";
            }
        }
        return "Here are the tasks on " + formattedDate + ":" + "\n" + result;
    }

    public String AddTask() {
        Task addedTask = tasks.get(tasks.getSize() - 1);
        s.writeFile(tasks);
        return ("Got it. I've added this task: \n" + addedTask.toString() +
                "\nNow you have " +  tasks.getSize() +  " tasks in the list.");
    }
}
