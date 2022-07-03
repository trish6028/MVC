package model;

public class Bill {
    private String BillId;
    private String Description;
    private double Payment;
    private String PetOwnerId;
    private String OwnerName;
    private String Contact;

    public Bill() {
    }

    public Bill(String billId, String description, double payment, String petOwnerId, String ownerName, String contact) {
        setBillId(billId);
        setDescription(description);
        setPayment(payment);
        setPetOwnerId(petOwnerId);
        setOwnerName(ownerName);
        setContact(contact);
    }

    public String getBillId() {
        return BillId;
    }

    public void setBillId(String billId) {
        BillId = billId;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public double getPayment() {
        return Payment;
    }

    public void setPayment(double payment) {
        Payment = payment;
    }

    public String getPetOwnerId() {
        return PetOwnerId;
    }

    public void setPetOwnerId(String petOwnerId) {
        PetOwnerId = petOwnerId;
    }

    public String getOwnerName() {
        return OwnerName;
    }

    public void setOwnerName(String ownerName) {
        OwnerName = ownerName;
    }

    public String getContact() {
        return Contact;
    }

    @Override
    public String toString() {
        return "Bill{" +
                "BillId='" + BillId + '\'' +
                ", Description='" + Description + '\'' +
                ", Payment=" + Payment +
                ", PetOwnerId='" + PetOwnerId + '\'' +
                ", OwnerName='" + OwnerName + '\'' +
                ", Contact='" + Contact + '\'' +
                '}';
    }

    public void setContact(String contact) {
        Contact = contact;


    }
}
