package duke;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;


public class Storage {

    /*
     * reads file that stores the existing tasks and parses it
     * @return Task<List> list of tasks read from duke.txt
     */
    public TaskList taskListReader(){
        File taskFile = new File("./data/duke.txt");
        TaskList taskList = new TaskList();
        try (Scanner input = new Scanner(taskFile)) {
            while (input.hasNextLine()) {
                String line = input.nextLine();
                String[] taskAttrs = line.split(" \\| " , -1);
                if (taskAttrs[0].equals("T")) {
                    Task newTask = new Task(taskAttrs[0].charAt(0), taskAttrs[1].equals("X"), taskAttrs[2]);
                    taskList.addTaskFromFile(newTask);
                } else {
                    Task newTask = new Task(taskAttrs[0].charAt(0), taskAttrs[1].equals("X"), taskAttrs[2], taskAttrs[3], taskAttrs[4]);
                    taskList.addTaskFromFile(newTask);
                }

            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return taskList;
    }

    /*
     * Writes the existing list of tasks back into duke.txt
     * @param taskList the existing list of tasks
     */
    public void taskListWriter(TaskList taskList) {
        File taskFile = new File("./data/duke.txt");
        String taskString = getTaskString(taskList);
        try {
            FileWriter fw = new FileWriter(taskFile.getAbsoluteFile());
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(taskString);
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    private String getTaskString(TaskList taskList) {
        String tasks = "";
        for (Task task : taskList.getTaskList()){
            tasks += task.getTaskString() + "\n";
        }
        return tasks;
    }
}
