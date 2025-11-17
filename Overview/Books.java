package Overview;

public abstract class Books implements Lendable{

    String isbn;
    String title;
    String author;
    boolean isAvailable;

    @Override
    public boolean lend(User user) {
       if(isAvailable && user.canBorrowBooks()){
           isAvailable = false;
           return true;
       }
       return false;
    }

    public void returnBook(User user){
        isAvailable = true;
    }
    public boolean isAvailable(){
        return isAvailable;
    }
    abstract void displayBookDetails();


}
