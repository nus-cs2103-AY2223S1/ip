package duke;

import java.io.IOException;
import java.time.LocalDate;

public class Duke {

    private Ui ui;
    private Storage storage;
    private TaskList taskList;
    private final static String FILEDESTINATION = "./src/main/duke.txt";

    public Duke() {
        ui = new Ui();
        storage = new Storage(FILEDESTINATION);
        taskList = new TaskList();
    }

    public void run() throws DukeException, IOException {
        storage.loadTasks();
        ui.greet();
        ui.showTaskList();

        // Boolean value to check if user wants to leave
        boolean isQuit = false;

        while (!isQuit) {
            try {
                String userCommand = ui.getCommand();
                if (userCommand.equals("bye")) {
                    isQuit = true;
                    ui.bye();
                } else if (userCommand.equals("list")) {
                    ui.showTaskList();
                } else {
                    // Get all the words the user has typed
                    String[] words = userCommand.split(" ");
                    // Check if user wants to mark task
                    if (Parser.isMarkTask(words)) {
                        int taskNumber = Integer.parseInt(words[1]);
                        // Check if user enters a number out of range
                        if (taskNumber < 0 || taskNumber > TaskList.taskArrayList.size()) {
                            throw new DukeException("Number out of range!");
                        } else {
                            ui.markTaskDoneAndPrintOutput(taskNumber);
                        }
                        // Checks if user wants to unmark task
                    } else if (Parser.isUnmarkTask(words)) {
                        int taskNumber = Integer.parseInt(words[1]);
                        // Check if user enters a number out of range
                        if (taskNumber < 0 || taskNumber > TaskList.taskArrayList.size()) {
                            throw new DukeException("Number out of range!");
                        } else {
                            ui.markTaskNotDoneAndPrintOutput(taskNumber);
                        }
                        // Check if user wants to delete a task
                    } else if (Parser.isDeleteTask(words)) {
                        int taskNumber = Integer.parseInt(words[1]);
                        if (taskNumber < 0 || taskNumber >TaskList.taskArrayList.size()) {
                            throw new DukeException("Number out of range!");
                        } else {
                            ui.markTaskDeletedAndPrintOutput(taskNumber);
                        }
                    } else {
                        // User is trying to add a new to-do / deadline / event
                        if (Parser.isAddTodoTask(words)) {
                            createAndAddTodo(words);
                        } else if (Parser.isAddDeadlineTask(words)) {
                            createAndAddDeadline(words);
                        } else if (Parser.isAddEventTask(words)) {
                            createAndAddEvent(words);
                        } else if (Parser.isFindTask(words)) {
                            String keywords = Parser.joinString(words, 1);
                            keywords = keywords.substring(0, keywords.length() - 1);
                            System.out.println("Here are the matching tasks in your list:");
                            for (Task task: TaskList.taskArrayList) {
                                if (task.getDescription().contains(keywords)) {
                                    System.out.println(task.toString());
                                }
                            }
                        }
                        else {
                            throw new DukeException("I'm sorry, I don't know what that means!");
                        }
                    }
                }
            } catch (DukeException dukeException) {
                System.out.println(dukeException.getMessage());
            }
            finally {
                storage.saveTasks();
            }
        }
    }

    public void createAndAddTodo(String[] wordsArray) throws DukeException {
        String description = "";
        if (wordsArray.length > 1) {
            description = Parser.joinString(wordsArray, 1);
            // Correct an extra spacing at the end
            description = description.substring(0, description.length() - 1);
        }
        Todo newTodo = new Todo(description);
        TaskList.taskArrayList.add(newTodo);
        ui.printAddedTask(newTodo);
    }

    public void createAndAddDeadline(String[] wordsArray) throws DukeException {
        String remainingDescription = "";
        String description = "";
        String[] remainingWords;
        String[] dateTimeArray = null;
        String by = "";
        if (wordsArray.length > 1) {
            // Remaining description are the words after the task description
            remainingDescription = Parser.joinString(wordsArray, 1);
            remainingWords = remainingDescription.split(" /by ");
            description = remainingWords[0];
            by = remainingWords[1];
            dateTimeArray = by.split(" ");
            // Cut down a white spacing at the end
            by = by.substring(0, by.length() - 1);
        }
        assert dateTimeArray != null;
        LocalDate byDate = Parser.createLocalDate(dateTimeArray[0].strip());
        Deadline newDeadline = new Deadline(description, byDate, by);
        TaskList.taskArrayList.add(newDeadline);
        ui.printAddedTask(newDeadline);
    }

    public void createAndAddEvent(String[] wordsArray) throws DukeException {
        // If user is trying to add an event, save the description and the 'at' date
        // Have a default value in case the user did not add any description
        String description = "";
        String[] remainingWords;
        String at = "";
        String remainingDescription = "";
        if (wordsArray.length > 1) {
            // Remaining description are the words after the task description
            remainingDescription = Parser.joinString(wordsArray, 1);
            remainingWords = remainingDescription.split(" /at ");
            description = remainingWords[0];
            at = remainingWords[1];
            // Cut down a white spacing at the end
            at = at.substring(0, at.length() - 1);
        }
        Event newEvent = new Event(description, at);
        TaskList.taskArrayList.add(newEvent);
        ui.printAddedTask(newEvent);
    }

    /**
     * The main function
     * @param args arguments
     */
    public static void main(String[] args) throws DukeException, IOException {
        new Duke().run();
    }



}
