import java.util.ArrayList;

public class ByeCommand extends Command {
    
    @Override
    public void deconstruct(ArrayList<DukeTask> taskList, Ui ui, Storage storage) throws InvalidFormatException {
       System.out.println("Bye, see you soon");        
    }
    
}
