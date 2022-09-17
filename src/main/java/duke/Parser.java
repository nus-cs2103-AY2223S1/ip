package duke;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * A Parser to make sense from the inputs from the user.
 *
 * @author Denzel Tan
 */
public class Parser {
    /**
     * Method to parse a line of text from a text file.
     *
     * @param currLine the line of text from the text file
     * @return the Task to be added to the current TaskList
     */
    public static Task parseTaskFromText(String currLine) {
        String[] currStrArr = currLine.split("\\|");
        String firstLetter = currStrArr[0];
        String markedDone = currStrArr[1];
        String description = currStrArr[2];

        assert !firstLetter.isEmpty(): "No string detected from text!";
        if ("T ".equals(firstLetter)) {
            String[] lastStrArr = currStrArr[2].split("#");
            Task currTask = new Todo(lastStrArr[0]);
            if (markedDone.equals(" X ")) {
                currTask.markAsDone();
            }
            setTagFromText(currLine, currTask);
            return currTask;
        } else if ("D ".equals(firstLetter)) {
            String[] lastStrArr = currStrArr[3].split("#");
            Task currTask = new Deadline(description, LocalDate.parse(lastStrArr[0]));
            if (markedDone.equals(" X ")) {
                currTask.markAsDone();
            }
            setTagFromText(currLine, currTask);
            return currTask;
        } else {
            String[] lastStrArr = currStrArr[3].split("#");
            Task currTask = new Event(description, lastStrArr[0]);
            if (markedDone.equals(" X ")) {
                currTask.markAsDone();
            }
            setTagFromText(currLine, currTask);
            return currTask;
        }
    }


    /**
     * Method to set the tag of the task from the text.
     *
     * @param currLine the text to get the information from
     * @param currTask the current task to set the tag
     */
    public static void setTagFromText(String currLine, Task currTask) {
        String[] strArrTag = currLine.split("#");
        if (strArrTag.length > 1) {
            currTask.setTag(strArrTag[1]);
        }
    }


    /**
     * Method to parse the input from the user, and add it to the list of tasks.
     *
     * @param input the input from the user
     * @param tasks the list of tasks to be edited
     * @return the boolean true, to stop the program when bye is inputted
     */
    public static String parse(String input, TaskList tasks) {
        String[] arrOfInput = input.split(" ");
        String firstWord = arrOfInput[0];
        assert !firstWord.isEmpty(): "No string detected from input!";
        try {
            if ("list".equals(firstWord)) {
                return Ui.printList(tasks);
            } else if ("mark".equals(firstWord)) {
                return parseMarkTask(arrOfInput, tasks);
            } else if ("unmark".equals(firstWord)) {
                return parseUnmarkTask(arrOfInput, tasks);
            } else if ("todo".equals(firstWord)) {
                return createNewTodo(arrOfInput, tasks);
            } else if ("deadline".equals(firstWord)) {
                return createNewDeadline(arrOfInput, tasks);
            } else if ("event".equals(firstWord)) {
                return createNewEvent(arrOfInput, tasks);
            } else if ("delete".equals(firstWord)) {
                return parseDeleteTask(arrOfInput, tasks);
            } else if ("find".equals(firstWord)) {
                return parseFindTask(arrOfInput, tasks);
            } else if ("tag".equals(firstWord)) {
                return parseTagTask(arrOfInput, tasks);
            } else if ("findtag".equals(firstWord)) {
                return parseFindTag(arrOfInput, tasks);
            } else {
                throw new DukeException("I'm sorry, I don't know what that means :-(");
            }
        } catch (DukeException e) {
            return e.getMessage();
        } catch (IOException e) {
            e.printStackTrace();
            return e.getMessage();
        }
    }


    /**
     * Method for marking tasks when the command is mark.
     *
     * @param arrOfInput array of the words from the input
     * @param tasks the TaskList to access
     * @return the String to show after marking the task
     */
    public static String parseMarkTask(String[] arrOfInput, TaskList tasks) throws DukeException, IOException {
        if (arrOfInput.length < 2) {
            throw new DukeException("Input a number after mark!");
        }
        int index = Integer.parseInt(arrOfInput[1]);
        return tasks.markTaskAsDone(index);
    }


