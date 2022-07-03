package model;

public class Doctor {
    private String DoctorId;
    private String DoctorName;
    private String Position;
    private String DoctorAddress;
    private double DoctorSalary;

    public Doctor() {
    }

    public Doctor(String doctorId, String doctorName, String position, String doctorAddress, double doctorSalary) {
        setDoctorId(doctorId);
        setDoctorName(doctorName);
        setPosition(position);
        setDoctorAddress(doctorAddress);
        setDoctorSalary(doctorSalary);
    }

    public String getDoctorId() {
        return DoctorId;
    }

    public void setDoctorId(String doctorId) {
        DoctorId = doctorId;
    }

    public String getDoctorName() {
        return DoctorName;
    }

    public void setDoctorName(String doctorName) {
        DoctorName = doctorName;
    }

    public String getPosition() {
        return Position;
    }

    public void setPosition(String position) {
        Position = position;
    }

    public String getDoctorAddress() {
        return DoctorAddress;
    }

    public void setDoctorAddress(String doctorAddress) {
        DoctorAddress = doctorAddress;
    }

    public double getDoctorSalary() {
        return DoctorSalary;
    }

    @Override
    public String toString() {
        return "Doctor{" +
                "DoctorId='" + DoctorId + '\'' +
                ", DoctorName='" + DoctorName + '\'' +
                ", Position='" + Position + '\'' +
                ", DoctorAddress='" + DoctorAddress + '\'' +
                ", DoctorSalary=" + DoctorSalary +
                '}';
    }

    public void setDoctorSalary(double doctorSalary) {
        DoctorSalary = doctorSalary;




    }
}
