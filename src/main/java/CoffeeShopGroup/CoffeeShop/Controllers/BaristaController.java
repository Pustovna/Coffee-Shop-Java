package CoffeeShopGroup.CoffeeShop.Controllers;

import CoffeeShopGroup.CoffeeShop.Models.Baristas;
import CoffeeShopGroup.CoffeeShop.Repo.BaristasRepository;
import java.util.ArrayList;
import java.util.Optional;
import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class BaristaController {
/*
	take an order from the client
	Differentiate between different kinds of clients – VIP and regular
	Change the order (if not already prepared)
	Record the table chosen (inside or outside (if it’s unoccupied) till the ordering (that is, after the seat is chosen and payment is processed, the table cannot be changed).
	Take a payment
	Etc.

*/
@Autowired
private BaristasRepository BaristasRepository;
    // подключение к репозиторию БД

    //------------------------------------------------------------------
/////////////////////////////         База Данных Барист
//  <<<--- получение данных
    @GetMapping("/admin_barista")//  ("/запрос")
    // Если нужен динамический параметр, то запрос должен его в себе содержать {id}

    //-> чтение базы
    //            НАЗВАНИЕ _метода (параметры, ... , модель)
    public String ALL_Barista (Model model)
    {
        //      -> Создание элементов данного типа для вывода отдельно по ID :
        Iterable<Baristas> Baristas = BaristasRepository.findAll();
        //      -> Функции
        model.addAttribute("Baristas",Baristas);    // тут мы получаем выбранный объект для передачи
        model.addAttribute("title", "Бариста");
        //      -> Возврат значений в виде (вставка в шаблон) :
        System.out.println("Данные отправлены --->>> " + getClass().getName()); // 'Baristas'
        System.out.println("==========================");
        return "admin_barista";
    }

    //------------------------------------------------------------------
    ////////////////////////////////     ID  Бариста
    //  <<<--- получение данных
    @GetMapping("/barista/{id}")//  ("/запрос")
    // Если нужен динамический параметр, то запрос должен его в себе содержать {id}
    //-> чтение базы

    //            НАЗВАНИЕ _метода     (параметры, ... , модель)
    public String INTERFACE_Barista_ID (@PathVariable(value = "id") long id,
                                        Model model) {
        //      -> Создание элементов данного типа для вывода отдельно по ID :
        Optional<Baristas> item = BaristasRepository.findById(id);
        //		-> Проверки (приведение к массиву, и др.)
        ArrayList <Baristas> res = new ArrayList<>();     // выделяем память (создаем массив)
        item.ifPresent(res::add);                         // синтаксис для перевода данных в массив
        model.addAttribute("Baristas",res);    // тут мы получаем выбранный объект для передачи
        model.addAttribute("title", "Бариста");
        //      -> Возврат значений в виде (вставка в шаблон) :
        //System.out.println(Name + ", " + LastName + ", " + Tel_number + ", " + Adress + ", " + id);
        System.out.println("Данные отправлены --->>> " + getClass().getName()); // 'Baristas'
        System.out.println("==========================");
        return "barista";
                                    }
    //------------------------------------------------------------------
    ////////////////////////////        Добавление Баристы
    // --->>> отправление данных
    @PostMapping("/admin_barista/add") //("/запрос")

            // Название метода-строки
    public String Barista_ADD
            // Запрос для статических параметров
         		(@RequestParam String Name,
                 @RequestParam String LastName,
                 @RequestParam String Phone,
                 @RequestParam String Adress,
                 @RequestParam String Email,
                 @RequestParam float Salary_ph,

                 Model model)
    {
        Boolean shift = false;
            //-> Создание/получение объекта из базы для записи параметров
        Baristas item = new Baristas(Name,LastName,Phone,Adress,Email,shift,Salary_ph) ;
            // -> Функции / присвоение необходимых значений (для уже созданных объектов)
        System.out.println("Данные получены <<<--- " + getClass().getName()); // 'Baristas'
            //-> Сохранение
        BaristasRepository.save(item);
            //-> Возврат пользователя в любое место программы
        return "redirect:/barista";
    }

//------------------------------------------------------------------
////////////////////////////////    Переход на управление заказами
//  <<<--- получение данных
@GetMapping("/barista_orders_and_tables")//  ("/запрос")
// Если нужен динамический параметр, то запрос должен его в себе содержать {id}
//-> чтение базы
//            НАЗВАНИЕ _метода         (параметры, ... , модель)
public String INTERFACE_Barista_Orders (//@PathVariable(value = "id") long id,
                                        Model model) {
    //      -> Создание элементов данного типа для вывода отдельно по ID :
    //Optional<Baristas> item = BaristasRepository.findById(id);
    //		-> Проверки (приведение к массиву, и др.)
    //ArrayList <Baristas> res = new ArrayList<>();     // выделяем память (создаем массив)
    //item.ifPresent(res::add);                         // синтаксис для перевода данных в массив
    //model.addAttribute("Baristas",res);    // тут мы получаем выбранный объект для передачи
    //model.addAttribute("title", "Бариста");
    //      -> Возврат значений в виде (вставка в шаблон) :
    //System.out.println(Name + ", " + LastName + ", " + Tel_number + ", " + Adress + ", " + id);
    //System.out.println("Данные отправлены --->>> " + getClass().getName()); // 'Baristas'
    //System.out.println("==========================");
    return "barista_Order_And_Tables";
}
}
