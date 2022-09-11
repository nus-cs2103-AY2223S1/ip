
package Duke;




import Duke.Tasks.Deadline;
import Duke.UserServer.ServerCLI;
import Duke.UserServer.ServerGUI;

import java.awt.print.Printable;
import java.time.LocalDateTime;


/**
 * Duke App Main class
 */

public class Duke {

    private static final ServerCLI serverCLI = new ServerCLI();
    private static final ServerGUI serverGUI = new ServerGUI();

    public static void main(String[] args) { //throws DukeException, FileNotFoundException {


        serverCLI.run();

    }




    public String getResponse(String input) {

        return serverGUI.getResponse(input);

    }

//
//


}
