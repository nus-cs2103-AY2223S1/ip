import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;
import java.io.FileWriter;

public class Duke {

    /**
     * Prints the string representation of all tasks that has been
     * added to the arraylist via the Task toString method.
     * @param inputList
     */
    public static void printList(ArrayList<Task> inputList) {
        Task[] inputArray = inputList.toArray(new Task[inputList.size()]);
        System.out.println("Here are the tasks in your list:");
        for (int i = 1; i <= inputArray.length; i++) {
            String item = String.format("%s. %s", i, inputArray[i-1].toString());
            System.out.println(item);
        }
    }

    /**
     * Marks the task as completed via the index of the task on the
     * arraylist. The method throws a DukeException if the
     * task is found to be non-existent.
     * @param parts
     * @param inputList
     * @throws DukeException thrown if there is no such task.
     */
    public static void markTask(String[] parts, ArrayList<Task> inputList) throws DukeException {
        if (parts.length == 1) {
            throw new DukeException("Please specify the index of the task (i.e. mark 2).");
        } else if (Integer.parseInt(parts[1]) == 0 || Integer.parseInt(parts[1]) > inputList.size()) {
            throw new DukeException("There is no such task!");
        } else {
            int index = Integer.parseInt(parts[1]);
            Task task = inputList.get(index - 1);
            System.out.println(task.markAsDone());
            writeToFile("./data", inputList);
        }
    }

    /**
     * Removes the task from the arraylist. The method throws a DukeException if the
     * task is found to be non-existent.
     * @param parts
     * @param inputList
     * @throws DukeException thrown if there is no such task.
     */
     public static void deleteTask(String[] parts, ArrayList<Task> inputList) throws DukeException {
        if (parts.length == 1) {
            throw new DukeException("Please specify the index of the task (i.e. delete 2).");
        } else if (Integer.parseInt(parts[1]) == 0 || Integer.parseInt(parts[1]) > inputList.size()) {
            throw new DukeException("There is no such task!");
        } else {
            int index = Integer.parseInt(parts[1]);
            Task task = inputList.get(index - 1);
            inputList.remove(index - 1);
            String output = String.format("Noted, I've removed this task:\n%s\nNow you have %s tasks in the list.",
                    task.toString(),
                    inputList.size());
            System.out.println(output);
            writeToFile("./data", inputList);
        }
     }

    /**
     * Adds the task to arraylist, and formats a String representation
     * that is to be printed along with the task representation and size of list.
     * @param task
     * @param inputList
     */
    public static void taskAdd(Task task, ArrayList<Task> inputList) throws IOException {
        inputList.add(task);
        String output = String.format("Got it. I've added this task:\n%s\nNow you have %s tasks in the list.",
                task.toString(),
                inputList.size());
        System.out.println(output);
        writeToFile("./data", inputList);
    }

    /**
     * Abstracts the creation of a todo object, with exception handling.
     * @param input
     * @return Todo object
     * @throws DukeException thrown if there is no description.
     */
    public static Todo createTodo(String input) throws DukeException {
        String[] taskType = input.split(" ", 2);
        if (taskType.length == 1) {
            throw new DukeException("The description of a todo cannot be empty.");
        } else {
            Todo todo = new Todo(taskType[1]);
            return todo;
        }
    }

    /**
     * Abstracts the creation of a Deadline object, with exception handling.
     * @param input
     * @return Deadline object
     * @throws DukeException thrown if there is no description or /by field.
     */
    public static Deadline createDeadline(String input) throws DukeException{
        String[] taskType = input.split(" ", 2);
        if (taskType.length == 1) {
            throw new DukeException("The description of a deadline cannot be empty.");
        } else if (taskType[1].split("/by ", 2).length == 1) {
            throw new DukeException("The /by field cannot be empty.");
        } else {
            String[] taskBy = taskType[1].split("/by ", 2);
            Deadline deadline = new Deadline(taskBy[0], taskBy[1]);
            return deadline;
        }
    }

    /**
     * Abstracts the creation of a Event object, with exception handling.
     * @param input
     * @return Event object
     * @throws DukeException thrown if there is no description or /at field
     */
    public static Event createEvent(String input) throws DukeException {
        String[] taskType = input.split(" ", 2);
        if (taskType.length == 1) {
            throw new DukeException("The description of a event cannot be empty.");
        } else if (taskType[1].split("/at ", 2).length == 1) {
            throw new DukeException("The /at field cannot be empty.");
        } else {
            String[] taskBy = taskType[1].split("/at ", 2);
            Event event = new Event(taskBy[0], taskBy[1]);
            return event;
        }
    }

