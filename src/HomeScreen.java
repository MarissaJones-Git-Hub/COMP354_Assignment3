import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

/**
    Represents the home screen for the MediLink appointment system.

    This class welcomes the user and handles all options regarding scheduling appointments.
    main(String) -> Home screen menu
    employeeMenu() -> Employee sub-menu
    patientMenu() -> Patient sub-menu
    addAppointment() -> Method to add appointments
    printAppointments() -> Method to print all appointments
    viewMyAppointments() -> Method to view personal appointments
    scheduleAppointment() -> Method to schedule an appointment
    prompt(String) -> Helper method for user prompts
 */
public class HomeScreen {
    private static final Scanner scanner = new Scanner(System.in);

    /*
        Main menu and landing page for users.
        Funnels users to the correct sub-menu for their role, exits on "0".
     */
    public static void main(String[] args) {
        System.out.println("Welcome to the MediLink Appointment System!");
        while (true) {
            System.out.println("""
                    Who are you?
                    1. Clinic employee
                    2. Patient
                    0. Exit""");
            switch(prompt("Choose an option: ")) {
                case "1": employeeMenu(); break;
                case "2": patientMenu(); break;
                case "0":
                    System.out.println("Thank you for using MediLink!\nExiting...");
                    return;
                default:
                    System.out.println("Invalid choice, please enter either 0, 1, or 2.");
            }

        }
    }

    /*
        Sub-menu for employees.
        This handles the addition of appointment slots (PBI1) and the listing of all appointment slots (PBI5).
        Returns to home screen on "0"
     */
    private static void employeeMenu() {
        System.out.println("\nWelcome to the Employee Menu!");
        while (true) {
            System.out.println("""
                    What would you like to do?
                    1. Add appointment slot
                    2. List all appointment slots
                    0. Back to home screen""");
            switch(prompt("Choose an option: ")) {
                case "1": addAppointment(); break;
                case "2": printAppointments(); break;
                case "0": return;
                default:
                    System.out.println("Invalid choice, please enter either 0, 1, or 2.");
            }
        }
    }

    /*
        Sub-menu for patients.
        This handles the scheduling of appointment slots (PBI4) and the viewing of all scheduled appointments (PBI5).
        Returns to home screen on "0".
     */
    private static void patientMenu() {
        System.out.println("\nWelcome to the Patient Menu!");
        while (true) {
            System.out.println("""
                    What would you like to do?
                    1. Schedule an appointment
                    2. View scheduled appointments
                    0. Back to home screen""");
            switch(prompt("Choose an option: ")) {
                case "1": scheduleAppointment(); break;
                case "2": viewMyAppointments(); break;
                case "0": return;
                default:
                    System.out.println("Invalid choice, please enter either 0, 1, or 2.");
            }
        }
    }

    /*
        Employee Only.
        This method allows an employee to add an open appointment slot.
        Collects information on the doctor, the date, and the time for the appointment, including formatting checks.
        Creates an Appointment object with the given information and prints the added slot.
        Returns to previous menu on "0".
     */
    private static void addAppointment() {
        System.out.println("\nAdd an appointment slot!");
        System.out.println("Enter 0 at any point to return to the home screen");

        /*
            Enter the doctor's name, repeat until the user produces a valid name, returns to previous menu on "0".
         */
        String doctor;
        while (true) {
            doctor = prompt("Doctor's name: ");
            if (doctor.equals("0")) {
                System.out.println("Returning to previous menu...\n");
                return;
            }
            if (!doctor.isEmpty()) break;
            System.out.println("Doctor name cannot be empty, please try again.");
        }

        /*
            Enter the appointment date, repeat until the user produces a valid date, returns to previous menu on "0".
         */
        LocalDate date;
        while (true) {
            String input = prompt("Date (YYYY-MM-DD): ");
            if (input.equals("0")) {
                System.out.println("Returning to previous menu...\n");
                return;
            }
            try {
                date = LocalDate.parse(input);
            } catch (DateTimeParseException e) {
                System.out.println("Invalid date format, please try again.");
                continue;
            }
            if (date.isBefore(LocalDate.now())) {
                System.out.println("The date cannot be in the past, please try again.");
                continue;
            }
            break;
        }

        /*
            Enter the appointment time, repeat until the user produces a valid time, returns to previous menu on "0".
         */
        LocalTime time;
        while (true) {
            String input = prompt("Time (HH:MM, 24-hour): ");
            if (input.equals("0")) {
                System.out.println("Returning to previous menu...\n");
                return;
            }
            try {
                time = LocalTime.parse(input);
                break;
            } catch (DateTimeParseException e) {
                System.out.println("Invalid time format, please try again.");
            }
        }

        /*
            Creates a new appointment object.
         */
        Appointment appointment = new Appointment(doctor, date, time);
        System.out.println("Appointment slot has successfully been added at the following date and time:");
        System.out.println(appointment);
    }

    /*
        Employee Only.
        This method prints all scheduled appointments and available appointment slots (PBI5).
     */
    private static void printAppointments() {
        System.out.println("All appointment slots:");
        System.out.println("This feature is currently unavailable.");
    }

    /*
        Patient Only.
        This method prints all scheduled appointments for the user (PBI5).
     */
    private static void viewMyAppointments() {
        System.out.println("Your scheduled appointments:");
        System.out.println("This feature is currently unavailable.");
    }

    /*
        Patient Only.
        This method handles the scheduling of an appointment for the user (PBI4).
     */
    private static void scheduleAppointment() {
        System.out.println("Schedule your appointment:");
        System.out.println("This feature is currently unavailable.");
    }


    /*
        Helper method for user prompting.
        If the scanner has information, read and trim the line and return it as a prompt.
        Return 0 if the line is empty to treat as "Exit" or "Go back to home screen".
     */
    private static String prompt(String message) {
        System.out.print(message);
        return scanner.hasNextLine() ? scanner.nextLine().trim() : "0";
    }
}
