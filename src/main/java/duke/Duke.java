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
    private final Ui UI;
    private final Storage STORAGE;
    private boolean isAcceptingInput;
    private final TaskList TASKS;

    public Duke(String savePath, String saveName) {
        this.UI = new Ui();
        this.STORAGE = new Storage(savePath, saveName);
        TASKS = new TaskList();

        STORAGE.loadFile(TASKS);
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
            return UI.getBannedCharacterInputResponse(e.getMessage());
        } catch (InvalidDukeInputException e) {
            return UI.getInvalidInputResponse();
        } catch (MissingDukeInputException e) {
            return UI.getMissingInputResponse(e.getMessage());
        } catch (InputIndexOutOfBoundsException e) {
            return executeIndexOutOfBoundsResponse(e);
        } catch (DateTimeParseException e) {
            return UI.getDateTimeErrorResponse();
        } finally {
            STORAGE.saveFile(TASKS);
        }
    }

    private void assertClassReferencesPresent() {
        assert TASKS != null : "There should be a TaskList at all times.";
        assert UI != null : "There should be a UI at all times";
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
        return UI.getExitMessage();
    }

    private void closeApplicationAfterDelay() {
        final int APPLICATION_CLOSE_DELAY = 1000;
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                System.exit(0);
            }
        };
        new Timer().schedule(timerTask, APPLICATION_CLOSE_DELAY);
    }

    private String executeListResponse() {
        return UI.listTasks(TASKS.getTasks());
    }

    private String executeFindResponse(String[] parsedOutput) {
        ArrayList<Task> foundTasks = TASKS.find(parsedOutput[1]);
        return UI.listFoundTasks(foundTasks);
    }

    private String executeMarkResponse(String[] parsedOutput, String cmd) {
        int taskIndex = getTaskIndexFromParsedOutput(parsedOutput, cmd);
        Task markedTask = TASKS.markTask(taskIndex);
        return UI.getMarkedTask(markedTask);
    }

    private String executeUnmarkResponse(String[] parsedOutput, String cmd) {
        int taskIndex = getTaskIndexFromParsedOutput(parsedOutput, cmd);
        Task unmarkedTask = TASKS.unmarkTask(taskIndex);
        return UI.getUnmarkedTask(unmarkedTask);
    }

    private String executeDeleteResponse(String[] parsedOutput, String cmd) {
        int taskIndex = getTaskIndexFromParsedOutput(parsedOutput, cmd);
        Task removedTask = TASKS.removeTask(taskIndex);
        return UI.getRemovedTask(removedTask, TASKS.getTaskCount());
    }

    private int getTaskIndexFromParsedOutput(String[] parsedOutput, String cmd) throws InputIndexOutOfBoundsException {
        int taskNum = Integer.parseInt(parsedOutput[1]);
        int taskIndex = taskNum - 1;
        if (taskIndex < 0 || taskIndex >= TASKS.getTaskCount()) {
            throw new InputIndexOutOfBoundsException(cmd + " " + taskNum);
        }
        return taskIndex;
    }

    private String executeTodoResponse(String[] parsedOutput) {
        Task addedTask = TASKS.addTodo(parsedOutput[1]);
        return UI.getAddedTask(addedTask, TASKS.getTaskCount());
    }

    private String executeDeadlineResponse(String[] parsedOutput) {
        String description = parsedOutput[1];
        String date = parsedOutput[2];
        String time = parsedOutput[3];
        Task addedTask = TASKS.addDeadline(description, date, time);
        return UI.getAddedTask(addedTask, TASKS.getTaskCount());
    }

    private String executeEventResponse(String[] parsedOutput) {
        String description = parsedOutput[1];
        String dateStart = parsedOutput[2];
        String timeStart = parsedOutput[3];
        String dateEnd = parsedOutput[4];
        String timeEnd = parsedOutput[5];
        Task addedTask = TASKS.addEvent(description, dateStart, timeStart, dateEnd, timeEnd);
        return UI.getAddedTask(addedTask, TASKS.getTaskCount());
    }

    private String executeIndexOutOfBoundsResponse(InputIndexOutOfBoundsException e) {
        final String SPACE_SEPARATOR = " ";
        String[] cmdNum = e.getMessage().split(SPACE_SEPARATOR);
        String cmd = cmdNum[0];
        String inputNum = cmdNum[1];
        return UI.getInputIndexOutOfBoundsResponse(cmd, inputNum);
    }
}
