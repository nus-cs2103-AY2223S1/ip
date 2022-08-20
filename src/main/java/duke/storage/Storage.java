package duke.storage;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import java.util.ArrayList;
import java.util.List;

import duke.exception.DukeException;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.TaskList;
import duke.task.ToDo;

public class Storage {
    private final String filePath;

    public Storage(String directoryName, String fileName) throws DukeException {
        this.filePath = directoryName + "/" + fileName;

        try {
            File storageDirectory = new File(directoryName);

            if (!storageDirectory.exists()) {
                storageDirectory.mkdir();
            }

            File storageFile = new File(this.filePath);

            if (!storageFile.exists()) {
                storageFile.createNewFile();
            }
        } catch (IOException error) {
            throw new DukeException(String.format("Failed to load tasks, %s",
                    error.getMessage()));
        }
    }

    public TaskList loadTasksInStorage() {
        TaskList loadedTaskList;

        try {
            List<Task> loadedTasks = new ArrayList<>();
            BufferedReader storageReader = new BufferedReader(new FileReader(this.filePath));
            String taskString = storageReader.readLine();

            while(taskString != null) {
                int dateSeparatorIndex = taskString.lastIndexOf("|");
                String taskType = taskString.substring(0, 1);
                String taskStatus = taskString.substring(2, 3);
                String description = dateSeparatorIndex == 3
                        ? taskString.substring(4)
                        : taskString.substring(4, dateSeparatorIndex);
                String taskDate = taskString.substring(dateSeparatorIndex + 1);

                Task currentLoadedTask = taskType.equals("D")
                        ? new Deadline(description, taskDate)
                        : taskType.equals("E")
                        ? new Event(description, taskDate)
                        : new ToDo(description);

                if (taskStatus.equals("X")) {
                    currentLoadedTask.markAsFinished();
                }

                loadedTasks.add(currentLoadedTask);
                taskString = storageReader.readLine();
            }

            storageReader.close();
            loadedTaskList = new TaskList(loadedTasks);
        } catch (DukeException | IOException error) {
            loadedTaskList = new TaskList();
        }

        return loadedTaskList;
    }

    public void saveTasksInStorage(List<String> taskListString) throws DukeException {
        try {
            FileWriter storageWriter = new FileWriter(this.filePath);
            for (int i = 0; i < taskListString.size(); i++) {
                storageWriter.write(taskListString.get(i) + "\n");
            }
            storageWriter.close();
        } catch (IOException error) {
            throw new DukeException(String.format("Failed to update tasks, %s",
                    error.getMessage()));
        }
    }
}
