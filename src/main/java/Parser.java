import java.util.Scanner;

public class Parser {
    public static Command inputCommand(String command, TaskList tasks, Ui ui) throws DukeException {
        String[] returnedArray = command.split(" ");
        if (returnedArray.length == 0 || returnedArray[0] == null || returnedArray[0].equals("")) {
            throw new DukeException("Sorry, I am a bit hard of hearing.\nCould you please repeat yourself?" +
                    "\nIf unsure, please use command [help] for the list of commands that I understand.");
        } else {
            Command.Commands word = Command.checkEnums(returnedArray[0]);
            switch (word) {
            case bye:
                return new ExitCommand();
            case list:
                return new ListCommand(tasks, ui);
            case help:
                return new HelpCommand();
            case mark:
                return new MarkCommand(returnedArray, tasks, ui);
            case unmark:
                return new UnmarkCommand(returnedArray, tasks, ui);
            case delete:
                return new DeleteCommand(returnedArray, tasks, ui);
            case todo:
                return new ToDoCommand(command, tasks, ui);
            case deadline:
                return new DeadlineCommand(command, tasks, ui);
            case event:
                return new EventCommand(command, tasks, ui);
            case invalid: //Notice the control flow still reaches here even if [invalid] is input
                throw new DukeException("I don't understand your command.\nCould you please repeat yourself?" +
                        "\nIf unsure, please use command [help] for the list of commands that I understand.");
            default:
                throw new DukeException("please do not mess with my software. This message should never" +
                        "pop up.");
            }
        }
    }

//    public void receiveCommands() {
//        while (commandInput.hasNextLine()) {
//            String command = commandInput.nextLine();
////            System.out.println(lineBreak2);
//            try {
//                inputCommands(command);
////                saveDuke(); //consider a bool check for if the save should happen?
//            } catch (DukeException e) {
//                System.out.println(e.getMessage());
//            } //catch (IOException e) {
////                System.out.println(e.getMessage());
////            }
////            System.out.println(lineBreak1);
//            if (isClosed) {
//                commandInput.close();
//                break;
//            }
//        }
//    }
}
