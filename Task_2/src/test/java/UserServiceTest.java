import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import student.anatoliy.IllegalPasswordException;
import student.anatoliy.User;
import student.anatoliy.UserRepository;
import student.anatoliy.UserService;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {
    @Mock
    private UserRepository mockedRepository;

    @InjectMocks
    private UserService userService;

    @BeforeEach
    public void setUp() {
        System.out.println("\ntest started:");
    }

    @AfterEach
    public void tearDown() {
        System.out.println("test ended");
    }

    @Test
    public void TestFindById_Positive() {
        User expectedUser = new User(1L, "Sam", "Sam'sPassword");
        when(mockedRepository.findById(1L)).thenReturn(Optional.of(expectedUser));

        User actualUser = userService.findById(1L);

        assertNotNull(actualUser);
        assertEquals("Sam", actualUser.name());

        verify(mockedRepository, times(1)).findById(1L);
    }

    @Test
    public void TestRegisterUser_Positive() {
        String name = "John";
        String password = "John'sPassword";
        User user = new User(null, name, password);
        when(mockedRepository.saveUser(user)).thenReturn(user);

        User actualUser = userService.registerUser(name, password);

        assertNotNull(actualUser);
        assertEquals(actualUser, user);

        verify(mockedRepository).saveUser(any(User.class));
    }

    @Test
    public void TestRegisterUser_Negative() {
        String name = "Rick";
        String password = "Rick'sPassw o r d";

        assertThrows(IllegalPasswordException.class, () -> userService.registerUser(name, password));

        verify(mockedRepository, never()).saveUser(any(User.class));
    }

    @Test
    public void TestWithFakeRepository_Positive() {
        UserRepository fakeRepository = new UserRepository() {
            private final Map<Long, User> db = new HashMap<>();
            private Long idCounter = 1L;

            @Override
            public Optional<User> findById(Long id) {
                return Optional.of(db.get(id));
            }

            @Override
            public User saveUser(User user) {
                User savedUser = new User(idCounter++, user.name(), user.password());
                db.put(savedUser.id(), savedUser);
                return savedUser;
            }
        };
        UserService userServiceWithFakeRepository = new UserService(fakeRepository);
        String name = "Abigail";
        String password = "LegalPassword";
        User createdUser = userServiceWithFakeRepository.registerUser(name, password);

        assertEquals(1L, createdUser.id());
        assertEquals(name, createdUser.name());
        assertEquals(password, createdUser.password());
    }
}
