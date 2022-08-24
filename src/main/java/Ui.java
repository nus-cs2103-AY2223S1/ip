import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Ui {
    static final String CHATBOX_NAME = "Ado";
    static final String PARTITION = "<><><><><><><><><><><><><><><><><><><><><><><><><><><><>";

    public Ui() {
    }

    void showWelcome(){
        String startMessage = PARTITION + "\n  /\\_/\\\n" +
                " ( o.o ) < Yo! I'm " + CHATBOX_NAME + "!\n" +
                "  > ^ <    What can I do for you? :)\n" + PARTITION + "\n";
        printMessage(startMessage);
    }

    String readCommand() throws DukeException{
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        validate(input.toLowerCase());
        return input;
    }

    void showLine(){
        System.out.println(PARTITION);
    }

     void printMessage(String message) {
        System.out.println(message);
    }

     String listToString(List<Task> list) {
        if (list.size() == 0) {
            return "List is empty ~\n";
        }
        StringBuilder output = new StringBuilder();
        output.append("Here are the tasks in your list: \n");
        for (int i =0; i < list.size(); i++) {
            output.append(i + 1).append(". ").append(list.get(i)).append("\n");
        }
        return output.toString();
    }

    void showLoadingError() {
        System.out.println("Error in loading task :( New task list created!");
    }

    public void showError(String message) {
        System.out.println(message);
    }

     void validateD (String input) throws DukeException {
        String[] segments = input.split(" ");
        String[] tasks = {"todo", "deadline", "event", "mark", "unmark", "delete"};
        String[] allCommands = {"todo", "deadline", "event", "list", "bye", "mark", "unmark", "delete"};
        if (!Arrays.asList(allCommands).contains(segments[0])) {
            //handles invalid commands
            throw new DukeException("(´･_･`) I don't know what that means\n");
        } else if (Arrays.asList(tasks).contains(segments[0])) {
            if (segments[0].equals("mark") || segments[0].equals("unmark") || segments[0].equals("delete")) {
                if (segments.length <= 1) {
                    //handles empty list index
                    throw new DukeException("The value of " + segments[0] + " cannot be empty. （ﾟДﾟ ）\n");
                }
                //todo: move this error to command?
                /*else if (Integer.parseInt(segments[1]) > list.size()) {
                    //handles invalid list index
                    throw new DukeException("There is no " + Integer.parseInt(segments[1])
                            + " index in the list. （ﾟДﾟ ）\n");
                }*/
            } else if (segments.length <= 1) {
                //handles empty description
                throw new DukeException("The description of a " + segments[0] + " cannot be empty. （ﾟДﾟ ）\n");
            } else if (segments[0].equals("deadline")) {
                if (!input.contains("/by")) {
                    //handles empty date/time
                    throw new DukeException("The date of " + segments[0] + " cannot be empty. （ﾟДﾟ ）\n");
                }
            } else if (segments[0].equals("event")) {
                if (!input.contains("/at")) {
                    //handles empty date/time error
                    throw new DukeException("The date of " + segments[0] + " cannot be empty. （ﾟДﾟ ）\n");
                }
            }
        }
    }

    void validate (String input) throws DukeException {
        String[] commandSegments = input.split(" ", 2);
        String mainCommand = commandSegments[0].toLowerCase().trim();

        String[] allCommands = {"list", "bye", "todo", "deadline", "event", "mark", "unmark", "delete"};
        if (!Arrays.asList(allCommands).contains(mainCommand)) {
            //handles invalid commands
            throw new DukeException(mainCommand + "? (´･_･`) I don't know what that means\n");
        }
        String[] commandsWithDescription = {"todo", "deadline", "event"};
        if (Arrays.asList(commandsWithDescription).contains(mainCommand)) {

            if(commandSegments.length <= 1) {
                throw new DukeException("The description of a " + mainCommand + " cannot be empty. （ﾟДﾟ ）\n");
            }

             switch(mainCommand){
             case "todo":
                 break;
             case "deadline":
                 String[] deadlineSegments = commandSegments[1].split("/by", 2);
                 if(deadlineSegments.length < 2){
                     throw new DukeException("The date of deadline cannot be empty. （ﾟДﾟ ）\n");
                 }
                 String description = deadlineSegments[0];
                 String by = deadlineSegments[1].trim();
                 try {
                     LocalDate date = LocalDate.parse(by);
                 } catch (Exception ex){
                     throw new DukeException("Put date after /by in terms of yyyy-MM-dd");
                 }
                 break;
             case "event":
                 String[] eventSegments = commandSegments[1].split("/at", 2);
                 if(eventSegments.length < 2){
                     throw new DukeException("The date of event cannot be empty. （ﾟДﾟ ）\n");
                 }
                 break;
             default:
                 throw new DukeException(mainCommand + "? (´･_･`) I don't know what that means\n");
             }



        }


    }



}
