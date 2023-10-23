package CoffeeShopGroup.CoffeeShop.Controllers;


import CoffeeShopGroup.CoffeeShop.Models.Baristas;
import CoffeeShopGroup.CoffeeShop.Models.CoffeeShop;
import CoffeeShopGroup.CoffeeShop.Models.Orders;
import CoffeeShopGroup.CoffeeShop.Repo.OrdersRepository;
import CoffeeShopGroup.CoffeeShop.Repo.CoffeeShopRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class OrderController extends CoffeeShop {

    @Autowired
    private OrdersRepository OrdersRepository;

    @Autowired
    private CoffeeShopRepository CoffeeShopRepository;

    // подключение к репозиторию БД
    /*
	Clients can choose an item for the menu and order it. The intermediate prices should be displayed and updated all the time.
	Show occupied/unoccupied tables (inside and outside)
	A table can be changed only before pressing “confirm” button in payment section.
	It’s impossible to choose an occupied seat
	It’s impossible to order an item which is unavailable (although it is still in the menu)
	Registered/unregistered users can make an order (that is the barista can ask for a VIP card)
	If a menu item has a price decrease (business lunch), show the strikethrough previous price, and a new price.
 */
    //------------------------------------------------------------------
/////////////////////////////         База Данных Заказов
        //  <<<--- вывод данных в представлении
    @GetMapping("/orders")//  ("/запрос")
    // Если нужен динамический параметр, то запрос должен его в себе содержать {id}
    //-> чтение базы
    //            НАЗВАНИЕ_метода (параметры, ... , модель)
    public String ALL_Orders (Model model)
    {
        //      -> Создание элементов данного типа для вывода всех ( по ID) :
                Iterable<Orders> Orders = OrdersRepository.findAll();

        //      -> Функции
                model.addAttribute("id",Orders);            // тут мы получаем выбранный объект для передачи
                //model.addAttribute("title", "Заказы");

        //      -> Возврат значений в виде (вставка в шаблон) :
                System.out.println("Данные отправлены ALL_Orders --->>> " + getClass().getName());
                System.out.println("==========================");
        return "barista_Order_And_Tables";
    }

    //------------------------------------------------------------------
    ////////////////////////////        Добавление Заказа
    // --->>> отправление данных

    @PostMapping("/menu") //("/запрос")

    public String order_ADD
                // Запрос для статических параметров
                (
                        @RequestParam long [] id_product,
                        @RequestParam int [] quantity,
                        @RequestParam Long id_client,
                        Model model)
                {
        System.out.println("Данные получены <<<--- Создаётся новый заказ ");
/*
при создании заказа мы

(А) в заказах
1. создаем новый номер заказа
2. записываем с этим номером все продукты и их количество

(Б) в реестре (кофешоп)
1. добавляем номера позиций
2. присваеем (стол и баристу)

 */
                    CoffeeShop position = new CoffeeShop(id_client);
                    CoffeeShopRepository.save(position);    //-> Сохранение



//        if(id_Order==null) id_Order= Long.valueOf(1);
//        else    id_Order++;

        //-> Создание/получение объекта из базы для записи параметров
        for(int i=0;i<=id_product.length-1;i++) {
            Orders Position = new Orders(  //id,
                                    id_product[i],
                                    quantity[i],
                                    position.getId_Order(),
                                    id_client
                                    );

            OrdersRepository.save(Position);    //-> Сохранение

            System.out.println(
                    "  Id_item: "   + Position.getId() +
                    ". Id_product: " + Position.getId_product() +
                    ", Quantity "    + Position.getQuantity() +
                    " \n" );



        }

        //-> Возврат пользователя в любое место программы
        return "redirect:/client_Payment";//
    }

}
