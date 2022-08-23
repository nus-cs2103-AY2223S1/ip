public class Ui {
    private final String SEPARATOR = "__________________________________";

    String introduction() {
        String logo ="    ____  ________ ____  __\n" +
                "   / __ \\/ ____/ //_/ / / /\n" +
                "  / / / / __/ / ,< / / / /\n" +
                " / /_/ / /___/ /| / /_/ /\n" +
                "/_____/_____/_/ |_\\____/\n";
        return logo +
                "\nHello I'm DEKU\nHow may I help you today?\n" +
                SEPARATOR;
    }

    String reply(String reply) {
        return SEPARATOR + "\n" + reply + "\n" + SEPARATOR;
    }

    String end() {
        return "Bye! Until next time!";
    }
}
