package student.anatoliy;

import java.util.ArrayList;
import java.util.Optional;

public interface UserRepository {
    Optional<User> findById(Long id);
    public User saveUser(User user);
}