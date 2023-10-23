package CoffeeShopGroup.CoffeeShop.service.impl;

import CoffeeShopGroup.CoffeeShop.Models.Clients;
import CoffeeShopGroup.CoffeeShop.Repo.ClientsRepository;
import lombok.extern.slf4j.Slf4j;
import CoffeeShopGroup.CoffeeShop.Models.Role;
import CoffeeShopGroup.CoffeeShop.Models.Status;
import CoffeeShopGroup.CoffeeShop.Models.User;
import CoffeeShopGroup.CoffeeShop.Repo.RoleRepository;
import CoffeeShopGroup.CoffeeShop.Repo.UserRepository;
import CoffeeShopGroup.CoffeeShop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Implementation of {@link UserService} interface.
 * Wrapper for {@link UserRepository} + business logic.
 *
 * @author Eugene Suleimanov
 * @version 1.0
 */

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    @Autowired
    private ClientsRepository ClientsRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User register(User user) {
        Role roleUser = roleRepository.findByName("ROLE_USER");
        List<Role> userRoles = new ArrayList<>();
        userRoles.add(roleUser);

        user.setRoles(userRoles);
        user.setStatus(Status.ACTIVE);

        User registeredUser = userRepository.save(user);

        Long user_id = registeredUser.getId();

        /* Запись в таблицу Clients */
        Clients client = new Clients(user.getFirstName(),user.getLastName(),"","",user.getEmail(),false, user_id);
        ClientsRepository.save(client);
        /* ---------------------- */

        log.info("IN register - user: {} successfully registered", registeredUser);

        return registeredUser;
    }

    @Override
    public List<User> getAll() {
        List<User> result = userRepository.findAll();
        log.info("IN getAll - {} users found", result.size());
        return result;
    }

    @Override
    public User findByUsername(String username) {
        User result = userRepository.findByUsername(username);
        log.info("IN findByUsername - user: {} found by username: {}", result, username);
        return result;
    }

    @Override
    public User findById(Long id) {
        User result = userRepository.findById(id).orElse(null);

        if (result == null) {
            log.warn("IN findById - no user found by id: {}", id);
            return null;
        }

        log.info("IN findById - user: {} found by id: {}", result);
        return result;
    }

    @Override
    public void delete(Long id) {
        userRepository.deleteById(id);
        log.info("IN delete - user with id: {} successfully deleted");
    }
}
