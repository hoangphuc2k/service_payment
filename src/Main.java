import commands.CommandProcessor;
import entities.Customer;
import services.PaymentService;
import services.PaymentServiceImpl;

public class Main {

  public static void main(String[] args) {
    Customer customer = new Customer();
    PaymentService paymentService = new PaymentServiceImpl(customer);
    CommandProcessor commandProcessor = new CommandProcessor(paymentService);
    commandProcessor.start();
  }
}