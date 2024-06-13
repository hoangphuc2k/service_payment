package tests;

import static enums.Feature.CASH_IN;
import static enums.Feature.DUE_DATE;
import static enums.Feature.LIST_BILL;
import static enums.Feature.LIST_PAYMENT;
import static enums.Feature.PAY;
import static enums.Feature.SCHEDULE;
import static enums.Feature.SEARCH_BILL;

import commands.CommandProcessor;
import entities.Customer;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import org.junit.Before;
import org.junit.Test;
import services.PaymentService;
import services.PaymentServiceImpl;

public class CommandProcessorTest {

  private PaymentService paymentService;
  private Customer customer;

  @Before
  public void setUp() {
    customer = new Customer();
    paymentService = new PaymentServiceImpl(customer);
  }

  private void runCommandProcessor(PaymentService paymentService, String input) {
    InputStream in = new ByteArrayInputStream(input.getBytes());
    System.setIn(in);
    CommandProcessor processor = new CommandProcessor(paymentService);
    processor.start();
  }

  @Test
  public void testAddFund() {
    runCommandProcessor(paymentService, CASH_IN.name() + " 1000\nEXIT\n");
  }

  @Test
  public void testListBills() {
    runCommandProcessor(paymentService, LIST_BILL.name() + "\nEXIT\n");
  }

  @Test
  public void testPayBill() {
    runCommandProcessor(paymentService, PAY.name() + " 1\nEXIT\n");
  }

  @Test
  public void testSearchBills() {
    runCommandProcessor(paymentService, SEARCH_BILL.name() + " WATER\nEXIT\n");
  }

  @Test
  public void testListTransactions() {
    runCommandProcessor(paymentService, LIST_PAYMENT.name() + "\nEXIT\n");
  }

  @Test
  public void testListDueBills() {
    runCommandProcessor(paymentService, DUE_DATE.name() + "\nEXIT\n");
  }

  @Test
  public void testScheduleBill() {
    runCommandProcessor(paymentService, SCHEDULE.name() + "1 13/06/2024\nEXIT\n");
  }

  @Test
  public void testUnknownCommand() {
    runCommandProcessor(paymentService, "UNKNOWN\nEXIT\n");
  }
}
