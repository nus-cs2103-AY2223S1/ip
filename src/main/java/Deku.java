import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.*;

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
    public void start() throws IOException {
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
        dekuBot.start();
    }
}
