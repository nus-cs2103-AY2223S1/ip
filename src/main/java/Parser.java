public class Parser {

    private static final String COMMAND_LIST = "list";
    private static final String COMMAND_BYE = "bye";
    private static final String COMMAND_TODO = "todo";
    private static final String COMMAND_DEADLINE = "deadline";
    private static final String COMMAND_EVENT = "event";
    private static final String COMMAND_MARK = "mark";
    private static final String COMMAND_UNMARK = "unmark";

    String type;
    String description;
    String dateTime;


    public Parser(String userInput) throws InvalidCommandException {
        String splitUserStatement[] = userInput.split(" ", 2);
        String command = splitUserStatement[0];
        switch (command) {
            
        }
        String userArgs = "";
        if (splitUserStatement.length > 1) {
            userArgs = splitUserStatement[1];
        }
        String[] splitUserArgs = userArgs.split("/by", 2);
        String dateTime = "";
        String description = splitUserArgs[0];
        if (splitUserArgs.length > 1) {
            dateTime = splitUserArgs[1]; 
        }
    }

    public static void parseUserInput(String userInput) {

    }


}
