package sally.storage;

import sally.exception.SallyException;
import sally.task.*;
import sally.storage.Storage;
import sally.ui.Ui;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Scanner;

/**
 * Storage class to store tasks into tasklist, reads tasks from files, and saves tasks to files.
 *
 * @author liviamil
 */

public class Storage {
    protected static final String FILEPATH = System.getProperty("user.home") + "/Sally/Sally.txt";
    protected File file;
    protected TaskList tasks;

    public Storage(String filePath) throws SallyException {
        try {
            File parentDir = new File(FILEPATH).getParentFile();

            if (!parentDir.exists()) {
                parentDir.mkdir();
            }

            file = new File(FILEPATH);

            if (!file.exists()) {
                file.createNewFile();
            }

            Paths.get(FILEPATH);
        } catch (IOException e) {
            throw new SallyException("Oops! There's some trouble in creating your new file. Please try again.");
        }
    }

    public void readsFile(TaskList tasks) throws SallyException {
        this.tasks = tasks;
        try {
            Scanner sc = new Scanner(file);
            while (sc.hasNext()) {
                String commands = sc.nextLine(); //scans the next input of file for command
                String[] arrOfCommands = commands.split("\\|");

                //Variables to create new sally.task.Task
                String taskTypeString = arrOfCommands[0].trim();
                String isDoneString = arrOfCommands[1].trim();
                String taskName = arrOfCommands[2].trim();
                String moreInfo = ""; //by for sally.task.Deadline, at for sally.task.Event

                if (taskTypeString.equals("E") || taskTypeString.equals("D")) {
                    moreInfo = moreInfo + arrOfCommands[3].trim();
                }

                if (taskTypeString.equals("T")) {
                    Task todo = new ToDo(taskName, false);
                    tasks.addTask(todo);
                } else if (taskTypeString.equals("D")) {
                    Task deadline = new Deadline(taskName, moreInfo, false);
                    tasks.addTask(deadline);
                } else if (taskTypeString.equals("E")) {
                    Task event = new Event(taskName, moreInfo, false);
                    tasks.addTask(event);
                }

                boolean isDone = toIsDone(isDoneString);

                int maxLength = tasks.getNumOfTasks();
                Task task = tasks.getTask(maxLength - 1);

                if (isDone) {
                    task.markAsDone();
                } else {
                    task.markAsUndone();
                }
            }
        } catch (IOException e) {
            throw new SallyException("File Not Found. Check your file path input!");
        }
    }

    // Complementary method for readsFile
    public static boolean toIsDone(String s) {
        if (s.contains("1")) {
            return true;
        } else if (s.contains("0")) {
            return false;
        }
        return false;
    }

    public void savesFile(TaskList tasks) throws SallyException {
        this.tasks = tasks;
//        System.out.println("enters savesFile");
        try {
            FileWriter writer = new FileWriter(file);

            String typeSymbol;
            String description;
            String moreInfo;
            String separator = " | ";
            String newFile = "";

            int numOfTasks = tasks.getNumOfTasks();

            for (int i = 0; i < numOfTasks; i++) {
                Task task = tasks.getTask(i);

                int indexDone = task.getDoneStatus() ? 1 : 0;
                description = task.getDescription();
                moreInfo = task.getMoreInfo();

                System.out.println("taskType = " + task.getTaskType());

                String type = task.getTaskType();

                switch (type) {
                    case "TODO":
                        typeSymbol = "T";
                        newFile = newFile + (typeSymbol + separator + indexDone + separator + description + "\n");
                        break;
                    case "DEADLINE":
                        typeSymbol = "D";
                        newFile = newFile + (typeSymbol + separator + indexDone + separator + description + separator + moreInfo + "\n");
                        break;
                    case "EVENT":
                        typeSymbol = "E";
                        newFile = newFile + (typeSymbol + separator + indexDone + separator + description + separator + moreInfo + "\n");
                        break;
                }

            }
            writer.write((newFile));
            writer.close();

        } catch (IOException e) {
            throw new SallyException("Sorry, there was an error in saving the file.");
        }
    }

}
