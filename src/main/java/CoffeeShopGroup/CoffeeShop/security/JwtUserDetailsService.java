package CoffeeShopGroup.CoffeeShop.security;

import lombok.extern.slf4j.Slf4j;
import CoffeeShopGroup.CoffeeShop.Models.User;
import CoffeeShopGroup.CoffeeShop.security.jwt.JwtUser;
import CoffeeShopGroup.CoffeeShop.security.jwt.JwtUserFactory;
import CoffeeShopGroup.CoffeeShop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * Implementation of {@link UserDetailsService} interface for {@link JwtUser}.
 *
 * @author Eugene Suleimanov
 * @version 1.0
 */

@Service
@Slf4j
public class JwtUserDetailsService implements UserDetailsService {

    private final UserService userService;

    @Autowired
    public JwtUserDetailsService(UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userService.findByUsername(username);

        if (user == null) {
            throw new UsernameNotFoundException("User with username: " + username + " not found");
        }

        JwtUser jwtUser = JwtUserFactory.create(user);
        log.info("IN loadUserByUsername - user with username: {} successfully loaded", username);
        return jwtUser;
    }
}