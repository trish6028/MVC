package view.tm;

public class UserTm {

    private String UserId;
    private String FirstName;
    private String LastName;
    private String Email;
    private String Gen;
    private String DateOfBirth;
    private String Status;
    private String UserName;
    private String Password;

    public UserTm() {
    }

    public UserTm(String userId, String firstName, String lastName, String email, String gen, String dateOfBirth, String status, String userName, String password) {
        setUserId(userId);
        setFirstName(firstName);
        setLastName(lastName);
        setEmail(email);
        setGen(gen);
        setDateOfBirth(dateOfBirth);
        setStatus(status);
        setUserName(userName);
        setPassword(password);
    }

    public String getUserId() {
        return UserId;
    }

    public void setUserId(String userId) {
        UserId = userId;
    }

    public String getFirstName() {
        return FirstName;
    }

    public void setFirstName(String firstName) {
        FirstName = firstName;
    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String lastName) {
        LastName = lastName;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getGen() {
        return Gen;
    }

    public void setGen(String gen) {
        Gen = gen;
    }

    public String getDateOfBirth() {
        return DateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        DateOfBirth = dateOfBirth;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public String getPassword() {
        return Password;
    }

    @Override
    public String toString() {
        return "User{" +
                "UserId='" + UserId + '\'' +
                ", FirstName='" + FirstName + '\'' +
                ", LastName='" + LastName + '\'' +
                ", Email='" + Email + '\'' +
                ", Gen='" + Gen + '\'' +
                ", DateOfBirth='" + DateOfBirth + '\'' +
                ", Status='" + Status + '\'' +
                ", UserName='" + UserName + '\'' +
                ", Password='" + Password + '\'' +
                '}';
    }

    public void setPassword(String password) {
        Password = password;


    }

 }