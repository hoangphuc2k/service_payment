package commands;

import static utils.DateUtil.parseDate;

import entities.Bill;
import enums.BillType;
import enums.Feature;
import java.util.Arrays;
import java.util.Date;
import java.util.Scanner;
import java.util.stream.Collectors;
import services.PaymentService;

public class CommandProcessor {

  private final PaymentService paymentService;
  private final Scanner scanner;

  public CommandProcessor(PaymentService paymentService) {
    this.paymentService = paymentService;
    this.scanner = new Scanner(System.in);
  }

  public void start() {
    System.out.println("Welcome to the Payment Service System.");
    System.out.println(
        "The System Features: " + Arrays.stream(Feature.values()).map(
            Enum::name).collect(Collectors.joining("|")));
    initMockData();
    boolean exit = false;

    while (!exit) {
      System.out.print("> ");
      String command = scanner.nextLine();
      String[] parts = command.split(" ");
      String value = parts[0].toUpperCase();
      int billId;

      Feature feature = findFeature(value);

      switch (feature) {
        case CASH_IN:
          double amount = Double.parseDouble(parts[1]);
          paymentService.addFund(amount);
          break;
        case LIST_BILL:
          paymentService.listBills();
          break;
        case PAY:
          billId = Integer.parseInt(parts[1]);
          paymentService.payBill(billId);
          break;
        case SEARCH_BILL:
          String search = parts[1];
          paymentService.searchBills(search.toUpperCase());
          break;
        case LIST_PAYMENT:
          paymentService.listTransactions();
          break;
        case DUE_DATE:
          paymentService.listDueBills();
          break;
        case SCHEDULE:
          billId = Integer.parseInt(parts[1]);
          Date paymentDate = parseDate(parts[2]);
          paymentService.scheduleBill(billId, paymentDate);
          break;
        case EXIT:
          exit = true;
          System.out.println("Goodbye!");
          break;
        case null, default:
          System.out.println("Unknown command.");
          break;
      }
    }
  }

  private Feature findFeature(String value) {
    return Arrays.stream(Feature.values())
        .filter(feature -> feature.name().equals(value))
        .findAny()
        .orElse(null);
  }

  private void initMockData() {
    paymentService.addBill(new Bill(BillType.WATER, 1000, parseDate("12/06/2024"), "HCM"));
    paymentService.addBill(
        new Bill(BillType.ELECTRIC, 1000, parseDate("12/06/2024"), "HCM"));
    paymentService.addBill(
        new Bill(BillType.INTERNET, 1000, parseDate("12/06/2024"), "HCM"));
  }
}
