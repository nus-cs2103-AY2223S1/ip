package duke;

import utils.Constants;
import utils.DukeUtils;

public enum Command {

    TODO {
        @Override
        public Command isValidInput(String input) throws DukeException {
            if (input.isEmpty()) {
                throw new DukeException(String.format(Constants.ERROR_EMPTY_DESCRIPTION, "TODO"));
            }
            return this;
        }
    },

    DEADLINE {
        @Override
        public Command isValidInput(String input) throws DukeException {
            if (input.isEmpty()) {
                throw new DukeException(String.format(Constants.ERROR_EMPTY_DESCRIPTION, "DEADLINE"));
            }

            if (!input.contains("/by") || input.substring(input.indexOf("/by")).isBlank()) {
                throw new DukeException(Constants.ERROR_DEADLINE_EMPTY_DATE_TIME);
            }
            return this;
        }
    },

    EVENT {
        @Override
        public Command isValidInput(String input) throws DukeException {
            if (input.isEmpty()) {
                throw new DukeException(String.format(Constants.ERROR_EMPTY_DESCRIPTION, "EVENT"));
            }

            if (!input.contains("/at") || input.substring(input.indexOf("/at")).isBlank()) {
                throw new DukeException(Constants.ERROR_EVENT_EMPTY_TIME);
            }

            return this;
        }
    },

    MARK {
        @Override
        public Command isValidInput(String input) throws DukeException {
            return isValidNumericInput(input);
        }
    },

    UNMARK {
        @Override
        public Command isValidInput(String input) throws DukeException {
           return isValidNumericInput(input);
        }
    },

    DELETE {
        @Override
        public Command isValidInput(String input) throws DukeException {
            return isValidNumericInput(input);
        }
    },

    LIST {
        @Override
        public Command isValidInput(String input) throws DukeException {
            if (!input.isEmpty()) {
                throw new DukeException(Constants.ERROR_INVALID_ARGUMENTS);
            }
            return this;
        }
    },

    BYE {
        @Override
        public Command isValidInput(String input) throws DukeException {
            if (!input.isEmpty()) {
                throw new DukeException(Constants.ERROR_INVALID_ARGUMENTS);
            }
            return this;
        }
    },

    INVALID {
        @Override
        public Command isValidInput(String input) {
            return this;
        }
    };

    public abstract Command isValidInput(String input) throws DukeException;

    public Command isValidNumericInput(String input) throws DukeException {
        if (input.isEmpty()) {
            throw new DukeException(Constants.ERROR_TASK_NOT_SPECIFIED);
        }

        if (DukeUtils.wordCount(input) > 1) {
            throw new DukeException(Constants.ERROR_INVALID_ARGUMENTS);
        }

        if (!DukeUtils.isNumeric(input)) {
            throw new DukeException(String.format(Constants.ERROR_NOT_NUMERIC, this.name().toLowerCase()));
        }

        return this;
    }

    public static Command contains(String command) {
        for (Command c : values()) {
            if (c.name().equalsIgnoreCase(command)) {
                return valueOf(command.toUpperCase());
            }
        }
        return INVALID;
    }
}