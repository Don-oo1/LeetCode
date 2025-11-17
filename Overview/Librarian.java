package Overview;

public  class Librarian extends User{

    private String employeeNumber;
    void displayDashboard(){
        System.out.println( "Librarian Dashboard and the employeeNumber is"+employeeNumber );
    }
    boolean canBorrowBooks(){
        return true;
    }
    void addNewBook(Books book){
        //todo
    }
    void removeBook(Books book){
        //todo
    }


}
