import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 Generic Calendar object which acts as a way to store all appointments.

 Allows the addition of a new appointment to the calendar.
 addAppointment(Appointment) -> adds an appointment to the calendar
 getAppointments() -> returns the list of appointments in the calendar
 hasSlot(String, LocalDate, LocalTime) -> returns true if the appointment already exists
 */
public class Calendar {
    private static final List<Appointment> appointments = new ArrayList<>();

    /*
        Adds an appointment to the Calendar.
     */
    public void addAppointment(Appointment appointment) {
        appointments.add(appointment);
    }

    /*
        Gets all appointments in the Calendar list.
     */
    public List<Appointment> getAppointments() {
        return Collections.unmodifiableList(appointments);
    }

    /*
        Checks if the given doctor already has an appointment at the given date and time.
        Returns true if the appointment already exists, false otherwise.
     */
    public boolean hasSlot(String doctor, LocalDate date, LocalTime time) {
        for (Appointment apt : appointments) {
            if (apt.getDoctorName().equalsIgnoreCase(doctor) &&
                    apt.getDate().equals(date) && apt.getTime().equals(time)) {
                return true;
            }
        }
        return false;
    }
}