package ServiceImpl;

import Implement.UserRepositoryImpl;
import Model.User;
import Repository.UserRepository;
import Service.UserService;

import java.util.List;

public class UserServiceImpl implements UserService {
    private UserRepository userRepository = new UserRepositoryImpl();

    @Override
    public User saveUser(User user) {
        return userRepository.saveUser(user);
    }

    @Override
    public boolean saveUserProduct(int userId, int productId) {
        return userRepository.saveUserProduct(userId,productId);
    }

    @Override
    public User updateUser(User user) {
        return userRepository.updateUser(user);
    }

    @Override
    public boolean removeUser(int id) {
        return userRepository.removeUser(id);
    }

    @Override
    public User findUserById(int id) {
        return userRepository.findUserById(id);
    }

    @Override
    public User findUserProductById(int id) {
        return userRepository.findUserById(id);
    }

    @Override
    public List<User> findUsers() {
        return userRepository.findUsers();
    }
}
