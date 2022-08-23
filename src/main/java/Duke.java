import java.util.Arrays;
import java.util.Scanner;

public class Duke {
    private final static String LINE = "____________________________________________________________";
    private final static String INDENTATION = "   ";
    private TaskList taskList;
    private boolean isGoodBye;


    public Duke() {
        this.taskList = new TaskList();
        this.isGoodBye = false;
    }

//    /**
//     * Greeting function of Duke
//     */
//    public static void greeting() {
//        System.out.println(INDENTATION + LINE);
//        System.out.println(INDENTATION + "Hello! I'm Duke");
//        System.out.println(INDENTATION + "What can I do for you?");
//        System.out.println(INDENTATION + LINE);
//    }
//
//    /**
//     * Exit function of Duke
//     */
//    public static void exit() {
//        System.out.println(INDENTATION + "Bye. Hope to see you again soon!");
//        System.out.println(INDENTATION + LINE);
//    }
//
//    /**
//     * List task(s) given by user in taskList
//     */
//    public void list() {
//        System.out.println(INDENTATION + "Here are the tasks in your list:");
//        for (int i = 0; i < this.taskList.getSize(); i++) {
//            System.out.println(INDENTATION + String.valueOf(i + 1) + ". " + this.taskList.getTask(i).toString());
//        }
//    }
//
//    /**
//     * Add task given by user to taskList in Duke
//     *
//     * @param task task given by user
//     */
//    public void add(Task task) {
//        this.taskList.addToTaskList(task);
//        int numTasks = this.taskList.getSize();
//        System.out.println(INDENTATION + "Got it. I've added this task:");
//        System.out.println(INDENTATION + INDENTATION + "added: " + task.toString());
//        System.out.println(INDENTATION + "Now you have " + String.valueOf(numTasks) + " tasks in the list.");
//    }
//
//    /**
//     * Marks task as done
//     *
//     * @param taskIndex index of task to mark as done
//     */
//    public void mark(int taskIndex) {
//        Task taskToMark = this.taskList.getTask(taskIndex);
//        taskToMark.markAsDone();
//        System.out.println(INDENTATION + "Nice! I've marked this task as done:");
//        System.out.println(INDENTATION + INDENTATION + taskToMark.toString());
//    }
//
//    public void unmark(int taskIndex) {
//        Task taskToMark = this.taskList.getTask(taskIndex);
//        taskToMark.markAsUndone();
//        System.out.println(INDENTATION + "OK, I've marked this task as not done yet:");
//        System.out.println(INDENTATION + INDENTATION + taskToMark.toString());
//    }
//
//    public void delete(int taskIndex) {
//        Task taskToDelete = this.taskList.getTask(taskIndex);
//        System.out.println(INDENTATION + "Noted. I've removed this task:");
//        System.out.println(INDENTATION + INDENTATION + taskToDelete.toString());
//        this.taskList.removeFromTaskList(taskIndex);
//        int numTasks = this.taskList.getSize();
//        System.out.println(INDENTATION + "Now you have " + String.valueOf(numTasks) + " tasks in the list.");
//    }


