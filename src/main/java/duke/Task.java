package duke;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

public class Task {
    protected String name;
    protected boolean isDone;

    Task(String name, boolean isDone){
        this.name = name;
        this.isDone = isDone;
    }

    public String getStatusIcon() {
       return(this.isDone? "[X] " : "[] " );
    }

    public void mark() throws IOException {
        /*Storage s = new Storage(Duke.getPathname, Duke.getDirectory);
        String[] oldLineOldContentPair = s.modifyFileHelper(this.toString());
        String oldLine = oldLineOldContentPair[0];
        String oldContent = oldLineOldContentPair[1];
        File file = new File(Duke.getPathname);
        FileWriter writer = new FileWriter(file);
        String newContent = oldContent.replace(oldLine, oldLine.substring(0,3) +
                "[X] " + oldLine.substring(6));
        writer.write(newContent);
        writer.close();

         */
        this.isDone = true;
        Ui ui = new Ui();
        ui.markHelperUi(this);
    }

    public void unmark() throws IOException {
       /* Storage s = new Storage(Duke.getPathname, Duke.getDirectory);
        String[] oldLineOldContentPair = s.modifyFileHelper(this.toString());
        String oldLine = oldLineOldContentPair[0];
        String oldContent = oldLineOldContentPair[1];
        File file = new File(Duke.getPathname);
        FileWriter writer = new FileWriter(file);
        String newContent = oldContent.replace(oldLine,
                oldLine.substring(0,3) + "[] " + oldLine.substring(7));
        writer.write(newContent);
        writer.close();
        */

        this.isDone = false;
        Ui ui = new Ui();
        ui.unmarkHelperUi(this);
    }

    public String getStatus() {
        return this.getStatusIcon() + "" + this.name;
    }

    }



