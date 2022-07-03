package view.tm;

public class PetTm {




    private String  PetId;
    private String PetAddress;
    private String PetOwnerId;
    private String PetName;
    private int Age;
    private String PetType;
    private int PetTypeId;

    public PetTm() {
    }

    public PetTm(String petId, String petAddress, String petOwnerId, String petName, int age, String petType, int petTypeId) {
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
        return "PetTm{" +
                "PetId='" + PetId + '\'' +
                ", PetAddress='" + PetAddress + '\'' +
                ", PetOwnerId='" + PetOwnerId + '\'' +
                ", PetName='" + PetName + '\'' +
                ", Age=" + Age +
                ", PetType='" + PetType + '\'' +
                ", PetTypeId=" + PetTypeId +
                '}';
    }
}
