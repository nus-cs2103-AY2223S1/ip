import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import Duke.Ui;
import Duke.TaskList;
import Duke.Storage;
import Duke.Parser;
import Duke.DukeException;
import Duke.Task;

public class Duke {
    private Ui bot = new Ui();
    private Storage storage;
    private TaskList tasklist;
    private Parser parser;
    /*
     * A method that takes in a string input and performs actions based on the string input
     * */
    public void DukeTask() throws DukeException, IOException {
        bot = new Ui();
        bot.welcome();
        storage = new Storage();
        Scanner scanner = new Scanner(System.in);
        String pathName = "data/Duke2.txt";
        File f = new File(pathName);
        if (f.createNewFile()) {
            bot.fileCreate(true);
        } else {
            bot.fileCreate(false);
        }
        Scanner filescanner = new Scanner(f);
        List<Task> newTasks = new ArrayList<>();
        //This part, im loading all the strings in the pre existing file
        List<Task> oldTasks = storage.readTasks(filescanner);
        tasklist = new TaskList(oldTasks);
        List<Task> newTasks2 = new ArrayList<>();
        parser = new Parser(tasklist, bot, storage);
        parser.readInput();

   }

    public static void main(String[] args)  {
        try {
            new Duke().DukeTask();
        } catch (DukeException e) {
            System.out.println(e.getMessage());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("Path name cannot be found");
            //e.printStackTrace();
        }
    }
}
