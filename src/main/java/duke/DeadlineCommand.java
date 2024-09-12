package duke;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.ArrayList;

public class DeadlineCommand extends TaskCommand {
    @Override
    boolean isTaskEmpty(String fullCommand) {
        return fullCommand.length() == 8;
    }
    
    @Override
    void handleEmptyTask(String fullCommand) throws DukeDeadlineEmptyException {
        if(isTaskEmpty(fullCommand)) {
            throw new DukeDeadlineEmptyException();
        }
        assert fullCommand.length() > 8;
    }

    @Override
    String addTaskToList(String fullCommand,ArrayList<Task> listOfTasks) {
       assert fullCommand.length() >= 8;
       int index = fullCommand.indexOf("/");
       String name = fullCommand.substring(9, index - 1);
       String dueDate = fullCommand.substring(index + 4);
       TaskList taskList = new TaskList(listOfTasks);
          try {
              return addTaskWithDateFormat(name, dueDate, taskList);
        } catch (DateTimeException e) {
              return addTaskWithNoDateFormat(name, dueDate, taskList);
        }
    }

    String addTaskWithDateFormat(String name, String dueDate,TaskList taskList) {
        LocalDate date = LocalDate.parse(dueDate);
        Task t = new Deadline(name,false, date);
        return taskList.addToList(t);
    }

    String addTaskWithNoDateFormat(String name, String dueDate, TaskList taskList) {
        Task t = new Deadline(name,false, dueDate);
        return taskList.addToList(t);
    }
}