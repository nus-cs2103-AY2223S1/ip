package duke;

import duke.command.Command;

import java.io.IOException;


/**
 * Represents the Duke chatbot that stores users tasks.
 */
public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;


    /**
     * Constructor for the Duke chatbot.
     * @param filePath filepath to save the tasks on.
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            createSaveFile();
            tasks = new TaskList(storage.load());
        } catch (IOException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }


//    /**
//     * Greeting function of duke.Duke
//     */
//    public static void greeting() {
//        System.out.println(INDENTATION + LINE);
//        System.out.println(INDENTATION + "Hello! I'm duke.Duke");
//        System.out.println(INDENTATION + "What can I do for you?");
//        System.out.println(INDENTATION + LINE);
//    }
//
//    /**
//     * Exit function of duke.Duke
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
//     * Add task given by user to taskList in duke.Duke
//     *
//     * @param task task given by user
//     */
//    public void add(duke.task.Task task) {
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
//        duke.task.Task taskToMark = this.taskList.getTask(taskIndex);
//        taskToMark.markAsDone();
//        System.out.println(INDENTATION + "Nice! I've marked this task as done:");
//        System.out.println(INDENTATION + INDENTATION + taskToMark.toString());
//    }
//
//    public void unmark(int taskIndex) {
//        duke.task.Task taskToMark = this.taskList.getTask(taskIndex);
//        taskToMark.markAsUndone();
//        System.out.println(INDENTATION + "OK, I've marked this task as not done yet:");
//        System.out.println(INDENTATION + INDENTATION + taskToMark.toString());
//    }
//
//    public void delete(int taskIndex) {
//        duke.task.Task taskToDelete = this.taskList.getTask(taskIndex);
//        System.out.println(INDENTATION + "Noted. I've removed this task:");
//        System.out.println(INDENTATION + INDENTATION + taskToDelete.toString());
//        this.taskList.removeFromTaskList(taskIndex);
//        int numTasks = this.taskList.getSize();
//        System.out.println(INDENTATION + "Now you have " + String.valueOf(numTasks) + " tasks in the list.");
//    }


