import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.io.*;
import java.util.*;

public class Duke {
    /**
     * global variables
     */
    static Writer newTextLine;
    static ArrayList<Task> taskList = new ArrayList<>();

    /**
     * Prints the starting message.
     */
    public static void sayGreeting() {
        System.out.println("Hello! I'm Duke\n" + "What can I do for you?\n");
    }


    /**
     * Prints the end message.
     */
    public static void sayGoodbye() {
        System.out.println("Bye. Hope to see you again soon!");
    }


    /**
     * Find the text file if it exists, or create it if it does not.
     */
    public static void findFile() {
        try {
            File myObj = new File("./src/main/java/duke.txt");
            if (myObj.createNewFile()) {
                System.out.println("File created: " + myObj.getName());
            } else {
                System.out.println("File " + myObj.getName() + " exists, please continue and type in a task!");
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
        }
    }


    /**
     * Loads the current text file, updates the ArrayList.
     */
    public static void loadFile() {
        try {
            File myObj = new File("./src/main/java/duke.txt");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String currLine = myReader.nextLine();
                String[] currStrArr = currLine.split("\\|");
                String firstLetter = currStrArr[0];
                String markedDone = currStrArr[1];
                String description = currStrArr[2];

                if ("T ".equals(firstLetter)) {
                    taskList.add(new Todo(description));
                    Task currTask = taskList.get(taskList.size() - 1);
                    if (markedDone.equals("X")) {
                        currTask.markAsDone();
                    }
                } else if ("D ".equals(firstLetter)) {
                    taskList.add(new Deadline(description, LocalDate.parse(currStrArr[3])));
                    Task currTask = taskList.get(taskList.size() - 1);
                    if (markedDone.equals("X")) {
                        currTask.markAsDone();
                    }
                } else if ("E ".equals(firstLetter)) {
                    taskList.add(new Event(description, currStrArr[3]));
                    Task currTask = taskList.get(taskList.size() - 1);
                    if (markedDone.equals("X")) {
                        currTask.markAsDone();
                    }
                }
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred. File not found.");
            e.printStackTrace();
        }
    }


    /**
     * Prints the task list.
     */
    public static void printList() throws IOException {
        newTextLine = new BufferedWriter(new FileWriter("./src/main/java/duke.txt", false));
        System.out.println("Here are the tasks in your list:");
        for (int i = 1; i <= taskList.size(); i++) {
            Task currTask = taskList.get(i - 1);
            System.out.println(i + ". " + currTask);
            if (currTask instanceof Todo) {
                newTextLine.append("T | ").append(currTask.getStatusIcon()).append(" |")
                        .append(currTask.description).append("\n");
            } else if (currTask instanceof Deadline) {
                Deadline currDeadline = (Deadline) currTask;
                newTextLine.append("D | ").append(currDeadline.getStatusIcon()).append(" |")
                        .append(currDeadline.description).append("|").append(currDeadline.date.toString()).append("\n");
            } else if (currTask instanceof Event) {
                Event currEvent = (Event) currTask;
                newTextLine.append("E | ").append(currEvent.getStatusIcon()).append(" |")
                        .append(currEvent.description).append("|").append(currEvent.at).append("\n");
            }
        }
        newTextLine.close();
    }


    /**
     * Marks the specified task as done.
     *
     * @param index The index of the task to be mark done
     */
    public static void markTaskAsDone(int index) throws DukeException, IOException {
        if (1 <= index && index <= taskList.size()) {
            Task currTask = taskList.get(index - 1);
            currTask.markAsDone();
        } else {
            throw new DukeException("Input a valid task index!");
        }
        printList();
    }


    /**
     * Unmarks the specified task as not done.
     *
     * @param index The index of the task to be unmarked
     */
    public static void unmarkTask(int index) throws DukeException, IOException {
        if (1 <= index && index <= taskList.size()) {
            Task currTask = taskList.get(index - 1);
            currTask.markUndone();
        } else {
            throw new DukeException("Input a valid task index!");
        }
        printList();
    }


    /**
     * Deletes the specified task.
     *
     * @param index The index of the task to be deleted
     */
    public static void deleteTask(int index) throws DukeException, IOException {
        if (1 <= index && index <= taskList.size()) {
            System.out.println("Noted. I've removed this task:\n  " + taskList.get(index - 1));
            taskList.remove(index - 1);
            System.out.println("Now you have " + taskList.size() + " taskList in the list.");
        } else {
            throw new DukeException("Input a valid task index!");
        }
        printList();
    }


    /**
     * Creates a new task and adds it to the task list.
     *
     * @param firstWord the first word typed in by the user
     * @param strArray the array of strings of the words typed in by the user
     */
    public static void createNewTask(String firstWord, String[] strArray) throws DukeException, IOException {
        switch (firstWord) {
            case "todo": {
                // throw exception if no word after to-do
                if (strArray.length < 2) {
                    throw new DukeException("The description of a todo cannot be empty.");
                }

                StringBuilder todoStr = new StringBuilder();
                for (int i = 1; i < strArray.length; i++) {
                    todoStr.append(" ").append(strArray[i]);
                }
                String currString = todoStr.toString();
                taskList.add(new Todo(currString));
                Task currTask = taskList.get(taskList.size() - 1);

                // print message when to-do is added
                System.out.println("Got it. I've added this task:\n  " + currTask +
                        "\nNow you have " + taskList.size() + " tasks in the list.");
                printList();
                break;
            }

            case "deadline": {
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
                    throw new DukeException("This description needs a timing! Add again with /by followed by the deadline timing.");
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
                    break;
                }

                // add deadline tasks to ArrayList once conditions are satisfied
                taskList.add(new Deadline(deadlineDescription, LocalDate.parse(deadline)));
                Task currTask = taskList.get(taskList.size() - 1);

                // print message when deadline is added
                System.out.println("Got it. I've added this task:\n  " + currTask +
                        "\nNow you have " + taskList.size() + " tasks in the list.");
                printList();
                break;
            }

            case "event": {
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
                    throw new DukeException("This description needs a timing! Add again with /at followed by the deadline timing.");
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
                taskList.add(new Event(eventDescription, eventDate));
                Task currTask = taskList.get(taskList.size() - 1);

                // print message when event is added
                System.out.println("Got it. I've added this task:\n  " + currTask +
                        "\nNow you have " + taskList.size() + " tasks in the list.");
                printList();
                break;
            }

        }
    }