    public void run() {
        Scanner sc = new Scanner(System.in);
        System.out.println(INDENTATION + LINE);
        System.out.println(INDENTATION + "Hello! I'm Duke");
        System.out.println(INDENTATION + "What can I do for you?");
        System.out.println(INDENTATION + LINE);
        while (!isGoodBye) {
            try {
                String[] userInput = sc.nextLine().trim().split(" ");
                System.out.println(INDENTATION + LINE);
                Command command = Command.valueOf(userInput[0].toUpperCase());
                if (userInput.length == 1) {
                    switch (command) {
                        case BYE:
                            System.out.println(INDENTATION + "Bye. Hope to see you again soon!");
                            isGoodBye = true;
                            break;
                        case LIST:
                            int numOfTasks = taskList.getSize();
                            if (numOfTasks == 0) {
                                System.out.println(INDENTATION + "You do not have any tasks in your list right now.");
                                break;
                            }
                            System.out.printf(INDENTATION + "Here %s the task%s in your list:\n",
                                    numOfTasks > 1 ? "are" : "is", numOfTasks > 1 ? "s" : "");
                            for (int i = 0; i < numOfTasks; i++) {
                                System.out.println(INDENTATION + String.valueOf(i + 1) + ". " +
                                        this.taskList.getTask(i).toString());
                            }
                            break;
                        case MARK:
                            throw new DukeException(INDENTATION +
                                    "☹ OOPS!!! The mark command should be used as shown. " +
                                    "eg. mark {num of task in list to be marked as done}");
                        case UNMARK:
                            throw new DukeException(INDENTATION +
                                    "☹ OOPS!!! The unmark command should be used as shown. " +
                                    "eg. mark {num of task in list to be unmarked as incomplete}");
                        case TODO:
                            throw new DukeException(INDENTATION +
                                    "☹ OOPS!!! The description of a todo cannot be empty, " +
                                    "usage of todo is as shown. eg. todo {task to be done}");
                        case DEADLINE:
                            throw new DukeException(INDENTATION +
                                    "☹ OOPS!!! The description of a deadline cannot be empty, " +
                                    "usage of deadline is as shown. " +
                                    "eg. deadline {task to be done} /by {date/time to complete}");
                        case EVENT:
                            throw new DukeException(INDENTATION +
                                    "☹ OOPS!!! The description of a event cannot be empty, " +
                                    "usage of event is as shown. " +
                                    "eg. event {event} /at {date/time}");
                        case DELETE:
                            throw new DukeException(INDENTATION +
                                    "☹ OOPS!!! The delete command should be used as shown. " +
                                    "eg. delete {num of task in list to be deleted.}");
                        default:
                            throw new DukeException(INDENTATION +
                                    "☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                    }
                } else {
                    switch (command) {
                        case MARK:
                            if (userInput.length != 2) {
                                throw new DukeException(INDENTATION +
                                        "☹ OOPS!!! The mark command should be used as shown. " +
                                        "eg. mark {num of task in list to be marked as done}");
                            }
                            if (!userInput[1].matches("\\d+")) {
                                throw new DukeException(INDENTATION +
                                        "☹ OOPS!!! The mark command should be used as shown. " +
                                        "eg. mark {num of task in list to be marked as done}");
                            }
                            int indexToMark = Integer.parseInt(userInput[1]) - 1;
                            if (indexToMark < 0 || indexToMark >= taskList.getSize()) {
                                throw new DukeException(INDENTATION +
                                        "☹ OOPS!!! You chose a task that does not exist.");
                            }
                            Task taskToMark = taskList.getTask(indexToMark);
                            taskToMark.markAsDone();
                            System.out.println(INDENTATION + "Nice! I've marked this task as done:");
                            System.out.println(INDENTATION + INDENTATION + taskToMark.toString());
                            break;
                        case UNMARK:
                            if (userInput.length != 2) {
                                throw new DukeException(INDENTATION +
                                        "☹ OOPS!!! The unmark command should be used as shown. " +
                                        "eg. mark {num of task in list to be unmarked as incomplete}");
                            }
                            if (!userInput[1].matches("\\d+")) {
                                throw new DukeException(INDENTATION +
                                        "☹ OOPS!!! The mark command should be used as shown. " +
                                        "eg. mark {num of task in list to be marked as done}");
                            }
                            int indexToUnmark = Integer.parseInt(userInput[1]) - 1;
                            if (indexToUnmark < 0 || indexToUnmark >= taskList.getSize()) {
                                throw new DukeException(INDENTATION +
                                        "☹ OOPS!!! You chose a task that does not exist.");
                            }
                            Task taskToUnmark = taskList.getTask(indexToUnmark);
                            taskToUnmark.markAsUndone();
                            System.out.println(INDENTATION + "OK, I've marked this task as not done yet:");
                            System.out.println(INDENTATION + INDENTATION + taskToUnmark.toString());
                            break;
                        case TODO:
                            String[] taskDescription = Arrays.copyOfRange(userInput, 1, userInput.length);
                            Task toDo = new ToDo(String.join(" ", taskDescription));
                            taskList.addToTaskList(toDo);
                            int numTasks = taskList.getSize();
                            System.out.println(INDENTATION + "Got it. I've added this task:");
                            System.out.println(INDENTATION + INDENTATION + "added: " + toDo.toString());
                            System.out.println(INDENTATION +
                                    "Now you have " + String.valueOf(numTasks) + " tasks in the list.");
                            break;
                        case DEADLINE:
                            if (!Arrays.asList(userInput).contains("/by")) {
                                throw new DukeException(INDENTATION +
                                        "☹ OOPS!!! Please use the deadline command in the correct manner, " +
                                        "usage of deadline is as shown. " +
                                        "eg. deadline {task to be done} /by {date/time to complete}");
                            }
                            int byIndex = Arrays.asList(userInput).indexOf("/by");
                            String[] deadlineDescription = Arrays.copyOfRange(userInput, 1, byIndex);
                            String[] by = Arrays.copyOfRange(userInput, byIndex + 1, userInput.length);
                            Task deadline = new Deadline(String.join(" ", deadlineDescription),
                                    String.join(" ", by));
                            taskList.addToTaskList(deadline);
                            System.out.println(INDENTATION + "Got it. I've added this task:");
                            System.out.println(INDENTATION + INDENTATION + "added: " + deadline.toString());
                            System.out.println(INDENTATION +
                                    "Now you have " + String.valueOf(taskList.getSize()) + " tasks in the list.");
                            break;
                        case EVENT:
                            if (!Arrays.asList(userInput).contains("/at")) {
                                throw new DukeException(INDENTATION +
                                        "☹ OOPS!!! Please use the event command in the correct manner, " +
                                        "usage of deadline is as shown. " +
                                        "eg. event {event} /at {date/time}");
                            }
                            int atIndex = Arrays.asList(userInput).indexOf("/at");
                            String[] eventDescription = Arrays.copyOfRange(userInput, 1, atIndex);
                            String[] at = Arrays.copyOfRange(userInput, atIndex + 1, userInput.length);
                            Task event = new Event(String.join(" ", eventDescription),
                                    String.join(" ", at));
                            taskList.addToTaskList(event);
                            System.out.println(INDENTATION + "Got it. I've added this task:");
                            System.out.println(INDENTATION + INDENTATION + "added: " + event.toString());
                            System.out.println(INDENTATION +
                                    "Now you have " + String.valueOf(taskList.getSize()) + " tasks in the list.");
                            break;
                        case DELETE:
                            if (userInput.length != 2) {
                                throw new DukeException(INDENTATION +
                                        "☹ OOPS!!! The delete command should be used as shown. " +
                                        "eg. delete {num of task in list to be deleted.}");
                            }
                            if (!userInput[1].matches("\\d+")) {
                                throw new DukeException(INDENTATION +
                                        "☹ OOPS!!! The mark command should be used as shown. " +
                                        "eg. mark {num of task in list to be marked as done}");
                            }
                            int indexToDelete = Integer.parseInt(userInput[1]) - 1;
                            if (indexToDelete < 0 || indexToDelete >= taskList.getSize()) {
                                throw new DukeException(INDENTATION +
                                        "☹ OOPS!!! You chose a task that does not exist.");
                            }
                            Task taskToDelete = taskList.getTask(indexToDelete);
                            System.out.println(INDENTATION + "Noted. I've removed this task:");
                            System.out.println(INDENTATION + INDENTATION + taskToDelete.toString());
                            taskList.removeFromTaskList(indexToDelete);
                            System.out.println(INDENTATION +
                                    "Now you have " + String.valueOf(taskList.getSize()) + " tasks in the list.");
                            break;
                        default:
                            throw new DukeException(INDENTATION +
                                    "☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                    }
                }
            } catch (IllegalArgumentException e) {
                System.out.println(INDENTATION + "☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
            } catch (DukeException e) {
                System.out.println(INDENTATION + e.getMessage());
            } finally {
                System.out.println(INDENTATION + LINE);
            }
        }
    }


    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        Duke dukeBot = new Duke();
        dukeBot.run();
    }
}
