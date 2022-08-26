import java.util.ArrayList;

class MarkCommand extends Command {
    String cmd;

    public MarkCommand(String cmd) {
        this.cmd = cmd;
    }
    
    @Override
    public void deconstruct(ArrayList<DukeTask> tasklist, Ui ui, Storage storage) throws InvalidFormatException {
        try {
            int j = Integer.valueOf(cmd.substring(5).trim());
            tasklist.get(j).isMarked = true;
            System.out.println("Nice! I've marked this task as done:");
            System.out.println(String.format("List %d: ", j) + tasklist.get(j).toString());
            storage.save();
        } catch (Exception e) {
            System.out.println("Something went wrong, here's the error message cuz im lazy to figure it out for you: " + e);
        }
        
    }
    
}