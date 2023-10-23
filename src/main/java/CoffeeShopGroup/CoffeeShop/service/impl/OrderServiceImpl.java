package CoffeeShopGroup.CoffeeShop.service.impl;

import CoffeeShopGroup.CoffeeShop.Models.*;
import CoffeeShopGroup.CoffeeShop.Repo.*;
import CoffeeShopGroup.CoffeeShop.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.Optional;


@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private  MenuRepository MenuRepository;
    @Autowired
    private OrdersRepository OrdersRepository;

    // получаем id продукта, количество в заказе,

    @Override
    public ArrayList <orderItem>  getProductInfo(Iterable <Orders> order) {
        ArrayList <orderItem> items = new ArrayList<orderItem>();
        for (Orders product : order) {
            Long idProdut = product.getId_product();
            int quantity = product.getQuantity();
            Optional<Menu> itemMenu = MenuRepository.findById(idProdut);
            ArrayList <Menu> result = new ArrayList<>();
            itemMenu.ifPresent(result::add);

            String title = result.get(0).getTitle();
            String fullText = result.get(0).getFull_text();
            int price = result.get(0).getPrice();
            items.add(new orderItem(title, fullText, quantity, price));
        }

        return items;
    }

    @Override
    public ArrayList <OrderWithItem> getOrdersInfo(Iterable <CoffeeShop> orders) {

        ArrayList <OrderWithItem> orderWithItems = new ArrayList<OrderWithItem>(); // массив заказов с продуктами

        // сбор информации по каждому заказу
        for (CoffeeShop order : orders) {
            Long orderNumber = order.getId_Order();
            Date orderDate = order.getDate();
            Iterable <Orders> orderInfo = OrdersRepository.findByIdOrder(orderNumber);

            orderWithItems.add(new OrderWithItem(getProductInfo(orderInfo), orderNumber, orderDate));
        }

        return orderWithItems;
    }

}
