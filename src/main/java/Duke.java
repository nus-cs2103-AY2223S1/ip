import java.io.IOException;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.*;
public class Duke {

    private Ui newUser;
    private Storage data;

    public Duke(String filePath) throws IOException {
        newUser = new Ui();
        data = new Storage(newUser.getTaskArr(), filePath);
    }

    public static void main(String[] args) throws IOException {
        new Duke("duke.txt");
    }

}

