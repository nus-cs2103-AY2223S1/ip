package duke;

import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Represents the main part of the Duke program and is in charge of coordinating all the various supporting classes in
 * the duke package.
 */
public class Duke {
    private Ui ui;
    private Storage storage;
    private boolean isAcceptingInput;
    private TaskList tasks;

    public Duke(String savePath, String saveName) {
        this.ui = new Ui();
        this.storage = new Storage(savePath, saveName);
        tasks = new TaskList();

        storage.loadFile(tasks);
        isAcceptingInput = true;
    }

    private String exit() {
        isAcceptingInput = false;
        closeApplicationAfterDelay();
        return ui.getExitMessage();
    }

    private void closeApplicationAfterDelay() {
        int delay = 1000;
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                System.exit(0);
            }
        };
        new Timer().schedule(timerTask, delay);
    }

    public boolean isAcceptingInput() {
        return isAcceptingInput;
    }

    private int getTaskIndexFromParsedOutput(String[] parsedOutput, String cmd) throws InputIndexOutOfBoundsException {
        int taskNum = Integer.parseInt(parsedOutput[1]);
        int taskIndex = taskNum - 1;
        if (taskIndex < 0 || taskIndex >= tasks.getTaskCount()) {
            throw new InputIndexOutOfBoundsException(cmd + " " + taskNum);
        }
        return taskIndex;
    }

    /**
     * Takes in a user input and processes it
     *
     * @param input A user input string.
     */
    public String getResponse(String input) {
        try {
            String[] parsedOutput = Parser.parseInput(input);
            String cmd = parsedOutput[0];
            switch (cmd) {
            case "bye":
                return exit();
            case "list":
                return ui.listTasks(tasks.getTasks());
            case "find": {
                ArrayList<Task> foundTasks = tasks.find(parsedOutput[1]);
                return ui.listFoundTasks(foundTasks);
            }
            case "mark": {
                int taskIndex = getTaskIndexFromParsedOutput(parsedOutput, cmd);
                Task markedTask = tasks.markTask(taskIndex);
                return ui.getMarkedTask(markedTask);
            }
            case "unmark": {
                int taskIndex = getTaskIndexFromParsedOutput(parsedOutput, cmd);
                Task unmarkedTask = tasks.unmarkTask(taskIndex);
                return ui.getUnmarkedTask(unmarkedTask);
            }
            case "delete": {
                int taskIndex = getTaskIndexFromParsedOutput(parsedOutput, cmd);
                Task removedTask = tasks.removeTask(taskIndex);
                return ui.getRemovedTask(removedTask, tasks.getTaskCount());
            }
            case "todo": {
                Task addedTask = tasks.addTodo(parsedOutput[1]);
                return ui.getAddedTask(addedTask, tasks.getTaskCount());
            }
            case "deadline": {
                String description = parsedOutput[1];
                String date = parsedOutput[2];
                String time = parsedOutput[3];
                Task addedTask = tasks.addDeadline(description, date, time);
                return ui.getAddedTask(addedTask, tasks.getTaskCount());
            }
            case "event": {
                String description = parsedOutput[1];
                String dateStart = parsedOutput[2];
                String timeStart = parsedOutput[3];
                String dateEnd = parsedOutput[4];
                String timeEnd = parsedOutput[5];
                Task addedTask = tasks.addEvent(description, dateStart, timeStart, dateEnd, timeEnd);
                return ui.getAddedTask(addedTask, tasks.getTaskCount());
            }
            default:
                throw new InvalidDukeInputException();
            }
        } catch (BannedDukeCharacterException e) {
            return ui.getBannedCharacterInputResponse(e.getMessage());
        } catch (InvalidDukeInputException e) {
            return ui.getInvalidInputResponse();
        } catch (MissingDukeInputException e) {
            return ui.getMissingInputResponse(e.getMessage());
        } catch (InputIndexOutOfBoundsException e) {
            String[] cmdNum = e.getMessage().split(" ");
            String cmd = cmdNum[0];
            String inputNum = cmdNum[1];
            return ui.getInputIndexOutOfBoundsResponse(cmd, inputNum);
        } catch (DateTimeParseException e) {
            return ui.getDateTimeErrorResponse();
        } finally {
            storage.saveFile(tasks);
        }
    }
}
