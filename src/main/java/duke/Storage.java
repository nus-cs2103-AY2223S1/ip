package duke;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import exceptions.EmptyNameException;


/**
 * Stores the task in a file under /data/duke.txt
 * Contains the methods to load the file and modify the file when the tasks are modified in the app
 */
public class Storage {
    private final String path;
    private final TaskList taskList;

    /**
     * Constructor for the Storage class
     *
     * @param path     path of the file
     * @param taskList current Tasklist state
     */
    public Storage(String path, TaskList taskList) {
        this.path = path;
        this.taskList = taskList;
    }

    /**
     * Adds text to the file
     *
     * @param filePath  path of the file
     * @param textToAdd text to be added to the file
     * @throws IOException If file is not found
     */
    private static void writeToFile(String filePath, String textToAdd) throws IOException {
        FileWriter fw = new FileWriter(filePath, true);
        fw.write(textToAdd + "\n");
        fw.close();
    }

    /**
     * Loads the file into the app so that a saved state can be retrieved
     * Creates file and folder if it doesn't exist
     *
     * @throws IOException Throws exception when file is missing
     */
    public void loadFile() throws IOException {
        File file = new File(this.path);
        File parentFile = file.getParentFile();

        if (parentFile != null && !parentFile.exists() && !parentFile.mkdirs()) {
            throw new IllegalStateException("Error creating directory" + parentFile);
        }
        file.createNewFile();

        Scanner scanner = new Scanner(file);
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            taskList.readTaskFromLoader(line);
        }

        scanner.close();

    }

    /**
     * Takes in command from User and handle the logic on what to save onto the file
     *
     * @param command Command received from User
     */
    public void addLineToFile(String command) {
        try {
            String[] combiStr;
            String[] splitString = command.split(" ", 2);
            if (splitString.length < 2) {
                throw new EmptyNameException();
            }
            String type = splitString[0];
            if (type.equals(TaskTypeEnum.todo.toString())) {
                String name = splitString[1];
                combiStr = new String[]{type, "0", name};
            } else {
                String[] innerSplitString = splitString[1].split("/", 2);
                String name = innerSplitString[0];
                String info = innerSplitString[1];
                if (type.equals(TaskTypeEnum.deadline.toString()) || type.equals("D")) {
                    combiStr = new String[]{type, "0", name, info};
                } else {
                    combiStr = new String[]{type, "0", name, info};
                }
            }
            StringBuilder ret = new StringBuilder();
            for (int i = 0; i < combiStr.length; i++) {
                if (i != 0) {
                    ret.append("|").append(combiStr[i].trim());
                } else {
                    ret.append(combiStr[i].trim());
                }
            }
            writeToFile(this.path, ret.toString());

        } catch (EmptyNameException | IOException e) {
            System.out.println(e.getMessage());
        }

    }


    /**
     * Changes the state of the task in the saved file
     *
     * @param index     line number of that task in the file
     * @param isSetDone decides whether to set the task to be done or undone
     */
    public void toggleDone(int index, boolean isSetDone) {
        File file = new File(this.path);
        try {
            Scanner scanner = new Scanner(file);
            StringBuilder builder = new StringBuilder();
            int currentIndex = 0;
            while (scanner.hasNextLine()) {
                if (currentIndex == index) {
                    String oldString = scanner.nextLine();

                    String newString = isSetDone ? oldString.replaceFirst("0", "1") : oldString.replaceFirst("1", "0");
                    builder.append(newString).append(System.lineSeparator());
                } else {
                    builder.append(scanner.nextLine()).append(System.lineSeparator());
                }
                currentIndex++;
            }
            String content = builder.toString();

            FileWriter writer = new FileWriter(this.path);
            writer.append(content);
            writer.flush();


        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * Delete line from the saved file based on line number
     *
     * @param index line number of the task
     */
    public void deleteLine(int index) {
        File file = new File(this.path);
        try {
            Scanner scanner = new Scanner(file);
            StringBuilder builder = new StringBuilder();
            int currentIndex = 0;
            while (scanner.hasNextLine()) {
                if (currentIndex != index) {
                    builder.append(scanner.nextLine()).append(System.lineSeparator());
                } else {
                    scanner.nextLine();
                }
                currentIndex++;
            }
            String content = builder.toString();

            FileWriter writer = new FileWriter(this.path);
            writer.append(content);
            writer.flush();


        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
