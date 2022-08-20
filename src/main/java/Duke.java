import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {
    //ArrayList to store tasks
    private List<Task> lst;
    //Scanner object to take in input from user
    private Scanner input;
    //Constant string to represent a line break
    private final String LINE_BREAK = "____________________________________________________________";

    public Duke() {
        this.lst = new ArrayList<>();
        this.input = new Scanner(System.in);
    }

    public static void main(String[] args) {
        //Create a Duke object
        Duke duke = new Duke();
        //Welcome message
        duke.hello();
        //Run bot
        duke.run();
        //Goodbye message
        duke.bye();
    }

    private void hello() {
        System.out.println(LINE_BREAK + "\nHello! I'm Donovan\nWhat can I do for you?\n" + LINE_BREAK);
    }

    private void bye() {
        System.out.println(LINE_BREAK + "\n\tBye! Hope to see you again soon!\n" + LINE_BREAK);
    }

    private void run() {
        String text = input.next();
        while (!text.equals("bye")) {
            try {
                switch (text) {//Handle case when task aTodo
                    case "todo" :
                        String tDescription = input.nextLine();
                        printTodo(tDescription);
                        break;

                    //Handle case when task is a deadline
                    case "deadline": {
                        String str = input.nextLine();
                        String dDescription = str.substring(0, str.indexOf('/') - 1);
                        String dBy = str.substring(str.indexOf('/') + 4);
                        printDeadline(dDescription, dBy);
                        break;
                    }

                    //Handle case when task is an event
                    case "event": {
                        String str = input.nextLine();
                        String eDescription = str.substring(0, str.indexOf('/') - 1);
                        String eAt = str.substring(str.indexOf('/') + 4);
                        printEvent(eDescription, eAt);
                        break;
                    }

                    //Handle case when user wants to list tasks
                    case "list" :
                        listTasks();
                        break;

                    //Handle case when user wants to mark task
                    case "mark": {
                        //-1 to get index in 0 indexing
                        int index = input.nextInt() - 1;
                        markTask(index);
                        break;
                    }

                    //Handle case when user wants to unmark task
                    case "unmark": {
                        //-1 to get index in 0 indexing
                        int index = input.nextInt() - 1;
                        unmarkTask(index);
                        break;
                    }

                    //Handle case when user wants to delete task
                    case "delete": {
                        //-1 to get in 0 indexing
                        int index = input.nextInt() - 1;
                        deleteTask(index);
                        break;
                    }

                    //Default case: Not any of the tasks(aTodo, Deadline, Event) and hence, throws an Exception
                    default:
                        //To handle any extra words the user keyed in
                        input.nextLine();
                        throw new DukeException("OOPS! I'm sorry, but I don't know what that means :-(");

                }
            } catch (DukeException dE) {
                System.out.println(dE);
            }
            text = input.next();
        }
    }

    private void listTasks() {
        System.out.println(LINE_BREAK);
        System.out.println("\tHere are the tasks in your list.");
        for (int i = 0; i < lst.size(); i++) {
            Task task = lst.get(i);
            System.out.printf("\t%d. %s\n", i+1, task.toString());
        }
        System.out.println(LINE_BREAK);
    }

    /**
     * @param tDescription A String of the description for the task
     * @throws DukeException
     */
    private void printTodo(String tDescription) throws DukeException {
        if (tDescription.equals("")) {
            throw new DukeException("OOPS! The description of a todo cannot be empty.");
        } else {
            Task todo = new Todo(tDescription);
            lst.add(todo);
            int size = lst.size();
            System.out.printf(LINE_BREAK
                            + "\n\tGot it. I've added this task:\n\t%s\n\tNow you have %d tasks in the list.\n"
                            + LINE_BREAK + "\n",
                    todo,
                    size);
        }
    }

    /**
     * @param dDescription A String of the description for the task
     * @param dBy A String representing the deadline for the task
     */
    private void printDeadline(String dDescription, String dBy) {
        Task deadline = new Deadline(dDescription, dBy);
        lst.add(deadline);
        int size2 = lst.size();
        System.out.printf(LINE_BREAK
                        + "\n\tGot it. I've added this task:\n\t%s\n\tNow you have %d tasks in the list.\n"
                        + LINE_BREAK + "\n",
                deadline,
                size2);
    }

    /**
     * @param eDescription A String of the description for the task
     * @param eAt A String representing the day for the task
     */
    private void printEvent(String eDescription, String eAt) {
        Task event = new Event(eDescription, eAt);
        lst.add(event);
        int size3 = lst.size();
        System.out.printf(LINE_BREAK
                        + "\n\tGot it. I've added this task:\n\t%s\n\tNow you have %d tasks in the list.\n"
                        + LINE_BREAK + "\n",
                event,
                size3);
    }

    /**
     * @param index An int representing the index of task to be marked
     * @throws DukeException
     */
    private void markTask(int index) throws DukeException {
        int size = lst.size();
        if (index >= size || index < 0) {
            throw new DukeException("OOPS! The index of the task does not exists.");
        } else {
            Task taskToBeMarked = lst.get(index);
            taskToBeMarked.markAsDone();
            System.out.printf(LINE_BREAK
                            + "\n\tNice! I've marked this task as done:\n\t%s\n"
                            + LINE_BREAK + "\n",
                    taskToBeMarked);
        }
    }

    /**
     * @param index An int representing the index of task to be unmarked
     * @throws DukeException
     */
    private void unmarkTask(int index) throws DukeException {
        int size = lst.size();
        if (index >= size || index < 0) {
            throw new DukeException("OOPS! The index of the task does not exists.");
        } else {
            Task taskToBeUnmarked = lst.get(index);
            taskToBeUnmarked.markAsUndone();
            System.out.printf(LINE_BREAK
                            + "\n\tOkay, I've marked this task as not done yet:\n\t%s\n"
                            + LINE_BREAK + "\n",
                    taskToBeUnmarked);
        }
    }

    /**
     * @param index An int representing the index of task to be deleted
     * @throws DukeException
     */
    private void deleteTask(int index) throws DukeException {
        int size = lst.size();
        if (index >= size || index < 0) {
            throw new DukeException("OOPS! The index of the task does not exists.");
        } else {
            Task taskToBeDeleted = lst.get(index);
            lst.remove(index);
            int newSize = lst.size();
            System.out.printf(LINE_BREAK
                            + "\n\tNoted. I've removed this task:\n\t%s\n" + "\tNow you have %d tasks in the list.\n"
                            + LINE_BREAK + "\n",
                    taskToBeDeleted, newSize);
        }
    }
}

