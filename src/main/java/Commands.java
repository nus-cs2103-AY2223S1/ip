public enum Commands {
    BYE {
        @Override
        public String toString() {
            return "bye";
        }
    },
    TODO {
        @Override
        public String toString() {
            return TaskTypes.TODO.toString();
        }
    },
    EVENT {
        @Override
        public String toString() {
            return TaskTypes.EVENT.toString();
        }
    },
    DEADLINE {
        @Override
        public String toString() {
            return TaskTypes.DEADLINE.toString();
        }
    },
    LIST {
        @Override
        public String toString() {
            return "list";
        }
    },
    MARK {
        @Override
        public String toString() {
            return "mark";
        }
    },
    UNMARK {
        @Override
        public String toString() {
            return "unmark";
        }
    },
    DELETE {
        @Override
        public String toString() {
            return "delete";
        }
    },
}
