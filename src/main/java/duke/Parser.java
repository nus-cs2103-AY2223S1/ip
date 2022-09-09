package duke;

import java.io.IOException;
import java.util.Scanner;

/** A class that handles the user input commands. */
public class Parser {

    private TaskList taskList;
    private Storage storage;

    /**
     * A constructor that initialises the Parser object.
     *
     * @param taskList List of tasks stored as an array.
     * @param storage  Stores the data of the list of tasks.
     */
    public Parser(TaskList taskList, Storage storage) {
        this.taskList = taskList;
        this.storage = storage;
    }

    /**
     * Parses the input commands entered by the user for GUI.
     */
    public String parse(String input) {
        String[] inputString = input.split(" ");
        String firstWord = inputString[0];
        int numberOfWords = inputString.length;

        String restOfString = "";
        for (int i = 1; i < numberOfWords; i++) {
            restOfString += (" " + inputString[i]);
        }

        try {
            if (input.equals("bye")) {
                return "Bye. Hope to see you again soon!";

            } else if (input.equals("list")) {
                return this.taskList.printList();

            } else if (firstWord.equals("mark")) {
                int number = Integer.parseInt(inputString[1]);

                this.taskList.markList(number);

                this.storage.writeToFile();

                return "Nice! I've marked this task as done:\n "
                        + this.taskList.getTask(number - 1).toString() + '\n';
            } else if (firstWord.equals("unmark")) {
                assert inputString[1] != null;
                int number = Integer.parseInt(inputString[1]);

                this.taskList.unMarkList(number);

                this.storage.writeToFile();

                return "OK, I've marked this task as not done yet:\n "
                        + this.taskList.getTask(number - 1).toString() + '\n';
            } else if (firstWord.equals("todo")) {
                String toDo = restOfString;

                if (toDo.equals("")) {
                    throw new DukeException("OOPS!!! " + "The description of a todo cannot be empty.\n");

                } else {
                    ToDo toDoTask = new ToDo(toDo);

                    this.taskList.addTask(toDoTask);

                    this.storage.writeToFile();

                    this.taskList.incrementIndex();

                    return "Got it. I've added this task:\n"
                            + this.taskList.getTask(this.taskList.getIndex() - 1).toString()
                            + "\nNow you have " + this.taskList.getIndex() + " tasks in the list.\n";
                }
            } else if (firstWord.equals("deadline")) {
                String deadline = restOfString;

                if (deadline.equals("")) {
                    throw new DukeException("OOPS!!! " +
                            "The description of a deadline cannot be empty.\n");
                } else {
                    assert deadline.contains("by") : "Deadline task has no deadline set!";
                    int integer = deadline.indexOf("/by");
                    String description = deadline.substring(0, integer - 1);
                    String by = deadline.substring(integer + 4);

                    Deadline deadLineTask = new Deadline(description, by);

                    if (by.contains("/") || by.contains("-")) {
                        int space = by.indexOf(' ');
                        String date = by.substring(0, space);
                        String time = by.substring(space + 1);

                        if (date.contains("/")) {
                            int firstSlash = date.indexOf('/', 0);
                            int secondSlash = date.indexOf('/', firstSlash + 1);
                            String day = date.substring(0, firstSlash);
                            String month = date.substring(firstSlash + 1, secondSlash);

                            if (day.length() == 1) {
                                day = "0" + day;
                            }

                            if (month.length() == 1) {
                                month = "0" + month;
                            }

                            String year = date.substring(secondSlash + 1);
                            date = (year + "-" + month + "-" + day);
                        }

                        String min = time.substring(2);
                        String hour = time.substring(0, 2);
                        time = (hour + ":" + min);

                        deadLineTask.parseTime(time);
                        deadLineTask.parseDate(date);
                    }

                    this.taskList.addTask(deadLineTask);

                    this.storage.writeToFile();

                    this.taskList.incrementIndex();

                    return "Got it. I've added this task:\n "
                            + this.taskList.getTask(this.taskList.getIndex() - 1).toString()
                            + "\nNow you have " + this.taskList.getIndex() + " tasks in the list.\n";
                }
            } else if (firstWord.equals("event")) {
                String event = restOfString;

                if (event.equals("")) {
                    throw new DukeException("OOPS!!! " + "The description of a event cannot be empty.\n");

                } else {
                    assert event.contains("at") : "Event has no date and time!";
                    int integer = event.indexOf("/at");
                    String description = event.substring(0, integer - 1);
                    String at = event.substring(integer + 4);

                    Event eventTask = new Event(description, at);

                    if (at.contains("/") || at.contains("-")) {
                        int space = at.indexOf(' ');
                        String date = at.substring(0, space);
                        String time = at.substring(space + 1);

                        if (date.contains("/")) {
                            int firstSlash = date.indexOf('/', 0);
                            int secondSlash = date.indexOf('/', firstSlash + 1);
                            String day = date.substring(0, firstSlash);
                            String month = date.substring(firstSlash + 1, secondSlash);

                            if (day.length() == 1) {
                                day = "0" + day;
                            }

                            if (month.length() == 1) {
                                month = "0" + month;
                            }

                            String year = date.substring(secondSlash + 1);
                            date = (year + "-" + month + "-" + day);
                        }

                        String min = time.substring(2);
                        String hour = time.substring(0, 2);
                        time = (hour + ":" + min);

                        eventTask.parseDate(date);
                        eventTask.parseTime(time);
                    }

                    this.taskList.addTask(eventTask);

                    this.storage.writeToFile();

                    this.taskList.incrementIndex();

                    return "Got it. I've added this task:\n "
                            + this.taskList.getTask(this.taskList.getIndex() - 1).toString() + "\nNow you have "
                            + this.taskList.getIndex() + " tasks in the list.\n";
                }
            } else if (firstWord.equals("delete")) {
                assert inputString[1] != null;
                int deleteIndex = Integer.parseInt(inputString[1]);

                Task task = this.taskList.getTask(deleteIndex - 1);

                this.taskList.removeTask(deleteIndex - 1);

                this.taskList.decrementIndex();

                this.storage.writeToFile();

                return "Noted. I've removed this task:\n " + task.toString() + "\nNow you have "
                        + (this.taskList.getIndex()) + " tasks in the list.\n";
            } else if (firstWord.equals("find")) {
                String task = restOfString;

                String output = this.storage.findTasks(task);

                return output;
            } else {
                throw new DukeException("OOPS!!! I'm sorry, " + "but I don't know what that means :-(\n");

            }
        } catch (DukeException e) {
            return e.getMessage();
        } catch (IOException e) {
            return e.getMessage();
        }
    }
}

