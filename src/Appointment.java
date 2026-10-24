import java.time.LocalDate;
import java.time.LocalTime;

/**
    This represents a single Appointment slot offered by the clinic.

    Uses doctor name, formatted date, and time to create an appointment slot.
    Patient name is set once appointment slot is scheduled.
    isBooked() -> returns patient name if booked, null if available
    book(String) -> sets the patient name after booking and assigns them to the appointment slot
 */
public class Appointment {
    private final String doctorName;
    private final LocalDate date;
    private final LocalTime time;
    private String patientName;

    /*
        Generic Constructor
     */
    public Appointment(String doctorName, LocalDate date, LocalTime time) {
        this.doctorName = doctorName;
        this.date = date;
        this.time = time;
    }

    public String getDoctorName() {return doctorName;}
    public LocalDate getDate() {return date;}
    public LocalTime getTime() { return time;}
    public String getPatientName() {return patientName;}
    public boolean isBooked() {return patientName != null;}

    /*
        This method assigns a patient to an appointment slot.
     */
    public void book(String patientName) {this.patientName = patientName;}

    /*
        String representation of an Appointment object.
        This includes the doctor's name, the date, and the time of the appointment.
        Checks if the appointment is assigned to a patient, displays the name if booked or available if not.
     */
    @Override
    public String toString() {
        return String.format("Doctor: DR. %s\nDate: %s\nTime: %s\n%s", doctorName, date, time,
                isBooked() ? "Booked by " + patientName : "Currently Available");
    }

}
