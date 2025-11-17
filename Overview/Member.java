package Overview;

public class Member  extends User{


    public Member(){

    }
    int borrowedBooksCount=0;
    final int MAX_BORROW_LIMIT = 5;

    void displayDashboard(){
        System.out.println("Member Dashboard and Books Borrowed: "+borrowedBooksCount);
    }

    boolean canBorrowBooks(){
        if( borrowedBooksCount < MAX_BORROW_LIMIT  ) return true;
        return false;
    }


}
