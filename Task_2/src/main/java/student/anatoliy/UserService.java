package student.anatoliy;

import java.util.Objects;

public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User findById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("User not found by id: " + id));
    }

    public User registerUser(String name, String password) {
        if (isPasswordValid(password)) {
            User user = new User(null, name, password);
            return userRepository.saveUser(user);
        } else throw new IllegalPasswordException("Something went wrong with password: " + password);
    }

    public static boolean isPasswordValid(String password) {
        Objects.requireNonNull(password);
        if (password.length() < 8 || password.trim().isEmpty()) {
            throw new IllegalPasswordException("Password should contain at least 8 symbols | input password: [" + password + "]");
        }
        if (password.contains(" ")) {
            throw new IllegalPasswordException("Password can`t contain spaces | input password: [" + password + "]");
        }
        return true;
    }
}
