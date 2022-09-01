package entry;

import commands.CommandResponse;
import commands.CommandRunner;
import exceptions.DukeException;
import exceptions.StorageException;
import input.Input;
import output.OutputLogger;
import task.TaskModel;


/**
 * Entry point for the chatbot
 */
public class Jarvis {
    private TaskModel taskModel;
    private CommandRunner cmdRunner;

    public Jarvis() throws StorageException {
        taskModel = new TaskModel();
        cmdRunner = new CommandRunner(taskModel);
    }

    public CommandResponse getResponse(String userInput) {
        try {
            CommandResponse res = cmdRunner.run(Input.newInput(userInput));
            return res;
        } catch (DukeException e) {
            return new CommandResponse(e.getMessage());
        }
    }

    public String getWelcome() {
        return OutputLogger.getIntroduction();
    }

    public void save() throws DukeException {
        taskModel.save();
    }

    /**
     * Entry point for Duke
     * @param args CLI args
     */
//    public static void main(String[] args) {
//        Scanner sc = new Scanner(System.in);
//        CommandRunner cmdRunner = null;
//        boolean canRun = true;
//        try {
//            cmdRunner = new CommandRunner(taskModel);
//        } catch (StorageException e) {
//            OutputLogger.output("There was an issue loading tasks. Shutting down...");
//            canRun = false;
//        }
//
//        if (canRun) {
//            OutputLogger.printIntroduction();
//        }
//
//        while (canRun) {
//            try {
//                System.out.print(">> ");
//                String input = sc.nextLine().trim();
//
//                Input ir = Input.newInput(input);
//
//                CommandResponse res = cmdRunner.run(ir);
//                OutputLogger.output(res.getMessage());
//                if (res.isExit()) {
//                    break;
//                }
//            } catch (DukeException err) {
//                OutputLogger.output(err.getMessage());
//            } catch (Exception err) {
//                System.out.println(err);
//                OutputLogger.output("An unrecognised issue has occurred:\n" + err.getMessage());
//            }
//        }
//    }
}
