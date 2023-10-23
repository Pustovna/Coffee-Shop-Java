package CoffeeShopGroup.CoffeeShop.Models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

//============================================================================
//-------------------------- Создаем таблицу ---------------------------------
@Entity                                                              // Аннотация для создания таблицы
public class Tables {

    @Id                                                              // Java persistance library
    @GeneratedValue(strategy = GenerationType.IDENTITY)                  // AUTOGeneration Number for ID
    private Long id;                                                 // Уникальный идентификатор
    private String table_type;                                            // Текстовые поля таблицы

    //--------------------------Constructor-----------------------------------------
    public Tables() {}

    public Tables(Long id, String table_type) {
        this.id = id;
        this.table_type = table_type;

    }
    //--------------------------Setters and getters---------------------------------

    public Long getId() {return id;}
    public void setId(Long id) {this.id = id;}
    public String getTable_type() {return table_type;}
    public void setTable_type(String title) {this.table_type = table_type;}

}