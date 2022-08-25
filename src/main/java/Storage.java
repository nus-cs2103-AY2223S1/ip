import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class Storage {
    private TaskList tasks;

    public Storage(TaskList tasks) {
        this.tasks = tasks;
    }
    public void loadFile() {
        try {
            File dir = new File("data");
            File f = new File("data/duke.txt");
            if (dir.exists()) {
                if (f.exists()) {
                    Scanner sc1 = new Scanner(f);
                    while (sc1.hasNextLine()) {
                        String str = sc1.nextLine();
                        if (str.charAt(0) == 'T') {
                            this.tasks.loadTodo(str);
                        } else if (str.charAt(0) == 'D') {
                            this.tasks.loadDeadline(str);
                        } else if (str.charAt(0) == 'E') {
                            this.tasks.loadEvent(str);
                        } else {
                            Ui.printLoadFileError();
                        }
                    }
                    sc1.close();
                } else {
                    f.createNewFile();
                }
            } else {
                dir.mkdir();
                f.createNewFile();
            }
        } catch (FileNotFoundException e) {
            Ui.printFileNotFoundError();
        } catch (IOException e) {
            Ui.printInputOutputError();
        } catch (InvalidDataFileException e) {
            Ui.printLoadFileError();
        }
    }

}
