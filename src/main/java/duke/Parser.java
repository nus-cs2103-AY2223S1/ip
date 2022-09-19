package duke;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDos;

/**
 * Deals with making sense of the user command.
 */
public class Parser {

    private TaskList taskList;
    private Ui ui;
    private Storage storage;
    private String response = "";

    /**
     * Constructs a new parser object with given
     * task list, Ui and storage.
     *
     * @param taskList to be used.
     * @param ui used to display texts.
     * @param storage used to save and load.
     */
    public Parser(TaskList taskList, Ui ui, Storage storage) {
        this.taskList = taskList;
        this.ui = ui;
        this.storage = storage;
    }

    /**
     * Checks whether command is valid and executes the command.
     *
     * @param input command input by user.
     * @return response.
     */
    public String checkAndExecuteCommand(String input) {
        String userInput = input.trim();
        if (userInput.equals("list")) {
            response = ui.taskListString(taskList);
        } else if (containsOperationWord(userInput)) {
            response = executeCommand(userInput.trim());
        } else {
            response = ui.invalidCommandErrorString();
        }
        return response;
    }

    private String executeCommand(String userInput) {
        try {
            String[] tokens = userInput.split("\\s+", 2);
            String firstWord = tokens[0];

            switch (firstWord) {
            case "find":
                executeFindCommand(tokens);
                break;
            case "mark":
                excecuteMarkCommand(tokens, firstWord);
                break;
            case "unmark":
                executeUnmarkCommand(tokens, firstWord);
                break;
            case "delete":
                executeDeleteCommand(tokens);
                break;
            case "todo":
                executeTodoCommand(tokens);
                break;
            case "deadline":
                executeDeadlineCommand(userInput);
                break;
            case "event":
                executeEventCommand(userInput);
                break;
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            return ui.showNoDescriptionError(userInput);
        }
        return response;
    }

    private void executeEventCommand(String userInput) {
        String[] eventToken = splitEventInput(userInput);
        String eventContent = eventToken[0];
        String eventDate = eventToken[1];

        Event event = new Event(eventContent, eventDate);
        this.taskList.addTask(event);
        storage.save(taskList);
        response = ui.addTaskMsgString(event, taskList);
    }

    private void executeDeadlineCommand(String userInput) {
        String[] deadlineToken = splitDeadlineInput(userInput);
        String deadlineContent = deadlineToken[0];
        String date = deadlineToken[1];

        Deadline deadline = new Deadline(deadlineContent, date);
        this.taskList.addTask(deadline);
        storage.save(taskList);
        response = ui.addTaskMsgString(deadline, taskList);
    }

    private void executeTodoCommand(String[] tokens) {
        String taskContent = tokens[1];
        ToDos toDos = new ToDos(taskContent);
        this.taskList.addTask(toDos);
        storage.save(taskList);
        response = ui.addTaskMsgString(toDos, taskList);
    }

    private void executeDeleteCommand(String[] tokens) {
        int taskNo = Integer.parseInt(tokens[1]);
        Task task3 = taskList.getTask(taskNo);
        this.taskList.deleteTask(taskNo);
        storage.save(taskList);
        response = ui.deleteTaskMsgString(task3, taskList);
    }

    private void executeUnmarkCommand(String[] tokens, String firstWord) {
        int taskNumber2 = Integer.parseInt(tokens[1]);
        Task task2 = taskList.getTask(taskNumber2);
        markingOfTasks(firstWord, taskNumber2);
        storage.save(taskList);
        response = ui.taskUnmarkedMsgString(task2);
    }

    private void excecuteMarkCommand(String[] tokens, String firstWord) {
        int taskNumber = Integer.parseInt(tokens[1]);
        Task task = taskList.getTask(taskNumber);
        markingOfTasks(firstWord, taskNumber);
        storage.save(taskList);
        response = ui.taskMarkedMsgString(task);
    }

    private void executeFindCommand(String[] tokens) {
        String wordToFind = tokens[1];
        String filteredListString = taskList.getTaskStringFiltered(wordToFind);
        response = ui.printFilteredList(filteredListString);
    }

    private boolean containsOperationWord(String userInput) {
        return userInput.toLowerCase().contains("mark")
                || userInput.toLowerCase().contains("unmark")
                || userInput.toLowerCase().contains("todo")
                || userInput.toLowerCase().contains("deadline")
                || userInput.toLowerCase().contains("event")
                || userInput.toLowerCase().contains("delete")
                || userInput.toLowerCase().contains("find");
    }

    private String[] splitDeadlineInput(String userInput) {
        String[] result = new String[2];

        String[] tokens2 = userInput.split(" /by ", 2);
        String date = tokens2[1];
        String content = tokens2[0];
        String[] tokens3 = content.split("\\s+", 2);
        String taskContent = tokens3[1];

        result[0] = taskContent;
        result[1] = date;
        return result;
    }

    private String[] splitEventInput(String userInput) {
        String[] result = new String[2];

        String[] tokens2 = userInput.split(" /at ", 2);
        String date = tokens2[1];
        String content = tokens2[0];
        String[] tokens3 = content.split("\\s+", 2);
        String taskContent = tokens3[1];

        result[0] = taskContent;
        result[1] = date;
        return result;
    }

    private void markingOfTasks(String firstWord, int taskNumber) {
        Task task = this.taskList.getTask(taskNumber);
        if (firstWord.equals("mark")) {
            task.setDone(true);
            this.ui.taskMarkedMsgString(task);
        } else {
            task.setDone(false);
            this.ui.taskUnmarkedMsgString(task);
        }
    }
}