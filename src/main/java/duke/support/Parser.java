package duke.support;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import duke.functions.TaskList;
import duke.functions.Ui;
import duke.tasks.*;
import duke.GUI.Main;

/**
 * Parser class to make sense of user input.
 * @author lauralee
 */
public class Parser {

    private Ui user;
    private TaskList taskList;

    /**
     * Constructor for the Parser class.
     */
    public Parser() {
        this.user = new Ui();
        this.taskList = Main.getDuke().getUserTaskList(); //im creating a new tasklist everytime parser is called
    }

    /**
     * Initiates scanner system which receives input from users.
     */
    public String userInput(String input) {

        String a = input;
        String output = "";

        // if input received is anything but "bye" system will output what the user
        // inputted
        if (a.equals("list")) {
            //lists out all elements in task list
            output = Ui.printList(this.taskList);
        } else if (a.contains("unmark")) {
            // if unmark, update status
            char b = a.charAt(7);
            int c = Character.getNumericValue(b);
            assert (c <= Task.getNumberTasks()) : "There is no task " + c;
            this.taskList.markIncomplete(c);
            output = "ok! task " + c + " has been unmarked!";
        } else if (a.contains("mark")) {
            char b = a.charAt(5);
            int c = Character.getNumericValue(b);
            assert (c <= Task.getNumberTasks()) : "There is no task " + c;
            this.taskList.markComplete(c);
            output = "ok! task " + c + " has been marked!";
        } else if (a.contains("delete")) {
            char b = a.charAt(7);
            int c = Character.getNumericValue(b);
            assert (c <= Task.getNumberTasks()) : "There is no task " + c;
            int numberTasksLeft = Task.getNumberTasks() - 1;
            Task deletedTask = this.taskList.getTaskArr()[c];
            this.taskList.deleteTask(c, numberTasksLeft);
            output = Ui.printDelete(deletedTask, numberTasksLeft);
        } else if (a.contains("todo")) {
            try {
                a.substring(5);
            } catch (Exception StringIndexOutOfBoundsException) {
                return DukeException.todoException();
            }
            String description = a.substring(5);
            Todo newTask = new Todo(description);
            // add task to this TaskList
            this.taskList.addTask(newTask);
            output = Ui.printToDo(newTask);
        } else if (a.contains("deadline")) {
            assert a.contains("by") : "Please provide a deadline.";
            String description = a.substring(9, a.lastIndexOf("/") - 1);
            String day = a.substring(a.lastIndexOf("/by") + 4);
            try {
                LocalDate.parse(day);
                Deadline newTask = new Deadline(description, day);
                // add task to this TaskList
                this.taskList.addTask(newTask);
                output = Ui.printDeadline(newTask);
            } catch (DateTimeParseException exception) {
                return DukeException.dateTimeException();
            } catch (DateTimeException exceptionTwo) {
                return DukeException.dateTimeException();
            }
        } else if (a.contains("event")) {
            assert a.contains("at") : "please provide a deadline";
            String description = a.substring(6, a.lastIndexOf("/") - 1);
            String time = a.substring(a.lastIndexOf("/at") + 4);
            Event newTask = new Event(description, time);
            // add task to this TaskList
            this.taskList.addTask(newTask);
            output = Ui.printEvent(newTask);
        } else if (a.contains("find")) {
            String word = a.substring(5);
            int index = 1;
            String findList = "";
            assert(Task.getNumberTasks() > 0);
            for (int i = 1; i <= Task.getNumberTasks(); i++) {
                if (this.taskList.getTaskArr()[i].output().contains(word)) {
                    findList = findList + Ui.printFindTasks(index, this.taskList.getTaskArr()[i].output()) + "\n";
                    index++;
                }
            }
            assert index > 1 : "No tasks contain this word";
            output =  Ui.printFind() + "\n" + findList;
        } else if (a.equals("bye")) {
            output = Ui.printBye();
        } else {
            return DukeException.taskException();
        }
        return output;
    }

    /**
     * Retrieves TaskList created by the user.
     * @return The TaskList created by the user.
     */
    public TaskList getTaskList() {
        return this.taskList;
    }

}
