package CoffeeShopGroup.CoffeeShop.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminController {
/*
Admin is responsible for:
	adding/removing different kinds of coffees          | at menuController
	Adding/removing items to the menu                   | at menuController
	managing the prices                                 | at menuController
	managing the sitting places (inside and outside)    | at tableController
	managing the number of seats in each room
	etc.

 */

    //-------------------------------------------Показываем Страницу----------------------------------------------------
    //@GetMapping("/admin_menu")                                            // Наименование запроса
    //public String Admin_Menu (Model model){return "admin_menu";}          // Вызов представления

    //-------------------------------------------Показываем Страницу----------------------------------------------------
    //@GetMapping("/admin_barista")                                            // Наименование запроса
   // public String Admin_Barista (Model model){return "admin_barista";}       // Вызов представления

    //-------------------------------------------Показываем Страницу----------------------------------------------------
    @GetMapping("/admin_tables")                                            // Наименование запроса
    public String Admin_Tables (Model model){return "admin_tables";}       // Вызов представления
    //-------------------------------------------Показываем Страницу----------------------------------------------------
    //@GetMapping("/admin_clients")                                            // Наименование запроса
    //public String Admin_Clients (Model model){return "admin_Clients";}       // Вызов представления
}
