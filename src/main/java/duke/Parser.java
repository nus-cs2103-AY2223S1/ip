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
    public static boolean parse(String input, TaskList tasks) {
        String[] arrOfInput = input.split(" ");
        String firstWord = arrOfInput[0];

        try {
            if ("bye".equals(firstWord)) {
                // end program when input is bye
                Ui.sayGoodbye();
                return true;

            } else if ("list".equals(firstWord)) {
                // list out the current list
                Ui.printListStartingMessage();
                for (int i = 0; i < tasks.size(); i++) {
                    Task currTask = tasks.get(i);
                    Ui.printTask(i + 1, currTask);
                }

            } else if ("mark".equals(firstWord)) {
                // to mark an element as done
                int index = Integer.parseInt(arrOfInput[1]);
                tasks.markTaskAsDone(index);
                Storage.writeToFile(tasks);

            } else if ("unmark".equals(firstWord)) {
                // to mark an element as undone
                int index = Integer.parseInt(arrOfInput[1]);
                tasks.unmarkTask(index);
                Storage.writeToFile(tasks);

            } else if ("todo".equals(firstWord) || "deadline".equals(firstWord) || "event".equals(firstWord)) {
                // adding the event, deadline or to-do to the list
                createNewTask(firstWord, arrOfInput, tasks);

            } else if ("delete".equals(firstWord)) {// deleting a task
                int index = Integer.parseInt(arrOfInput[1]);
                tasks.deleteTask(index);
                Storage.writeToFile(tasks);

            } else {
                throw new DukeException("I'm sorry, but I don't know what that means :-(");
            }
        } catch (DukeException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * Creates a new task and adds it to the task list.
     *
     * @param firstWord the first word typed in by the user
     * @param strArray the array of strings of the words typed in by the user
     */
    public static void createNewTask(String firstWord, String[] strArray, TaskList tasks)
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

            // print message when to-do is added
            System.out.println("Got it. I've added this task:\n  " + currTask +
                    "\nNow you have " + tasks.size() + " tasks in the list.");
            Storage.writeToFile(tasks);

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
                        System.out.println("Please type a deadline after /by");
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
                System.out.println("Invalid deadline format! Please type in deadline format as yyyy-MM-dd");
                return;
            }

            // add deadline tasks to ArrayList once conditions are satisfied
            tasks.add(new Deadline(deadlineDescription, LocalDate.parse(deadline)));
            Task currTask = tasks.get(tasks.size() - 1);

            // print message when deadline is added
            System.out.println("Got it. I've added this task:\n  " + currTask +
                    "\nNow you have " + tasks.size() + " tasks in the list.");
            Storage.writeToFile(tasks);

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

            // print message when event is added
            System.out.println("Got it. I've added this task:\n  " + currTask +
                    "\nNow you have " + tasks.size() + " tasks in the list.");
            Storage.writeToFile(tasks);
        }
    }
}
