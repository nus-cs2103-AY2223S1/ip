import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    private String filePath;
    private String folderPath;
    private File dukeFile;

    public Storage(String filePath, String fileName) {
        this.filePath = filePath;
        this.folderPath = filePath.replace("/" + fileName, "");
        this.dukeFile = new File(filePath);
    }

    public TaskList loadFile() {
        ArrayList<Task> temp = new ArrayList<>();
        Scanner sc;

        if (dukeFile.exists()) {
            try {
                sc = new Scanner(dukeFile);

                while (sc.hasNextLine()) {
                    String[] st = sc.nextLine().split(" \\| ");
                    boolean isTaskDone;

                    if (st[1].equals("1")) {
                        isTaskDone = true;
                    } else {
                        isTaskDone = false;
                    }

                    switch (st[0]) {
                    case "T":
                        temp.add(new Todo(st[2], isTaskDone));
                        break;
                    case "D":
                        temp.add(new Deadline(st[2], isTaskDone, st[3]));
                        break;
                    case "E":
                        temp.add(new Event(st[2], isTaskDone, st[3]));
                        break;
                    default:
                        break;

                    }
                }
                sc.close();
            } catch (FileNotFoundException e) {
                System.out.println(e.getMessage());
            }
        } else {
            try {
                File dataFolder = new File(folderPath);
                dataFolder.mkdirs();
                dukeFile.createNewFile();
            } catch (SecurityException e) {
                System.out.println(e.getMessage());
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
        return new TaskList(temp);
    }

    public void updateFile(TaskList tasks) {
        try {
            FileWriter fw = new FileWriter(filePath);
            tasks.writeToFile(fw);
            fw.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
