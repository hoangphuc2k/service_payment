package tests;

import static org.junit.Assert.assertEquals;

import entities.Bill;
import entities.Customer;
import enums.BillState;
import enums.BillType;
import java.util.Date;
import org.junit.Before;
import org.junit.Test;
import services.PaymentService;
import services.PaymentServiceImpl;
import utils.DateUtil;

public class PaymentServiceTest {

  private PaymentService paymentService;
  private Customer customer;

  @Before
  public void setUp() {
    customer = new Customer();
    paymentService = new PaymentServiceImpl(customer);
  }

  @Test
  public void testAddFunds() {
    paymentService.addFund(1000);
    assertEquals(1000, customer.getBalance(), 0);
  }

  @Test
  public void testListBills() {
    Bill bill = new Bill(BillType.ELECTRIC, 200, new Date(), "HCM");
    customer.addBill(bill);
    paymentService.listBills();
    assertEquals(1, customer.getBills().size());
  }

  @Test
  public void testPayBill() {
    customer.addFund(500);
    Bill bill = new Bill(BillType.ELECTRIC, 200, new Date(), "HCM");
    customer.addBill(bill);
    paymentService.payBill(bill.getId());
    assertEquals(300, customer.getBalance(), 0);
    assertEquals(BillState.PAID, bill.getState());
  }

  @Test
  public void testSearchBill() {
    Bill bill = new Bill(BillType.INTERNET, 300, new Date(), "HCM");
    customer.addBill(bill);
    paymentService.searchBills("INTERNET");
    assertEquals(1, customer.getBills().size());
  }

  @Test
  public void testScheduleBill() {
    Bill bill = new Bill(BillType.WATER, 200, new Date(), "HCM");
    customer.addBill(bill);
    Date paymentDate = DateUtil.parseDate("13/06/2024");
    paymentService.scheduleBill(bill.getId(), paymentDate);
    assertEquals(BillState.PENDING, bill.getState());
  }

  @Test
  public void testListTransactions() {
    Bill bill = new Bill(BillType.WATER, 200, new Date(), "HCM");
    paymentService.addBill(bill);
    paymentService.addFund(1000);
    paymentService.payBill(bill.getId());
    paymentService.listTransactions();
    assertEquals(BillState.PAID, bill.getState());
  }

  @Test
  public void testListDueBills() {
    Bill bill = new Bill(BillType.WATER, 200, new Date(), "HCM");
    customer.addBill(bill);
    paymentService.listDueBills();
  }
}
