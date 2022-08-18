import java.util.*;

public class Duke {
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
     * Prints the task list.
     *
     * @param tasks The task list
     */
    public static void printList(ArrayList<Task> tasks) {
        System.out.println("Here are the tasks in your list:");
        for (int i = 1; i <= tasks.size(); i++) {
            System.out.println(i + ". " + tasks.get(i - 1));
        }
    }


    /**
     * Marks the specified task as done.
     *
     * @param tasks The task list
     * @param index The index of the task to be mark done
     */
    public static void markTaskAsDone(ArrayList<Task> tasks, int index) throws DukeException {
        if (1 <= index && index <= tasks.size()) {
            Task currTask = tasks.get(index - 1);
            currTask.markAsDone();
        } else {
            throw new DukeException("Input a valid task index!");
        }
    }


    /**
     * Unmarks the specified task as not done.
     *
     * @param tasks The task list
     * @param index The index of the task to be unmarked
     */
    public static void unmarkTask(ArrayList<Task> tasks, int index) throws DukeException {
        if (1 <= index && index <= tasks.size()) {
            Task currTask = tasks.get(index - 1);
            currTask.markUndone();
        } else {
            throw new DukeException("Input a valid task index!");
        }
    }


    /**
     * Deletes the specified task.
     *
     * @param tasks The task list
     * @param index The index of the task to be deleted
     */
    public static void deleteTask(ArrayList<Task> tasks, int index) throws DukeException {
        if (1 <= index && index <= tasks.size()) {
            System.out.println("Noted. I've removed this task:\n  " + tasks.get(index - 1));
            tasks.remove(index - 1);
            System.out.println("Now you have " + tasks.size() + " tasks in the list.");
        } else {
            throw new DukeException("Input a valid task index!");
        }
    }


    /**
     * Creates a new task and adds it to the task list.
     *
     * @param firstWord the first word typed in by the user
     * @param strArray the array of strings of the words typed in by the user
     * @param tasks The task list
     */
    public static void createNewTask(String firstWord, String[] strArray, ArrayList<Task> tasks) throws DukeException {
        if (firstWord.equals("todo")) {
            // throw exception if no word after to-do
            if (strArray.length < 2) {
                throw new DukeException("The description of a todo cannot be empty.");
            }

            StringBuilder todoStr = new StringBuilder();
            for (int i = 1; i < strArray.length; i++) {
                todoStr.append(" ").append(strArray[i]);
            }
            tasks.add(new Todo(todoStr.toString()));
            System.out.println("Got it. I've added this task:\n  " + tasks.get(tasks.size() - 1) +
                    "\nNow you have " + tasks.size() + " tasks in the list.");

        } else if (firstWord.equals("deadline")) {
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

            // create deadline string and deadline
            StringBuilder deadlineStr = new StringBuilder();
            StringBuilder deadline = new StringBuilder();
            for (int i = 1; i < strArray.length; i++) {
                if (strArray[i].equals("/by")) {
                    break;
                } else {
                    deadlineStr.append(" ");
                    deadlineStr.append(strArray[i]);
                }
            }
            for (int i = 1; i < strArray.length; i++) {
                if (strArray[i].equals("/by")) {
                    for (int j = i + 1; j < strArray.length; j++) {
                        deadline.append(" ");
                        deadline.append(strArray[j]);
                    }
                    break;
                }
            }
            tasks.add(new Deadline(deadlineStr.toString(), deadline.toString()));
            System.out.println("Got it. I've added this task:\n  " + tasks.get(tasks.size() - 1) +
                    "\nNow you have " + tasks.size() + " tasks in the list.");

        } else if (firstWord.equals("event")) {
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
            tasks.add(new Event(eventStr.toString(), eventTime.toString()));
            System.out.println("Got it. I've added this task:\n  " + tasks.get(tasks.size() - 1) +
                    "\nNow you have " + tasks.size() + " tasks in the list.");

        }
    }


    /**
     * The main function.
     */
    public static void main(String[] args) {
        // print out starting message
        sayGreeting();

        // initialise variables
        ArrayList<Task> taskList = new ArrayList<>();
        Scanner sc = new Scanner(System.in);

        while (true) {
            // take in input every loop
            String input = sc.nextLine();
            String[] arrOfInput = input.split(" ");
            String firstWord = arrOfInput[0];

            try {
                if (firstWord.equals("bye")) {
                    // end program when input is bye
                    sayGoodbye();
                    break;

                } else if (firstWord.equals("list")) {
                    // list out the current list
                    printList(taskList);

                } else if (firstWord.equals("mark")) {
                    // to mark an element as done
                    int index = Integer.parseInt(arrOfInput[1]);
                    markTaskAsDone(taskList, index);

                } else if (firstWord.equals("unmark")) {
                    // to mark an element as undone
                    int index = Integer.parseInt(arrOfInput[1]);
                    unmarkTask(taskList, index);

                } else if (firstWord.equals("todo")) {
                    // adding the to-do to the list
                    createNewTask(firstWord, arrOfInput, taskList);

                } else if (firstWord.equals("deadline")) {
                    // adding the deadline to the list
                    createNewTask(firstWord, arrOfInput, taskList);

                } else if (firstWord.equals("event")) {
                    // adding the event to the list
                    createNewTask(firstWord, arrOfInput, taskList);

                } else if (firstWord.equals("delete")) {
                    // deleting a task
                    int index = Integer.parseInt(arrOfInput[1]);
                    deleteTask(taskList, index);

                } else {
                    throw new DukeException("I'm sorry, but I don't know what that means :-(");
                }
            } catch (DukeException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
