package CoffeeShopGroup.CoffeeShop.Repo;

import CoffeeShopGroup.CoffeeShop.Models.Clients;
import org.springframework.data.repository.CrudRepository;

public interface ClientsRepository extends CrudRepository<Clients,Long> {
    Clients findByUserId(Long userId);
}
