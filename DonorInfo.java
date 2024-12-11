import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

class InKindSupportItem {
    private String itemType;
    private int quantity;

    public InKindSupportItem(String itemType, int quantity) {
        this.itemType = itemType;
        this.quantity = quantity;
    }

    public String getItemType() {
        return itemType;
    }

    public int getQuantity() {
        return quantity;
    }
}

class FoodQty extends InKindSupportItem {
    public FoodQty(int quantity) {
        super("Food", quantity);
    }
}

class ClothingQty extends InKindSupportItem {
    public ClothingQty(int quantity) {
        super("Clothing", quantity);
    }
}

class MedicalSuppliesQty extends InKindSupportItem {
    public MedicalSuppliesQty(int quantity) {
        super("Medical Supplies", quantity);
    }
}

class WaterQty extends InKindSupportItem {
    public WaterQty(int quantity) {
        super("Water", quantity);
    }
}

class OthersQty extends InKindSupportItem {
    public OthersQty(int quantity) {
        super("Others", quantity);
    }
}

class Donor {
    private int transactionChoice;
    private String donorName;
    private String donorAddress;
    private String donorContact;
    private String donorEmail;
    private String donationType;
    private String accountName;
    private String accountNumber;
    private int cashDonationAmount;
    private List<InKindSupportItem> inKindSupportItems = new ArrayList<>();

    public void setUserDetails(int transactionChoice, String donorName, String donationType, String accountName, String accountNumber, int cashDonationAmount) {
        this.transactionChoice = transactionChoice;
        this.donorName = donorName;
        this.donationType = donationType;
        this.accountName = accountName;
        this.accountNumber = accountNumber;
        this.cashDonationAmount = cashDonationAmount;
    }

    public void setDonorDetails(String donorName, String donorAddress, String donorContact, String donorEmail) {
        this.donorName = donorName;
        this.donorAddress = donorAddress;
        this.donorContact = donorContact;
        this.donorEmail = donorEmail;
    }

    public void addInKindSupportItem(InKindSupportItem item) {
        inKindSupportItems.add(item);
    }

    public void donationProcess() {
        System.out.println("\n==============================================================================");
        System.out.println("               . . .  WE ARE CURRENTLY PROCESSING YOUR DONATION . . .           ");
        System.out.println("");
        System.out.println("Name: " + donorName);
        System.out.println("Address: " + donorAddress);
        System.out.println("Contact Number: " + donorContact);
        System.out.println("Email: " + donorEmail);
        System.out.println("Transaction Choice: " + (transactionChoice == 1 ? "Donate" : "Cancel"));
        System.out.println("Donation Type: " + donationType);

        if (donationType.equals("In-Kind Support")) {
            System.out.println("In-Kind Support Breakdown:");
            int totalQuantity = 0;
            for (InKindSupportItem item : inKindSupportItems) {
                System.out.println(item.getItemType() + ": " + item.getQuantity());
                totalQuantity += item.getQuantity();
            }
            System.out.println("Total Items Donated: " + totalQuantity);
        } else if (donationType.equals("Cash")) {
            System.out.println("Cash Donation Breakdown:");
            System.out.println("Amount Donated: P" + cashDonationAmount);
        }

        if (donationType.equals("Cash")) {
            System.out.println("Account Name: " + accountName);
            System.out.println("Account Number: " + accountNumber);
        }

        System.out.println("");
        System.out.println("                   AGAIN, THANK YOU FOR YOUR GENEROSITY!                        ");
        System.out.println("        A copy of your receipt will be sent to your email very shortly.         ");
        System.out.println("==============================================================================\n");

        try (BufferedWriter writer = new BufferedWriter(new FileWriter("C:\\Users\\Francine\\OneDrive\\Desktop\\Pleaseeeee\\Last-OOP\\DonorInfo.txt", true))) {
            writer.write("Donor Name: " + donorName + "\n");
            writer.write("Donor Address: " + donorAddress + "\n");
            writer.write("Donor Contact: " + donorContact + "\n");
            writer.write("Donor Email: " + donorEmail + "\n");
            writer.write("Donation Type: " + donationType + "\n");
    
            if (donationType.equals("Cash")) {
                writer.write("Cash Amount: P" + cashDonationAmount + "\n");
                writer.write("Account Name: " + accountName + "\n");
                writer.write("Account Number: " + accountNumber + "\n");
            } else if (donationType.equals("In-Kind Support")) {
                writer.write("In-Kind Support Details:\n");
                writer.write("Donor Name: " + donorName + "\n");
                writer.write("Donor Address: " + donorAddress + "\n");
                writer.write("Donor Contact: " + donorContact + "\n");
                writer.write("Donor Email: " + donorEmail + "\n");
                for (InKindSupportItem item : inKindSupportItems) {
                    writer.write("- " + item.getItemType() + ": " + item.getQuantity() + "\n");
                }
            }
            writer.write("==============================================================================\n");
            writer.flush();
        } catch (IOException e) {
            System.out.println("Error saving donor information to file.");
            e.printStackTrace();
        }
    }
}

