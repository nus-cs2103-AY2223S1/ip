package dukechatbot.utility;
import java.io.*;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * The Storage class encapsulates the operations on the file to be read and written.
 *
 * @author A0233290M
 * @version Week3
 */
public class Storage {
    /**
     * Defines the file to be associated with this instance of the Storage class.
     */
    private File file;
    /**
     * Defines the instance of the BufferedReader to allow the instance of Storage to read from input.
     */
    private BufferedReader br;
    /**
     * Encapsulates the instance of TaskList associated with the instance of Storage.
     */
    private TaskList tasks;
    /**
     * The instance of ui to be passed in for use by the class to interact with user.
     */
    private Ui ui;
    /**
     * Constructs the instance of storage to read and write from and to the file passed in.
     *
     * @param fileName the name of the file to be processed.
     * @param tasks the task list to be manipulated by the instance of Storage.
     * @param ui the ui associated with this current run of the program.
     * @throws IOException when createNewFile fails to create the file.
     */
    public Storage(String fileName, TaskList tasks, Ui ui) throws IOException {
        this.file = Path.of(fileName).toFile();
        this.br = new BufferedReader(new FileReader(file));
        this.tasks = tasks;
        this.ui = ui;
        if (file.exists()) {
            this.load();
        } else {
            if (file.createNewFile()) {
                this.load();
            } else {
                throw new IOException("File failed creation!");
            }
        }
    }

    /**
     * Loads the content of the file and into the list of tasks stored in the associated txt file.
     *
     * @throws IOException if the file fails to be read.
     */
    public void load() throws IOException {
        if (file.canRead()) {
            String ln = this.br.readLine();
            while (ln != null) {
                this.tasks.add(ln);
                ln = br.readLine();
            }
        } else {
            this.ui.showLoadingError();
        }
    }

    /**
     * Saves the tasks of the task list into the associated file to be loaded in the next run of the program.
     *
     * @throws IOException when method fails to write into the associated file.
     */
    public void save() throws IOException {
        FileWriter fw = new FileWriter(file.getAbsolutePath());
        for (Iterator<Task> it = this.tasks.getArrayList().iterator(); it.hasNext();) {
            Task curr = it.next();
            fw.write(curr.toString() + "\r\n");
        }
        fw.close();
    }
}
