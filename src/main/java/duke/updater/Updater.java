package duke.updater;

import duke.exception.DukeException;
import duke.parser.Parser;
import duke.task.Task;

import java.time.LocalDate;

public class Updater {

    /**
     * Updates a task's description and/ or date
     *
     * @param task Task to be updated
     * @param parser Contains updated values
     * @throws DukeException Exception is not caught at this level
     */
    public void updateTask(Task task, Parser parser) throws DukeException {
        boolean hasUpdateDateClause = parser.hasUpdateDateClause();

        if (hasUpdateDateClause && task.isTaskTypeEvent()) {
            LocalDate updatedDate = parser.getEventDate();
            task.updateDate(updatedDate);
        } else if (hasUpdateDateClause && task.isTaskTypeDeadline()) {
            LocalDate updatedDate = parser.getDeadlineDate();
            task.updateDate(updatedDate);
        }

        boolean hasUpdateDescClause = parser.hasUpdateDescClause();
        if (hasUpdateDescClause) {
            String updatedDescription = parser.getUpdatedDescription();
            task.updateTask(updatedDescription);
        }
    }


}
