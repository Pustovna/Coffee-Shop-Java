package CoffeeShopGroup.CoffeeShop.Repo;

import CoffeeShopGroup.CoffeeShop.Models.Menu;
import CoffeeShopGroup.CoffeeShop.Models.User;
import org.springframework.data.repository.CrudRepository;

public interface MenuRepository extends CrudRepository<Menu,Long> {   // Long - datatype for ID
//
}
