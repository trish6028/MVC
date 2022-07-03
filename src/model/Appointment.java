package model;

public class Appointment {

    private String  AppointmentId;
    private String  PetOwnerId;
    private String Contact;
    private String  Date;
    private String time;

    public Appointment() {
    }

    public Appointment(String appointmentId, String petOwnerId, String contact, String date, String time) {
        setAppointmentId(appointmentId);
        setPetOwnerId(petOwnerId);
        setContact(contact);
        setDate(date);
        this.setTime(time);
    }

    public String getAppointmentId() {
        return AppointmentId;
    }

    public void setAppointmentId(String appointmentId) {
        AppointmentId = appointmentId;
    }

    public String getPetOwnerId() {
        return PetOwnerId;
    }

    public void setPetOwnerId(String petOwnerId) {
        PetOwnerId = petOwnerId;
    }

    public String getContact() {
        return Contact;
    }

    public void setContact(String contact) {
        Contact = contact;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public String getTime() {
        return time;
    }

    @Override
    public String toString() {
        return "Appointment{" +
                "AppointmentId='" + AppointmentId + '\'' +
                ", PetOwnerId='" + PetOwnerId + '\'' +
                ", Contact='" + Contact + '\'' +
                ", Date='" + Date + '\'' +
                ", time='" + time + '\'' +
                '}';
    }

    public void setTime(String time) {
        this.time = time;



    }
}

