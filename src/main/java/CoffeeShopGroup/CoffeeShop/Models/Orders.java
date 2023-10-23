package CoffeeShopGroup.CoffeeShop.Models;

        import javax.persistence.Entity;
        import javax.persistence.GeneratedValue;
        import javax.persistence.GenerationType;
        import javax.persistence.Id;
//        import CoffeeShopGroup.CoffeeShop.MyDate;

//============================================================================
//-------------------------- Создаем таблицу ---------------------------------
@Entity                                                              // Аннотация для создания таблицы
public class Orders{

    @Id                                                              // Java persistance library
    @GeneratedValue(strategy = GenerationType.IDENTITY)                  // AUTOGeneration Number for ID
    private Long id;       // id_item in order                                          // Уникальный идентификатор
    private Long id_product;
    private Long idOrder;
    private int quantity;

    private Long idClient;

    //--------------------------Constructor-----------------------------------------
    public Orders() {}

    public Orders(Long id,
                  Long id_product,
                  int quantity,
                  Long IdOrder,
                  Long idClient
                )
    {
        //this.id = id;
        this.id_product = id_product;
        this.quantity = quantity;
        this.idOrder = IdOrder;
        this.idClient = idClient;
    }

    public Orders(//Long id_client,
                  Long id_product,
                  int quantity,
                  Long IdOrder,
                  Long idClient
                    )
    {
        this.id_product = id_product;
        this.quantity = quantity;
        this.idOrder = IdOrder;
        this.idClient = idClient;
    }
    //--------------------------Setters and getters---------------------------------

    public Long getId() {return id;}
    public void setId(Long id) {this.id = id;}
    public Long getId_product() {return id_product;}
    public void setId_product(Long id_product) {this.id_product = id_product;}
    public int getQuantity() {return quantity;}
    public void setQuantity(int quantity) {this.quantity = quantity;}

    public Long getId_order() {return idOrder;}
    public void setId_order(Long id_order) {this.idOrder = id_order;}
    public Long getIdClient() {
        return idClient;
    }

    public void setIdClient(Long idClient) {
        this.idClient = idClient;
    }

}