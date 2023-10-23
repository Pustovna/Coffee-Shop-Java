package CoffeeShopGroup.CoffeeShop.Models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

//============================================================================
//-------------------------- Создаем таблицу ---------------------------------
@Entity                                                              // Аннотация для создания таблицы
public class Menu {

    @Id                                                              // Java persistance library
    @GeneratedValue(strategy = GenerationType.IDENTITY)                  // AUTOGeneration Number for ID
    private Long id;                                                 // Уникальный идентификатор
    private String title,full_text;                                   // Текстовые поля таблицы
    private int price;                                                // Числовые поля таблицы

//--------------------------Constructor-----------------------------------------

    public Menu() {}
    public Menu(String title, String full_text, int price) {
        this.title = title;
        this.full_text = full_text;
        this.price = price;
    }

//--------------------------Setters and getters---------------------------------
    public Long getId() {return id;}
    public void setId(Long id) {this.id = id;}
    public String getTitle() {return title;}
    public void setTitle(String title) {this.title = title;}
    public String getFull_text() {return full_text;}
    public void setFull_text(String full_text) {this.full_text = full_text;}
    public int getPrice() {return price;}
    public void setPrice(int price) {this.price = price;}
}
