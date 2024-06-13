package entities;

import enums.TransactionState;
import java.util.Date;

public class Transaction {

  private static int nextId = 1;

  private int id;

  private Date date;

  private double amount;

  private TransactionState state;

  private int billId;

  public Transaction(Date date, double amount, TransactionState state, int billId) {
    this.id = nextId++;
    this.date = date;
    this.amount = amount;
    this.state = state;
    this.billId = billId;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public int getBillId() {
    return billId;
  }

  public void setBillId(int billId) {
    this.billId = billId;
  }

  public Date getDate() {
    return date;
  }

  public void setDate(Date date) {
    this.date = date;
  }

  public double getAmount() {
    return amount;
  }

  public void setAmount(double amount) {
    this.amount = amount;
  }

  public TransactionState getState() {
    return state;
  }

  public void setState(TransactionState state) {
    this.state = state;
  }
}
