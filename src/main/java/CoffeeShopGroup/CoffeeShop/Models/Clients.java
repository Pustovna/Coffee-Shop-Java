package CoffeeShopGroup.CoffeeShop.Models;

//import CoffeeShopGroup.CoffeeShop.MyDate;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

//============================================================================
//-------------------------- Создаем таблицу ---------------------------------
@Entity                                                              // Аннотация для создания таблицы
public class Clients {

    @Id                                                              // Java persistance library
    @GeneratedValue(strategy = GenerationType.IDENTITY)                  // AUTOGeneration Number for ID
    private Long id;                                                   // Уникальный идентификатор
    private String Name,LastName;                                    // Текстовые поля таблицы
    private String Phone,Adress,Email;                             // Текстовые поля таблицы
    private Boolean status;

    private Long userId;                                          // Ид из базы зарегистрированных клиентов


//--------------------------Constructor-----------------------------------------

    public Clients () {}
    public Clients (String Name,
                    String LastName,
                    String Phone,
                    String Adress,
                    String Email,
                    Boolean status,
                    Long userId//,
                    //MyDate Birthday
    )
    {
        this.Name = Name;
        this.LastName = LastName;
        this.Phone = Phone;
        this.Adress = Adress;
        this.Email = Email;
        this.status = status;


        if (userId == null) {
            this.userId = null;
        } else {
            this.userId = userId;
        }
        //this.Birthday = Birthday;
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
    public String getPhone() { return Phone;}
    public void setPhone(String phone) {Phone = phone;}
    public String getEmail() {return Email;}
    public void setEmail(String email) {Email = email;}
    public Boolean getStatus() {return status;}
    public void setStatus(Boolean status) {this.status = status;}
    public Long getUserId() {return userId;}
    public void setUserId(Long userId) {this.userId = userId;}
    //public MyDate getBirthday() {return Birthday;}
    //public void setBirthday(MyDate birthday) {Birthday = birthday;}
}
