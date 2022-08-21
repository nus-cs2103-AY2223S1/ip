import java.io.IOException;

public abstract class Command {
    private boolean isBye;

    public boolean isBye() {
        return isBye;
    }
    public abstract void execute(DobbyList dl, UserInput ui) throws IOException;
}