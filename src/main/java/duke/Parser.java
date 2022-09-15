package duke;

import java.io.FileNotFoundException;
import java.io.IOException;

/** A class that handles the user input commands. */
public class Parser {
    private TaskList taskList;
    private Storage storage;

    /**
     * Initialises the Parser object.
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
     *
     * @param input String that the user enters into the text field.
     * @return String that shows output from the Duke bot.
     */
    public String parse(String input) {
        //Split the string into words stored in an array
        String[] inputString = input.split(" ");
        String firstWord = inputString[0];
        int numberOfWords = inputString.length;

        //Forms the string without the first word
        String restOfString = "";
        for (int i = 1; i < numberOfWords; i++) {
            restOfString += (" " + inputString[i]);
        }

        //Parse user input
        try {
            if (input.equals("bye")) {
                return "Bye. Hope to see you again soon!";
            } else if (input.equals("list")) {
                return this.taskList.printList();
            } else if (firstWord.equals("mark")) {
                return this.markTask(inputString);
            } else if (firstWord.equals("unmark")) {
                return this.unmarkTask(inputString);
            } else if (firstWord.equals("high") || firstWord.equals("medium") || firstWord.equals("low")) {
                return this.setPriority(inputString);
            } else if (firstWord.equals("todo")) {
                return this.parseToDo(restOfString);
            } else if (firstWord.equals("deadline")) {
                return this.parseDeadline(restOfString);
            } else if (firstWord.equals("event")) {
                return this.parseEvent(restOfString);
            } else if (firstWord.equals("delete")) {
                return this.parseDelete(inputString);
            } else if (firstWord.equals("find")) {
                return this.parseFind(restOfString);
            } else {
                throw new DukeException("OOPS!!! I'm sorry, " + "but I don't know what that means :-(\n");
            }
        } catch (DukeException e) {
            return e.getMessage();
        } catch (IOException e) {
            return e.getMessage();
        }
    }

    /**
     * Sets priority of the task.
     *
     * @param inputString String array containing all the words in the user input.
     * @return String showing that the priority of the task has been set.
     * @throws IOException From the writeToFile() method.
     */
    public String setPriority(String[] inputString) throws IOException {
        //Get the task number to set priority for
        int number = Integer.parseInt(inputString[1]);

        //Set priority of task
        this.taskList.setTaskPriority(number, inputString[0]);

        //Update the contents of the task list file
        this.storage.writeToFile();

        return "Nice! I've set this task as " + inputString[0] + " priority:\n "
                + this.taskList.getTask(number - 1).toString() + '\n';
    }

    /**
     * Commands the Duke bot to mark a task.
     *
     * @param inputString String array that contains all the words in the user input.
     * @return String showing that the chosen task is marked as done.
     * @throws IOException From the writeToFile() method.
     */
    public String markTask(String[] inputString) throws IOException {
        //Get the task number to mark
        int number = Integer.parseInt(inputString[1]);

        //Mark the task in the task list as done
        this.taskList.markList(number);

        //Update the contents of the task list file
        this.storage.writeToFile();

        return "Nice! I've marked this task as done:\n "
                + this.taskList.getTask(number - 1).toString() + '\n';
    }

    /**
     * Commands the Duke bot to unmark a task.
     *
     * @param inputString String array that contains all the words in the user input.
     * @return String showing that the chosen task is unmarked as not done.
     * @throws IOException From the writeToFile() method.
     */
    public String unmarkTask(String[] inputString) throws IOException {
        //Get the task number to unmark
        int number = Integer.parseInt(inputString[1]);

        //Unmark the task in the task list as not done
        this.taskList.unMarkList(number);

        //Update the contents in the task list file
        this.storage.writeToFile();

        return "OK, I've marked this task as not done yet:\n "
                + this.taskList.getTask(number - 1).toString() + '\n';
    }

    /**
     * Commands the Duke bot to add a Todo task.
     *
     * @param restOfString String that contains the necessary information for the Todo task.
     * @return String showing the Todo task that was added.
     * @throws DukeException Thrown when user does not enter any description and date and time for the task.
     * @throws IOException From the writeToFile() method.
     */
    public String parseToDo(String restOfString) throws DukeException, IOException {
        //Get description and date and time of task
        String toDo = restOfString;

        if (toDo.equals("")) {
            //Throw exception if description and date and time is empty
            throw new DukeException("OOPS!!! " + "The description of a todo cannot be empty.\n");
        } else {
            //Add ToDo task to taskList
            ToDo toDoTask = new ToDo(toDo);
            this.taskList.addTask(toDoTask);

            //Update contents in the task list file
            this.storage.writeToFile();

            //Increment index of taskList
            this.taskList.incrementIndex();

            return "Got it. I've added this task:\n"
                    + this.taskList.getTask(this.taskList.getIndex() - 1).toString()
                    + "\nNow you have " + this.taskList.getIndex() + " tasks in the list.\n";
        }
    }

