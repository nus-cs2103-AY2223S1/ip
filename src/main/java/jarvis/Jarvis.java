package jarvis;

/**
 * Jarvis (credit: Iron Man) is a a Personal Assistant Chatbot that helps a person
 * to keep track of their todos, events and deadlines
 */
public class Jarvis {
    private Storage storage;
    private TaskList taskList;
    private Parser parser;

    /**
     * Constructor for a Jarvis Chatbot. Initialize the needed objects
     * @param filePath The file to load data from and store data to
     */
    public Jarvis(String filePath) {

        storage = new Storage(filePath);
        try {
            taskList = storage.loadTaskList();
        } catch (JarvisException e) {
            taskList = new TaskList();
        }
        parser = new Parser(taskList);
    }

    public String getResponse(String input) {
        return this.parser.parse(input);
    }

    public boolean getByeStatus() {
        return parser.getByeStatus();
    }

}
