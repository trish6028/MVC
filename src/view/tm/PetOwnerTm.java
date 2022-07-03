package view.tm;

public class PetOwnerTm {
    private String PetOwnerId;
    private String PetOwnerName;
    private String Contact;



    public PetOwnerTm(String petOwnerId, String petOwnerName, String contact) {
        PetOwnerId = petOwnerId;
        PetOwnerName = petOwnerName;
        Contact = contact;
    }

    public String getPetOwnerId() {
        return PetOwnerId;
    }

    public void setPetOwnerId(String petOwnerId) {
        PetOwnerId = petOwnerId;
    }

    public String getPetOwnerName() {
        return PetOwnerName;
    }

    public void setPetOwnerName(String petOwnerName) {
        PetOwnerName = petOwnerName;
    }

    public String getContact() {
        return Contact;
    }

    public void setContact(String contact) {
        Contact = contact;
    }

    @Override
    public String toString() {
        return "PetOwner{" +
                "PetOwnerId='" + PetOwnerId + '\'' +
                ", PetOwnerName='" + PetOwnerName + '\'' +
                ", Contact=" + Contact +
                '}';
    }
}
