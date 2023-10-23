package CoffeeShopGroup.CoffeeShop.Repo;

import CoffeeShopGroup.CoffeeShop.Models.CoffeeShop;
import CoffeeShopGroup.CoffeeShop.Models.Orders;
import org.springframework.data.repository.CrudRepository;

public interface CoffeeShopRepository extends CrudRepository<CoffeeShop,Long> {
    Iterable <CoffeeShop> findByIdClient(Long idClient);
}
