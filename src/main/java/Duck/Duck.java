package Duck;
import Models.Todo;
import Commands.Commands;

import UI.UI;
import javafx.application.Application;
import javafx.application.Platform;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Duck {
    private Storage storage;
    private TaskList<Todo> list;

    public static void main(String[] args) {
        Application.launch(UI.class, args);
    }

    /**
     * Primary Constructor for the Duck Class.
     * Initializes Storage and TaskList for use in the entire class
     */
    public Duck() {
        try {
            this.storage = new Storage("data", "duck.txt");
            this.list = new TaskList<Todo>(storage.readFromFile());
        }
        catch (IOException e) {
            System.out.println("Error! File not found!");
        }
    }

    /**
     * function to receive the command inputs from the UI
     * @param text user input
     * @param ui UI object that called this method
     */
    public void passOnCommands(String text, UI ui) {
        Commands c = Parser.parseText(text, ui);
        try {
            c.execute(list, storage, ui);
        } catch (IndexOutOfBoundsException e) {
            ui.sendTextToUi("Items do not exist! Or List is empty!");
        }
        if(c.isExit()) {
            Platform.exit();
        }
    }

    /**
     * Provides a centralized converter to convert date formats
     * Just replace the format here and all other date formats will follow suite
     * @param date date to be converted into string
     * @return  string converted in the dd/MM/yyyy HHmm format
     */
    public static String dateToStringConverter(Date date) {
        SimpleDateFormat dateFormatter = new SimpleDateFormat("dd/MM/yyyy HHmm");
        return dateFormatter.format(date);
    }
    /**
     * Provides a centralized converter to convert date formats
     * Just replace the format here and all other date formats will follow suite
     * @param date string to be converted into date
     * @return  date converted in the dd/MM/yyyy HHmm format
     */
    public static Date dateStorageConverter(String date) throws ParseException {
        SimpleDateFormat dateFormatter = new SimpleDateFormat("dd/MM/yyyy HHmm");
        return dateFormatter.parse(date);
    }

}