    /**
     * Commands the Duke bot to create a Deadline task.
     *
     * @param restOfString String input containing description and date and time of Deadline task.
     * @return String showing the Deadline task that was added.
     * @throws DukeException Thrown when user does not enter description and date and time for the task.
     * @throws IOException From the writeToFile() method.
     */
    public String parseDeadline(String restOfString) throws DukeException, IOException {
        //Get description and date and time of Deadline task
        String deadline = restOfString;

        if (deadline.equals("")) {
            //User only types "deadline"
            throw new DukeException("OOPS!!! " + "The description of a deadline cannot be empty.\n");
        } else if (!deadline.contains("/by")) {
            //User only types description of Deadline task
            throw new DukeException("No date and time set for deadline task!\n");
        } else {
            //Getting the description, date and time of the Deadline task
            int integer = deadline.indexOf("/by");
            String description = deadline.substring(0, integer - 1);
            String by = deadline.substring(integer + 4);

            //Create a new Deadline task
            Deadline deadlineTask = new Deadline(description, by);

            //Parse date and time of Deadline task
            if (by.contains("/") || by.contains("-")) {
                String dateTime = this.parseDateAndTime(by);
                String[] dateTimeArray = dateTime.split(" ");
                deadlineTask.setDate(dateTimeArray[0]);
                deadlineTask.setTime(dateTimeArray[1]);
            }

            //Add Deadline task to the taskList array
            this.taskList.addTask(deadlineTask);

            //Update contents of the task list file
            this.storage.writeToFile();

            //Increment index of task list array
            this.taskList.incrementIndex();

            return "Got it. I've added this task:\n "
                    + this.taskList.getTask(this.taskList.getIndex() - 1).toString()
                    + "\nNow you have " + this.taskList.getIndex() + " tasks in the list.\n";
        }
    }

    /**
     * Parses date and time of task.
     *
     * @param dateAndTime String containing date and time of the task.
     * @return String representing date and time after parsing.
     */
    public String parseDateAndTime(String dateAndTime) {
        //Get the date and time of task individually
        int space = dateAndTime.indexOf(' ');
        String date = dateAndTime.substring(0, space);
        String time = dateAndTime.substring(space + 1);

        //Modifies the user input of date and time for parsing
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

        return date + " " + time;
    }

    /**
     * Commands the Duke bot to add an Event task.
     *
     * @param restOfString String input from user containing the necessary information to create an Event task.
     * @return String showing the Event task that was added.
     * @throws DukeException Thrown when user does not enter any description or date and time for the task.
     * @throws IOException From the writeToFile() method.
     */
    public String parseEvent(String restOfString) throws DukeException, IOException {
        //Get description and date and time of Event task
        String event = restOfString;

        if (event.equals("")) {
            //User only inputs "event"
            throw new DukeException("OOPS!!! " + "The description of a event cannot be empty.\n");
        } else if (!event.contains("/at")) {
            //User only inputs description of Event task
            throw new DukeException("No date and time set for event task!\n");
        } else {
            //Get the description, date and time of the Event task
            int integer = event.indexOf("/at");
            String description = event.substring(0, integer - 1);
            String at = event.substring(integer + 4);

            //Create a new Event task
            Event eventTask = new Event(description, at);

            //Parse date and time of Event task
            if (at.contains("/") || at.contains("-")) {
                String dateTime = this.parseDateAndTime(at);
                String[] dateTimeArray = dateTime.split(" ");
                eventTask.setDate(dateTimeArray[0]);
                eventTask.setTime(dateTimeArray[1]);
            }

            //Add Event task to taskList array
            this.taskList.addTask(eventTask);

            //Update contents of the task list file
            this.storage.writeToFile();

            //Increment index of the taskList array
            this.taskList.incrementIndex();

            return "Got it. I've added this task:\n "
                    + this.taskList.getTask(this.taskList.getIndex() - 1).toString() + "\nNow you have "
                    + this.taskList.getIndex() + " tasks in the list.\n";
        }
    }

    /**
     * Commands the Duke bot to delete a task.
     *
     * @param inputString String array of words entered by the user.
     * @return String containing the task that was removed and number of tasks left.
     * @throws IOException From the writeToFile() method.
     */
    public String parseDelete(String[] inputString) throws IOException {
        //Get the task number to delete
        int deleteIndex = Integer.parseInt(inputString[1]);

        //Get the task in the task list
        Task task = this.taskList.getTask(deleteIndex - 1);

        //Remove the task from the task list
        this.taskList.removeTask(deleteIndex - 1);

        //Decrement the index of the task list
        this.taskList.decrementIndex();

        //Update contents of the task list file
        this.storage.writeToFile();

        return "Noted. I've removed this task:\n " + task.toString() + "\nNow you have "
                + (this.taskList.getIndex()) + " tasks in the list.\n";
    }

    /**
     * Commands the Duke bot to find tasks.
     *
     * @param restOfString String input from user with the keywords to find tasks.
     * @return List of tasks containing the keywords.
     * @throws FileNotFoundException From the findTasks method.
     */
    public String parseFind(String restOfString) throws FileNotFoundException {
        //Get the keywords to find tasks
        String task = restOfString;

        //Find the tasks in the file with the keywords
        String output = this.storage.findTasks(task);

        return output;
    }
}

