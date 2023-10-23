package CoffeeShopGroup.CoffeeShop.Controllers;

import CoffeeShopGroup.CoffeeShop.Models.CoffeeShop;
import CoffeeShopGroup.CoffeeShop.Repo.CoffeeShopRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CoffeeShopController {

    @Autowired
    private CoffeeShopRepository CoffeeShopRepository;

    //------------------------------------------------------------------
/////////////////////////////         База Данных Заказов
    //  <<<--- вывод данных в представлении
    @GetMapping("/orders_CoffeeShop")//  ("/запрос")
    // Если нужен динамический параметр, то запрос должен его в себе содержать {id}
    //-> чтение базы
    //            НАЗВАНИЕ_метода (параметры, ... , модель)
    public String ALL_Orders (Model model)
    {
        //      -> Создание элементов данного типа для вывода всех ( по ID) :
        Iterable<CoffeeShop> CoffeeShop = CoffeeShopRepository.findAll();

        //      -> Функции
        model.addAttribute("id",CoffeeShop);            // тут мы получаем выбранный объект для передачи
        //model.addAttribute("title", "Заказы");

        //      -> Возврат значений в виде (вставка в шаблон) :
        System.out.println("Данные отправлены ALL_Orders CoffeeShop--->>> " + getClass().getName());
        System.out.println("==========================");
        return "barista_Order_And_Tables";
    }

}
