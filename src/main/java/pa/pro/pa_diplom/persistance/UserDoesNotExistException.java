package pa.pro.pa_diplom.persistance;

/**
 * Indicates that users does not exists in the DB
 */
public class UserDoesNotExistException extends RuntimeException {

    public UserDoesNotExistException(String s) {
        super(s);
    }
}
