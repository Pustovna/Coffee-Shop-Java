package CoffeeShopGroup.CoffeeShop.Controllers;

import CoffeeShopGroup.CoffeeShop.Models.Clients;
import CoffeeShopGroup.CoffeeShop.Models.Role;
import CoffeeShopGroup.CoffeeShop.Models.User;
import CoffeeShopGroup.CoffeeShop.Repo.ClientsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import CoffeeShopGroup.CoffeeShop.Repo.UserRepository;
import org.springframework.web.util.WebUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Controller
public class MainController {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ClientsRepository clientsRepository;

    @GetMapping("/")
    public String empty(Model model) {
        model.addAttribute("title", "Главная страница");
        System.out.println("==========================");
        return "main";
    }

    @GetMapping("/login")
    public String login(Model model) {
        model.addAttribute("title", "Главная страница");
        System.out.println("==========================");
        return "login";
    }

    @GetMapping("/main")
    public String main(HttpServletRequest req, Model model) {
        model.addAttribute("title", "Главная страница");


        /* получаем из кук name пользователя, ищем его роль */
        Cookie cookies = WebUtils.getCookie(req, "user");
        String name = "";
        if (cookies != null) {
            name = cookies.getValue();
        }

        User item = userRepository.findByUsername(name);
        List<Role> userRole = item.getRoles();
        String role = userRole.get(0).getName();
        model.addAttribute("role", role);


        // Если к нам зашёл пользователь с ролью USER, вытаскиваем его id из таблицы Clients
        if (Objects.equals(role, "ROLE_USER")) {
            Long userId = item.getId();
            System.out.println(userId);
            Clients client = clientsRepository.findByUserId(userId);

            Long clientId = client.getId();
            model.addAttribute("id", clientId);
        }
        //-----------------------------------------------------------------------------------

        System.out.println("==========================");
        return "main";
    }

    @GetMapping("/admin")
    public String InterfaceAdmin(Model model) {
        model.addAttribute("title", "Администратор");
        System.out.println("==========================");
        return "admin";
    }

    @GetMapping("/client")
    public String InterfaceClient(Model model) {
        model.addAttribute("title", "Клиент");
        System.out.println("==========================");
        return "client";
    }

}
