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
    public void readAndSaveFile(File file, TaskList inputs) throws FileNotFoundException {
        Scanner scanner = new Scanner(file);
        //System.out.println("parsing activated");

        while (scanner.hasNext()) {
            //this is for formating
            String[] task = scanner.nextLine().split("[\\[||\\]]");
            String taskContentHelper = task[4].split(" ", 2)[1];//the task and time without the brackets
            String taskContent = taskContentHelper.split("\\(")[0].trim();//just the task
            //System.out.println("task main content" + taskContentHelper);
            //System.out.println("task1 " + taskContent);
            String taskMark = task[3];
            String taskType = task[1];
            Task newTask;
            // System.out.println("the mark:" + taskMark);

            if (taskType.equals("T")) {
                //System.out.println("todo parsed");
                newTask = new ToDo(taskContent);
                if (taskMark.equals("X")) {
                    newTask.setDone();
                }
                inputs.addTask(newTask);
            } else if (taskType.equals("D")) {
                //System.out.println("event parsed");
                String[] timingContent = taskContentHelper.split("[\\(||\\)]");//this is to split up and obtain time segment
                System.out.println("timingContent: " + timingContent[1]);
                //small problem where the user may not specify porperly
                String time[] = timingContent[1].split(" ");//array of the elements in the time
                String dateHelper = "";// the specific date
                for (int i = 1; i < time.length; i++) {
                    dateHelper += time[i] + " ";
                }
                String date = dateHelper.trim();
                //System.out.println(date + "no");
                newTask = new Deadline(taskContent, date);
                if (taskMark.equals("X")) {
                    newTask.setDone();
                }
                inputs.addTask(newTask);
            } else {
                String[] timingContent = taskContentHelper.split("[\\(||\\)]");//this is to split up and obtain time segment
                //System.out.println("timingContent: " + timingContent[1]);
                //small problem where the user may not specify porperly
                String time[] = timingContent[1].split(" ");//array of the elements in the time
                String dateHelper = "";// the specific date
                for (int i = 1; i < time.length; i++) {
                    dateHelper += time[i] + " ";
                }
                String date = dateHelper.trim();
                //System.out.println(date + "no");
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
        FileWriter fileWriter = new FileWriter(file);
        for (int i = 0; i < taskList.getSize(); i++) {
            fileWriter.write(taskList.getTask(i).toString() + "\n");
        }
        fileWriter.close();
    }
}
