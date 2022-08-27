package duke;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    private final String pathFile;

<<<<<<< HEAD
    /**
     * Constructor to create instance of Storage
     *
     * @param pathFile path
     */
    public Storage(String pathFile){
        this.pathFile = pathFile;
    }

    /**
     * Saves the taskList into a file
     * in the path
     *
     * @param taskList List of tasks
     */
    public void save(ArrayList<Task> taskList){
        try{
            FileWriter fw = new FileWriter(pathFile,false);
=======
    public Storage(String pathFile) {
        this.pathFile = pathFile;
    }

    public void save(ArrayList<Task> taskList) {
        try {
            FileWriter fw = new FileWriter(pathFile, false);
>>>>>>> branch-A-CodingStandard
            String allTasks = "";
            for (Task task : taskList) {
                allTasks += task.changeFormat() + "\n";
            }
            fw.write(allTasks);
            fw.close();
        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
    }

<<<<<<< HEAD
    /**
     * Loads the taskList from the file
     * in the path
     *
     * @return Task List
     * @throws IOException If IO error occurs
     */
    public TaskList load() throws IOException{
=======
    public TaskList load() throws IOException {
>>>>>>> branch-A-CodingStandard
        TaskList taskList = new TaskList();
        File file = new File(this.pathFile);
        file.getParentFile().mkdirs();
        if (!file.exists()) {
            file.createNewFile();
        }
        Scanner sc = new Scanner(file);
        while (sc.hasNext()) {
            String cur = sc.nextLine();
            String[] curTask = cur.split(" \\| ");
            Task currentTask = null;
            boolean isDone = curTask[1].equals("1");
            if (curTask[0].equals("T")) {
                currentTask = new ToDo(curTask[2]);
            } else if (curTask[0].equals("D")) {
                currentTask = new Deadline(curTask[2], curTask[3]);
            } else if (curTask[0].equals("E")) {
                currentTask = new Event(curTask[2], curTask[3]);
            } else {
            }
            if (isDone){
                currentTask.markAsDone();
            }
            taskList.load(currentTask);
        }
        return taskList;
    }
}
