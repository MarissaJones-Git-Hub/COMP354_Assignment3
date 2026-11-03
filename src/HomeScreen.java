import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.List;
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
    private static Scanner scanner = new Scanner(System.in);
    private static final Calendar calendar = new Calendar();
    private static final AddAppointment addAppointment = new AddAppointment(calendar);
    private static final AppointmentSearch appointmentSearch = new AppointmentSearch(calendar);
    private static final AppointmentBooking appointmentBooking = new AppointmentBooking(calendar);
    private static final AppointmentViewer appointmentViewer = new AppointmentViewer(calendar);

    /*
        Main menu and landing page for users.
        Funnels users to the correct sub-menu for their role, exits on "0".
     */
    public static void main(String[] args) {
        boolean quit = false;

        while(!quit) {
            System.out.println("1. Run as User");
            System.out.println("2. Run test");
            System.out.println();

            switch(prompt("Choose an option: "))
            {
                case "1":
                    quit = true;
                    break;
                case "2":
                    scanner = new Scanner(testString());
                    quit = true;
                    break;
                default:
                    System.out.println("Invalid choice, please enter 0 or 1.\n");
            }
        }

        System.out.println("Welcome to the MediLink Appointment System!");
        while (true) {
            System.out.println("""
                    Who are you?
                    1. Clinic employee
                    2. Patient
                    0. Exit""");
            switch(prompt("Choose an option: ")) {
                case "1": System.out.println(); employeeMenu(); break;
                case "2": System.out.println(); patientMenu(); break;
                case "0":
                    System.out.println();
                    System.out.println("Thank you for using MediLink!\nExiting...");
                    return;
                default:
                    System.out.println("Invalid choice, please enter 0, 1, or 2.\n");
            }

        }
    }

    /*
        Sub-menu for employees.
        This handles the addition of appointment slots (PBI1) and the listing of all appointment slots (PBI5).
        Returns to home screen on "0"
     */
    private static void employeeMenu() {
        System.out.println("Welcome to the Employee Menu!");
        while (true) {
            System.out.println("""
                    What would you like to do?
                    1. Add appointment slot
                    2. View all scheduled appointments
                    0. Back to home screen""");
            switch(prompt("Choose an option: ")) {
                case "1": addAppointment(); break;
                case "2": printAppointments(); break;
                case "0": System.out.println(); return;
                default:
                    System.out.println("Invalid choice, please enter 0, 1, or 2.\n");
            }
        }
    }

    /*
        Sub-menu for patients.
        This handles the scheduling of appointment slots (PBI4) and the viewing of all scheduled appointments (PBI5).
        Returns to home screen on "0".
     */
    private static void patientMenu() {
        System.out.println("Welcome to the Patient Menu!");
        while (true) {
            System.out.println("""
                    What would you like to do?
                    1. Schedule an appointment
                    2. View scheduled appointments
                    0. Back to home screen""");
            switch(prompt("Choose an option: ")) {
                case "1": scheduleAppointment(); break;
                case "2": viewMyAppointments(); break;
                case "0": System.out.println(); return;
                default:
                    System.out.println("Invalid choice, please enter 0, 1, or 2.\n");
            }
        }
    }

    /*
        Employee Only.
        This method allows an employee to add an open appointment slot.
     */
    private static void addAppointment() {
        addAppointment.run();
    }

    /*
        Employee Only.
        This method prints all scheduled appointments and available appointment slots (PBI5).
     */
    private static void printAppointments() {
        System.out.println();
        System.out.println("All scheduled appointments:");
        appointmentViewer.viewAppointments();
        System.out.println();
    }

    /*
        Patient Only.
        This method prints all scheduled appointments for the user (PBI5).
     */
    private static void viewMyAppointments() {
        System.out.println();
        System.out.print("Enter Name: ");
        String patientName = scanner.nextLine();
        System.out.println();
        System.out.println("Your scheduled appointments:");
        appointmentViewer.viewAppointments(patientName);
        System.out.println();
    }

    /*
        Patient Only.
        This method handles the scheduling of an appointment for the user (PBI4).
     */
    private static void scheduleAppointment() {
        System.out.println();
        System.out.println("Schedule your appointment:");

        Appointment appointment = null;

        String doctorName = "";
        LocalDate date = null;
        LocalTime time = null;

        boolean quit = false;

        do {
            System.out.println();
            System.out.print("Current Filters: ");

            if(doctorName.isBlank() && date == null && time == null)
            {
                System.out.println("None");
            }
            else
            {
                boolean addComma = false;

                if(!doctorName.isBlank())
                {
                    System.out.print("Doctor: " + doctorName);
                    addComma = true;
                }

                if(date != null)
                {
                    if(addComma)
                    {
                        System.out.print(", ");
                        addComma = false;
                    }

                    System.out.print("Date: " + date);
                    addComma = true;
                }

                if(time != null)
                {
                    if(addComma)
                    {
                        System.out.print(", ");
                        addComma = false;
                    }

                    System.out.print("Time: " + time);
                }

                System.out.println();
            }

            System.out.println("How would you like to search for appointments?");
            System.out.println("1. Filter by Doctor");
            System.out.println("2. Filter by Date");
            System.out.println("3. Filter by Time");
            System.out.println("4. Remove filter");
            System.out.println("5. Search");
            System.out.println("0. Quit");

            System.out.print("Choose an option: ");
            String choice = scanner.nextLine();

            switch(choice)
            {
                case "1":
                    System.out.println();
                    doctorName = prompt("Doctor's first and last name: ");
                    break;
                case "2":
                    while(true) {
                        System.out.println();
                        String dateString = prompt("Date (YYYY-MM-DD): ");
                        try {
                            date = LocalDate.parse(dateString);
                            break;
                        } catch (DateTimeParseException e) {
                            System.out.println("Invalid date format, please try again.");
                        }
                    }
                    break;
                case "3":
                    while(true) {
                        System.out.println();
                        String timeString = prompt("Time (HH:MM, 24-hour): ");
                        try {
                            time = LocalTime.parse(timeString);
                            break;
                        } catch (DateTimeParseException e) {
                            System.out.println("Invalid time format, please try again.");
                        }
                    }
                    break;
                case "4":
                    boolean removeFilterQuit = false;

                    do {
                        System.out.println();
                        System.out.println("Which filter would you like to remove?");
                        System.out.println("1. Doctor");
                        System.out.println("2. Date");
                        System.out.println("3. Time");
                        System.out.println("0. Back");

                        String choice2 = prompt("Choose an option: ");

                        switch (choice2)
                        {
                            case "1":
                                doctorName = "";
                                break;
                            case "2":
                                date = null;
                                break;
                            case "3":
                                time = null;
                                break;
                            case "0":
                                removeFilterQuit = true;
                                break;
                            default:
                                System.out.println("Invalid choice, please enter 1, 2, 3, or 0.");
                        }
                    } while(!removeFilterQuit);
                    break;
                case "5":
                    List<Appointment> matchingAppointments = appointmentSearch.search(doctorName, date, time);
                    appointmentSearch.displayResults(matchingAppointments);

                    if(!matchingAppointments.isEmpty())
                    {
                        boolean invalidInput;

                        do {
                            invalidInput = false;

                            System.out.println();
                            String choice3 = prompt("Select an appointment to book (0 to go back): ");

                            try {
                                int choiceInt = Integer.parseInt(choice3);

                                if (choiceInt > matchingAppointments.size() || choiceInt < 0) {
                                    throw (new NumberFormatException());
                                }

                                if (choiceInt == 0) {
                                    break;
                                }

                                appointment = matchingAppointments.get(choiceInt - 1);
                            } catch (NumberFormatException e) {
                                System.out.println("Invalid choice, please enter a valid number.");
                                invalidInput = true;
                            }
                        } while(invalidInput);
                    }
                    break;
                case "0":
                    quit = true;
                    System.out.println();
                    break;
                default:
                    System.out.println("Invalid choice, please enter 1, 2, 3, 4, 5, or 0.");
            }
        } while(!quit && appointment == null);

        if(quit)
        {
            return;
        }

        if(appointment != null)
        {
            appointmentBooking.bookAppointment(appointment, prompt("Enter name: "));
        }
    }

    public static String testString()
    {
        return """
                1
                1
                Marissa Jones
                2026-12-01
                16:00
                2
                1
                Tioluwani Adesina
                2026-11-14
                14:00
                0
                2
                2
                Tina Joudi
                1
                5
                1
                Tina Joudi
                1
                1
                Tioluwani Adesina
                2
                2026-12-06
                5
                4
                2
                0
                5
                1
                Troy Williams
                2
                Tina Joudi
                2
                Troy Williams
                0
                1
                2
                0
                0
                """;
    }

    /*
        Helper method for user prompting.
        If the scanner has information, read and trim the line and return it as a prompt.
        Return 0 if there is no more input to treat as "Exit" or "Go back to home screen".
     */
    public static String prompt(String message) {
        System.out.print(message);
        return scanner.hasNextLine() ? scanner.nextLine().trim() : "0";
    }
}