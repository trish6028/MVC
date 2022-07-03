package view.tm;

public class MedicineTm {
    private String MedicineId;
    private String Name;
    private String Description;
    private String PetId;
    private String Date;

    public MedicineTm() {
    }

    public MedicineTm(String medicineId, String name, String description, String petId, String date) {
        setMedicineId(medicineId);
        setName(name);
        setDescription(description);
        setPetId(petId);
        setDate(date);
    }

    public String getMedicineId() {
        return MedicineId;
    }

    public void setMedicineId(String medicineId) {
        MedicineId = medicineId;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getPetId() {
        return PetId;
    }

    public void setPetId(String petId) {
        PetId = petId;
    }

    public String getDate() {
        return Date;
    }

    @Override
    public String toString() {
        return "Medicine{" +
                "MedicineId='" + MedicineId + '\'' +
                ", Name='" + Name + '\'' +
                ", Description='" + Description + '\'' +
                ", PetId='" + PetId + '\'' +
                ", Date='" + Date + '\'' +
                '}';
    }

    public void setDate(String date) {
        Date = date;

    }
}
