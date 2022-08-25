public class EndChatBotCmd extends Command {
    @Override
    public void execute(TaskList tasks, UI ui, Storage storage) {
        ui.goodbye();
    }
}
