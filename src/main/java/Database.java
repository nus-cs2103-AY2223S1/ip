import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Database {
    
    private File file;
    public static char LINE_SEPARATOR = '|';    
    public Database() {
        setup();
    }
    
    public void setup() {
        String curDirectory = System.getProperty("user.dir");
        String directoryName = "data";
        String fileName = "duke.txt";

        File directory = new File(curDirectory + "/" + directoryName);
        if (!directory.exists()) {
            directory.mkdir();
        }

        File file = new File(directoryName + "/" + fileName);
        try {
            if (!file.exists()) {
                file.createNewFile();
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        
        this.file = file;
    }
    
    public List<Task> load() {
        List<Task> newList = new ArrayList<>();
        try {
            Scanner s = new Scanner(file);
            String line;
            while (s.hasNext()) {
                line = s.nextLine();
                String[] strArr = line.split("\\|");
                Task task;
                String desc = strArr[2];
                switch(strArr[0]) {
                    case "TODO":
                        task = new Todo(desc);
                        break;
                    case "EVENT":
                        task = new Event(desc, strArr[3]);
                        break;
                    case "DEADLINE":
                        task = new Deadline(desc, LocalDate.parse(strArr[3]));
                        break;
                    default:
                        throw new IOException();
                }
                if (strArr[1].equals("true")) {
                    Task.markAsDone(task);    
                }
                newList.add(task);
            }
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            System.out.println("Error: Could not load file into array");
        } catch (DateTimeParseException e) {
            System.out.println("Deadline format is incorrect");
        }
        return newList;
    }
    
    public void store(List<Task> taskList) {

        StringBuilder buffer = new StringBuilder();
    
        
        for (Task task : taskList) {
            StringBuilder row = new StringBuilder();
            row.append(task.type);
            row.append(LINE_SEPARATOR);
            row.append(task.isDone);
            row.append(LINE_SEPARATOR);
            row.append(task.description);
            
            switch (task.type) {
                case EVENT:
                    row.append(LINE_SEPARATOR);
                    row.append(((Event) task).at);
                case DEADLINE:
                    row.append(LINE_SEPARATOR);
                    row.append(((Deadline) task).by);
                default:
                    break;
            }
            buffer.append(row).append("\n");
        }
        
        try {
            PrintWriter pw = new PrintWriter(file);
            pw.print(buffer.toString());
            pw.close();
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
        
    }
}
