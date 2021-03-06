package user;

import org.jdbi.v3.core.Handle;
import org.jdbi.v3.core.Jdbi;
import org.jdbi.v3.sqlobject.SqlObjectPlugin;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public class Main implements UserDao {
    public static void main(String[] args) {

        Jdbi jdbi = Jdbi.create("jdbc:h2:mem:test");
        jdbi.installPlugin(new SqlObjectPlugin());
        try (Handle handle = jdbi.open()) {
            UserDao dao = handle.attach(UserDao.class);
            dao.createTable();

            User user1 = User.builder()
                    .id(1L)
                    .username("ttdoni99")
                    .password("Jelszó")
                    .name("Donát")
                    .email("ttdoni99@gmail.com")
                    .gender(User.Gender.MALE)
                    .dob(LocalDate.parse("1999-07-23"))
                    .enabled(true)
                    .build();

            User user2 = User.builder()
                    .id(2L)
                    .username("ttdoni99")
                    .password("Jelsző")
                    .name("Donát")
                    .email("ttdoni99@gmail.com")
                    .gender(User.Gender.MALE)
                    .dob(LocalDate.parse("1999-07-23"))
                    .enabled(true)
                    .build();

            dao.insert(user1);
            dao.insert(user2);
            dao.insert(3L, "eXampLe", "PaSsWoRd", "Mr. Example", "example@anotherexample.com", User.Gender.FEMALE, LocalDate.parse("1923-05-30"), true);
            System.out.println("Find by ID: " + dao.findById(1L));
            System.out.println("Find by Username: " + dao.findByUsername("eXampLe"));
            dao.list().stream().forEach(System.out::println);
            dao.delete(user2);
            System.out.println("-------------");
            dao.list().stream().forEach(System.out::println);
            List<User> list = dao.list();
            System.out.println( list.get(0).getId() + " " + list.get(0).getUsername() + " " + list.get(0).getPassword() + " " + list.get(0).getEmail() + " " + list.get(0).getGender() + " " + list.get(0).getDob().toString());
        }
    }

    @Override
    public void createTable() {

    }

    @Override
    public Long insert(Long id, String username, String password, String name, String email, User.Gender gender, LocalDate dob, boolean enabled) {
        return id;
    }

    @Override
    public Long insert(User user) {
        return user.getId();
    }

    @Override
    public Optional<User> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public Optional<User> findByUsername(String username) {
        return Optional.empty();
    }

    @Override
    public void delete(User user) {

    }

    @Override
    public List<User> list() {
        return null;
    }
}
