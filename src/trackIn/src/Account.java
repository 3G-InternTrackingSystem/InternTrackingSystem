public class Account {

    //Properties
    private String email;
    private String userName;
    private String password;
    private String userType;
    private int userID;


 public Account(String userName, String password, String userType){

     this.userName= userName;
     this.password = password;
     this.userType = userType;
     //userID will be initialised fro mthe database
}

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public String userType() {
        return userType;
    }

    public int getUserID () {
        return userID;
    }







}

