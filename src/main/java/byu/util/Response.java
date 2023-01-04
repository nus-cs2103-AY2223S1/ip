package byu.util;

import byu.commands.Command;
import byu.exceptions.ByuException;
import javafx.application.Platform;

public class Response {
    private String output;
    private boolean isExit;
    private boolean isHelp;
    public Response(String output, boolean isExit, boolean isHelp) {
        this.output = output;
        this.isExit = isExit;
        this.isHelp = isHelp;
    }
    public boolean isExit() {
        return isExit;
    }

    public boolean isHelp() {
        return isHelp;
    }
    public String getOutput() {
        return this.output;
    }
}