    /**
     * The main function.
     */
    public static void main(String[] args) {
        // print out starting message
        sayGreeting();

        Scanner sc = new Scanner(System.in);

        // find and load file
        findFile();
        loadFile();

        label:
        while (true) {
            // take in input every loop
            String input = sc.nextLine();
            String[] arrOfInput = input.split(" ");
            String firstWord = arrOfInput[0];

            try {
                switch (firstWord) {
                    case "bye":
                        // end program when input is bye
                        sayGoodbye();
                        break label;

                    case "list":
                        // list out the current list
                        printList();

                        break;
                    case "mark": {
                        // to mark an element as done
                        int index = Integer.parseInt(arrOfInput[1]);
                        markTaskAsDone(index);

                        break;
                    }
                    case "unmark": {
                        // to mark an element as undone
                        int index = Integer.parseInt(arrOfInput[1]);
                        unmarkTask(index);

                        break;
                    }
                    case "todo":
                    case "deadline":

                    case "event":
                        // adding the event to the list
                        // adding the deadline to the list
                        // adding the to-do to the list
                        createNewTask(firstWord, arrOfInput);

                        break;

                    case "delete": {
                        // deleting a task
                        int index = Integer.parseInt(arrOfInput[1]);
                        deleteTask(index);

                        break;
                    }
                    default:
                        throw new DukeException("I'm sorry, but I don't know what that means :-(");
                }
            } catch (DukeException e) {
                System.out.println(e.getMessage());
            } catch (IOException e) {
                System.out.println("error occurred!");
                e.printStackTrace();
            }
        }
    }
}
