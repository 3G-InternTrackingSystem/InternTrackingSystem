public class User {

    // ATTRIBUTES
    private String name;
    private String eMail;
    private String phoneNumber;
    private String userType;
    private String userName;

    // CONSTRUCTOR
    public User( String name, String userName, String eMail, String phoneNumber, String userType){
        this.name = name;
        this.eMail = eMail;
        this.userName = userName;
        this.phoneNumber = phoneNumber;
        this.userType = userType;
    }

    // METHODS
    // Getter and setter methods
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String geteMail() {
        return eMail;
    }

    public void seteMail(String eMail) {
        this.eMail = eMail;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getUserType() {
        return userType;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    // Other functional methods


}
