public class ExitCommand extends Command{
    @Override
    public void execute( TaskList taskList, Storage storage){
        super.setIsExitTrue();
    }
}
