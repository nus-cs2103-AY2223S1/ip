package neo;//package neo;
import neo.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDate;
import java.util.*;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.layout.Region;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * Main class neo.
 */
public class Neo {
    private Storage stor;
    private Ui ui;
    private TaskList arrayLL;
    private Parser parser;

    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;

    private Image user = new Image(this.getClass().getResourceAsStream("/images/Amy.png"));
    private Image duke = new Image(this.getClass().getResourceAsStream("/images/Jake.png"));

    File f = new File("/Users/richavm/Documents/NUS/Y2S1/CS2103T/data/Neo.txt");

    /**
     * Constructor for neo class.
     */
    public Neo() {
        this.stor = new Storage();
        this.ui = new Ui();
        this.arrayLL = new TaskList();
        this.parser = new Parser(ui, stor, arrayLL);

        addToTaskList(String.valueOf(f), arrayLL);

    }

    /**
     * Retrieves tasks from neo.txt and add to task list.
     *
     * @param filePath file path
     * @param arrayLL taskList to store tasks
     */
    public static void addToTaskList(String filePath, TaskList arrayLL)  {
        File f = new File(filePath);
        Scanner sc = null;
        try {
            sc = new Scanner(f);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        String str = "";
        Task task;

        while (sc.hasNext()) {
            String stri = sc.nextLine();
            String arr[];
            arr = stri.split("] ", 2);
            String temp0 = arr[0];

            String temp1 = arr[1];

            if (temp0.equals("[D][X")) {
                Deadline d = new Deadline(temp1);
                d.setIsDone(true);
                arrayLL.addTask(d);
            }
            if (temp0.equals("[D][ ")) {
                Deadline d = new Deadline(temp1);
                arrayLL.addTask(d);
            }
            if (temp0.equals("[E][X")) {
                Event e = new Event(temp1);
                e.setIsDone(true);
                arrayLL.addTask(e);
            }
            if (temp0.equals("[E][ ")) {
                Event e = new Event(temp1);
                arrayLL.addTask(e);
            }
            if (temp0.equals("[T][ ")) {
                ToDo td = new ToDo(temp1);
                arrayLL.addTask(td);
            }
            if (temp0.equals("[T][X")) {
                ToDo td = new ToDo(temp1);
                td.setIsDone(true);
                arrayLL.addTask(td);
            }
            sc.nextLine();
        }
    }

    /**
     * Gets bots response from parser.
     *
     * @param userText user string input
     * @return
     * @throws NeoException neo exception
     * @throws IOException input output exception
     */
    public String getResponse(String userText) throws NeoException, IOException {

        if (userText.equals("hi") || userText.equals("Hi")) {
            return ui.printStart();
        }
        if (userText.equals("bye") || userText.equals("Bye")) {
            return ui.printEnd();
        }

        try {
            return parser.checkText(userText);
        } catch (NeoException e) {
            return ui.errorMessage(e.getMessage());
        }

    }
}


