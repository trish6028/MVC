package model;

import java.util.List;

public class Pet {
    private String  PetId;
    private String PetAddress;
    private String PetOwnerId;
    private String PetName;
    private int Age;
    private String PetType;
    private int PetTypeId;
    private String DoctorId;
    private String TreatmentName;
    private String TreatmentType;
    public Pet() {
    }

    public Pet(String petId, String petAddress, String petOwnerId, String petName, int age, String petType, int petTypeId, String doctorId, String treatmentName, String treatmentType) {
        PetId = petId;
        PetAddress = petAddress;
        PetOwnerId = petOwnerId;
        PetName = petName;
        Age = age;
        PetType = petType;
        PetTypeId = petTypeId;
        DoctorId = doctorId;
        TreatmentName = treatmentName;
        TreatmentType = treatmentType;
    }

    public Pet(String petId, String petAddress, String petOwnerId, String petName, int age, String petType, int petTypeId  ) {
        setPetId(petId);
        setPetAddress(petAddress);
        setPetOwnerId(petOwnerId);
        setPetName(petName);
        setAge(age);
        setPetType(petType);
        setPetTypeId(petTypeId);

    }

    public String getPetId() {
        return PetId;
    }

    public void setPetId(String petId) {
        PetId = petId;
    }

    public String getPetAddress() {
        return PetAddress;
    }

    public void setPetAddress(String petAddress) {
        PetAddress = petAddress;
    }

    public String getPetOwnerId() {
        return PetOwnerId;
    }

    public void setPetOwnerId(String petOwnerId) {
        PetOwnerId = petOwnerId;
    }

    public String getPetName() {
        return PetName;
    }

    public void setPetName(String petName) {
        PetName = petName;
    }

    public int getAge() {
        return Age;
    }

    public void setAge(int age) {
        Age = age;
    }

    public String getPetType() {
        return PetType;
    }

    public void setPetType(String petType) {
        PetType = petType;
    }

    public int getPetTypeId() {
        return PetTypeId;
    }

    public void setPetTypeId(int petTypeId) {
        PetTypeId = petTypeId;
    }





    @Override
    public String toString() {
        return "Pet{" +
                "PetId='" + PetId + '\'' +
                ", PetAddress='" + PetAddress + '\'' +
                ", PetOwnerId='" + PetOwnerId + '\'' +
                ", PetName='" + PetName + '\'' +
                ", Age=" + Age +
                ", PetType='" + PetType + '\'' +
                ", PetTypeId=" + PetTypeId +

                '}';
    }

    public String getDoctorId() {
        return DoctorId;
    }

    public void setDoctorId(String doctorId) {
        DoctorId = doctorId;
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
}
