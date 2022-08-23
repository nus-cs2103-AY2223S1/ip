import java.io.File;
import java.io.FileNotFoundException;
import java.io.FilenameFilter;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

public class Deku {
    private BotList botList;

    Deku() {
        this.botList = new BotList();
    }

    private final String SEPARATOR = "__________________________________";
    private void introduction() {
        String logo ="    ____  ________ ____  __\n" +
                "   / __ \\/ ____/ //_/ / / /\n" +
                "  / / / / __/ / ,< / / / /\n" +
                " / /_/ / /___/ /| / /_/ /\n" +
                "/_____/_____/_/ |_\\____/\n";
        System.out.println(logo +
                "\nHello I'm DEKU\nHow may I help you today?\n" +
                SEPARATOR);
    }
    /**
    * Method to start the chat-bot
    */
    private void start() {
        Scanner scanner = new Scanner(System.in);
        this.introduction();
        boolean active = true;
        while (active && scanner.hasNext()) {
            String userInput = scanner.nextLine();
            if (userInput.equals("bye")) {
                active = false;
            }
            System.out.println(
                    this.parseReply(userInput)
            );
        }
    }

    /**
     * Method to load data from stored file to the chat-bot
     */
    public void load() {
        Path directoryPath = Paths.get(System.getProperty("user.dir"), "data");
        Path filePath = directoryPath.resolve("save.txt");
        Task newTask;
        try {
            File file = new File(filePath.toUri());
            Scanner scanner = new Scanner(file);

            while (scanner.hasNextLine()) {
                String data = scanner.nextLine();
                List<String> stored = new ArrayList<>(List.of(data.split("\\|")));
                System.out.println(stored);
                stored = stored
                        .stream()
                        .filter(e -> !e.equals("") && !e.equals("null"))
                        .collect(Collectors.toList());
                String taskType = stored.remove(0);
                Boolean isCompleted = stored.remove(0).equals("1");
                try {
                    switch (taskType) {
                    case ("T"):
                        newTask = new ToDo(stored);
                        break;
                    case ("E"):
                        newTask = new Event(stored);
                        break;
                    case ("D"):
                        newTask = new Deadline(stored);
                        break;
                    default:
                        throw new DekuExceptions("");
                    }
                    newTask.setCompletionStatus(isCompleted);
                    this.botList.addWithoutSave(newTask);
                } catch (DekuExceptions e) {
                    System.out.println("Error reading data file! Sorry... :(");
                    start();
                }
            }
            scanner.close();
            if (file.length() != 0) {
                System.out.println(SEPARATOR + "\n" + "Successfully Loaded Saved Tasks! :)" + "\n" + SEPARATOR);
            }
            start();
        } catch (FileNotFoundException e) {
            start();
        }
    }

    private String parseReply(String input) {
        String reply = input;
        List<String> separate = new LinkedList<>(Arrays.asList(input.split("\\s+")));
        KeyPhrases keyPhrase = KeyPhrases.get(separate.remove(0));
        try {
            switch (keyPhrase) {
            case BYE:
                reply = "Bye! Until next time!";
                break;
            case LIST:
                reply = this.botList.toString();
                break;
            case MARK:
                reply = this.botList.mark(Integer.parseInt(separate.get(0)));
                break;
            case UNMARK:
                reply = this.botList.unmark(Integer.parseInt(separate.get(0)));
                break;
            case DELETE:
                reply = this.botList.delete(Integer.parseInt(separate.get(0)));
                break;
            case DEADLINE:
                reply = this.botList.add(new Deadline(separate));
                break;
            case EVENT:
                reply = this.botList.add(new Event(separate));
                break;
            case TODO:
                reply = this.botList.add(new ToDo(separate));
                break;
            case FIND_DATE:
                reply = this.botList.find(separate.get(0));
                break;
            default:
                throw new DekuExceptions("I have no idea what that means. (T _ T)");
            }
        } catch (DekuExceptions e) {
            reply = e.toString();
        }
        return SEPARATOR + "\n" + reply + "\n" + SEPARATOR;
    }

    /*
    * Default main method
    */
    public static void main(String[] args) throws IOException {
        Deku dekuBot = new Deku();
        dekuBot.load();
    }
}
