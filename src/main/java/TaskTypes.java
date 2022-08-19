public enum TaskTypes {
    TODO {
        @Override
        public String toString() {
            return "todo";
        }
    },
    EVENT {
        @Override
        public String toString() {
            return "event";
        }
    },
    DEADLINE {
        @Override
        public String toString() {
            return "deadline";
        }
    }
}
