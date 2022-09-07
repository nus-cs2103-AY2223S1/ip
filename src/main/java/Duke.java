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
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Duke{
    private Ui bot = new Ui();
    private Storage storage;
    private TaskList tasklist;
    private Parser parser;
    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;
    private Image user = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image duke = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));
    /*
     * A method that takes in a string input and performs actions based on the string input
     * */
    public String DukeTask(String input) throws DukeException, IOException {
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
        String stringReturned =  parser.readInput2(input);

        return stringReturned;

   }

    public static void main(String[] args)  {
        try {
            new Duke().DukeTask("hello world");
        } catch (DukeException e) {
            System.out.println(e.getMessage());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("Path name cannot be found");
            //e.printStackTrace();
        }
    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    public String getResponse(String input) {
        String response = "";
        try {
            response = DukeTask(input);
        } catch (DukeException e) {
            response = e.getMessage();
        } catch (IOException e) {
            response = e.getMessage();
        }
        return response;
        //return "Duke heard: " + input;
    }
}

