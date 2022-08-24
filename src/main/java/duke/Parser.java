package duke;
import java.util.Scanner;

public class Parser {
    //Constant string to represent a line break
    private final String LINE_BREAK = "____________________________________________________________";
    private TaskList tasks;
    private Storage storage;

    public Parser(TaskList tasks, Storage storage) {
        this.tasks = tasks;
        this.storage = storage;
    }

    public void start() {
        Scanner input = new Scanner(System.in);
        String text = input.next();
        while (!text.equals("bye")) {
            try {
                switch (text) {
                    //Handle case when task aTodo
                    case "todo" :
                        String tDescription = input.nextLine();
                        printTodo(tDescription);
                        storage.save(tasks);
                        break;

                    //Handle case when task is a deadline
                    case "deadline": {
                        String str = input.nextLine();
                        String dDescription = str.substring(0, str.indexOf('/') - 1);
                        String dBy = str.substring(str.indexOf('/') + 4);
                        printDeadline(dDescription, dBy);
                        storage.save(tasks);
                        break;
                    }

                    //Handle case when task is an event
                    case "event": {
                        String str = input.nextLine();
                        String eDescription = str.substring(0, str.indexOf('/') - 1);
                        String eAt = str.substring(str.indexOf('/') + 4);
                        printEvent(eDescription, eAt);
                        storage.save(tasks);
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
                        tasks.markTask(index);
                        storage.save(tasks);
                        break;
                    }

                    //Handle case when user wants to unmark task
                    case "unmark": {
                        //-1 to get index in 0 indexing
                        int index = input.nextInt() - 1;
                        tasks.unmarkTask(index);
                        storage.save(tasks);
                        break;
                    }

                    //Handle case when user wants to delete task
                    case "delete": {
                        //-1 to get in 0 indexing
                        int index = input.nextInt() - 1;
                        tasks.deleteTask(index);
                        storage.save(tasks);
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
        for (int i = 0; i < tasks.getSize(); i++) {
            Task task = tasks.getTask(i);
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
            tasks.addTask(todo);
            int size = tasks.getSize();
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
        tasks.addTask(deadline);
        int size2 = tasks.getSize();
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
        tasks.addTask(event);
        int size3 = tasks.getSize();
        System.out.printf(LINE_BREAK
                        + "\n\tGot it. I've added this task:\n\t%s\n\tNow you have %d tasks in the list.\n"
                        + LINE_BREAK + "\n",
                event,
                size3);
    }
}
