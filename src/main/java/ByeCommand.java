import java.io.IOException;

public class ByeCommand extends Command{
    @Override
    public void execute(DobbyList dl, UserInput ui) throws IOException {
        isBye();
        DobbyIO.save(dl);
        DobbyChat.sayBye();
        isBye = true;
    }
}
