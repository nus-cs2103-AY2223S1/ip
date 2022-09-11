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
    private final Ui ui;
    private final Storage storage;
    private boolean isAcceptingInput;
    private final TaskList tasks;

    public Duke(String savePath, String saveName) {
        this.ui = new Ui();
        this.storage = new Storage(savePath, saveName);
        tasks = new TaskList();

        storage.loadFile(tasks);
        isAcceptingInput = true;
    }

    /**
     * Returns if Duke is currently accepting input. Read only.
     *
     * @return A boolean value indicating if Duke is currently accepting input.
     */
    public boolean isAcceptingInput() {
        return isAcceptingInput;
    }

    /**
     * Takes in a user input, processes it, and returns a string to be displayed back to the Duke interface.
     *
     * @param input A user input string.
     * @return A string to be displayed back to the Duke interface.
     */
    public String getResponse(String input) {
        try {
            assertClassReferencesPresent();
            assertCurrentlyAcceptingInput();

            String[] parsedOutput = Parser.parseInput(input);
            assertArrayElementsNotNull(parsedOutput);
            String cmd = parsedOutput[0];
            switch (cmd) {
            case "bye":
                return executeByeResponse();
            case "list":
                return executeListResponse();
            case "help":
                return executeHelpResponse();
            case "find":
                return executeFindResponse(parsedOutput);
            case "mark":
                return executeMarkResponse(parsedOutput, cmd);
            case "unmark":
                return executeUnmarkResponse(parsedOutput, cmd);
            case "delete":
                return executeDeleteResponse(parsedOutput, cmd);
            case "todo":
                return executeTodoResponse(parsedOutput);
            case "deadline":
                return executeDeadlineResponse(parsedOutput);
            case "event":
                return executeEventResponse(parsedOutput);
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
            return executeIndexOutOfBoundsResponse(e);
        } catch (NumberFormatException e) {
            return ui.getBadNumberFormatResponse();
        } catch (DateTimeParseException e) {
            return ui.getDateTimeErrorResponse();
        } finally {
            storage.saveFile(tasks);
        }
    }

    private void assertClassReferencesPresent() {
        assert tasks != null : "There should be a TaskList at all times.";
        assert ui != null : "There should be a UI at all times";
    }

    private void assertCurrentlyAcceptingInput() {
        assert isAcceptingInput : "getResponse should not be run if not accepting input.";
    }

    private void assertArrayElementsNotNull(String[] arr) {
        for (String i : arr) {
            assert i != null : "The string array should not have any null elements";
        }
    }

    private String executeByeResponse() {
        isAcceptingInput = false;
        closeApplicationAfterDelay();
        return ui.getExitMessage();
    }

    private void closeApplicationAfterDelay() {
        int applicationCloseDelay = 1000;
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                System.exit(0);
            }
        };
        new Timer().schedule(timerTask, applicationCloseDelay);
    }

    private String executeListResponse() {
        return ui.listTasks(tasks.getTasks());
    }

    private String executeHelpResponse() {
        return ui.getHelpMessage();
    }

    private String executeFindResponse(String[] parsedOutput) {
        ArrayList<Task> foundTasks = tasks.find(parsedOutput[1]);
        return ui.listFoundTasks(foundTasks);
    }

    private String executeMarkResponse(String[] parsedOutput, String cmd) {
        int taskIndex = getTaskIndexFromParsedOutput(parsedOutput, cmd);
        Task markedTask = tasks.markTask(taskIndex);
        return ui.getMarkedTask(markedTask);
    }

    private String executeUnmarkResponse(String[] parsedOutput, String cmd) {
        int taskIndex = getTaskIndexFromParsedOutput(parsedOutput, cmd);
        Task unmarkedTask = tasks.unmarkTask(taskIndex);
        return ui.getUnmarkedTask(unmarkedTask);
    }

    private String executeDeleteResponse(String[] parsedOutput, String cmd) {
        int taskIndex = getTaskIndexFromParsedOutput(parsedOutput, cmd);
        Task removedTask = tasks.removeTask(taskIndex);
        return ui.getRemovedTask(removedTask, tasks.getTaskCount());
    }

    private int getTaskIndexFromParsedOutput(String[] parsedOutput, String cmd)
            throws InputIndexOutOfBoundsException, NumberFormatException {
        int taskNum = Integer.parseInt(parsedOutput[1]);
        int taskIndex = taskNum - 1;
        if (taskIndex < 0 || taskIndex >= tasks.getTaskCount()) {
            throw new InputIndexOutOfBoundsException(cmd + " " + taskNum);
        }
        return taskIndex;
    }

    private String executeTodoResponse(String[] parsedOutput) {
        Task addedTask = tasks.addTodo(parsedOutput[1]);
        return ui.getAddedTask(addedTask, tasks.getTaskCount());
    }

    private String executeDeadlineResponse(String[] parsedOutput) {
        String description = parsedOutput[1];
        String date = parsedOutput[2];
        String time = parsedOutput[3];
        Task addedTask = tasks.addDeadline(description, date, time);
        return ui.getAddedTask(addedTask, tasks.getTaskCount());
    }

    private String executeEventResponse(String[] parsedOutput) {
        String description = parsedOutput[1];
        String dateStart = parsedOutput[2];
        String timeStart = parsedOutput[3];
        String dateEnd = parsedOutput[4];
        String timeEnd = parsedOutput[5];
        Task addedTask = tasks.addEvent(description, dateStart, timeStart, dateEnd, timeEnd);
        return ui.getAddedTask(addedTask, tasks.getTaskCount());
    }

    private String executeIndexOutOfBoundsResponse(InputIndexOutOfBoundsException e) {
        String spaceSeparator = " ";
        String[] cmdNum = e.getMessage().split(spaceSeparator);
        String cmd = cmdNum[0];
        String inputNum = cmdNum[1];
        return ui.getInputIndexOutOfBoundsResponse(cmd, inputNum);
    }
}
