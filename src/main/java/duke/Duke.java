package duke;

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

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

//testing
public class Duke  {
    private static final List<Task> ListofMessages  = new ArrayList<>();

    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;

    public Duke () throws IOException {
        Scanner input = new Scanner(System.in);
        File log = new File("log.txt");
        File help = new File("help.txt");
        help.createNewFile();

        if(log.exists()==false){
            //System.out.println("We had to make a new file.");
            log.createNewFile();
        }

        //Reading in data from the file
        Scanner readfile = new Scanner(log);

        Storage.readfilez(readfile,ListofMessages); //Reads all the input

    }
    /**
     * Runs Parser and the entire code
     * @param args
     * @throws DukeException
     * @throws IOException
     * @throws ParseException
     */
    public static void main(String[] args) throws DukeException, IOException, ParseException {
    }

    public String getResponse(String input) throws DukeException, IOException {
       // Parser.HandleUserInput(input,ListofMessages)
        return Parser.HandleUserInput(input,ListofMessages);
    }

}
