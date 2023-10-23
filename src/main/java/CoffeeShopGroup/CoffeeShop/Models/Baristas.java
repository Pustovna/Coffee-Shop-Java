package CoffeeShopGroup.CoffeeShop.Models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

//============================================================================
//-------------------------- Создаем таблицу ---------------------------------
@Entity                                                              // Аннотация для создания таблицы
public class Baristas {

    @Id                                                              // Java persistance library
    @GeneratedValue(strategy = GenerationType.IDENTITY)                  // AUTOGeneration Number for ID
    private Long id;                                                 // Уникальный идентификатор
    private String Name,LastName,Email;                                    // Текстовые поля таблицы
    private String Phone,Adress;                                    // Текстовые поля таблицы
    private Boolean status;
    private float salary_ph;
//--------------------------Constructor-----------------------------------------

    public Baristas() {}
    public Baristas(String Name,
                    String LastName,
                    String Phone,
                    String Adress,
                    String Email,
                    Boolean status,
                    float salary_ph
                    )
    {
        this.Name = Name;
        this.LastName = LastName;
        this.Phone = Phone;
        this.Adress = Adress;
        this.Email = Email;
        this.status = status;
        this.salary_ph =  salary_ph;

    }

    //--------------------------Setters and getters---------------------------------

    public Long getId() {return id;}
    public void setId(Long id) {this.id = id;}
    public String getName() {return Name;}
    public void setName(String name) {Name = name;}
    public String getLastName() {return LastName;}
    public void setLastName(String lastName) {LastName = lastName;}
    public String getAdress() {return Adress;}
    public void setAdress(String adress) {Adress = adress;}
    public String getEmail() {return Email;}
    public void setEmail(String email) {Email = email;}
    public String getPhone() {return Phone;}
    public void setPhone(String phone) {Phone = phone;}
    public Boolean getStatus() {return status;}
    public void setStatus(Boolean status) {this.status = status;}
    public float getSalary_ph() {return salary_ph;}
    public void setSalary_ph(float salary_ph) {this.salary_ph = salary_ph;}
}
