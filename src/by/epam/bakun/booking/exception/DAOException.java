package by.epam.bakun.booking.exception;

public class DAOException extends RuntimeException {
        private final int id;

        public DAOException(String message, int id) {
            super(message);
            this.id = id;
        }

        public int getId() {
            return id;
        }
}
