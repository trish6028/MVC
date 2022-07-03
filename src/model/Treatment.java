package model;

public class Treatment {
    private String DoctorId;
    private String PetId;
    private String TreatmentName;
    private String TreatmentType;

    public Treatment() {
    }

    public Treatment(String doctorId, String petId, String treatmentName, String treatmentType) {
        setDoctorId(doctorId);
        setPetId(petId);
        setTreatmentName(treatmentName);
        setTreatmentType(treatmentType);
    }

    public String getDoctorId() {
        return DoctorId;
    }

    public void setDoctorId(String doctorId) {
        DoctorId = doctorId;
    }

    public String getPetId() {
        return PetId;
    }

    public void setPetId(String petId) {
        PetId = petId;
    }

    public String getTreatmentName() {
        return TreatmentName;
    }

    public void setTreatmentName(String treatmentName) {
        TreatmentName = treatmentName;
    }

    public String getTreatmentType() {
        return TreatmentType;
    }

    public void setTreatmentType(String treatmentType) {
        TreatmentType = treatmentType;
    }

    @Override
    public String toString() {
        return "Treatment{" +
                "DoctorId='" + DoctorId + '\'' +
                ", PetId='" + PetId + '\'' +
                ", TreatmentName='" + TreatmentName + '\'' +
                ", TreatmentType='" + TreatmentType + '\'' +
                '}';
    }
}
