
public class AppointmentViewer {

    // Employee view: displays all booked appointments.
    public void viewAppointments(Calendar calendar) {
        boolean found = false;

        for (Appointment appointment : calendar.getAppointments()) {
            if (appointment.isBooked()) {
                System.out.println(appointment);
                System.out.println("--------------------");
                found = true;
            }
        }

        if (!found) {
            System.out.println("No scheduled appointments.");
        }
    }

    // Patient view: displays appointments booked by this patient.
    public void viewAppointments(Calendar calendar, String patientName) {
        boolean found = false;

        for (Appointment appointment : calendar.getAppointments()) {
            if (appointment.isBooked()
                    && appointment.getPatientName()
                            .equalsIgnoreCase(patientName)) {
                System.out.println(appointment);
                System.out.println("--------------------");
                found = true;
            }
        }

        if (!found) {
            System.out.println("No scheduled appointments for this patient.");
        }
    }
}
