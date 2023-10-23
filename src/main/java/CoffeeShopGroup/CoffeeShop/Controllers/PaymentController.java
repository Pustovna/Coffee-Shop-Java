package CoffeeShopGroup.CoffeeShop.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PaymentController {

    @GetMapping("/client_Payment")
    public String clientPayment(Model model) {
        model.addAttribute("title", "payment");
        return "client_Payment";
    }

    /*
    	Managing intermediate sums of money
	Processing a payment (using SSL certificate – there are free ones online – is a bonus)
	No credit card must be stored in the database
	Bonus: the ability to pay with a PayPal (redirection to a PayPal site using its API)
	The user can choose to pay with the credit card or in cash.
	Show notification message after the payment is accepted or failed. After that, a user is redirected to Home page.

     */
}
