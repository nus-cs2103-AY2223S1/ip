import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Duke {
    private static final String LOGO = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";
    private static final String GREETING = "Hello from\n"  + LOGO + "\nHow can I help you ?\n" ;
    private static final List<Task> TASK_LIST = new ArrayList<>();



    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println(GREETING);

        while(scanner.hasNext()){
            String userInput = scanner.nextLine();
            String[] split = userInput.split(" ");
            try {
                if (userInput.equals("bye")) {
                    System.out.println("Bye. Hope to see you again soon!");
                    scanner.close();
                    break;
                } else if (userInput.equals("list")) {
                    printList();
                } else if (split[0].equals("todo")) {
                    handleTodo(split);
                } else if (split[0].equals("deadline")) {
                    handleDeadline(split);
                } else if (split[0].equals("event")) {
                    handleEvent(split);
                } else if (split.length == 2 && isNumeric(split[1])) {
                    String keyword = split[0];
                    int index = Integer.parseInt(split[1]);
                    switch (keyword) {
                        case "mark" :
                            markDone(index);
                            break;
                        case "unmark" :
                            markNotDone(index);
                            break;
                        case "delete" :
                            delete(index);
                            break;
                    }
                }
                else {
                    throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                }
            } catch (DukeException error) {
                System.out.println(error.getMessage());
            }
        }
    }
    /**
     * Handles the input for a Todo task, and adds it to the Task List.
     * @param input The parsed Todo task
     */
    private static void handleTodo(String[] input) throws DukeException {
        String taskName = String.join(" ", Arrays.copyOfRange(input, 1, input.length));
        if(taskName.equals("")){
            throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
        }
        addTask(new Todo(taskName));
    }

    /**
     * Handles the input for a Deadline task, and adds it to the Task List.
     * @param input The parsed Deadline task
     */
    private static void handleDeadline(String[] input) throws DukeException{
        int indexOfDate = findDate(input);
        if( indexOfDate == -1){
            throw new DukeException("☹ OOPS!!! Please add a date for your deadline with /by.");
        } else {
            String taskName = String.join(" ", Arrays.copyOfRange(input, 1, indexOfDate));
            String date = String.join(" ", Arrays.copyOfRange(input, indexOfDate +1 , input.length));
            if( taskName.equals("")){
                throw new DukeException("☹ OOPS!!! The description of a deadline cannot be empty.");
            }
            if( date.equals("")){
                throw new DukeException("☹ OOPS!!! The date of a deadline cannot be empty.");
            }
            addTask(new Deadline(taskName,date));
        }
    }

    /**
     * Handles the input for an Event task, and adds it to the Task List.
     * @param input The parsed Event task
     */
    private static void handleEvent(String[] input) throws DukeException{
        int indexOfDate = findDate(input);
        if( indexOfDate == -1){
            throw new DukeException("☹ OOPS!!! Please add a date for your event with /at.");
        } else {
            String taskName = String.join(" ", Arrays.copyOfRange(input, 1, indexOfDate));
            String date = String.join(" ", Arrays.copyOfRange(input, indexOfDate +1 , input.length));
            if( taskName.equals("")){
                throw new DukeException("☹ OOPS!!! The description of an event cannot be empty.");
            }
            if( date.equals("")){
                throw new DukeException("☹ OOPS!!! The date of an event cannot be empty.");
            }
            addTask(new Event(taskName,date));
        }
    }

    /**
     * A helper function that adds the task to the Task List and prints out the appropriate message.
     * @param task The task to be added
     */
    private static void addTask (Task task){
        TASK_LIST.add(task);
        System.out.println("Got it. I've added this task:\n " + task.toString() + "\nNow you have " + TASK_LIST.size() +" tasks in the list.");
    }

    /**
     * A helper function retrieved a task from the Task List based on index, and throws
     * an exception if the index is invalid.
     * @param index The 1-based index of the task.
     * @return The retrieved task.
     */
    private static Task getTask(Integer index) throws DukeException{
        if(index < 1 || index > TASK_LIST.size()){
            throw new DukeException("☹ OOPS!!! That index is out of bounds.");
        }
        return TASK_LIST.get(index-1);
    }

    /**
     * Marks a task as done.
     * @param index The 1-based index of the task to be marked
     */
    private static void markDone(Integer index) throws DukeException{
        Task task = getTask(index);
        task.setDone();

        System.out.println("Nice! I've marked this task as done:");
        System.out.println(" " + task.toString());
    }

    /**
     * Marks a task as not done.
     * @param index The 1-based index of the task to be un-marked
     */
    private static void markNotDone(Integer index) throws DukeException{
        Task task = getTask(index);
        task.setNotDone();

        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println(" " + task.toString());
    }

    /**
     * Deletes a task.
     * @param index The 1-based index of the task to be deleted
     */
    private static void delete(Integer index) throws DukeException{
        Task task = getTask(index);
        System.out.println(" Noted. I've removed this task:");
        System.out.println(" " + TASK_LIST.get(index-1).toString() +"\nNow you have " + (TASK_LIST.size() - 1) +" tasks in the list.");
        TASK_LIST.remove(task);
    }

    /**
     * Prints the current list of tasks.
     */
    private static void printList(){
        for(int i = 0; i<TASK_LIST.size(); i++){
            Integer index = i+1;
            System.out.println(index + ". " + TASK_LIST.get(i));
        }
    }

    /**
     * Find the index in the parsed input where the description ends and the time begins
     * @param split The parsed input.
     * @return The index of the /by or /at in the input string. Returns -1 if not found.
     */
    private static int findDate(String[] split) {
        for(int i = 0; i<split.length; i++){
            if(split[i].equals("/by") || split[i].equals("/at")){
                return i;
            }
        }
        return -1;
    }

    /**
     * Determines if the input string is a number (only 0-9)
     * @param input The string to be tested.
     * @return The result of the test.
     */
    private static boolean isNumeric(String input) {
        return input.matches("^[0-9]*$");
    }
}