    /**
     * Method for marking tasks as undone when the command is unmark.
     *
     * @param arrOfInput array of the words from the input
     * @param tasks the TaskList to access
     * @return the String to show after marking the task as undone
     */
    public static String parseUnmarkTask(String[] arrOfInput, TaskList tasks) throws DukeException, IOException {
        if (arrOfInput.length < 2) {
            throw new DukeException("Input a number after unmark!");
        }
        int index = Integer.parseInt(arrOfInput[1]);
        return tasks.unmarkTask(index);
    }


    /**
     * Method for deleting tasks when the command is delete.
     *
     * @param arrOfInput array of the words from the input
     * @param tasks the TaskList to access
     * @return the String to show after deleting a task
     */
    public static String parseDeleteTask(String[] arrOfInput, TaskList tasks) throws DukeException, IOException {
        if (arrOfInput.length < 2) {
            throw new DukeException("Input a number after delete!");
        }
        int index = Integer.parseInt(arrOfInput[1]);
        return tasks.deleteTask(index);
    }


    /**
     * Method for finding tasks when the command is find.
     *
     * @param arrOfInput array of the words from the input
     * @param tasks the TaskList to access
     * @return the String to show after finding the tasks
     */
    public static String parseFindTask(String[] arrOfInput, TaskList tasks) throws DukeException {
        if (arrOfInput.length < 2) {
            throw new DukeException("Input a word after find!");
        }
        String str = arrOfInput[1];
        return tasks.find(str);
    }


    /**
     * Method to tag the task according to the user input.
     *
     * @param arrOfInput the array of words from the input
     * @param tasks the TaskList to access
     * @return the String to show after tagging the task
     */
    public static String parseTagTask(String[] arrOfInput, TaskList tasks) throws DukeException, IOException {
        // check for exceptions
        tagExceptionsCheck(arrOfInput);

        int index = Integer.parseInt(arrOfInput[1]);
        String tag = arrOfInput[3];
        return tasks.tagTask(index, tag);
    }


    /**
     * Method to check for exceptions when tagging a task.
     *
     * @param strArray the array of words from the input
     */
    public static void tagExceptionsCheck(String[] strArray) throws DukeException {
        // throw exception if no word after tag
        if (strArray.length != 4) {
            throw new DukeException("Please input a task number after tag followed by # and the tag name.");
        }

        // throw exception if no tag
        int indexCheck = 1000;
        for (int i = 1; i < strArray.length; i++) {
            if (strArray[i].equals("#")) {
                indexCheck = i;
            }
        }
        if (indexCheck == 1000) {
            throw new DukeException("Please input the tag right after #, like # homework");
        }
    }


    /**
     * Method to find the tasks that have the specific tag according to the user input.
     *
     * @param arrOfInput the array of words from the input
     * @param tasks the TaskList to find the tasks from
     * @return
     */
    public static String parseFindTag(String[] arrOfInput, TaskList tasks) throws DukeException {
        if (arrOfInput.length < 2) {
            throw new DukeException("Input a word after find!");
        }
        String str = arrOfInput[1];
        return tasks.findTag(str);
    }


    /**
     * Creates a new to-do and adds it to the task list.
     *
     * @param strArray the array of strings of the words typed in by the user
     * @param tasks the task list of which the task is to be added to
     */
    public static String createNewTodo(String[] strArray, TaskList tasks) throws DukeException, IOException {
        // throw exception if no word after to-do
        if (strArray.length < 2) {
            throw new DukeException("The description of a todo cannot be empty.");
        }

        StringBuilder todoStr = new StringBuilder();
        for (int i = 1; i < strArray.length; i++) {
            todoStr.append(" ").append(strArray[i]);
        }
        String currString = todoStr.toString();
        tasks.add(new Todo(currString));
        Task currTask = tasks.get(tasks.size() - 1);
        Storage.writeToFile(tasks);

        // print message when to-do is added
        return "Got it. I've added this task:\n  " + currTask +
                "\nNow you have " + tasks.size() + " tasks in the list.";
    }


