import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.io.File;

public class Storage {
    private final String pathFile;

    public Storage(String pathFile){
        this.pathFile = pathFile;
    }

    public void save(ArrayList<Task> taskList){
        try{
            FileWriter fw = new FileWriter(pathFile,false);
            for (Task task : taskList){
                fw.write(task.changeFormat() + "\n");
            }
            fw.close();
        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
    }

    public ArrayList<Task> load() throws IOException{
        ArrayList<Task> taskList = new ArrayList<>();
        try {
            File file = new File(this.pathFile);
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
                taskList.add(currentTask);
            }

        } catch (FileNotFoundException e) {
            if (new File("data").mkdir()) {
                System.out.println("data folder does not exist, create now");
            } else if (new File("data/duke.txt").createNewFile()){
                System.out.println("duke.txt file not exist, create now");
            }
        }
        return taskList;
    }




}
