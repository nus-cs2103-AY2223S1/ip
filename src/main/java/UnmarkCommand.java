public class UnmarkCommand extends Command{
    @Override
    public void execute(DobbyList dl, UserInput ui) {
        try {
            int toUnmark = ui.getInd();
            if(toUnmark <= 0) {
                DobbyChat.wrongTaskNumber();
            } else if(!(dl.getTask(toUnmark).isDone())) {
                DobbyChat.alreadyUnmarked();
            } else {
                dl.unmark(toUnmark);
                DobbyChat.unmarked(dl.getTaskString(toUnmark));
            }
        } catch(StringIndexOutOfBoundsException e) {
            DobbyChat.noTaskNumber();
        } catch(NumberFormatException e) {
            DobbyChat.noNumber();
        } catch(IndexOutOfBoundsException e) {
            DobbyChat.tooLittleTasks();
        }
    }
}
