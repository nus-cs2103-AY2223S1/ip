import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {
    //ArrayList to store tasks
    private List<Task> lst = new ArrayList<>();

    public static void main(String[] args) throws DukeException {
        //Create a Duke object
        Duke duke = new Duke();
        //Scanner object to take in input from user
        Scanner input = new Scanner(System.in);
        //Welcome message
        duke.hello();
        String text = input.next();
        while (!text.equals("bye")) {
            try {
                switch (text) {
                    //Handle case when task aTodo
                    case "todo" :
                        try {
                            String tDescription = input.nextLine();
                            duke.printTodo(tDescription);
                        } catch (DukeException dE) {
                            System.out.println("Caught Exception is " + dE);
                        }
                        break;

                    //Handle case when task is a deadline
                    case "deadline":
                        String str = input.nextLine();
                        String dDescription = str.substring(0, str.indexOf('/') - 1);
                        String dBy = str.substring(str.indexOf('/') + 4);
                        duke.printDeadline(dDescription, dBy);
                        break;

                    //Handle case when task is an event
                    case "event":
                        String str2 = input.nextLine();
                        String eDescription = str2.substring(0, str2.indexOf('/') - 1);
                        String eAt = str2.substring(str2.indexOf('/') + 4);
                        duke.printEvent(eDescription, eAt);
                        break;

                    //Handle case when user wants to list tasks
                    case "list" :
                        duke.listTasks();
                        break;

                    //Handle case when user wants to mark task
                    case "mark":
                        //-1 to get index in 0 indexing
                        int index = input.nextInt() - 1;
                        duke.markTask(index);
                        break;

                    //Handle case when user wants to unmark task
                    case "unmark":
                        //-1 to get index in 0 indexing
                        int index2 = input.nextInt() - 1;
                        duke.unmarkTask(index2);
                        break;

                    //Handle case when user wants to delete task
                    case "delete":
                        //-1 to get in 0 indexing
                        int index3 = input.nextInt() - 1;
                        duke.deleteTask(index3);
                        break;

                    //Default case: Not any of the tasks(aTodo, Deadline, Event) and hence, throws an Exception
                    default:
                        throw new DukeException("OOPS! I'm sorry, but I don't know what that means :-(");
                }
            } catch (DukeException dE) {
                System.out.println("Caught Exception is " + dE);
            }
            text = input.next();
        }
        //Goodbye message
        duke.bye();
    }

    public void hello() {
        System.out.println("Hello! I'm Donovan\nWhat can I do for you?");
    }

    public void bye() {
        System.out.println("\tBye! Hope to see you again soon!");
    }

    public void listTasks() {
        System.out.println("\tHere are the tasks in your list.");
        for (int i = 0; i < lst.size(); i++) {
            Task task = lst.get(i);
            System.out.printf("\t%d. %s\n", i+1, task.toString());
        }
    }

    /**
     * @param tDescription A String of the description for the task
     * @throws DukeException
     */
    public void printTodo(String tDescription) throws DukeException {
        if (tDescription.equals("")) {
            throw new DukeException("OOPS! The description of a todo cannot be empty.");
        } else {
            Task todo = new Todo(tDescription);
            lst.add(todo);
            int size = lst.size();
            System.out.printf("\tGot it. I've added this task:\n\t%s\n\tNow you have %d tasks in the list.\n",
                    todo,
                    size);
        }
    }

    /**
     * @param dDescription A String of the description for the task
     * @param dBy A String representing the deadline for the task
     */
    public void printDeadline(String dDescription, String dBy) {
        Task deadline = new Deadline(dDescription, dBy);
        lst.add(deadline);
        int size2 = lst.size();
        System.out.printf("\tGot it. I've added this task:\n\t%s\n\tNow you have %d tasks in the list.\n",
                deadline,
                size2);
    }

    /**
     * @param eDescription A String of the description for the task
     * @param eAt A String representing the day for the task
     */
    public void printEvent(String eDescription, String eAt) {
        Task event = new Event(eDescription, eAt);
        lst.add(event);
        int size3 = lst.size();
        System.out.printf("\tGot it. I've added this task:\n\t%s\n\tNow you have %d tasks in the list.\n",
                event,
                size3);
    }

    /**
     * @param index An int representing the index of task to be marked
     */
    public void markTask(int index) {
        Task taskToBeMarked = lst.get(index);
        taskToBeMarked.markAsDone();
        System.out.printf("\tNice! I've marked this task as done:\n\t%s\n",
                taskToBeMarked);
    }

    /**
     * @param index An int representing the index of task to be unmarked
     */
    public void unmarkTask(int index) {
        Task taskToBeUnmarked = lst.get(index);
        taskToBeUnmarked.markAsUndone();
        System.out.printf("\tOkay, I've marked this task as not done yet:\n\t%s\n",
                taskToBeUnmarked);
    }

    /**
     * @param index An int representing the index of task to be deleted
     */
    public void deleteTask(int index) {
        Task taskToBeDeleted = lst.get(index);
        lst.remove(index);
        int newSize = lst.size();
        System.out.printf("\tNoted. I've removed this task:\n\t%s\n" +
                        "\tNow you have %d tasks in the list.\n",
                taskToBeDeleted, newSize);
    }
}

