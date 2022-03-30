package Service;

import Model.User;

import java.util.List;

public interface UserService {
    User saveUser(User user);

    boolean saveUserProduct(int userId, int productId);

    User updateUser(User user);

    boolean removeUser(int id);

    User findUserById(int id);

    User findUserProductById(int id);

    List<User> findUsers();
}