    /**
     * Writes all events on the ArrayList to a txt File. The directory and file
     * is created if it does not exist.
     * @param filePath
     * @param inputList
     */
    public static void writeToFile(String filePath, ArrayList<Task> inputList)  {
        try {
            File dir = new File(filePath);
            if (!dir.exists()) dir.mkdirs();
            File textFile = new File(filePath + "/duke.txt");
            textFile.createNewFile();

            FileWriter fw = new FileWriter(textFile);
            for (int i = 0; i < inputList.size(); i++) {
                String fileTextInput = inputList.get(i).formatFileText();
                fw.write(fileTextInput);
            }
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Reads all tasks in file and adds them to an ArrayList. Throws a
     * FileNotFoundException if no such file exists.
     * @param filePath
     * @return ArrayList with all saved tasks added
     * @throws FileNotFoundException
     * @throws DukeException
     */
    public static ArrayList<Task> readFile(String filePath) throws FileNotFoundException, DukeException {
        File f = new File(filePath);
        ArrayList<Task> arrayInput = new ArrayList<>();
        Scanner s = new Scanner(f); // create a Scanner using the File as the source
        while (s.hasNext()) {
            String[] task = s.nextLine().split(" \\| ",-1);
            switch (task[0]) {
                case "T": // create event task
                    String taskString = String.format("todo %s", task[2]);
                    Todo newTodo = createTodo(taskString);
                    if (task[1] == "X") {
                        newTodo.markAsDone();
                    }
                    arrayInput.add(newTodo);
                    break;
                case "D": // create deadline task
                    String deadlineString = String.format("deadline %s/by %s", task[2], task[3]);
                    Deadline newDeadline = createDeadline(deadlineString);
                    if (task[1] == "X") {
                        newDeadline.markAsDone();
                    }
                    arrayInput.add(newDeadline);
                    break;
                case "E": // create event task
                    String eventString = String.format("event %s/at %s", task[2], task[3]);
                    Event newEvent = createEvent(eventString);
                    if (task[1] == "X") {
                        newEvent.markAsDone();
                    }
                    arrayInput.add(newEvent);
                    break;
            }
        }
        return arrayInput;
    }

    public static void main(String[] args) {

        ArrayList<Task> inputList = new ArrayList<>();
        try { // file found
            inputList = readFile("./data/duke.txt");
        } catch (FileNotFoundException e){ // no such file
            inputList = new ArrayList<Task>();
        } catch (DukeException e) { // will not reach here
            e.printStackTrace();
        }

        Scanner myObj = new Scanner(System.in);
        System.out.println("Hello! I'm Duke\nWhat can I do for you?");

        String input = myObj.nextLine();
        String[] parts = input.split(" ");

        while (!input.equals("bye")) {
            if (input.equals("list")) {
                printList(inputList);
            } else if (parts[0].equals("mark")) {
                try {
                    markTask(parts, inputList);
                } catch (DukeException e) {
                    System.out.println(e.getMessage());
                }
            } else if (parts[0].equals("todo")) {
                try {
                    Task todo = createTodo(input);
                    taskAdd(todo, inputList);
                } catch (DukeException e) {
                    System.out.println(e.getMessage());
                } catch (IOException e) {
                    System.out.println("Something went wrong: " + e.getMessage());
                }
            } else if (parts[0].equals("deadline")) {
                try {
                    Deadline deadline = createDeadline(input);
                    taskAdd(deadline, inputList);
                } catch (DukeException e) {
                    System.out.println(e.getMessage());
                } catch (IOException e) {
                    System.out.println("Something went wrong: " + e.getMessage());
                }
            } else if (parts[0].equals("event")) {
                try {
                    Event event = createEvent(input);
                    taskAdd(event, inputList);
                } catch (DukeException e) {
                    System.out.println(e.getMessage());
                } catch (IOException e) {
                    System.out.println("Something went wrong: " + e.getMessage());
                }
            } else if (parts[0].equals("delete")) {
                try {
                    deleteTask(parts, inputList);
                } catch (DukeException e) {
                    System.out.println(e.getMessage());
                }
            } else {
                System.out.println("I'm sorry, but I don't know what that means :(");
            }
            input = myObj.nextLine();
            parts = input.split(" ");
        }

        myObj.close();
        System.out.println("Bye. Hope to see you again soon!");
    }
}
