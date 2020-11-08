/**
 * This class represents a book, which has a title, author, year of publication and different literary aspects.
 */
class Book {

    /** The title of this book. */
    final String bookTitle;

    /** The name of the author of this book. */
    final String bookAuthor;

    /** The year this book was published. */
    final int bookYearOfPublication;

    /** The comic value of this book. */
    int bookComicValue;

    /** The dramatic value of this book. */
    int bookDramaticValue;

    /** The educational value of this book. */
    int bookEducationalValue;

    /** The id of the current borrowe of this book. */
    int currentBorrowerId = -1;

    /** The id of the current book. */
    int thisBookId = -1;

    /*----=  Constructors  =-----*/

    /**
     * Creates a new book with the given characteristic.
     * @param bookTitle The title of the book.
     * @param bookAuthor The name of the author of the book.
     * @param bookYearOfPublication The year the book was published.
     * @param bookComicValue The comic value of the book.
     * @param bookDramaticValue The dramatic value of the book.
     * @param bookEducationalValue The educational value of the book.
     */
    Book(String bookTitle,
         String bookAuthor,
         int bookYearOfPublication,
         int bookComicValue,
         int bookDramaticValue,
         int bookEducationalValue){
        this.bookTitle = bookTitle;
        this.bookAuthor = bookAuthor;
        this.bookYearOfPublication = bookYearOfPublication;
        this.bookComicValue = bookComicValue;
        this.bookDramaticValue = bookDramaticValue;
        this.bookEducationalValue = bookEducationalValue;
    }

    /*----=  Instance Methods  =-----*/

    /**
     * Returns a string representation of the book, which is a sequence
     * of the title, author, year of publication and the total literary value of the book, separated by
     * commas, inclosed in square brackets. For example, if the book is
     * titled "Monkey Feet", was written by Ernie Douglas, published in 1987 and has a comic value of 7,
     * dramatic value of 3 and an educational value of 1, this method will return the string:
     * "[MonkeyFeet,Ernie Douglas,1987,11]"
     * @return the String representation of this book.
     */
    String stringRepresentation(){
        return "["+ bookTitle +","+bookAuthor+","+ bookYearOfPublication +","+getLiteraryValue()+"]";
    }

    /**gets the borrower id. If the book is not borrowed by anyone - the borrower id will be -1.*/
    int getCurrentBorrowerId(){
        return currentBorrowerId;
    }

    /**Calculates the sum of all of the comics values of the different */
    int getLiteraryValue(){
        return bookComicValue + bookDramaticValue + bookEducationalValue;
    }
    void setBorrowerId(int borrowerId){
        currentBorrowerId = borrowerId;
    }
    void returnBook(){
        currentBorrowerId = -1;
    }}