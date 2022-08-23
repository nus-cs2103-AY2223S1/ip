import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {
    private Ui bot; //bot is in charge of printing statements
    private Storage storage; //storage is in charge of reading tasks from the file and writing to the file.
    private TaskList tasklist;  //tasklist will perform actions on the tasklist
    private Parser parser; //parser in charge of reading inputs and calling the methods to the tasklist
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
        if(f.createNewFile()){
            bot.fileCreate(true);
        } else {
            bot.fileCreate(false);
        }
        Scanner filescanner = new Scanner(f);
        List<Task> newTasks = new ArrayList<>();
        //This part, im loading all the strings in the pre existing file
        List<String> oldTasks = storage.readTasks(filescanner);
        tasklist = new TaskList(oldTasks);
        List<String> newTasks2 = new ArrayList<>();
        parser = new Parser(tasklist, bot, storage);
        String str;
        parser.readInput();

   }

    public static void main(String[] args)  {
        try {
            new Duke().DukeTask();
        }catch(DukeException e) {
            System.out.println(e.getMessage());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("Path name cannot be found");
//            e.printStackTrace();
        }
    }
}
