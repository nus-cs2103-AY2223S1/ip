package duke.util;

/**
 * Text UI for Duke app.
 */
public class Ui {
    /**
     * Prints a greeting to the user.
     */
    public String greet() {
        String logo = "_________                                         ___\n"
                +  "\\        __  \\    ________   ___        ____|  |_________      ____\n"
                +  "/        \\    \\/_/  __  \\_    __  \\_/  __  \\|  __  \\_    __  \\/    _  \\\n"
                +  "\\          \\___\\    ___/|     |  \\/\\    ___/|  \\_\\  \\    |  \\(    (_)  )\n"
                +  "  \\________/ \\_____> ___|        \\_____>_____/__|       \\____/\n";
        String output = "Hello from\n" + logo;
        output += "What can I do for you?";
        System.out.println(output);
        return output;
    }

    /**
     * Prints a goodbye message to the user.
     */
    public String bye() {
        return "Goodbye! See you soon!";
    }

    /**
     * Prints a help message to the user.
     */
    public String help() {
        String message = "Here are the list of commands available: \n"
            + "1. todo <task> - Adds a todo task to the list.\n"
            + "2. deadline <task> /by <date> - Adds a deadline task to the list.\n"
            + "3. event <task> /at <date> - Adds an event task to the list.\n"
            + "4. list - Displays all tasks in the list.\n"
            + "5. mark <task number> - Marks the specified task as done.\n"
            + "6. unmark <task number> - Marks the specified task as not done.\n"
            + "7. delete <task number> - Deletes the specified task.\n"
            + "8. find <keyword> - Finds all tasks with the specified keyword.\n"
            + "9. help - Displays this help message.\n"
            + "10. bye - Exits the program.";
        return message;
    }

    /**
     * Displays the error to the user.
     * 
     * @param error Error message to be shown to user.
     */
    public String showError(String error) {
        return "OOPS! " + error;
    }

    /**
     * Displays the error to the user.
     * 
     * @param e Exception to be shown to user.
     */
    public String showError(Exception e) {
        return "OOPS! " + e.getMessage();
    }
}
