package student.anatoliy;

import student.anatoliy.exceptions.IllegalPasswordException;

import java.util.Objects;

public class PasswordPolicy {
    private int passwordHash;


    public void setPassword(String password) {
        if (PasswordPolicy.checksIfValid(password)) {
            this.passwordHash = password.hashCode();
        } else {
            System.out.println("Something went wrong ;(, password was not saved");
        }
    }

    public boolean compare(String password) {
        if (PasswordPolicy.checksIfValid(password) && password.hashCode() == passwordHash) {
            return true;
        } else return false;
    }

    public static boolean checksIfValid(String password) {
        Objects.requireNonNull(password);
        if (password.length() < 8 || password.trim().isEmpty()) {
            throw new IllegalPasswordException("Password should contain at least 8 symbols | input password: ["+password+"]");
        }
        if (password.contains(" ")) {
            throw new IllegalPasswordException("Password can`t contain spaces | input password: ["+password+"]");
        }
        return true;
    }
}
