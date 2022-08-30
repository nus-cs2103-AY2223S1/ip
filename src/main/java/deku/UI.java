package deku;

class UI {
    private final String separator = "__________________________________";

    String introduction() {
        String logo = "    ____  ________ ____  __\n"
                + "   / __ \\/ ____/ //_/ / / /\n"
                + "  / / / / __/ / ,< / / / /\n"
                + " / /_/ / /___/ /| / /_/ /\n"
                + "/_____/_____/_/ |_\\____/\n";
        return logo
                + "\nHello I'm DEKU\nHow may I help you today?\n"
                + separator;
    }

    String reply(String reply) {
        return separator + "\n" + reply + "\n" + separator;
    }

    String end() {
        return "Bye! Until next time!";
    }
}