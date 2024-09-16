package duke;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 * A Storage class to deal with loading and saving tasks in the file.
 */
public class Storage {
    private String filePath;

    /**
     * The Storage constructor.
     * @param filePath to store the tasks in.
     */
    Storage (String filePath) {
        this.filePath = filePath;
    }

    /**
     * A method to scan a file and save its contents into a TaskList.
     *
     * @param file to be read and have its contents loaded.
     * @param inputs the TaskList to save the files of the contents into.
     * @throws FileNotFoundException
     */
    public void readAndSaveFile(File file, TaskList inputs) throws FileNotFoundException, DukeException {

        Scanner scanner = new Scanner(file);

        while (scanner.hasNext()) {
            //this is for splitting the read task from the file into different components.
            String[] task = scanner.nextLine().split("[\\[||\\]]");
            String taskContentHelper = task[4].split(" ", 2)[1];//the task and time without the brackets.
            String taskContent = taskContentHelper.split("\\(")[0].trim();//just the task.
            String taskMark = task[3];//the X mark of the task.
            String taskType = task[1];
            Task newTask;

            if (taskType.equals("T")) {
                newTask = new ToDo(taskContent);
                if (taskMark.equals("X")) {
                    newTask.setDone();
                }
                inputs.addTask(newTask);
            } else if (taskType.equals("D")) {
                String[] timingContent = taskContentHelper.split("[\\(||\\)]");//this is to split up and obtain time segment.
                String time[] = timingContent[1].split(" ");//array of the elements in the time segment.
                String dateHelper = "";// the specific date.
                for (int i = 1; i < time.length; i++) {
                    dateHelper += time[i] + " ";
                }
                String date = dateHelper.trim();
                newTask = new Deadline(taskContent, date);
                if (taskMark.equals("X")) {
                    newTask.setDone();
                }
                inputs.addTask(newTask);
            } else {
                String[] timingContent = taskContentHelper.split("[\\(||\\)]");//this is to split up and obtain time segment.
                //System.out.println("timingContent: " + timingContent[1]);
                String time[] = timingContent[1].split(" ");//array of the elements in the time segment.
                String dateHelper = "";// the specific date.
                for (int i = 1; i < time.length; i++) {
                    dateHelper += time[i] + " ";
                }
                String date = dateHelper.trim();
                newTask = new Event(taskContent, date);
                if (taskMark.equals("X")) {
                    newTask.setDone();
                }
                inputs.addTask(newTask);
            }
        }
    }

    /**
     * A method to update and write the contents of a TaskList into a file.
     *
     * @param file to be written on.
     * @param taskList to save its contents into the file.
     * @throws IOException
     */
    public void updateFile(String file, TaskList taskList) throws IOException {
        FileWriter fileWriter = new FileWriter(file, false);
        for (int i = 0; i < taskList.getSize(); i++) {
            fileWriter.write(taskList.getTask(i).toString() + "\n");
        }
        fileWriter.close();
    }

    public void inputSavedFile(TaskList taskList) {
        try{
            File dukeFile = new File("data/duke.txt");
            dukeFile.getParentFile().mkdirs();//create the directory
            if(dukeFile.createNewFile()){
                System.out.println("new file created!");
            } else {
                readAndSaveFile(dukeFile, taskList);
                System.out.println("updated file");
            }
        }catch (IOException e) {
            System.out.println("Error in creating file");
        } catch (DukeException e) {
            e.printStackTrace();
        }
    }
}
