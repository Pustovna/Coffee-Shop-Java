package CoffeeShopGroup.CoffeeShop.Controllers;


import CoffeeShopGroup.CoffeeShop.Models.*;
import CoffeeShopGroup.CoffeeShop.Repo.*;
import CoffeeShopGroup.CoffeeShop.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.util.WebUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.*;


@Controller
public class ClientController {

    @Autowired
    private MenuRepository MenuRepository;
    @Autowired
    private ClientsRepository ClientsRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private OrdersRepository OrdersRepository;
    @Autowired
    private CoffeeShopRepository CoffeeShopRepository;

    @Autowired
    private OrderService orderService;

    // подключение к репозиторию БД

/*
	make an order from the menu list
	Change the order (if not already prepared)
	Choose a seat (if it’s unoccupied)
    till the ordering (that is, after the table is chosen and
    payment is processed, the table cannot be changed).
	Can have a VIP membership card,
    which allows for a free coffee after 10 cups of coffee bought.
	Make a payment
	Etc.
 */
    //------------------------------------------------------------------
/////////////////////////////         База Данных Клиентов
//  <<<--- получение данных
    @GetMapping("/admin_clients")//  ("/запрос")
    // Если нужен динамический параметр, то запрос должен его в себе содержать {id}
    //-> чтение базы
    //            НАЗВАНИЕ _метода (параметры, ... , модель)
    public String ALL_Clients (Model model)
    {
        //      -> Создание элементов данного типа для вывода отдельно по ID :
        Iterable<Clients> Clients = ClientsRepository.findAll();
        //      -> Функции
        model.addAttribute("CLIENTS",Clients);            // тут мы получаем выбранный объект для передачи
        model.addAttribute("title", "Клиенты");
        //      -> Возврат значений в виде (вставка в шаблон) :
        System.out.println("Данные отправлены ALL_Clients --->>> " + getClass().getName()); // 'Clients'
        System.out.println("==========================");
        return "admin_Clients";
    }
    //------------------------------------------------------------------
    ////////////////////////////////     ID  Клиента
    //  <<<--- получение данных
    @GetMapping("/client/{id}")//  ("/запрос")
    // Если нужен динамический параметр, то запрос должен его в себе содержать {id}
    //-> чтение базы

    //            НАЗВАНИЕ _метода     (параметры, ... , модель)
    public String INTERFACE_Client_ID (@PathVariable(value = "id") long id,
                                        Model model) {

        Optional<Clients> item = ClientsRepository.findById(id);
        //Переведем объекты в массив
        ArrayList<Clients> res = new ArrayList<>();                //выделяем память
        item.ifPresent(res::add);                                  // синтаксис для перевода данных в массив
        model.addAttribute("Clients", res);            // тут мы получаем выбранный объект по ID и его будем передавать в шаблон


        //System.out.println("-----  *  тут все нормально  *  ------");

        Iterable<Menu> Menu = MenuRepository.findAll();
//        Iterable<Menu> Menu = MenuRepository.findAll();                  // тут все функции из CRUDrepository



        // получение заказов
        Iterable <CoffeeShop> orders = CoffeeShopRepository.findByIdClient(id);

        ArrayList <OrderWithItem> orderWithItems = orderService.getOrdersInfo(orders);

        model.addAttribute("orderWithItems",orderWithItems);

        //-----------------------------

        if (!ClientsRepository.existsById(id)) {
            return "main";
        }

        //      -> Возврат значений в виде (вставка в шаблон) :
                System.out.println("Данные отправлены INTERFACE_Client_ID --->>> " + getClass().getName());
                System.out.println("==========================");
        return "client_details";
    }
    //------------------------------------------------------------------
    @GetMapping("client_add") //("/запрос")
    // Название метода-строки
    public String Client_Registration_FORM (Model model){
        model.addAttribute("title", "Новый Клиент");
        return "client_Registration";
    }

    //------------------------------------------------------------------
    ////////////////////////////        Добавление Клиента
    // --->>> отправление данных
    @PostMapping("client_add") //("/запрос")

    // Название метода-строки

