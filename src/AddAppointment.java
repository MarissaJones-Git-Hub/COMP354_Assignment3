import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;

/**
    This class stores the main functionality for adding appointments to a calendar as an employee.

    Uses the shared calendar to add appointments
    run() -> main workflow for adding an appointment
    promptDoctor() -> prompts user for doctor name
    promptDate() -> prompts user for appointment date
    promptTime(String, LocalDate) -> prompts user for appointment time and checks calendar for duplicate appointments
 */
public class AddAppointment {
    private final Calendar calendar;

    /*
        Generic AddAppointment object
     */
    public AddAppointment(Calendar calendar) {
        this.calendar = calendar;
    }

    /*
        Carries out the main workflow for adding an appointment.
        Collects information on the doctor, the date, and the time for the appointment, including formatting checks.
        Creates an Appointment object with the given information and prints the added slot.
        Returns to previous menu on "0".
     */
    public void run() {
        System.out.println("\nAdd an appointment slot!");
        System.out.println("Enter 0 at any point to return to the previous menu.");

        String doctor = promptDoctor();
        if (doctor == null) return;

        LocalDate date = promptDate();
        if (date == null) return;

        LocalTime time = promptTime(doctor, date);
        if (time == null) return;

        /*
            Creates a new appointment object and adds it to the calendar.
         */
        Appointment appointment = new Appointment(doctor, date, time);
        calendar.addAppointment(appointment);
        System.out.println("Appointment slot has successfully been added at the following date and time:");
        System.out.println(appointment);
    }

    /*
        Enter the doctor's name, repeat until the user produces a valid name, returns to previous menu on "0".
     */
    private String promptDoctor() {
        String doctor;
        while (true) {
            doctor = HomeScreen.prompt("Doctor's first and last name: ");
            if (doctor.equals("0")) {
                System.out.println("Returning to previous menu...\n");
                return null;
            }
            if (!doctor.isEmpty()) return doctor;
            System.out.println("Doctor name cannot be empty, please try again.");
        }
    }

    /*
        Enter the appointment date, repeat until the user produces a valid date, returns to previous menu on "0".
     */
    private LocalDate promptDate() {
        LocalDate date;
        while (true) {
            String input = HomeScreen.prompt("Date (YYYY-MM-DD): ");
            if (input.equals("0")) {
                System.out.println("Returning to previous menu...\n");
                return null;
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
            return date;
        }
    }

    /*
        Enter the appointment time, repeat until the user produces a valid time, returns to previous menu on "0".
     */
    private LocalTime promptTime(String doctor, LocalDate date) {
        LocalTime time;
        while (true) {
            String input = HomeScreen.prompt("Time (HH:MM, 24-hour): ");
            if (input.equals("0")) {
                System.out.println("Returning to previous menu...\n");
                return null;
            }
            try {
                time = LocalTime.parse(input);
            } catch (DateTimeParseException e) {
                System.out.println("Invalid time format, please try again.");
                continue;
            }
            if (date.equals(LocalDate.now()) && time.isBefore(LocalTime.now())) {
                System.out.println("The time cannot be in the past, please try again.");
                continue;
            }

            /*
                Checks if an appointment slot already exists for a doctor.
             */
            if (calendar.hasSlot(doctor, date, time)){
                System.out.println("This doctor already has an appointment in this slot, please try again.");
                continue;
            }
            return time;
        }
    }
}
