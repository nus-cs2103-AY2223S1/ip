package sky.storage;

import java.util.ArrayList;
import java.util.List;

import sky.Sky;
import sky.TaskList;
import sky.exception.TextNoMeaningException;

/**
 * The HistoryLog class deals with storing all the histories from start up of the chat bot to its termination.
 * Its use-case is to undo commands by the user.
 */
public class History {
    private Sky sky;
    private List<TaskList> historyLog = new ArrayList<>();
    private int maxNumberOfStepsToRetrace = 0;

    /**
     * Sets up the historyLog in History to include the initial task list as the starting point of history.
     *
     * @param tasklist Tasklist to be set as the starting point of history.
     */
    public History(Sky sky, TaskList tasklist) {
        this.sky = sky;
        assert this.historyLog.size() == 0 : "historyLog should be empty when initially setting up historyLog";
        this.historyLog.add(tasklist.makeACopy());
    }

    /**
     * Adds history to the history log.
     *
     * @param taskList Tasklist to be added into history log.
     */
    public void addHistoryInTime(TaskList taskList) {
        assert this.historyLog.size() > 0 : "historyLog should never be empty when adding history.";
        this.historyLog.add(taskList.makeACopy());
        this.maxNumberOfStepsToRetrace += 1;
    }

    /**
     * Returns a task list whose state is at a specified point in history.
     *
     * @param steps The number of steps to retrace back by.
     * @return Task list whose state is at a point in history.
     */
    public TaskList revertStepsBack(int steps) throws TextNoMeaningException {
        if (this.historyLog.size() <= 1) {
            throw new TextNoMeaningException("There is no record of history thus far.");
        }
        if (steps < 1) {
            throw new TextNoMeaningException("Specify a step number within the range [1, "
                    + maxNumberOfStepsToRetrace + "]. The number you provide represents "
                    + "how many steps back do you want to retrace.");
        } else if (steps >= this.historyLog.size()) {
            throw new TextNoMeaningException("History is not that long. Specify a step number "
                    + "within the range [1, " + maxNumberOfStepsToRetrace + "].");
        }
        TaskList retrievedTaskList = this.historyLog.get(historyLog.size() - steps - 1);
        this.sky.changeHistory(retrievedTaskList.makeACopy());
        this.maxNumberOfStepsToRetrace = this.maxNumberOfStepsToRetrace - steps;
        this.historyLog = this.historyLog.subList(0, (historyLog.size() - steps));
        return retrievedTaskList;
    }
}
