package student.anatoliy;

public class IllegalPasswordException extends RuntimeException {
  public IllegalPasswordException(String message) {
    super(message);
  }
}