    public String Client_ADD
                            // Запрос для статических параметров
                            (@RequestParam String Name,
                             @RequestParam String LastName,
                             @RequestParam String Phone,
                             @RequestParam String Adress,
                             @RequestParam String Email,
                             //@RequestParam Boolean status,
                             //@RequestParam MyDate Birthday,
                             Model model)
    {
                Boolean status = false;
        //-> Создание/получение объекта из базы для записи параметров
                Clients item = new Clients(Name,LastName,Phone,Adress,Email,status, null);//,Birthday) ;

        // -> Функции / присвоение необходимых значений (для уже созданных объектов)
                System.out.println("Данные получены <<<--- Создан новый клиент  " + getClass().getName());
        //-> Сохранение
                ClientsRepository.save(item);
        //-> Возврат пользователя в любое место программы
        return "redirect:/admin_clients";
    }

    //-------------------------------------------Показываем Страницу----------------------------------------------------
    ////////////////////////////        Поиск одного элемента БД     ----+---
    //                                             + детали
    @GetMapping("/Clients/{id}/details")                 //Здесь указываем динамический динамический параметр(можно и не один)

    public String CLIENT_Element (@PathVariable(value = "id") long id, Model model) {
                Optional<Clients> item = ClientsRepository.findById(id);
        //Переведем объекты в массив
                ArrayList<Clients> res = new ArrayList<>();                //выделяем память
                item.ifPresent(res::add);                                  // синтаксис для перевода данных в массив
                model.addAttribute("Clients", res);            // тут мы получаем выбранный объект по ID и его будем передавать в шаблон


        //System.out.println("-----  *  тут все нормально  *  ------");

        Iterable<Menu> Menu = MenuRepository.findAll();
//        Iterable<Menu> Menu = MenuRepository.findAll();                  // тут все функции из CRUDrepository
        model.addAttribute("Menu",Menu);



                if (!ClientsRepository.existsById(id)) {
                    return "admin_menu";
                } // исключение, если такого элемента нет - просто возвращаемся

        return "client";
    }

    //-----------------------------------------------------------------------------------------------


    @GetMapping("/Clients/{id}/edit")
    public String CLIENTS_Edition (@PathVariable(value = "id") long id, Model model){  //Здесь указываем динамический динамический параметр(можно и не один)

        Optional<Clients> item = ClientsRepository.findById(id) ;                  // тут все функции из CRUDrepository
        System.out.println("Entering to editor");
        //Переведем объекты в массив
        ArrayList <Clients> res = new ArrayList<>();     //выделяем память
        item.ifPresent(res::add);                    // синтаксис для перевода данных в массив
        model.addAttribute("Clients",res);  // тут мы получаем выбранный объект по ID и его будем передавать в шаблон
        return "/client";
    }
    @PostMapping("/Clients/{id}/edit")
    public String CLIENTS_Update   (HttpServletRequest req, @PathVariable(value = "id")          //Здесь указываем динамический динамический параметр(можно и не один)
                                    long id,
                                    @RequestParam String Name,
                                    @RequestParam String LastName,
                                    @RequestParam String Phone,
                                    @RequestParam String Adress,
                                    @RequestParam String Email,
                                    @RequestParam Boolean status,
                                    //@RequestParam MyDate Birthday,
                                    Model model)

    {
                System.out.println("Entering to PostMapping");

                Clients item = ClientsRepository.findById(id).orElseThrow() ;
                item.setName(Name);
                item.setLastName(LastName);
                item.setPhone(Phone);
                item.setAdress(Adress);
                item.setEmail(Email);
                item.setStatus(status);
                ClientsRepository.save(item);                              //Сохраняем новый объект класса
                                                                          //Сохраняем данные

        Cookie cookies = WebUtils.getCookie(req, "user");
        String name = "";
        if (cookies != null) {
            name = cookies.getValue();
        }

        User user = userRepository.findByUsername(name);
        List<Role> userRole = user.getRoles();
        String role = userRole.get(0).getName();

        if (Objects.equals(role, "ROLE_USER")) {
            return "redirect:/client/" + id;
        } else {
            return "redirect:/admin_clients";
        }
        //Направляем на исходную страницу
    }


    @GetMapping("/Clients/{id}/remove")
    public String Client_Remove (@PathVariable(value = "id")          //Здесь указываем динамический динамический параметр(можно и не один)
                                    long id,
                                    Model model)
    {
        Clients Client = ClientsRepository.findById(id).orElseThrow();
        ClientsRepository.delete(Client);
        return "redirect:/Clients";                                       //Направляем на исходную страницу
    }
//
}


