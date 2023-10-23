package CoffeeShopGroup.CoffeeShop.service;

import CoffeeShopGroup.CoffeeShop.Models.CoffeeShop;
import CoffeeShopGroup.CoffeeShop.Models.OrderWithItem;
import CoffeeShopGroup.CoffeeShop.Models.Orders;
import CoffeeShopGroup.CoffeeShop.Models.orderItem;
import org.hibernate.criterion.Order;

import java.util.ArrayList;

public interface OrderService {
    ArrayList<orderItem> getProductInfo(Iterable <Orders> order);

    ArrayList <OrderWithItem> getOrdersInfo(Iterable <CoffeeShop> orders);

}
