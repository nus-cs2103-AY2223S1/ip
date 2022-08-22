import utils.IOUtils;

import java.util.Scanner;
public class CommandListener {

    private Scanner in;
    private boolean active = true;

    public CommandListener() {
        IOUtils.printContentWithHR("Hello! I'm " + Main.name + "\n" + "What can I do for you?");
        this.in = new Scanner(System.in);
        this.active = true;

        while(this.active && this.in.hasNext()) {
            String line = this.in.nextLine();
            String output = commandHandler(line);
            IOUtils.printContentWithHR(output);
        }
    }

    public String commandHandler(String command) {
        switch (command) {
            case "Bye":
            case "bye":
                this.close();
                return "Bye. Hope to see you again!";
        }

        return command;
    }

    private void close() {
        this.in.close();
        this.active = false;
    }


}