//    public void run() {
//        if (!storage.isDirectoryCreated()){
//            storage.createDirectory();
//        }
//        if (!storage.isFilePresent()) {
//            storage.createFile();
//        }
//        ArrayList<duke.task.Task> loadedTaskList = storage.load();
//        taskList = new duke.TaskList(loadedTaskList);
//
//        Scanner sc = new Scanner(System.in);
//        System.out.println(INDENTATION + LINE);
//        System.out.println(INDENTATION + "Hello! I'm duke.Duke");
//        System.out.println(INDENTATION + "What can I do for you?");
//        System.out.println(INDENTATION + LINE);
//        while (!isGoodBye) {
//            try {
//                String[] userInput = sc.nextLine().trim().split(" ");
//                System.out.println(INDENTATION + LINE);
//                duke.command.Command command = duke.command.Command.valueOf(userInput[0].toUpperCase());
//                if (userInput.length == 1) {
//                    switch (command) {
//                        case BYE:
//                            System.out.println(INDENTATION + "Bye. Hope to see you again soon!");
//                            isGoodBye = true;
//                            storage.save(taskList.getTaskList());
//                            break;
//                        case LIST:
//                            int numOfTasks = taskList.getSize();
//                            if (numOfTasks == 0) {
//                                System.out.println(INDENTATION + "You do not have any tasks in your list right now.");
//                                break;
//                            }
//                            System.out.printf(INDENTATION + "Here %s the task%s in your list:\n",
//                                    numOfTasks > 1 ? "are" : "is", numOfTasks > 1 ? "s" : "");
//                            for (int i = 0; i < numOfTasks; i++) {
//                                System.out.println(INDENTATION + String.valueOf(i + 1) + ". " +
//                                        this.taskList.getTask(i).toString());
//                            }
//                            break;
//                        case MARK:
//                            throw new duke.DukeException(INDENTATION +
//                                    "☹ OOPS!!! The mark command should be used as shown. " +
//                                    "eg. mark {num of task in list to be marked as done}");
//                        case UNMARK:
//                            throw new duke.DukeException(INDENTATION +
//                                    "☹ OOPS!!! The unmark command should be used as shown. " +
//                                    "eg. mark {num of task in list to be unmarked as incomplete}");
//                        case TODO:
//                            throw new duke.DukeException(INDENTATION +
//                                    "☹ OOPS!!! The description of a todo cannot be empty, " +
//                                    "usage of todo is as shown. eg. todo {task to be done}");
//                        case DEADLINE:
//                            throw new duke.DukeException(INDENTATION +
//                                    "☹ OOPS!!! The description of a deadline cannot be empty, " +
//                                    "usage of deadline is as shown. " +
//                                    "eg. deadline {task to be done} /by {date/time to complete}");
//                        case EVENT:
//                            throw new duke.DukeException(INDENTATION +
//                                    "☹ OOPS!!! The description of a event cannot be empty, " +
//                                    "usage of event is as shown. " +
//                                    "eg. event {event} /at {date/time}");
//                        case DELETE:
//                            throw new duke.DukeException(INDENTATION +
//                                    "☹ OOPS!!! The delete command should be used as shown. " +
//                                    "eg. delete {num of task in list to be deleted.}");
//                        default:
//                            throw new duke.DukeException(INDENTATION +
//                                    "☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
//                    }
//                } else {
//                    switch (command) {
//                        case MARK:
//                            if (userInput.length != 2) {
//                                throw new duke.DukeException(INDENTATION +
//                                        "☹ OOPS!!! The mark command should be used as shown. " +
//                                        "eg. mark {num of task in list to be marked as done}");
//                            }
//                            if (!userInput[1].matches("\\d+")) {
//                                throw new duke.DukeException(INDENTATION +
//                                        "☹ OOPS!!! The mark command should be used as shown. " +
//                                        "eg. mark {num of task in list to be marked as done}");
//                            }
//                            int indexToMark = Integer.parseInt(userInput[1]) - 1;
//                            if (indexToMark < 0 || indexToMark >= taskList.getSize()) {
//                                throw new duke.DukeException(INDENTATION +
//                                        "☹ OOPS!!! You chose a task that does not exist.");
//                            }
//                            duke.task.Task taskToMark = taskList.getTask(indexToMark);
//                            taskToMark.markAsDone();
//                            System.out.println(INDENTATION + "Nice! I've marked this task as done:");
//                            System.out.println(INDENTATION + INDENTATION + taskToMark.toString());
//                            break;
//                        case UNMARK:
//                            if (userInput.length != 2) {
//                                throw new duke.DukeException(INDENTATION +
//                                        "☹ OOPS!!! The unmark command should be used as shown. " +
//                                        "eg. mark {num of task in list to be unmarked as incomplete}");
//                            }
//                            if (!userInput[1].matches("\\d+")) {
//                                throw new duke.DukeException(INDENTATION +
//                                        "☹ OOPS!!! The mark command should be used as shown. " +
//                                        "eg. mark {num of task in list to be marked as done}");
//                            }
//                            int indexToUnmark = Integer.parseInt(userInput[1]) - 1;
//                            if (indexToUnmark < 0 || indexToUnmark >= taskList.getSize()) {
//                                throw new duke.DukeException(INDENTATION +
//                                        "☹ OOPS!!! You chose a task that does not exist.");
//                            }
//                            duke.task.Task taskToUnmark = taskList.getTask(indexToUnmark);
//                            taskToUnmark.markAsUndone();
//                            System.out.println(INDENTATION + "OK, I've marked this task as not done yet:");
//                            System.out.println(INDENTATION + INDENTATION + taskToUnmark.toString());
//                            break;
//                        case TODO:
//                            String[] taskDescription = Arrays.copyOfRange(userInput, 1, userInput.length);
//                            duke.task.Task toDo = new duke.task.ToDo(String.join(" ", taskDescription));
//                            taskList.addToTaskList(toDo);
//                            int numTasks = taskList.getSize();
//                            System.out.println(INDENTATION + "Got it. I've added this task:");
//                            System.out.println(INDENTATION + INDENTATION + "added: " + toDo.toString());
//                            System.out.println(INDENTATION +
//                                    "Now you have " + String.valueOf(numTasks) + " tasks in the list.");
//                            break;
//                        case DEADLINE:
//                            if (!Arrays.asList(userInput).contains("/by")) {
//                                throw new duke.DukeException(INDENTATION +
//                                        "☹ OOPS!!! Please use the deadline command in the correct manner, " +
//                                        "usage of deadline is as shown. " +
//                                        "eg. deadline {task to be done} /by {yyyy-mm-dd}");
//                            }
//                            int byIndex = Arrays.asList(userInput).indexOf("/by");
//                            String[] deadlineDescription = Arrays.copyOfRange(userInput, 1, byIndex);
//                            String[] by = Arrays.copyOfRange(userInput, byIndex + 1, userInput.length);
//                            if (!String.join(" ", by).matches("^\\d{4}-\\d{2}-\\d{2}$")) {
//                                throw new duke.DukeException(INDENTATION +
//                                        "☹ OOPS!!! Please use the deadline command in the correct manner, " +
//                                        "usage of deadline is as shown. " +
//                                        "eg. deadline {task to be done} /by {yyyy-mm-dd}");
//                            }
//                            duke.task.Task deadline = new duke.task.Deadline(String.join(" ", deadlineDescription),
//                                    String.join(" ", by));
//                            taskList.addToTaskList(deadline);
//                            System.out.println(INDENTATION + "Got it. I've added this task:");
//                            System.out.println(INDENTATION + INDENTATION + "added: " + deadline.toString());
//                            System.out.println(INDENTATION +
//                                    "Now you have " + String.valueOf(taskList.getSize()) + " tasks in the list.");
//                            break;
//                        case EVENT:
//                            if (!Arrays.asList(userInput).contains("/at")) {
//                                throw new duke.DukeException(INDENTATION +
//                                        "☹ OOPS!!! Please use the event command in the correct manner, " +
//                                        "usage of deadline is as shown. " +
//                                        "eg. event {event} /at {yyyy-mm-dd}");
//                            }
//                            int atIndex = Arrays.asList(userInput).indexOf("/at");
//                            String[] eventDescription = Arrays.copyOfRange(userInput, 1, atIndex);
//                            String[] at = Arrays.copyOfRange(userInput, atIndex + 1, userInput.length);
//                            if (!String.join(" ", at).matches("^\\d{4}-\\d{2}-\\d{2}$")) {
//                                throw new duke.DukeException(INDENTATION +
//                                        "☹ OOPS!!! Please use the event command in the correct manner, " +
//                                        "usage of deadline is as shown. " +
//                                        "eg. event {event} /at {yyyy-mm-dd}");
//                            }
//                            duke.task.Task event = new duke.task.Event(String.join(" ", eventDescription),
//                                    String.join(" ", at));
//                            taskList.addToTaskList(event);
//                            System.out.println(INDENTATION + "Got it. I've added this task:");
//                            System.out.println(INDENTATION + INDENTATION + "added: " + event.toString());
//                            System.out.println(INDENTATION +
//                                    "Now you have " + String.valueOf(taskList.getSize()) + " tasks in the list.");
//                            break;
//                        case DELETE:
//                            if (userInput.length != 2) {
//                                throw new duke.DukeException(INDENTATION +
//                                        "☹ OOPS!!! The delete command should be used as shown. " +
//                                        "eg. delete {num of task in list to be deleted.}");
//                            }
//                            if (!userInput[1].matches("\\d+")) {
//                                throw new duke.DukeException(INDENTATION +
//                                        "☹ OOPS!!! The mark command should be used as shown. " +
//                                        "eg. mark {num of task in list to be marked as done}");
//                            }
//                            int indexToDelete = Integer.parseInt(userInput[1]) - 1;
//                            if (indexToDelete < 0 || indexToDelete >= taskList.getSize()) {
//                                throw new duke.DukeException(INDENTATION +
//                                        "☹ OOPS!!! You chose a task that does not exist.");
//                            }
//                            duke.task.Task taskToDelete = taskList.getTask(indexToDelete);
//                            System.out.println(INDENTATION + "Noted. I've removed this task:");
//                            System.out.println(INDENTATION + INDENTATION + taskToDelete.toString());
//                            taskList.removeFromTaskList(indexToDelete);
//                            System.out.println(INDENTATION +
//                                    "Now you have " + String.valueOf(taskList.getSize()) + " tasks in the list.");
//                            break;
//                        default:
//                            throw new duke.DukeException(INDENTATION +
//                                    "☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
//                    }
//                }
//            } catch (IllegalArgumentException e) {
//                System.out.println(INDENTATION + "☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
//            } catch (duke.DukeException e) {
//                System.out.println(INDENTATION + e.getMessage());
//            } finally {
//                System.out.println(INDENTATION + LINE);
//            }
//        }
//    }


    /**
     * Starts the chatbot function of Duke for users to enter their tasks.
     * @throws IOException if unable to load or save to file.
     */
    public void run() throws IOException {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (DukeException e) {
                ui.showError(e.getMessage());
            } catch (IndexOutOfBoundsException e) {
                ui.showOutOfBoundsError();
            } catch (IllegalArgumentException e) {
                ui.showIllegalArgumentError();
            }
            finally {
                ui.showLine();
                storage.save(tasks.getTaskList());
            }
        }
    }


    /**
     * Checks if the filepath to save the tasks on already exists.
     * If it does not exist, directory and file is created.
     * @throws IOException
     */
    public void createSaveFile() throws IOException {
        if (!storage.isDirectoryPresent()) {
            storage.createDirectory();
        }
        if (!storage.isFilePresent()) {
            storage.createFile();
        }
    }


    public static void main(String[] args) throws IOException {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        new Duke(System.getProperty("user.home")).run();
    }
}
