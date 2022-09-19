package pixel.util;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.ResolverStyle;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import java.util.Objects;

import pixel.Pixel;
import pixel.task.Deadline;
import pixel.task.Event;
import pixel.task.Task;
import pixel.task.ToDo;

/**
 * Handles tasks
 */
public class TaskList {

    private final String filePath;

    private final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.US)
        .withResolverStyle(ResolverStyle.SMART);

    private final DateValidator validator = new DateValidator(dateFormatter);

    /**
     * Constructor for a new TaskList object
     *
     * @param filePath path of file to save the tasks
     */
    public TaskList(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Checks whether a duplicate task in the array list
     *
     * @param newTask task created from latest user input
     * @return whether a duplicate task exists in the array list
     */
    private boolean findDuplicate(Task newTask) {
        for (Task task : Storage.INPUT_TASKS) {
            if (task.toString().equals(newTask.toString())) {
                return true;
            }
        }
        return false;
    }

    /**
     * Lists out all the tasks in the array list
     *
     * @return a list of all the tasks recorded
     */
    public static String listTasks() {
        String output = "Here are the tasks in your list: \n";
        for (int i = 0; i < Pixel.getTaskCount(); i++) {
            Task currentTask = Storage.INPUT_TASKS.get(i);
            output += ((i + 1) + ". " + currentTask + "\n");
        }
        return output;
    }

    /**
     * Lists out all the tasks whose description contains the query string
     *
     * @param findResults an array list of results from the query
     * @return A list of all the tasks whose description contains the query string
     */
    public static String listFindResults(ArrayList<Task> findResults) {
        String output = "Here are the matching tasks in your list: \n";
        for (int i = 0; i < findResults.size(); i++) {
            Task currentTask = findResults.get(i);
            output += ((i + 1) + ". " + currentTask + "\n");
        }
        return output;
    }

    /**
     * converts input date&time from yyyy-MM-dd HHmm to MONTH dd yyyy hh:mm aa format
     *
     * @param due due date and time
     * @return date and time in MONTH dd yyyy hh:mm aa format
     */
    private String processDateTime(String due) throws ParseException, IndexOutOfBoundsException {
        String[] tempStringArray = due.strip().split(" ", 2);

        String dueDate = tempStringArray[0];
        String dueTime = tempStringArray.length < 2 ? "" : tempStringArray[1];

        if (!validator.isValid(dueDate)) {
            System.out.println("(Note: Due is not in yyyy-MM-dd(SPACE)HHmm format)");
            return due;
        }

        LocalDate inputDue = LocalDate.parse(dueDate);
        String year = String.valueOf(inputDue.getYear());
        String month = String.valueOf(inputDue.getMonth());
        String date = String.valueOf(inputDue.getDayOfMonth());

        //time pattern of input date in 24 hour format -- HH for 24h, hh for 12h
        DateFormat inputTimeFormat = new SimpleDateFormat("HHmm");
        inputTimeFormat.setLenient(false);

        //Date/time pattern of desired output date
        DateFormat outputTimeFormat = new SimpleDateFormat("hh:mm aa"); // aa for AM/ PM

        if (!Objects.equals(dueTime, "")) {
            Date inputTime = inputTimeFormat.parse(dueTime);
            String outputTime = outputTimeFormat.format(inputTime);
            return month + " " + date + " " + year + " " + outputTime;
        }

        return month + " " + date + " " + year;
    }

    /**
     * Creates a new task object, stores them in the array list and external text file
     * Also creates a new pixel.txt file automatically if no such file exists
     *
     * @param userInput input of user
     * @param type type of task to create
     * @throws IOException
     * @throws DuplicateEntryException
     * @throws ParseException
     */
    public String handleNewTask(String userInput, Parser.TaskType type)
            throws IOException, DuplicateEntryException, ParseException {

        int indexOfSlash = userInput.indexOf("/"); // returns -1 if such a string doesn't exist
        // If there's a "/by" or "/at" in the input string, then the info behind the "/by" or "/at" is the due
        // if there's no "/by" and "/at" string, then due should be empty
        String due = indexOfSlash == -1 ? "" : userInput.substring(indexOfSlash + 4);

        try {
            due = processDateTime(due);
        } catch (IndexOutOfBoundsException e) {
            throw new IncorrectFormatException("Please ensure that you have entered "
                + "both date and time in yyyy-MM-dd(SPACE)HHmm(24h) format");
        }

        int indexOfEndOfDescription = (indexOfSlash == -1) ? userInput.length() : indexOfSlash;
        Task newTask;
        String commandWord = "";

        if (indexOfSlash != -1) {
            if (userInput.substring(indexOfSlash + 1).startsWith("by")) {
                commandWord = "by";
            } else if (userInput.substring(indexOfSlash + 1).startsWith("at")) {
                commandWord = "at";
            } else {
                throw new IncorrectFormatException("Slash should be followed by \"by\" or \"at\"!"); // programme breaks
            }
        }

        switch (type) {
        case TODO: {
            String description = userInput.substring(5, indexOfEndOfDescription).strip();
            newTask = new ToDo(description, due, commandWord); // Stores user input

            break;
        }
        case DEADLINE: { // deadline
            String description = userInput.substring(9, indexOfEndOfDescription).strip();
            newTask = new Deadline(description, due, commandWord); // Stores user input

            break;
        }
        case EVENT: { // event
            String description = userInput.substring(6, indexOfEndOfDescription).strip();
            newTask = new Event(description, due, commandWord); // Stores user input

            break;
        }
        default: //shouldn't reach here
            throw new IncorrectFormatException("Incorrect format of input!"); // programme breaks
        }

        if (findDuplicate(newTask)) {
            throw new DuplicateEntryException("Same task already exists in database!");
        }

        Storage.INPUT_TASKS.add(Pixel.getTaskCount(), newTask);

        // index of last element in ArrayList is always smaller than size
        assert Storage.INPUT_TASKS.size() == (Pixel.getTaskCount() + 1)
            : "Size of ArrayList did not increase by 1 after adding new task";

        // Not so efficient method
        // first delete existing content in old file
        Storage.resetFile(this.filePath);

        // run through all the files in the list and update pixel.txt accordingly
        Storage.appendAllTasksToFile(this.filePath);

        Pixel.addOneToTaskCount();

        return ("Got it. I've added this task: \n"
            + newTask + "\n"
            + "Now you have " + Pixel.getTaskCount() + " tasks in the list.");
    }

}
