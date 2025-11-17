package Overview;

public abstract class User {


    final static int generateUniqueId(){
        return 0;
    }
    static int totalUsers = 0 ;
    static int getTotalUsers(){
        return totalUsers;
    }
    public User(){
        ++totalUsers;
    }
    abstract void displayDashboard();
    abstract boolean canBorrowBooks();


    public User(String name,String  contactInfo){
        this.name = name;
        this.contactInfo = contactInfo;
    }
    public User(User user){
         this(user.name, user.contactInfo);
    }

    private String UserId;
    private String name;
    private String contactInfo;

    String getName() {
        return name;
    }
    String getContactInfo() {
        return contactInfo;
    }
    void setname(String name){
        this.name = name;
    }
    void setContactInfo(String contactInfo){
        this.contactInfo = contactInfo;
    }


}
