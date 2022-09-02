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

        if ("T ".equals(firstLetter)) {
            Task currTask = new Todo(description);
            if (markedDone.equals(" X ")) {
                currTask.markAsDone();
            }
            return currTask;
        } else if ("D ".equals(firstLetter)) {
            Task currTask = new Deadline(description, LocalDate.parse(currStrArr[3]));
            if (markedDone.equals(" X ")) {
                currTask.markAsDone();
            }
            return currTask;
        } else {
            Task currTask = new Event(description, currStrArr[3]);
            if (markedDone.equals(" X ")) {
                currTask.markAsDone();
            }
            return currTask;
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

        try {
            if ("list".equals(firstWord)) {
                // list out the current list
                return Ui.printList(tasks);

            } else if ("mark".equals(firstWord)) {
                // throw exception if no number after mark
                if (arrOfInput.length < 2) {
                    throw new DukeException("Input a number after mark!");
                }

                // to mark an element as done
                int index = Integer.parseInt(arrOfInput[1]);
                return tasks.markTaskAsDone(index);

            } else if ("unmark".equals(firstWord)) {
                // throw exception if no number after unmark
                if (arrOfInput.length < 2) {
                    throw new DukeException("Input a number after unmark!");
                }

                // to mark an element as undone
                int index = Integer.parseInt(arrOfInput[1]);
                return tasks.unmarkTask(index);

            } else if ("todo".equals(firstWord) || "deadline".equals(firstWord) || "event".equals(firstWord)) {
                // adding the event, deadline or to-do to the list
                return createNewTask(firstWord, arrOfInput, tasks);

            } else if ("delete".equals(firstWord)) {
                // throw exception if no number after delete
                if (arrOfInput.length < 2) {
                    throw new DukeException("Input a number after delete!");
                }

                // deleting a task
                int index = Integer.parseInt(arrOfInput[1]);
                return tasks.deleteTask(index);

            } else if ("find".equals(firstWord)) {
                // throw exception if no word after find
                if (arrOfInput.length < 2) {
                    throw new DukeException("Input a word after find!");
                }

                // finding a task from the list
                String str = arrOfInput[1];
                return tasks.find(str);

            } else {
                throw new DukeException("I'm sorry, but I don't know what that means :-(");
            }
        } catch (DukeException e) {
            return "Something went wrong " + e.getMessage();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "Error! Please try again.";
    }

    /**
     * Creates a new task and adds it to the task list.
     *
     * @param firstWord the first word typed in by the user
     * @param strArray the array of strings of the words typed in by the user
     */
    public static String createNewTask(String firstWord, String[] strArray, TaskList tasks)
            throws DukeException, IOException {
        if ("todo".equals(firstWord)) {
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

        } else if ("deadline".equals(firstWord)) {
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

            // create deadline description and deadline date
            StringBuilder deadlineStr = new StringBuilder();
            String deadline = "";
            for (int i = 1; i < strArray.length; i++) {
                if (strArray[i].equals("/by")) {
                    if (i + 1 > strArray.length - 1) {
                        return "Please type a deadline after /by";
                    } else {
                        deadline = strArray[i + 1];
                    }
                    break;
                } else {
                    deadlineStr.append(" ");
                    deadlineStr.append(strArray[i]);
                }
            }
            String deadlineDescription = deadlineStr.toString();

            // make sure deadline is in correct format to accept input
            String deadlinePattern = "yyyy-MM-dd";
            DateTimeFormatter deadlineFormatter = DateTimeFormatter.ofPattern(deadlinePattern);
            try {
                LocalDate.parse(deadline, deadlineFormatter);
            } catch (DateTimeParseException e) {
                return "Invalid deadline format! Please type in deadline format as yyyy-MM-dd";
            }

            // add deadline tasks to ArrayList once conditions are satisfied
            tasks.add(new Deadline(deadlineDescription, LocalDate.parse(deadline)));
            Task currTask = tasks.get(tasks.size() - 1);

            Storage.writeToFile(tasks);

            // print message when deadline is added
            return "Got it. I've added this task:\n  " + currTask +
                    "\nNow you have " + tasks.size() + " tasks in the list.";

        } else if ("event".equals(firstWord)) {// throw exception if no word after event
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
                        + "Add again with /at followed by the deadline timing.");
            }

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
        return "Error! Please try again.";
    }
}
