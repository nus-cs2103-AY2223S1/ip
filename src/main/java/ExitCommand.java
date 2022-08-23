public class ExitCommand extends Command{

    /**
     * Command to exit the application
     */
    @Override
    public void run(TaskList taskList, Storage storage) {
        Ui.bye();
        System.exit(0);
    }
}
