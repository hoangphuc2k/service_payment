package entities;

import enums.BillState;
import enums.TransactionState;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Customer {

  private static int nextId = 1;

  private int id;

  private double balance;

  private List<Bill> bills;

  private List<Transaction> transactions;

  public Customer() {
    this.id = nextId++;
    this.balance = 0.0;
    this.bills = new ArrayList<>();
    this.transactions = new ArrayList<>();
  }

  public Customer(int id, List<Bill> bills, List<Transaction> transactions) {
    this.id = id;
    this.bills = bills;
    this.transactions = transactions;
  }

  public void addBill(Bill bill) {
    this.bills.add(bill);
  }

  public void addTransaction(Transaction transaction) {
    this.transactions.add(transaction);
  }

  public void addFund(double amount) {
    this.balance += amount;
  }

  public void payBill(Bill bill) {
    if (bill.getAmount() <= balance && bill.getState() == BillState.NOT_PAID) {
      balance -= bill.getAmount();
      bill.setState(BillState.PAID);
      Transaction transaction = new Transaction(new Date(), bill.getAmount(),
          TransactionState.PROCESSED, bill.getId());
      addTransaction(transaction);
    }
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public List<Bill> getBills() {
    return bills;
  }

  public void setBills(List<Bill> bills) {
    this.bills = bills;
  }

  public List<Transaction> getTransactions() {
    return transactions;
  }

  public void setTransactions(List<Transaction> transactions) {
    this.transactions = transactions;
  }

  public double getBalance() {
    return balance;
  }

  public void setBalance(double balance) {
    this.balance = balance;
  }
}
