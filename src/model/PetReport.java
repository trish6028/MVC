package model;

public class PetReport {
    private String  PetReportId;
    private String  PetId;
    private String DoctorId;
    private String  Disease;
    private String Description;

    public PetReport() {
    }

    public PetReport(String petReportId, String petId, String doctorId, String disease, String description) {
        setPetReportId(petReportId);
        setPetId(petId);
        setDoctorId(doctorId);
        setDisease(disease);
        setDescription(description);
    }

    public String getPetReportId() {
        return PetReportId;
    }

    public void setPetReportId(String petReportId) {
        PetReportId = petReportId;
    }

    public String getPetId() {
        return PetId;
    }

    public void setPetId(String petId) {
        PetId = petId;
    }

    public String getDoctorId() {
        return DoctorId;
    }

    public void setDoctorId(String doctorId) {
        DoctorId = doctorId;
    }

    public String getDisease() {
        return Disease;
    }

    public void setDisease(String disease) {
        Disease = disease;
    }

    public String getDescription() {
        return Description;
    }

    @Override
    public String toString() {
        return "PetReport{" +
                "PetReportId='" + PetReportId + '\'' +
                ", PetId='" + PetId + '\'' +
                ", DoctorId='" + DoctorId + '\'' +
                ", Disease='" + Disease + '\'' +
                ", Description='" + Description + '\'' +
                '}';
    }

    public void setDescription(String description) {
        Description = description;

    }
}
