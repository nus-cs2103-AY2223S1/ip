package duke;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
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
        File log = new File("log.txt");
        File help = new File("help.txt");
        help.createNewFile();

        if(log.exists()==false){
            //System.out.println("We had to make a new file."-Remove for now);
            log.createNewFile();
        }

        //Reading in data from the file
        Scanner readfile = new Scanner(log);
        Storage.readFile(readfile,ListofMessages); //Reads all the input

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
        return Parser.handleUserInput(input,ListofMessages);
    }

}
