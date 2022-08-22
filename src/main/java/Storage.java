import java.io.File;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.FileWriter;
public class Storage {
    private File file;
    private String filepath;
    public Storage(String filepath) {
        this.filepath = filepath;
        File newFile = new File(filepath);
        if (!newFile.exists()) {
            try {
                boolean result = newFile.createNewFile();
                if (result) {
                    System.out.println("New file created since no duke.txt is found");
                }

            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
        this.file = newFile;
    }

    public ArrayList<Task> load() {
         ArrayList<Task> taskList = new ArrayList<>();
        try {
            Scanner sc = new Scanner(this.file);
            while (sc.hasNextLine()) {
                String message = sc.nextLine();
                String[] messageArr = message.split("#");
                if (messageArr[0].equals("T")) {
                    ToDo todo = new ToDo(messageArr[2]);
                    if (messageArr[1].equals("1")) {
                        todo.setCompleted();
                    }
                    taskList.add(todo);
                }

                if (messageArr[0].equals("D")) {
                    Deadline deadline = new Deadline(messageArr[2],messageArr[3],messageArr[4]);
                    if (messageArr[1].equals("1")) {
                        deadline.setCompleted();
                    }
                    taskList.add(deadline);
                }

                if (messageArr[0].equals("E")) {
                    Event event = new Event(messageArr[2],messageArr[3]);
                    if (messageArr[1].equals("1")) {
                        event.setCompleted();
                    }
                    taskList.add(event);
                }
            }

        } catch (FileNotFoundException e) {
            System.out.println("File not found!");
        }
        return taskList;

    }

    public void store(ArrayList<Task> taskList) {
        try {
            FileWriter fw = new FileWriter(filepath);
            String message = "";
            for (Task item : taskList) {
                message += item.parse();
                message += "\n";
            }
            fw.write(message);
            fw.close();
        }
        catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
