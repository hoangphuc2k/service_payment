package services;

import entities.Bill;
import java.util.Date;

public interface PaymentService {
  void addFund(double amount);

  void listBills();

  void payBill(int billId);

  void addBill(Bill bill);

  void listDueBills();

  void searchBills(String search);

  void listTransactions();

  void scheduleBill(int billId, Date paymentDate);
}
