package duke;

/**
 * Represents an interface that interacts with the user by printing the necessary messages
 */
public class Ui {

    String showError(Exception e){
        return e.getMessage();
    }

}
