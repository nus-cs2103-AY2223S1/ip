import java.io.File;

public class Storage {
    private File file;

    public Storage(String filePath) {
        file = new File(filePath);
    }

    public boolean hasExisted() {
        return file.isFile();
    }

    public TaskList load() {
        //todo
        return new TaskList();
    }

}
