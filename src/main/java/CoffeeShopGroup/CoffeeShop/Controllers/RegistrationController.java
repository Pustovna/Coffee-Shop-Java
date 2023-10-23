package CoffeeShopGroup.CoffeeShop.Controllers;


import CoffeeShopGroup.CoffeeShop.Models.Clients;
import CoffeeShopGroup.CoffeeShop.Models.Status;
import CoffeeShopGroup.CoffeeShop.Models.Role;
import CoffeeShopGroup.CoffeeShop.Repo.RoleRepository;
import CoffeeShopGroup.CoffeeShop.service.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import CoffeeShopGroup.CoffeeShop.Models.User;
import CoffeeShopGroup.CoffeeShop.Repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.*;

@Controller
public class RegistrationController {
    @Autowired
    private  UserService userService;

    @Autowired
    private UserRepository UserRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        return bCryptPasswordEncoder;
    }
    @GetMapping("/registration")
    public String registration() {
        return "registration";
    }

    @PostMapping("/registration")
    public String add_user(@RequestParam(name = "username") String username,
                           @RequestParam(name = "password") String password,
                           @RequestParam(name = "email") String email
                                            ) {
//        User userFromDb = UserRepo.findByUsername(user.getUsername());
//        User item = new User(username, password);
//        Boolean status = false;
////        if (userFromDb != null) {
////            model.put("message", "User exists!");
////            return "registration";
////        }
//
//        item.setActive(true);
//        item.setRoles("USER");
//        UserRepo.save(item);
//
//        System.out.println("Отправили в ДБ" + item.getUsername());

//        Role roleUser = roleRepository.findByName("ROLE_USER");
//        List<Role> userRoles = new ArrayList<>();
//        userRoles.add(roleUser);

        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(password);


        User newUser = new User();
        newUser.setPassword(encodedPassword);
        newUser.setUsername(username);
        newUser.setFirstName("");
        newUser.setLastName("");
        newUser.setEmail(email);
//        newUser.setRoles(userRoles);
        newUser.setCreated(new Date());
        newUser.setUpdated(new Date());
//        newUser.setStatus(Status.ACTIVE);
//        UserRepository.save(newUser);



        User user =  userService.register(newUser);

//        if (user != null) {
//            return "/menu";
//        }
//
//        UserDto result = UserDto.fromUser(user);
//        System.out.println(result);
        return "/login";
    }
}

