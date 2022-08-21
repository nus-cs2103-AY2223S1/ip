package dobby;

import dobby.commands.*;
import dobby.tasks.*;

import java.util.Scanner;

public class UserInput {
    Scanner scanner = new Scanner(System.in);

    private String cmd;
    private String rest;
    private int ind;
    private String desc;
    private String date;

    public String readCommand() {
        String input = scanner.nextLine();
        if(!input.contains(" ")) {
            cmd = input;
        } else {
            cmd = Parser.getCmd(input);
            rest = Parser.getRest(input);
            if(cmd.equals("mark") || cmd.equals("unmark") || cmd.equals("delete") ) {
                ind = Integer.parseInt(rest);
            } else if(cmd.equals("todo")) {
                desc = rest;
            } else if (cmd.equals("deadline") || cmd.equals("event")){
                date = Parser.getDate(rest);
                desc = Parser.getDesc(rest);
            }
        }
        return cmd;
    }
    public int getInd() {
        return ind;
    }
    public String getDesc() {
        return desc;
    }
    public String getDate() {
        return date;
    }
    public String getCmd() {
        return cmd;
    }
}
