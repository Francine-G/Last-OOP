import java.io.*;
import java.util.Scanner;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

class IDManager {
    private static int currentID = 1;
    private static final String ID_FILE_PATH = System.getProperty("user.home") +
            System.getProperty("file.separator") + "Desktop" +
            System.getProperty("file.separator") + "Java Family" +
            System.getProperty("file.separator") + "IDCounter.txt";

    static {
        // Load the current ID from the file if it exists
        try (Scanner scanner = new Scanner(new File(ID_FILE_PATH))) {
            if (scanner.hasNextInt()) {
                currentID = scanner.nextInt();
            }
        } catch (FileNotFoundException e) {
            System.out.println("ID file not found. Starting from ID 1.");
        }
    }

    public static synchronized int getNextID() {
        int nextID = currentID++;
        saveCurrentID();
        return nextID;
    }

    private static void saveCurrentID() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(ID_FILE_PATH))) {
            writer.write(String.valueOf(currentID));
        } catch (IOException e) {
            System.out.println("Error saving current ID: " + e.getMessage());
        }
    }
}

abstract class UserType {
    protected String userName;

    public UserType(String userName) {
        this.userName = userName;
    }

    public abstract void displayTypeDetails();
}

class Facilitator extends UserType {
    public Facilitator() {
        super("Facilitator");
    }

    @Override
    public void displayTypeDetails() {
        System.out.println("Type: Facilitator - Facilitates events and activities.");
    }
}

class Donor extends UserType {
    public Donor() {
        super("Donor");
    }

    @Override
    public void displayTypeDetails() {
        System.out.println("Type: Donor - Provides financial or material support.");
    }
}

class Volunteer extends UserType {
    public Volunteer() {
        super("Volunteer");
    }

    @Override
    public void displayTypeDetails() {
        System.out.println("Type: Volunteer - Participates in on-ground activities.");
    }
}

class LoginUser {
    private String userName;
    private String userPassword;
    private UserType userType;

    public void setLogin(String userName, String userPassword, UserType userType) {
        this.userName = userName;
        this.userPassword = userPassword;
        this.userType = userType;
    }

    public void saveToFile() {
        int loginID = IDManager.getNextID();
        String filePath = System.getProperty("user.home") +
                System.getProperty("file.separator") + "Desktop" +
                System.getProperty("file.separator") + "Java Family" +
                System.getProperty("file.separator") + "UserDataBase.txt";
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true))) {
            writer.write("Action: Login, ID " + String.format("%03d", loginID) +
                    " - Name: " + userName + ", Username: " + userName +
                    ", User Type: " + userType.getClass().getSimpleName() + "\n");
            System.out.println("Login details saved successfully!");
        } catch (IOException e) {
            System.out.println("Error saving login details: " + e.getMessage());
        }
    }
}

class SignUp {
    private String userName;
    private String firstName;
    private String lastName;
    private String userPassword;
    private String userContact;
    private UserType userType;

    public SignUp(String userName, String userPassword, String firstName, String lastName, String userContact, UserType userType) {
        this.userName = userName;
        this.userPassword = userPassword;
        this.firstName = firstName;
        this.lastName = lastName;
        this.userContact = userContact;
        this.userType = userType;

        if (!userPassword.matches("\\d{6}")) {
            System.out.println("Invalid password. Password must be exactly 6 digits.");
            return;
        }

        if (!userContact.matches("\\d{11}")) {
            System.out.println("Invalid contact number. Please enter exactly 11 digits.");
            return;
        }

        if (userType == null) {
            System.out.println("Invalid user type selected. Please try again.");
            return;
        }

        saveToFile();
    }

    public void saveToFile() {
        int signUpID = IDManager.getNextID();
        String filePath = System.getProperty("user.home") +
                System.getProperty("file.separator") + "Desktop" +
                System.getProperty("file.separator") + "Java Family" +
                System.getProperty("file.separator") + "UserDataBase.txt";
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true))) {
            writer.write("Action: Sign-Up, ID " + String.format("%03d", signUpID) +
                    " - Name: " + firstName + " " + lastName + ", Username: " + userName +
                    ", User Type: " + userType.getClass().getSimpleName() + "\n");
            System.out.println("Sign-up details saved successfully!");
        } catch (IOException e) {
            System.out.println("Error saving sign-up details: " + e.getMessage());
        }
    }
}

public class user {
    public static void main(String[] args) {
        Scanner userInput = new Scanner(System.in);

        System.out.println("\n|===== 1. Login =====|                   |===== 2. Sign up =====|\n");
        System.out.print("Enter your choice: ");
        int option = userInput.nextInt();
        userInput.nextLine();

        switch (option) {
            case 1:
                System.out.print("Enter Username: ");
                String loginName = userInput.nextLine();

                System.out.print("Enter Password (6-Digits): ");
                String loginPass;
                while (true) {
                    loginPass = userInput.nextLine();
                    if (loginPass.matches("\\d{6}")) {
                        break;
                    } else {
                        System.out.println("Invalid password. Password must be exactly 6 digits.");
                        System.out.print("Enter Password (6-Digits): ");
                    }
                }

                UserType loginType = selectType(userInput);
                if (loginType == null) break;

                LoginUser loginUser = new LoginUser();
                loginUser.setLogin(loginName, loginPass, loginType);
                System.out.println("\nLogin Successful!");
                loginUser.saveToFile();
                break;

            case 2:
                System.out.print("Create a Username: ");
                String signUpName = userInput.nextLine();

                System.out.print("Create a Password (6-Digits): ");
                String signUpPass;
                while (true) {
                    signUpPass = userInput.nextLine();
                    if (signUpPass.matches("\\d{6}")) {
                        break;
                    } else {
                        System.out.println("Invalid password. Password must be exactly 6 digits.");
                        System.out.print("Create a Password (6-Digits): ");
                    }
                }

                System.out.print("Enter First Name: ");
                String firstName = userInput.nextLine();

                System.out.print("Enter Last Name: ");
                String lastName = userInput.nextLine();

                String contact;
                while (true) {
                    System.out.print("Enter your Contact Number (11 digits): ");
                    contact = userInput.nextLine();

                    if (contact.matches("\\d{11}")) {
                        break;
                    } else {
                        System.out.println("Invalid contact number. Please enter exactly 11 digits.");
                    }
                }

                UserType signUpType = selectType(userInput);
                if (signUpType == null) break;

                SignUp signUpUser = new SignUp(signUpName, signUpPass, firstName, lastName, contact, signUpType);
                break;

            default:
                System.out.println("Invalid option. Please select 1 or 2.");
                break;
        }

        userInput.close();
    }

    public static UserType selectType(Scanner userInput) {
        System.out.println("\nSelect Your Type:");
        System.out.println("1. Facilitator");
        System.out.println("2. Donor");
        System.out.println("3. Volunteer");
        System.out.print("Enter your type (1/2/3): ");
        int typeChoice = userInput.nextInt();
        userInput.nextLine();

        switch (typeChoice) {
            case 1:
                return new Facilitator();
            case 2:
                return new Donor();
            case 3:
                return new Volunteer();
            default:
                System.out.println("Invalid type selected. Please restart.");
                return null;
        }
    }
}