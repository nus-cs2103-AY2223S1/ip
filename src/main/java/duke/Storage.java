package duke;



import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;


public class Storage {
    private String filePath;

    Storage (String filePath) {
        this.filePath = filePath;
    }

    public void readAndSaveFile(File file, TaskList inputs) throws FileNotFoundException {
        Scanner scanner = new Scanner(file);
        //System.out.println("parsing activated");
        //fix the whitespacing problem or it is gonna be even more painful later!

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

    public void updateFile(String file, TaskList taskList) throws IOException {
        FileWriter fileWriter = new FileWriter(file);
        for (int i = 0; i < taskList.getSize(); i++) {
            fileWriter.write(taskList.getTask(i).toString() + "\n");
        }
        fileWriter.close();
    }
}
