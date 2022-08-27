import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    private final String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
        try {
            File file = new File(filePath);
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String read() throws IOException {
        return Files.readString(Path.of(this.filePath));
    }

    public TaskList load() throws DukeException {
        TaskList taskList = new TaskList();
        try {
            String fileContent = this.read();
            String[] lines = fileContent.split(System.lineSeparator());
            for (String line : lines) {
                Command command = Parser.parseFileLine(line);
                command.execute(taskList, this);
            }

//                String[] words = line.split("\\s\\|\\s");
//                int size = words.length;
//                String typeOfTask = words[0];
//                boolean isDone = words[1] == "X" ? true : false;
//                String description = words[2];
//                String date = " ";
//                if (size == 4) {
//                    date = words[3];
//                }
//                switch (typeOfTask) {
//                    case ("T"):
//                        Command addTodo =  new AddCommand(description, isDone);
//                        addTodo.execute(taskList, this);
//                        break;
//                    case ("D"):
//                        Command addDeadline =  new AddDeadlineCommand(description, isDone, date);
//                        addDeadline.execute(taskList, this);
//                        break;
//                    case ("E"):
//                        Command addEvent =  new AddEventCommand(description, isDone, date);
//                        addEvent.execute(taskList, this);
//                }

        } catch (FileNotFoundException f) {
            throw new DukeException("File not found");
        } catch (IOException i) {
            throw new DukeException("IO exception");
        }
        return taskList;
    }


    public void saveData(TaskList taskList) throws DukeException {
        try {
            File f = new File(this.filePath);
            if (!f.createNewFile()) f.delete();
            f.createNewFile();
            FileWriter fw = new FileWriter(this.filePath, true);
            for (Task t : taskList.getList()) {
                fw.write(t.toString() + System.lineSeparator());
            }
            fw.close();
        } catch (IOException i) {
            throw new DukeException("OOPS!! data/duke.txt file does not exist");
        }
    }


}

//    public void load() throws DukeException {
//        try {
//            File file = new File(filePath);
//            FileReader fr = new FileReader(file);   //reads the file
//            BufferedReader br = new BufferedReader(fr);  //creates a buffering character input stream
//            StringBuffer sb = new StringBuffer();    //constructs a string buffer with no characters
//            String line;
//            while ((line = br.readLine()) != null) {
//                sb.append(line);      //appends line to string buffer
//                sb.append("\n");     //line feed
//            }
//            fr.close();    //closes the stream and release the resources
//
//            String fileContent = sb.toString();
//            Scanner sc = new Scanner(fileContent);
//            while (sc.hasNext()) {
//                String rawTask = sc.nextLine();
//                String[] taskArray = rawTask.split("\\s\\|\\s");
//                int sizeOfArray = taskArray.length;
//                String typeOfTask = taskArray[0].trim();
//                String description = taskArray[2].trim();
//                String date = " ";
//                if (sizeOfArray >= 4) {
//                    date = taskArray[3].trim();
//                }
//
//                switch (typeOfTask) {
//                    case ("T"):
//                        this.taskList.add(new ToDo(description));
//                        break;
//                    case ("D"):
//                        this.taskList.add(new Deadline(description, date));
//                        break;
//                    case ("E"):
//
//                        this.taskList.add(new Event(description, date));
//                }
//            }
//        } catch (FileNotFoundException f) {
//            throw new DukeException("File not found");
//        } catch (IOException i) {
//            throw new DukeException("IO exception");
//        }
//    }


//    public void load() throws DukeException{
//        File f = new File(this.filePath);
//        try {
//            if (f.exists()) {
//                Scanner sc = new Scanner(f);
//                while (sc.hasNext()) {
//                    String rawTask = sc.nextLine();
//                    String rawDesc = rawTask.substring(8);
//                    String description = "";
//
//                    Scanner scanner = new Scanner(rawDesc);
//                    while (!scanner.hasNext("/by")) {
//                        description += scanner.next();
//                    }
//                    scanner.next();
//                    String date = scanner.nextLine();
//
//                    char typeOfTask = rawTask.charAt(1);
//                    switch (typeOfTask) {
//                        case ('T'):
//                            this.taskList.add(new ToDo(description));
//                            break;
//                        case ('D'):
//                            this.taskList.add(new Deadline(description, date));
//                            break;
//                        case ('E'):
//
//                            this.taskList.add(new Event(description, date));
//                    }
//                }
//            } else {
//                f.createNewFile();
//            }
//        } catch (FileNotFoundException e) {
//            throw new DukeException("File not found!!");
//        } catch (IOException i) {
//            throw new DukeException("IO Exception!!");
//        }
//    }

