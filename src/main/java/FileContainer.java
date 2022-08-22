import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;

import java.io.IOException;

import java.util.List;

import java.util.Scanner;

public class FileContainer {
    File file;

    public boolean isFileExist(){
        if (file == null){
            return false;
        }else {
            return true;
        }
    }
    //create a duke.txt file in [project root]/data;
    public void createAFile(){
        try {
            File f = new File("./data/");
            this.file = new File("./data/duke.txt");
            if (!this.file.exists()) {
                f.mkdir();
                this.file.createNewFile();
            }
        }catch(IOException e){
            System.out.println("Sorry, something went wrong when creating the file."
                    +e.getMessage());
        }
    }

    //Save a change to the file
    public void updateFile(List<Task> t) {
        try {
            FileWriter fw = new FileWriter(this.file);
            for (int i = 0; i < t.size(); i++) {
                if (t.get(i) != null) {
                    if (t.get(i).getStatusIcon().equals("X")) {
                        fw.write(t.get(i).getStatusIcon() +" "
                                + t.get(i).getDescription() + "\n");
                    }else {
                        fw.write("Wait " + t.get(i).getDescription() + "\n");
                    }
                }
            }
            fw.close();
        }catch (IOException e){
            System.out.println("Sorry, something went wrong when updating the file."
                    +e.getMessage());
        }
    }
    //Load tasks from file and return a TaskList
    public TaskList loadTasks() {
        try {
            Scanner s = new Scanner(this.file); // create a Scanner using the File as the source
            TaskList taskList = new TaskList();
            while (s.hasNextLine()) {
                String s0 =s.nextLine();
                String s1 = s0.split(" ",2)[1];
                String s2 = s0.split(" ", 2)[0];
                Task t = Task.createATask(s1);
                if (t!= null) {
                    if (s2.equals("X")){
                        t.taskDone();
                        taskList.getTaskList().add(t);
                    }else {
                        taskList.getTaskList().add(t);
                    }
                }
            }
            s.close();
            return taskList;
        }catch (FileNotFoundException e){
            System.out.println("Sorry, but something went wrong when loading task "
                    +e.getMessage());
        }catch (IndexOutOfBoundsException e){
            System.out.println("Sorry, but something went wrong when loading task "
                    +e.getMessage());
        } catch (DukeException d) {
            System.out.println("Sorry, but something went wrong when loading task "
                    + d.getMessage());
        }
        return null;
    }
}
