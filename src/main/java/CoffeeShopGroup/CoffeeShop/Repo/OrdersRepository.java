package CoffeeShopGroup.CoffeeShop.Repo;

import CoffeeShopGroup.CoffeeShop.Models.Clients;
import CoffeeShopGroup.CoffeeShop.Models.Orders;
import org.springframework.data.repository.CrudRepository;

public interface OrdersRepository  extends CrudRepository<Orders,Long>{
    Iterable <Orders> findByIdOrder(Long idOrder);


}
