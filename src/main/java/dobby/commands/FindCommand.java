package dobby.commands;

import dobby.tasks.*;
import dobby.*;

import java.io.IOException;

public class FindCommand extends Command {
    @Override
    public void execute(DobbyList dl, UserInput ui) throws IOException {
        if(dl.isEmpty()) {
            DobbyChat.listEmpty();
        } else {
            DobbyChat.echo(dl.toFind(ui.getDesc()));
        }
    }
}
