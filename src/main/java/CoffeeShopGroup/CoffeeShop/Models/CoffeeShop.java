package CoffeeShopGroup.CoffeeShop.Models;

import CoffeeShopGroup.CoffeeShop.Repo.BaristasRepository;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import CoffeeShopGroup.CoffeeShop.Models.Baristas;
import CoffeeShopGroup.CoffeeShop.Models.Orders;

import java.util.Date;

//============================================================================
//-------------------------- Создаем таблицу ---------------------------------
@Entity
public class CoffeeShop {

    @Id                                                              // Java persistance library
    @GeneratedValue(strategy = GenerationType.IDENTITY)                  // AUTOGeneration Number for ID
    protected Long id_Order;
    protected Long id_Baristas;
    protected Long id_Tables;
    protected Date date;


    protected Long idClient;

    //--------------------------Constructor-----------------------------------------
    public CoffeeShop() {}


    public CoffeeShop(Long id_Baristas,
                      Long id_Order,
                      Long id_Tables,
                      Long idClient
                    )
    {
        this.id_Baristas = id_Baristas;
        this.id_Order = id_Order;
        this.id_Tables = id_Tables;
        this.idClient = idClient;
        this.date = new Date();
    }

    public CoffeeShop(
                      Long idClient
                      )
    {
        this.idClient = idClient;
        this.date = new Date();
    }


    //--------------------------Setters and getters---------------------------------
    //public Long getId() {return id_row;}
    //public void setId(Long id_row) {this.id_row = id_row;}
    public Long getId_Baristas() {return id_Baristas;}
    public void setId_Baristas(Long id_Baristas) {this.id_Baristas = id_Baristas;}
    public  Long getId_Order() {return id_Order;}
    public void setId_Order(Long id_Order) {this.id_Order = id_Order;}
    public Long getId_Tables() {return id_Tables;}
    public void setId_Tables(Long id_Tables) {this.id_Tables = id_Tables;}
    public Long getIdClient() {
        return idClient;
    }

    public void setIdClient(Long idClient) {
        this.idClient = idClient;
    }
    public Date getDate() {
        return this.date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

}
