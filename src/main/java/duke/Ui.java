package duke;



/**
 * Represents the User Interface that users will be communicating with when using the Duke Chatbot
 * each <code>Ui</code> contains a <code>Parser</code> and a <code>Storage</code>
 */
public class Ui {
    private Parser parser;
    private Storage storage;

    public Ui() {
    }

    /**
     * Constructor Method for the Ui class
     *
     * @param s
     * @param tasks
     */
    public Ui(Storage s, TaskList tasks) {
        this.storage = s;
        this.parser = new Parser(s, tasks);
    }

    /**
     * Initialises the Ui of the Duke Chatbot
     */
    public void start() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("Oi, What u want?");

    }



    /**
     */
    public void showLoadingError() {
        System.out.println("ERROR: No File Found... \nCreating New File");
    }

}
