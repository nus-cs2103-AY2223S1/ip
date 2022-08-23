import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Ui {
    private Scanner scanner;
    private TaskList taskList;
    private File file;

    public static final String PATH = "./data/";

    public Ui(Scanner scanner) {
        this.scanner = scanner;
        this.taskList = new TaskList();
    }

    public void userInterface(){
        greet();
        setup();
        load();

        while (scanner.hasNextLine()) {
            System.out.println();
            String userInput = scanner.nextLine();

            try {
                Parser parser = new Parser(userInput, taskList);
                parser.handle();
                save();
            } catch(DukeException dukeEx) {
                System.out.println(dukeEx.getMessage());
            }
        }

    }

    private void greet() {
        System.out.println("Hello I'm Duke\n What can I do for you?");
    }

    private void setup() {
        File directory = new File(PATH);
        try {
            if (!directory.exists()) {
                directory.mkdir();
            }

            file = new File(PATH, "duke.txt");
            if (!file.exists()) {
                file.createNewFile();
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }


    private void load() {
        try {
            Scanner fileScanner = new Scanner(this.file);

            while(fileScanner.hasNext()) {
                String[] taskString = fileScanner.nextLine().split(",");
                String taskType = taskString[0];

                switch(taskType) {
                    case "T":
                        Task todo = new ToDo(taskString[2], taskString[1].equals("Y"));
                        this.taskList.add(todo);
                        break;
                    case "E":
                        Task event = new Event(taskString[2], taskString[3], taskString[1].equals("Y"));
                        this.taskList.add(event);
                        break;
                    case "D":
                        Task deadline = new Deadline(taskString[2], taskString[3], taskString[1].equals("Y"));
                        this.taskList.add(deadline);
                        break;
                }
            }
            fileScanner.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    private void save() {
        this.taskList.save(this.file);
    }

}
