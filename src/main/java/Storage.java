import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

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

                //Variables to create new Task
                String taskTypeString = arrOfCommands[0].trim();
                String isDoneString = arrOfCommands[1].trim();
                String taskName = arrOfCommands[2].trim();
                String moreInfo = ""; //by for Deadline, at for Event

                if (taskTypeString.equals("E") || taskTypeString.equals("D")) {
                    moreInfo = moreInfo + arrOfCommands[3].trim();
                }

                //Convert String to each variable type
                Task.Type taskType = toTaskType(taskTypeString);
                boolean isDone = toIsDone(isDoneString);

                // Update information accordingly
                Task.makeTask(taskName, moreInfo, taskType, false);
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
    public static Task.Type toTaskType(String s) {
        try {
            if (s.contains("T")) {
                return Task.Type.TODO;
            } else if (s.contains("D")) {
                return Task.Type.DEADLINE;
            } else if (s.contains("E")) {
                return Task.Type.EVENT;
            } else {
                throw new SallyException.SallyInvalidInputException();
            }
        } catch (SallyException e) {
            System.out.println(e);
        }
        return Task.Type.TODO;
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

    public void savesFile() throws SallyException {
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

                int indexDone = task.isDone ? 1 : 0;
                description = task.description;
                moreInfo = task.getMoreInfo();

                System.out.println("taskType = " + task.taskType);
                switch (task.taskType) {
                    case TODO:
                        typeSymbol = "T";
                        newFile = newFile + (typeSymbol + separator + indexDone + separator + description + "\n");
                        break;
                    case DEADLINE:
                        typeSymbol = "D";
                        newFile = newFile + (typeSymbol + separator + indexDone + separator + description + separator + moreInfo + "\n");
                        break;
                    case EVENT:
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
