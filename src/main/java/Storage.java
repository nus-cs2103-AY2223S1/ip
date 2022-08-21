import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    private String filepath;

    public Storage (String filepath) {
        this.filepath = filepath;
    }

    public ArrayList<Task> load() throws JeanException, IOException{
        File f = new File(this.filepath);
        ArrayList<Task> taskList = new ArrayList<>();

        if (!f.createNewFile()) {
            Scanner s = new Scanner(f);
            while (s.hasNext()) {
                String[] info = s.nextLine().split("\\|");
                Task temp = null;

                switch (info[0]) {
                case "T":
                    temp = new Todo(info[2]);
                    break;
                case "D":
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
                    LocalDateTime dateTime = LocalDateTime.parse(info[3], formatter);
                    temp = new Deadline(info[2], dateTime);
                    break;
                case "E":
                    temp = new Event(info[2], info[3]);
                    break;
                }

                if (temp == null) {
                    throw new JeanException("File is corrupted!"
                            + "\nLe fichier est corrompu!");
                }

                temp.setIsDone(info[1].equals("1"));
                taskList.add(temp);
            }
        }

        return taskList;
    }

    public static void saveFile(TaskList taskList) throws IOException {
        FileWriter fw = new FileWriter("data/list.txt");
        for (Task x : taskList.getTaskList()) {
            fw.write(x.getSaveData());
            fw.write("\n");
        }
        fw.close();
    }
}
