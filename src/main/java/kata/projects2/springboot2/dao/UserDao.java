package kata.projects2.springboot2.dao;



import kata.projects2.springboot2.model.User;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface UserDao {
    List<User> showUsers();

    public void addUser(User user);

    public User findUserById(int id);

    public void update(int id, User updatedUser);

    public void delete(int id);
}
