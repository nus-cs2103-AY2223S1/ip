package duke;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDos;

public class Parser {

    private TaskList taskList;
    private Ui ui;
    private Storage storage;

    public Parser(TaskList taskList, Ui ui, Storage storage) {
        this.taskList = taskList;
        this.ui = ui;
        this.storage = storage;
    }

    public void checkAndPerformOperations(String userInput) throws DukeException {
        if (userInput.equals("list")) {
            ui.printTaskList(taskList);

        } else if (containsOperationWord(userInput)) {
            performOperations(userInput.trim());
        } else {
            throw new DukeException();
        }
    }

    public void performOperations(String userInput) {
        try {
            String[] tokens = userInput.split("\\s+", 2);
            String firstWord = tokens[0];

            switch (firstWord) {
                case "mark":
                case "unmark":
                    int taskNumber = Integer.parseInt(tokens[1]);
                    markingOfTasks(firstWord, taskNumber); //and print
                    storage.save(taskList);
                    break;

                case "delete":
                    int taskNo = Integer.parseInt(tokens[1]);
                    Task task = taskList.getTask(taskNo);
                    this.taskList.deleteTask(taskNo);
                    ui.printDeleteTaskMsg(task, taskList);
                    storage.save(taskList);
                    break;

                case "todo":
                    String taskContent = tokens[1];
                    ToDos toDos = new ToDos(taskContent);
                    this.taskList.addTask(toDos);
                    ui.printAddTaskMsg(toDos, taskList);
                    storage.save(taskList);
                    break;

                case "deadline":
                    String[] deadlineToken = splitDeadlineInput(userInput);
                    String deadlineContent = deadlineToken[0];
                    String date = deadlineToken[1];

                    Deadline deadline = new Deadline(deadlineContent, date);
                    this.taskList.addTask(deadline);
                    ui.printAddTaskMsg(deadline, taskList);
                    storage.save(taskList);
                    break;

                case "event":
                    String[] eventToken = splitEventInput(userInput);
                    String eventContent = eventToken[0];
                    String eventDate = eventToken[1];

                    Event event = new Event(eventContent, eventDate);
                    this.taskList.addTask(event);
                    ui.printAddTaskMsg(event, taskList);
                    storage.save(taskList);
                    break;
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            ui.showNoDescriptionError(userInput);
        }
    }

    public boolean containsOperationWord(String userInput) {
        return userInput.toLowerCase().contains("mark")
                || userInput.toLowerCase().contains("unmark")
                || userInput.toLowerCase().contains("todo")
                || userInput.toLowerCase().contains("deadline")
                || userInput.toLowerCase().contains("event")
                || userInput.toLowerCase().contains("delete");
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
            this.ui.printTaskMarkedMsg(task);
        } else {
            task.setDone(false);
            this.ui.printTaskUnmarkedMsg(task);
        }
    }

}
