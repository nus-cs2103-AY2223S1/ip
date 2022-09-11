
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


        System.out.println(LocalDateTime.parse("2022-11-11T00:00").toLocalDate());
        System.out.println(LocalDateTime.parse("2022-11-11T00:00").toLocalTime());


        //serverGUI.run();

    }




    public String getResponse(String input) {

        return serverGUI.getResponse(input);

    }

//
//


}
