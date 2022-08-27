package duke;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    private final String fileName;

    public Storage(String fileName) {
        this.fileName = fileName;
    }

    /**
     * Overwrites the contents of fileName (if exists) with the current tasks.
     *
     * @param taskList The TaskList instance which contains the task list.
     */
    public void writeAllTasksToFile(TaskList taskList) {
        File file = new File(this.fileName);
        try {
            FileWriter fw = new FileWriter(file, false);
            for (int i = 0; i < taskList.count(); i++) {
                Task task = taskList.get(i);
                String encodedTask = task.encode();
                fw.write(encodedTask);
            }
            fw.close();
        } catch (IOException e) {
            System.out.println(String.format("\tError writing to %s in Storage.addAllTasksToFile()",
                    fileName));
        }
    }

    /**
     * This function is only called when we are sure that data.txt exists (has already been created).
     * It inserts all the tasks that were stored in data.txt, decodes them into Task objects and stores them
     * in the Task.tasks array
     */
    public void loadAllTasksFromFile(TaskList taskList) {
        // Get all tasks from file and store in ArrayList
        File file = new File(fileName);
        try (Scanner sc = new Scanner(file)) {
            ArrayList<Task> tasksInFile = new ArrayList<>();
            while (sc.hasNextLine()) {
                String encodedTask = sc.nextLine();
                Task task = decode(encodedTask);
                if (task == null) {
                    continue;
                }
                tasksInFile.add(task);
            }
            taskList.addTasks(tasksInFile);

        } catch (FileNotFoundException e) {
            System.out.println(String.format("\tFile %s not found!!!", fileName));
        } catch (DukeException e) {
            System.out.println(String.format("\t%s", e.getMessage()));
        }
    }

    /**
     * Appends the encoded representation of the task into the file.
     *
     * @param task The task to be stored in the file
     */
    public void addTaskToFile(Task task) {
        File file = new File(fileName);
        FileWriter fw = null;
        try {
            fw = new FileWriter(file, true);
            String encodedTask = task.encode();
            fw.write(encodedTask);
            fw.write("\n");
            fw.close();
        } catch (IOException e) {
            System.out.println(String.format("\tError writing to %s in Storage.addAllTasksToFile()",
                    fileName));
        }
    }

    /**
     * Takes a given encodedTask and returns the corresponding Task object.
     *
     * @param encodedTask the text-representation of a task
     * @return
     */
    public static Task decode(String encodedTask) throws DukeException {
        // Skip Empty line
        if (encodedTask.trim().equals("")) {
            return null;
        }
        encodedTask = encodedTask.trim();
        String[] encodedDetails = encodedTask.split("\\|");
        String taskType = encodedDetails[0];

        switch (taskType) {
        case Todo.ENCODED_TASK_TYPE:
            if (encodedDetails.length == 2) {
                throw new DukeException("Error reading Todo from file in Task.decode");
            }
            if (encodedDetails[2].trim().equals("")) {
                throw new DukeException("Oops! You forgot to specify a description for your Todo");
            }
            Task todo = new Todo(encodedDetails[2]);
            if (encodedDetails[1].trim().equals("1")) {
                todo.markAsDone();
            }
            return todo;

        case Deadline.ENCODED_TASK_TYPE:

            if (encodedDetails.length == 3) {
                throw new DukeException("Error reading Deadline from file in Task.decode");
            }
            String deadlineDescription = encodedDetails[2].trim();
            String deadlineDeadline = encodedDetails[3].trim();
            if (deadlineDescription.length() == 0) {
                throw new DukeException("Oops! You forgot to indicate the description " +
                        "for your deadline");
            }
            if (deadlineDeadline.length() == 0) {
                throw new DukeException("Oops! You forgot to indicate the deadline");
            }
            LocalDateTime deadilneDateTime = LocalDateTime.parse(deadlineDeadline, Duke.DATE_TIME_FORMATTER);
            Deadline deadlineTask = new Deadline(deadlineDescription, deadilneDateTime);
            if (encodedDetails[1].trim().equals("1")) {
                deadlineTask.markAsDone();
            }
            return deadlineTask;

        case Event.ENCODED_TASK_TYPE:
            if (encodedDetails.length == 3) {
                throw new DukeException("Error reading Event from file in Task.decode");
            }
            String eventDescription = encodedDetails[2].trim();
            String eventTiming = encodedDetails[3].trim();
            if (eventDescription.length() == 0) {
                throw new DukeException("Oops! You forgot to indicate the description " +
                        "for your event");
            }
            if (eventTiming.length() == 0) {
                throw new DukeException("Oops! You forgot to indicate the event");
            }
            LocalDateTime eventDateTime = LocalDateTime.parse(eventTiming, Duke.DATE_TIME_FORMATTER);
            Event eventTask = new Event(eventDescription, eventDateTime);
            if (encodedDetails[1].trim().equals("1")) {
                eventTask.markAsDone();
            }
            return eventTask;

        default:
            return null;
        }

    }
}
