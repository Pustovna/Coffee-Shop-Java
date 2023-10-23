package CoffeeShopGroup.CoffeeShop.Controllers;

import CoffeeShopGroup.CoffeeShop.Models.Menu;
import CoffeeShopGroup.CoffeeShop.Repo.MenuRepository;
import java.util.ArrayList;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class MenuController {
/*
Coffee shop:
	has a list of items from the menu with their description, image, price and availability
	has an age limitation (if it sells alcoholic drinks)
	a menu list can be displayed according to
o	price increase
o	price decrease
o	most popular
o	a drink/dish of the day
	Menu can be updated according to a specific date/category/time/price range/table location (outside/inside)
	Menu items list can be filtered to show only items with the decreased price (business lunch)
	Different clients can be seated at the same time but in different tables
	Manages tables according the number of people (e.g., a table for 2/4/ etc.)
	Some items can be available only at has a time (business lunch) and date (closed party).
	Outside tables can be unavailable at certain days.
	VIP members have special deals (e.g., a free cup of coffee after the 10th cup).
	Etc.
 */
    @Autowired
    private MenuRepository MenuRepository;
//-------------------------------------------Показываем Страницу----------------------------------------------------
    @GetMapping("/menu")
    public String ALL_Menu (Model model){

        //System.out.println("-----  *  тут все нормально  *  ------");
        Iterable<Menu> Items = MenuRepository.findAll();                  // тут все функции из CRUDrepository
        model.addAttribute("Items",Items);
        return "/menu";
    }

//-------------------------------------------Показываем Страницу----------------------------------------------------
   @GetMapping("/admin_menu")                                            // Наименование запроса
   public String ADMIN_Menu_R (Model model){
       Iterable<Menu> Items = MenuRepository.findAll();      //находим все элементы меню            // тут все функции из CRUDrepository
       model.addAttribute("Items",Items);
        return "admin_menu";                                 // Вызов представления
    }

//-------------------------------------------Принимаем данные----------------------------------------------------
    @PostMapping("/admin_menu")
    public String Menu_ADD (@RequestParam String title,      // Запрашиваем даннные из шаблона
                           @RequestParam String full_text,
                           @RequestParam int price,
                           Model model)
    {
        Menu item = new Menu(title,full_text,price);             // ! не забыть написать конструктор в Классе
        MenuRepository.save(item);                               //Сохраняем новый объект класса
                                                                 // ID присваивается автоматически                              //Сохраняем данные
        return "redirect:/admin_menu";                                 //Направляем на исходную страницу
    }

    //System.out.println("-----  *  тут все нормально  *  ------");
    //System.out.println("-----  **  тут все нормально  **  ------");

    //-------------------------------------------Показываем Страницу----------------------------------------------------
    @GetMapping("/admin_menu/{id}/details")                 //Здесь указываем динамический динамический параметр(можно и не один)
    public String MENU_Element (@PathVariable(value = "id") long id, Model model){
        Optional<Menu> item = MenuRepository.findById(id) ;
                                                                //Переведем объекты в массив
        ArrayList <Menu> res = new ArrayList<>();               //выделяем память
        item.ifPresent(res::add);                               // синтаксис для перевода данных в массив
        model.addAttribute("item",res);             // тут мы получаем выбранный объект по ID и его будем передавать в шаблон

        if (!MenuRepository.existsById(id)){return "admin_menu";}// исключение, если такого элемента нет - просто возвращаемся

        return "menu_details";
    }
    //-------------------------------------------Показываем Страницу----------------------------------------------------

    @GetMapping("/admin_menu/{id}/edit")
    public String MENU_Edition (@PathVariable(value = "id") long id, Model model){  //Здесь указываем динамический динамический параметр(можно и не один)

        Optional<Menu> item = MenuRepository.findById(id) ;                  // тут все функции из CRUDrepository
        System.out.println("Entering to editor");
                                                      //Переведем объекты в массив
        ArrayList <Menu> res = new ArrayList<>();     //выделяем память
        item.ifPresent(res::add);                    // синтаксис для перевода данных в массив
        model.addAttribute("item",res);  // тут мы получаем выбранный объект по ID и его будем передавать в шаблон
        return "admin_menu_edit";
    }
    //-------------------------------------------Принимаем данные----------------------------------------------------
    @PostMapping("/admin_menu/{id}/edit")
    public String MENU_Item_Update (@PathVariable(value = "id")          //Здесь указываем динамический динамический параметр(можно и не один)
                                        long id,
                                        @RequestParam String title,      // Запрашиваем даннные из шаблона
                                        @RequestParam String full_text,
                                        @RequestParam int price,
                                        Model model)

     {
         System.out.println("Entering to PostMapping");
        //System.out.println("-----  *  тут все нормально  *  ------");
        Menu item = MenuRepository.findById(id).orElseThrow();
        item.setTitle(title);
        item.setFull_text(full_text);
        item.setPrice(price);

        MenuRepository.save(item);                                        //Сохраняем новый объект класса
        // ID присваивается автоматически                              //Сохраняем данные
        return "redirect:/admin_menu";                                       //Направляем на исходную страницу
    }

    @GetMapping("/admin_menu/{id}/remove")
    public String MENU_Item_Remove (@PathVariable(value = "id")          //Здесь указываем динамический динамический параметр(можно и не один)
                                    long id,
                                    Model model)
    {
        Menu item = MenuRepository.findById(id).orElseThrow();
        MenuRepository.delete(item);
        return "redirect:/admin_menu";                                       //Направляем на исходную страницу
    }



}