    /**
     * Creates a new deadline and adds it to the task list.
     *
     * @param strArray the array of strings of the words typed in by the user
     * @param tasks the task list of which the task is to be added to
     */
    public static String createNewDeadline(String[] strArray, TaskList tasks) throws IOException, DukeException {
        // check for exceptions
        deadlineExceptionsCheck(strArray);

        String[] deadlineDescDate = deadlineDescDate(strArray);
        String deadlineDescription = deadlineDescDate[0];
        String deadlineDate = deadlineDescDate[1];

        // make sure deadline is in correct format to accept input
        String deadlinePattern = "yyyy-MM-dd";
        DateTimeFormatter deadlineFormatter = DateTimeFormatter.ofPattern(deadlinePattern);
        try {
            LocalDate.parse(deadlineDate, deadlineFormatter);
        } catch (DateTimeParseException e) {
            return "Invalid deadline format! Please type in deadline format as yyyy-MM-dd";
        }

        // add deadline tasks to ArrayList once conditions are satisfied
        tasks.add(new Deadline(deadlineDescription, LocalDate.parse(deadlineDate)));
        Task currTask = tasks.get(tasks.size() - 1);

        Storage.writeToFile(tasks);

        // print message when deadline is added
        return "Got it. I've added this task:\n  " + currTask +
                "\nNow you have " + tasks.size() + " tasks in the list.";
    }


    /**
     * Method to return the deadline description and date.
     *
     * @param strArray the array of strings to get the description and date from
     * @return the String array containing the deadline description and date
     */
    public static String[] deadlineDescDate(String[] strArray) throws DukeException {
        String[] endArray = new String[2];
        StringBuilder deadlineStr = new StringBuilder();
        String deadline = "";
        for (int i = 1; i < strArray.length; i++) {
            if (strArray[i].equals("/by")) {
                if (i + 1 > strArray.length - 1) {
                    throw new DukeException("Please type a deadline after /by");
                } else {
                    deadline = strArray[i + 1];
                }
                break;
            } else {
                deadlineStr.append(" ");
                deadlineStr.append(strArray[i]);
            }
        }
        endArray[0] = deadlineStr.toString();
        endArray[1] = deadline;
        return endArray;
    }


    /**
     * Method to check for exceptions for the deadline command.
     *
     * @param strArray the array of strings to check for exceptions
     */
    public static void deadlineExceptionsCheck(String[] strArray) throws DukeException {
        // throw exception if no word after deadline
        if (strArray.length < 2) {
            throw new DukeException("The description of a deadline cannot be empty.");
        }

        // throw exception if no deadline time
        int indexCheck = 1000;
        for (int i = 1; i < strArray.length; i++) {
            if (strArray[i].equals("/by")) {
                indexCheck = i;
            }
        }
        if (indexCheck == 1000) {
            throw new DukeException("This description needs a timing! "
                    + "Add again with /by followed by the deadline timing.");
        }
    }


    /**
     * Creates a new event and adds it to the task list.
     *
     * @param strArray the array of strings of the words typed in by the user
     * @param tasks the task list of which the task is to be added to
     */
    public static String createNewEvent(String[] strArray, TaskList tasks) throws DukeException, IOException {
        // check for exceptions
        eventExceptionsCheck(strArray);

        // create event string and deadline
        StringBuilder eventStr = new StringBuilder();
        StringBuilder eventTime = new StringBuilder();
        for (int i = 1; i < strArray.length; i++) {
            if (strArray[i].equals("/at")) {
                break;
            } else {
                eventStr.append(" ");
                eventStr.append(strArray[i]);
            }
        }
        for (int i = 1; i < strArray.length; i++) {
            if (strArray[i].equals("/at")) {
                for (int j = i + 1; j < strArray.length; j++) {
                    eventTime.append(" ");
                    eventTime.append(strArray[j]);
                }
                break;
            }
        }
        String eventDescription = eventStr.toString();
        String eventDate = eventTime.toString();
        tasks.add(new Event(eventDescription, eventDate));
        Task currTask = tasks.get(tasks.size() - 1);
        Storage.writeToFile(tasks);

        // print message when event is added
        return "Got it. I've added this task:\n  " + currTask +
                "\nNow you have " + tasks.size() + " tasks in the list.";
    }


    /**
     * Method to check for exceptions for the event command.
     *
     * @param strArray the array of strings to check for exceptions
     */
    public static void eventExceptionsCheck(String[] strArray) throws DukeException {
        // throw exception if no word after event
        if (strArray.length < 2) {
            throw new DukeException("The description of an event cannot be empty.");
        }

        // throw exception if no event time
        int indexCheck = 1000;
        for (int i = 1; i < strArray.length; i++) {
            if (strArray[i].equals("/at")) {
                indexCheck = i;
            }
        }
        if (indexCheck == 1000) {
            throw new DukeException("This description needs a timing! "
                    + "Add again with /at followed by the event timing.");
        }
    }
}