public class DonorInfo {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Donor donor = new Donor();

        while (true) {
            System.out.println("==============================================================================");
            System.out.println("                            TRANSACTION CHOICE                        ");
            System.out.println("    |===== 1. Donate =====|                   |===== 2. Cancel =====|");
            System.out.println("==============================================================================");
            System.out.print("Enter your choice: ");

            int transactionChoice;
            try {
                transactionChoice = Integer.parseInt(scanner.nextLine());
                if (transactionChoice < 1 || transactionChoice > 2) {
                    throw new NumberFormatException();
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter 1 or 2.");
                continue;
            }

            if (transactionChoice == 2) {
                System.out.println("Thank you for considering us! Would you like to do another transaction? (yes/no)");
                String response = scanner.nextLine().toLowerCase();
                if (response.equals("no")) {
                    System.out.println(". . . You are now exiting the program. Thank you! . . . ");
                    break;
                } else {
                    continue;
                }
            }

            System.out.println("==============================================================================");
            System.out.print("Enter Full Name: ");
            String donorName = scanner.nextLine();

            System.out.print("Enter Address: ");
            String donorAddress = scanner.nextLine();

            System.out.print("Enter Contact Number: ");
            String donorContact = getValidContact(scanner);

            System.out.print("Enter Personal Email: ");
            String donorEmail = scanner.nextLine();

            System.out.println("==============================================================================");
            System.out.println("                            TYPE OF DONATION                        ");
            System.out.println("    |===== 1. Cash =====|                   |===== 2. In-Kind Support =====|");
            System.out.println("==============================================================================");
            System.out.print("Enter Type of Donation: ");

            String donationType = "";
            int donationTypeChoice;
            try {
                donationTypeChoice = Integer.parseInt(scanner.nextLine());
                switch (donationTypeChoice) {
                    case 1:
                        donationType = "Cash";
                        break;
                    case 2:
                        donationType = "In-Kind Support";
                        break;
                    default:
                        System.out.println("Invalid input. Please enter 1 or 2.");
                        continue;
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter 1 or 2.");
                continue;
            }

            if (donationType.equals("In-Kind Support")) {
                int index = 0;
                while (index < 5) {
                    System.out.println("");
                    System.out.println("==============================================================================");
                    System.out.println("                   MAKE A DIFFERENCE TODAY!                    ");
                    System.out.println("Your support helps us bring positive change to those in need.\n"
                            + "By donating, you contribute to initiatives that uplift communities, \n"
                            + "offer resources, and create lasting impacts. Together, we can build a brighter future");
                    System.out.println("==============================================================================");
                    System.out.println("");

                    System.out.println("What type of In-Kind support would you like to donate?");
                    System.out.println("1. Food");
                    System.out.println("2. Water");
                    System.out.println("3. Medical Supplies");
                    System.out.println("4. Clothing");
                    System.out.println("5. Others");

                    System.out.print("Enter your choice: ");
                    String inKindSupportType = null;
                    int inKindChoice;
                    try {
                        inKindChoice = Integer.parseInt(scanner.nextLine());
                        switch (inKindChoice) {
                            case 1:
                                inKindSupportType = "Food";
                                break;
                            case 2:
                                inKindSupportType = "Water";
                                break;
                            case 3:
                                inKindSupportType = "Medical Supplies";
                                break;
                            case 4:
                                inKindSupportType = "Clothing";
                                break;
                            case 5:
                                System.out.print("Please specify the type of in-kind support: ");
                                inKindSupportType = scanner.nextLine();
                                break;
                            default:
                                System.out.println("Invalid input. Please enter a valid option.");
                                continue;
                        }
                        
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid input. Please enter a valid option.");
                        continue;
                    }

                    System.out.print("Enter the quantity for " + inKindSupportType + ": ");
                    int quantity;
                    try {
                        quantity = Integer.parseInt(scanner.nextLine());
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid quantity. Please enter a valid number.");
                        continue;
                    }

                    InKindSupportItem item = null;
                    switch (inKindSupportType) {
                        case "Food":
                            item = new FoodQty(quantity);
                            break;
                        case "Water":
                            item = new WaterQty(quantity);
                            break;
                        case "Medical Supplies":
                            item = new MedicalSuppliesQty(quantity);
                            break;
                        case "Clothing":
                            item = new ClothingQty(quantity);
                            break;
                        case "Others":
                            item = new OthersQty(quantity);
                            break;
                    }
                    donor.addInKindSupportItem(item);
                    index++;

                    System.out.print("Would you like to add more items? (yes/no): ");
                    String addMore = scanner.nextLine().toLowerCase();
                    if (addMore.equals("no")) {
                        System.out.println(". . . We are now taking records of your donated inventories. Please wait . . . ");
                        break;
                    }
                }

                donor.setDonorDetails(donorName, donorAddress, donorContact, donorEmail);
                donor.setUserDetails(transactionChoice, donorName, donationType, "N/A", "N/A", 0);
                donor.donationProcess();
                break;
            }

            // For Cash Donation
            System.out.println("");
            System.out.println("==============================================================================");
            System.out.println("                   MAKE A DIFFERENCE TODAY!                    ");
            System.out.println("Your support helps us bring positive change to those in need.\n"
                    + "By donating, you contribute to initiatives that uplift communities, \n"
                    + "offer resources, and create lasting impacts. Together, we can build a brighter future");
            System.out.println("==============================================================================");
            System.out.println("");

            System.out.print("Enter your account name: ");
            String accountName = scanner.nextLine();
            System.out.print("Enter your account number (11-12 digits): ");
            String accountNumber = getValidAccountNumber(scanner);

            System.out.println("\nCash Denomination Options:");
            System.out.println("=====================================");
            System.out.println("| 1. P50                            |");
            System.out.println("| 2. P100                           |");
            System.out.println("| 3. P350                           |");
            System.out.println("| 4. P500                           |");
            System.out.println("| 5. P1000                          |");
            System.out.println("| 6. P2000                          |");
            System.out.println("| 7. P5000                          |");
            System.out.println("| 8. Enter other denomination       |");
            System.out.println("=====================================");
            System.out.print("Please choose an amount: ");

            int cashDonationAmount;
            try {
                int amountChoice = Integer.parseInt(scanner.nextLine());
                switch (amountChoice) {
                    case 1: cashDonationAmount = 50; break;
                    case 2: cashDonationAmount = 100; break;
                    case 3: cashDonationAmount = 350; break;
                    case 4: cashDonationAmount = 500; break;
                    case 5: cashDonationAmount = 1000; break;
                    case 6: cashDonationAmount = 2000; break;
                    case 7: cashDonationAmount = 5000; break;
                    case 8:
                        System.out.print("Enter your preferred denomination: P");
                        cashDonationAmount = Integer.parseInt(scanner.nextLine());
                        break;
                    default:
                        throw new NumberFormatException();
                }
            } catch (NumberFormatException e) {
                System.out.println("Error! Invalid input. Please choose a valid amount.");
                continue;
            }

            donor.setDonorDetails(donorName, donorAddress, donorContact, donorEmail);
            donor.setUserDetails(transactionChoice, donorName, donationType, accountName, accountNumber, cashDonationAmount);
            donor.donationProcess();
            break;
        }
    }

    private static String getValidContact(Scanner scanner) {
        String contactNumber;
        while (true) {
            contactNumber = scanner.nextLine();
            if (contactNumber.matches("\\d{11}")) {
                break;
            } else {
                System.out.print("Invalid contact number. It must be exactly 11 digits long. \nPlease re-enter: ");
            }
        }
        return contactNumber;
    }

    private static String getValidAccountNumber(Scanner scanner) {
        String accountNumber;
        while (true) {
            accountNumber = scanner.nextLine();
            if (accountNumber.matches("\\d{11,12}")) {
                break;
            } else {
                System.out.print("Invalid account number. It must be 11-12 digits long.\nPlease re-enter: ");
            }
        }
        return accountNumber;
    }
}
