package scruffles;

/**
 * This class is responsible for the user interactions of the program
 *
 * @author Shamus Tan
 */
public class Ui {

    /**
     * Greets the user when they first run the program
     */
    public String greet() {
        return "woof! I'm scruffles the task tracking doggo\n" + "what can I woof for you today?";
    }
    
    /**
     * Greets the user when they exit the program
     *
     * @return goodbye message
     */
    public static String bye() {
        return "woof see you again woof!";
    }
}
