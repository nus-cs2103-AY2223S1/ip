package duke.command;

import duke.tasklist.TaskList;
import duke.storage.Storage;
public class OtherCommand extends Command{
    String fullCommand;
    public OtherCommand(String fullCommand){
        this.fullCommand = fullCommand;
    }
    @Override
    public void execute(TaskList taskList, Storage storage){
        if (this.fullCommand.split(" ")[0].equals("Get")){
            System.out.println(taskList.getASpecificDay(fullCommand));
        } else if (this.fullCommand.split(" ")[0].equals("find")) {
            System.out.println(taskList.find(fullCommand));
        } else{
            System.out.println(taskList.listAllTask());
        }
    }
}
