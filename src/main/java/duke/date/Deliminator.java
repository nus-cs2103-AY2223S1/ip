package duke.date;

import java.util.List;

/**
 * Represents the date deliminators separating the date fields supported by the program.
 */
public enum Deliminator {
    noDeliminator {
        @Override
        public String getDeliminator() {
            return "";
        }

        @Override
        public String getDeliminatorRegex() {
            return "";
        }
    },
    slashDeliminator {
        @Override
        public String getDeliminator() {
            return "/";
        }

        @Override
        public String getDeliminatorRegex() {
            return "\\/";
        }
    },
    dashDeliminator {
        @Override
        public String getDeliminator() {
            return "-";
        }

        @Override
        public String getDeliminatorRegex() {
            return "-";
        }
    },
    dotDeliminator {
        @Override
        public String getDeliminator() {
            return ".";
        }

        @Override
        public String getDeliminatorRegex() {
            return "\\.";
        }
    },
    whitespaceDeliminator {
        @Override
        public String getDeliminator() {
            return " ";
        }

        @Override
        public String getDeliminatorRegex() {
            return "\\s";
        }
    };

    public abstract String getDeliminator();
    public abstract String getDeliminatorRegex();

    public static List<Deliminator> getAllDeliminators() {
        return List.of(noDeliminator, dashDeliminator, slashDeliminator,
                dotDeliminator, whitespaceDeliminator);
    }
}
