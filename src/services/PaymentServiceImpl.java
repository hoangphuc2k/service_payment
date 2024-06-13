package services;

import entities.Bill;
import entities.Customer;
import entities.Transaction;
import enums.BillState;
import java.util.Date;
import java.util.List;

public class PaymentServiceImpl implements PaymentService {

  private static final String HEADERS = "Bill No. Type Amount Due Date State PROVIDER";

  private final Customer customer;

  public PaymentServiceImpl(Customer customer) {
    this.customer = customer;
  }

  @Override
  public void addFund(double amount) {
    customer.addFund(amount);
    System.out.println("Your available balance: " + customer.getBalance());
  }

  @Override
  public void listBills() {
    List<Bill> bills = customer.getBills();
    printTableBills(bills, "No bills found.");
  }

  @Override
  public void payBill(int billId) {
    Bill bill = getBillById(customer.getBills(), billId);

    if (bill == null) {
      System.out.println("Sorry! Not found a bill with such id");
      return;
    }

    if (customer.getBalance() < bill.getAmount()) {
      System.out.println("Sorry! Not enough fund to proceed with payment.");
      return;
    }

    customer.payBill(bill);
    System.out.println("Payment has been completed for Bill with id " + billId);
    System.out.println("Your current balance is: " + customer.getBalance());
  }

  @Override
  public void addBill(Bill bill) {
    customer.addBill(bill);
  }

  @Override
  public void listDueBills() {
    List<Bill> bills = customer.getBills().stream()
        .filter(bill -> bill.getState() == BillState.NOT_PAID)
        .toList();

    printTableBills(bills, "No due bills found.");
  }

  @Override
  public void searchBills(String search) {
    List<Bill> bills = customer.getBills().stream()
        .filter(
            bill -> bill.getProvider().contains(search) || bill.getType().name().contains(search)
                || bill.getState().name().contains(search))
        .toList();

    printTableBills(bills, "No bills found for text: " + search);
  }

  @Override
  public void listTransactions() {
    List<Transaction> transactions = customer.getTransactions();
    if (transactions.isEmpty()) {
      System.out.println("No payment transactions found.");
    } else {
      System.out.println("No. Amount Payment Date State BillId");

      for (Transaction transaction : transactions) {
        System.out.printf("%d. %.2f %s %s %d\n",
            transactions.indexOf(transaction) + 1,
            transaction.getAmount(),
            transaction.getDate(),
            transaction.getState(),
            transaction.getBillId());
      }
    }
  }

  @Override
  public void scheduleBill(int billId, Date paymentDate) {
    Bill bill = getBillById(customer.getBills(), billId);
    if (bill == null) {
      System.out.println("Sorry! Not found a bill with such id");
      return;
    }

    bill.setState(BillState.PENDING);
    System.out.println("Payment for bill id " + billId + " is scheduled on " + paymentDate);
  }

  private void printTableBills(List<Bill> bills, String errorMessage) {
    if (bills == null || bills.isEmpty()) {
      System.out.println(errorMessage);
      return;
    }

    System.out.println(HEADERS);
    bills.forEach(bill -> System.out.printf("%d. %s %.2f %s %s %s\n",
        bill.getId(),
        bill.getType(),
        bill.getAmount(),
        bill.getDueDate(),
        bill.getState(),
        bill.getProvider()));
  }

  private Bill getBillById(List<Bill> bills, int billId) {
    return bills.stream().filter(bill -> billId == bill.getId()).findFirst().orElse(null);
  }
}
