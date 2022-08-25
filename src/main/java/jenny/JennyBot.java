package jenny;

import jenny.commands.AbstractCommand;
import jenny.commands.ByeCommand;
import jenny.exceptions.JennyException;
import jenny.tasks.AbstractTask;
import jenny.util.Parser;
import jenny.util.UserInterface;

import java.util.ArrayList;

/**
 * Initialise the JennyBot application.
 * CS2103 Week 2
 * AY21/22 Semester 1
 *
 * @author Deon
 */
public final class JennyBot {
    private static ArrayList<AbstractTask> tasks = new ArrayList<>();

    public void run() {
        UserInterface.greet();
        String nextLine = UserInterface.read();
        AbstractCommand cmd = Parser.parse(nextLine);
        while (!(cmd instanceof ByeCommand)) {
            try {
                cmd.run(tasks);
            } catch (JennyException e) {
                UserInterface.print(e.getMessage());
            }
            nextLine = UserInterface.read();
            cmd = Parser.parse(nextLine);
        }
    }

    public static void main(String[] args) {
        new JennyBot().run();
    }
}
