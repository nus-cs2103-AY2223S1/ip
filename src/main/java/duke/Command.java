package duke;

import utils.Constants;

import java.util.Arrays;

public enum Command {

    TODO {
        @Override
        public Command hasValidInput(String inputDesc) throws DukeException {
            if (inputDesc.isEmpty()) {
                throw new DukeException(String.format(Constants.ERROR_EMPTY_DESCRIPTION, "TODO"));
            }
            return this;
        }
    },

    DEADLINE {
        @Override
        public Command hasValidInput(String inputDesc) throws DukeException {
            return hasValidStringInput(inputDesc, "/by");
        }
    },

    EVENT {
        @Override
        public Command hasValidInput(String inputDesc) throws DukeException {
            return hasValidStringInput(inputDesc, "/at");
        }
    },

    MARK {
        @Override
        public Command hasValidInput(String inputDesc) throws DukeException {
            return hasValidNumericInput(inputDesc);
        }
    },

    UNMARK {
        @Override
        public Command hasValidInput(String inputDesc) throws DukeException {
           return hasValidNumericInput(inputDesc);
        }
    },

    DELETE {
        @Override
        public Command hasValidInput(String inputDesc) throws DukeException {
            return hasValidNumericInput(inputDesc);
        }
    },

    LIST {
        @Override
        public Command hasValidInput(String inputDesc) throws DukeException {
            if (!inputDesc.isEmpty()) {
                throw new DukeException(Constants.ERROR_INVALID_ARGUMENTS);
            }
            return this;
        }
    },

    BYE {
        @Override
        public Command hasValidInput(String inputDesc) throws DukeException {
            if (!inputDesc.isEmpty()) {
                throw new DukeException(Constants.ERROR_INVALID_ARGUMENTS);
            }
            return this;
        }
    },

    INVALID {
        @Override
        public Command hasValidInput(String inputDesc) {
            return this;
        }
    };

    public abstract Command hasValidInput(String inputDesc) throws DukeException;

    public Command hasValidNumericInput(String inputDesc) throws DukeException {
        if (inputDesc.isEmpty()) {
            throw new DukeException(Constants.ERROR_TASK_NOT_SPECIFIED);
        }

        if (inputDesc.trim().split("\\s+").length > 1) {
            throw new DukeException(Constants.ERROR_INVALID_ARGUMENTS);
        }

        if (!inputDesc.chars().allMatch(Character::isDigit)) {
            throw new DukeException(Constants.ERROR_INVALID_NUMBER);
        }

        return this;
    }

    public Command hasValidStringInput(String inputDesc, String separator) throws DukeException {
        if (inputDesc.isEmpty()) {
            throw new DukeException(Constants.ERROR_INVALID_ARGUMENTS);
        }

        if (!Arrays.asList(inputDesc.split(" ")).contains(separator)) {
            throw new DukeException(String.format(Constants.ERROR_MISSING_SEPARATOR, separator));
        }

        String[] inputArray = Arrays.stream(inputDesc.split(separator))
                .map(String::trim)
                .filter(x -> !x.isBlank())
                .toArray(String[]::new);

        if (inputArray.length != 2) {
            throw new DukeException(Constants.ERROR_EMPTY_DESCRIPTION_TIME);
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