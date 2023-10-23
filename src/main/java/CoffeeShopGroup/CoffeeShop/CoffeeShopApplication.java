package CoffeeShopGroup.CoffeeShop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CoffeeShopApplication {

	public static void main(String[] args) {
		SpringApplication.run(CoffeeShopApplication.class, args);
	}

}
/*
//------------------------------------------------------------------
/////////////////
	Порядок построения
      "MVC"

			  1. Создаем модель для подключения БД
			  - переменные (атрибуты БД)
			  - конструктор БД + конструктор по-умолчанию
			  - функции загрузки/выгрузки (set+get)
//------------------------------------------------------------------
//------------------------------------------------------------------
			  2. Создаем контроллер
			  //
@Controller
public class AdminController {}

	@Autowired
	private MenuRepository MenuRepository;
	// подключение к репозиторию БД
//______________________________________________________________________
    ////////////////////////////////     ID  Бариста
    //  <<<--- получение данных
    @GetMapping("/barista/{id}")//  ("/запрос")
    // Если нужен динамический параметр, то запрос должен его в себе содержать {id}
    //-> чтение базы

    	//        НАЗВАНИЕ _Метода     (параметры, ... , модель)
    public String INTERFACE_Barista_ID (@PathVariable(value = "id") long id,
                                        Model model) {
        //      -> Создание элементов данного типа для вывода отдельно по ID :
        Optional<Baristas> item = BaristasRepository.findById(id);
        //		-> Проверки (приведение к массиву, и др.)
        ArrayList <Baristas> res = new ArrayList<>();     // выделяем память (создаем массив)
        item.ifPresent(res::add);                         // синтаксис для перевода данных в массив
        model.addAttribute("Baristas",res);    // тут мы получаем выбранный объект для передачи
        model.addAttribute("title", "Бариста");
        //      -> Возврат значений в виде (вставка в шаблон) :
        //System.out.println(Name + ", " + LastName + ", " + Tel_number + ", " + Adress + ", " + id);
        System.out.println("Данные отправлены --->>> " + getClass().getName()); // 'Baristas'
        System.out.println("==========================");
        return "barista";
        }
//______________________________________________________________________
////////////////////////////////
// --->>> отправление данных

		@PostMapping // ("/запрос")

//            НАЗВАНИЕ _Метода     (параметры, ... , модель)
public String INTERFACE_Barista_ID (@PathVariable(value = "id") long id,
                                        Model model) {
		// Запрос для статических параметров
				(@RequestParam String title,
				 @RequestParam String Name,
                 @RequestParam String LastName,
                 @RequestParam String Tel_number,
                 @RequestParam String Adress,
                 Model model)
		// или динамический параметр (или несколько)
				(@PathVariable(value = "id") long id, ... , модель)
//-------------------------------------------------------------------
	{
		//-> Создание/получение объекта из базы для записи параметров
// _тип _объект    _Класс (параметры)
	Menu item = new Menu  (title,full_text,price);
			// или
// _тип _объект   База данных.   (параметры поиска)
	Menu   item = MenuRepository.findById(id).orElseThrow() ;
		// -----------------------------------------------------
		// -> Функции / присвоение необходимых значений
	item.setTitle(title);
	item.setFull_text(full_text);
	item.setPrice(price);
		// -----------------------------------------------------
		//-> Сохранение и возврат пользователя в любое место программы
	System.out.println("Данные получены <<<--- " + getClass().getName());
	MenuRepository.save(item);
	return "redirect:/menu";
	}

//------------------------------------------------------------------
/////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////

Популярные контроллеры *

         База Данных Барист ----+--- ALL_Barista (Model model)
         ID  Бариста        ----+--- INTERFACE_Barista_ID (@PathVariable(value = "id") long id, Model model)
P        Добавление Баристы ----+--- Barista_ADD (@RequestParam String Name,    // Запрос для статических параметров
                                                 @RequestParam String LastName,
                                                 @RequestParam String Tel_number,
                                                 @RequestParam String Adress,
                                                 Model model)
Поиск одного элемента БД     ----+---
        (детали)
Форма для внесения изменений ----+---
Отправка и сохранение в БД   ----+---
   Удаление элемента из БД   ----+---


        =================      ***       ===================
                      /menu ----+--- ALL_Menu (Model model)
                /admin_menu ----+--- ADMIN_Menu_R (Model model)
P               /admin_menu ----+--- Menu_ADD (@RequestParam String title,      // Запрашиваем даннные из шаблона
                                                @RequestParam String full_text,
                                                @RequestParam int price,
                                                Model model)
  /admin_menu/{id}/details  ----+--- MENU_Element (@PathVariable(value = "id") long id, Model model)
     /admin_menu/{id}/edit  ----+--- MENU_Edition (@PathVariable(value = "id") long id, Model model)
P    /admin_menu/{id}/edit  ----+--- MENU_Item_Update (@PathVariable(value = "id")       //Здесь указываем динамический динамический параметр(можно и не один)
                                                        long id,
                                                @RequestParam String title,      // Запрашиваем даннные из шаблона
                                                @RequestParam String full_text,
                                                @RequestParam int price,
                                                Model model)


*//**/
/*
//------------------------------------------------------------------
/////////////////
	Порядок построения
      "MVC"

			  1. Создаем модель для подключения БД
			  - переменные (атрибуты БД)
			  - конструктор БД + конструктор по-умолчанию
			  - функции загрузки/выгрузки (set+get)
//------------------------------------------------------------------
//------------------------------------------------------------------
			  2. Создаем контроллер
			  //
@Controller
public class AdminController {}

	@Autowired
	private MenuRepository MenuRepository;
	// подключение к репозиторию БД
//______________________________________________________________________
    ////////////////////////////////     ID  Бариста
    //  <<<--- получение данных
    @GetMapping("/barista/{id}")//  ("/запрос")
    // Если нужен динамический параметр, то запрос должен его в себе содержать {id}
    //-> чтение базы

    	//        НАЗВАНИЕ _Метода     (параметры, ... , модель)
    public String INTERFACE_Barista_ID (@PathVariable(value = "id") long id,
                                        Model model) {
        //      -> Создание элементов данного типа для вывода отдельно по ID :
        Optional<Baristas> item = BaristasRepository.findById(id);
        //		-> Проверки (приведение к массиву, и др.)
        ArrayList <Baristas> res = new ArrayList<>();     // выделяем память (создаем массив)
        item.ifPresent(res::add);                         // синтаксис для перевода данных в массив
        model.addAttribute("Baristas",res);    // тут мы получаем выбранный объект для передачи
        model.addAttribute("title", "Бариста");
        //      -> Возврат значений в виде (вставка в шаблон) :
        //System.out.println(Name + ", " + LastName + ", " + Tel_number + ", " + Adress + ", " + id);
        System.out.println("Данные отправлены --->>> " + getClass().getName()); // 'Baristas'
        System.out.println("==========================");
        return "barista";
        }
//______________________________________________________________________
////////////////////////////////
// --->>> отправление данных

		@PostMapping // ("/запрос")

//            НАЗВАНИЕ _Метода     (параметры, ... , модель)
public String INTERFACE_Barista_ID (@PathVariable(value = "id") long id,
                                        Model model) {
		// Запрос для статических параметров
				(@RequestParam String title,
				 @RequestParam String Name,
                 @RequestParam String LastName,
                 @RequestParam String Tel_number,
                 @RequestParam String Adress,
                 Model model)
		// или динамический параметр (или несколько)
				(@PathVariable(value = "id") long id, ... , модель)
//-------------------------------------------------------------------
	{
		//-> Создание/получение объекта из базы для записи параметров
// _тип _объект    _Класс (параметры)
	Menu item = new Menu  (title,full_text,price);
			// или
// _тип _объект   База данных.   (параметры поиска)
	Menu   item = MenuRepository.findById(id).orElseThrow() ;
		// -----------------------------------------------------
		// -> Функции / присвоение необходимых значений
	item.setTitle(title);
	item.setFull_text(full_text);
	item.setPrice(price);
		// -----------------------------------------------------
		//-> Сохранение и возврат пользователя в любое место программы
	System.out.println("Данные получены <<<--- " + getClass().getName());
	MenuRepository.save(item);
	return "redirect:/menu";
	}

//------------------------------------------------------------------
/////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////

Популярные контроллеры *

         База Данных Барист ----+--- ALL_Barista (Model model)
         ID  Бариста        ----+--- INTERFACE_Barista_ID (@PathVariable(value = "id") long id, Model model)
P        Добавление Баристы ----+--- Barista_ADD (@RequestParam String Name,    // Запрос для статических параметров
                                                 @RequestParam String LastName,
                                                 @RequestParam String Tel_number,
                                                 @RequestParam String Adress,
                                                 Model model)
Поиск одного элемента БД     ----+---
        (детали)
Форма для внесения изменений ----+---
Отправка и сохранение в БД   ----+---
   Удаление элемента из БД   ----+---


        =================      ***       ===================
                      /menu ----+--- ALL_Menu (Model model)
                /admin_menu ----+--- ADMIN_Menu_R (Model model)
P               /admin_menu ----+--- Menu_ADD (@RequestParam String title,      // Запрашиваем даннные из шаблона
                                                @RequestParam String full_text,
                                                @RequestParam int price,
                                                Model model)
  /admin_menu/{id}/details  ----+--- MENU_Element (@PathVariable(value = "id") long id, Model model)
     /admin_menu/{id}/edit  ----+--- MENU_Edition (@PathVariable(value = "id") long id, Model model)
P    /admin_menu/{id}/edit  ----+--- MENU_Item_Update (@PathVariable(value = "id")       //Здесь указываем динамический динамический параметр(можно и не один)
                                                        long id,
                                                @RequestParam String title,      // Запрашиваем даннные из шаблона
                                                @RequestParam String full_text,
                                                @RequestParam int price,
                                                Model model)


*//**/