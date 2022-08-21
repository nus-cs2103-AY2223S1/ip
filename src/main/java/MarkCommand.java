public class MarkCommand extends Command{
    @Override
    public void execute(DobbyList dl, UserInput ui) {
        try {
            int toMark = ui.getInd();
            if(toMark <= 0) {
                DobbyChat.wrongTaskNumber();
            } else if(dl.getTask(toMark).isDone()) {
                DobbyChat.alreadyMarked();
            } else {
                dl.mark(toMark);
                DobbyChat.marked(dl.getTaskString(toMark));
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
