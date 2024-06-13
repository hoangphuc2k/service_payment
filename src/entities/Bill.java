package entities;

import enums.BillState;
import enums.BillType;
import java.util.Date;

public class Bill {
  private static int nextId = 1;

  private int id;

  private BillType type;

  private double amount;

  private Date dueDate;

  private BillState state;

  private String provider;

  public Bill(BillType type, double amount, Date dueDate, String provider) {
    this.id = nextId++;
    this.type = type;
    this.amount = amount;
    this.dueDate = dueDate;
    this.state = BillState.NOT_PAID;
    this.provider = provider;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public BillType getType() {
    return type;
  }

  public void setType(BillType type) {
    this.type = type;
  }

  public double getAmount() {
    return amount;
  }

  public void setAmount(double amount) {
    this.amount = amount;
  }

  public Date getDueDate() {
    return dueDate;
  }

  public void setDueDate(Date dueDate) {
    this.dueDate = dueDate;
  }

  public BillState getState() {
    return state;
  }

  public void setState(BillState state) {
    this.state = state;
  }

  public String getProvider() {
    return provider;
  }

  public void setProvider(String provider) {
    this.provider = provider;
  }
}
