/**This class represents a library, which hold a collection of books.
 * Patrons can register at the library to be able to check out books,
 * if a copy of the requested book is available.
 * */

class Library {
    int maxBookNum;
    int maxBorrowedNum;
    int maxPatronNum;
    Book[] bookLst; // an array of all the books the library has.
    Patron[] patronsLst; // an array of all the patrons the library has.

    /** An array that document the borrows of the patrons. Every patron has his
    * own value in the borrowsCapacityPerPatron array. His individual index is the same as his
    * index. Every time a patron borrows or returns a book, the value changes. The default is 0.*/
    int[] borrowsCapacityPerPatron;

    /*----=  Constructors  =-----*/
    /**Creates a new library with the given parameters.
     * @param maxBookCapacity - The maximal number of books this library can hold.
     * @param maxBorrowedBooks - The maximal number of books this library
     * allows a single patron to borrow at the same time.
     * @param maxPatronCapacity - The maximal number of registered patrons this library can handle.
     * */
    Library(int maxBookCapacity, int maxBorrowedBooks, int maxPatronCapacity) {
        this.maxBookNum = maxBookCapacity;
        this.maxBorrowedNum = maxBorrowedBooks;
        this.maxPatronNum = maxPatronCapacity;
        this.bookLst = new Book[maxBookNum];
        this.patronsLst = new Patron[maxPatronNum];
        this.borrowsCapacityPerPatron = new int[maxPatronNum];
    }

    /**Adds the given book to this library, if there is place available,
     * and it isn't already in the library.*/
    int addBookToLibrary(Book book) {
        if (maxBookNum == 0){
            return -1;
        }
        else if (isBookIdValid(book.thisBookId)){
            return book.thisBookId;
        }
        else{
            for (int i = 0; i < maxBookNum; i++) {
                if (bookLst[i] == null) {
                    bookLst[i] = book; // the id of the book also express his index in the book's array.
                    book.thisBookId = i;
                    return book.thisBookId;
                }
            }
        }
        return -1;
    }

    /**Returns true if the given number is an id of some book in the library, false otherwise.*/
    boolean isBookIdValid(int bookId) {
        //checking if the id even makes sense, before diving into the iteration
        if (bookId < 0 || bookId > maxBookNum){
            return false;
        }
        for (int i = 0; i < maxBookNum; i++) {
            if (bookLst[i] == null){
                return false;
            }
            else if (bookId == bookLst[i].thisBookId) {
                return true;
            }
        }
        return false;
    }

    /**Finds a book by his ID. The function runs on the array that carries the information about the books
     * in the library.*/
    Book getBookById(int bookId) {
        for (int i = 0; i < maxBookNum; i++) {
            if (bookLst[i] == null){
                return null;
            }
            else if (bookLst[i].thisBookId == bookId) {
                return bookLst[i];
            }
        }
        return null;
    }

    /**Returns the non-negative id number of the given book if he is owned by this library, -1 otherwise.*/
    int getBookId(Book book) {
        if (!isBookIdValid(book.thisBookId)) {
            return -1;
        }
        return book.thisBookId;
    }

    /**Returns true if the book with the given id is available, false otherwise.*/
    boolean isBookAvailable(int bookId) {
        if (!isBookIdValid(bookId)) {
            return false;
        } else {
            Book currentBook = getBookById(bookId);
            return (currentBook.currentBorrowerId == -1); //
            // if the borrower id is -1, it means the book is available
        }
    }

    /**Registers the given Patron to this library, if there is a spot available.*/
    int registerPatronToLibrary(Patron patron) {
        if (maxPatronNum == 0){                //taking care of an edge case.
            return -1;
        }
        for (int i = 0; i < maxPatronNum; i++) {
            if (patronsLst[i] == null) {
                patronsLst[i] = patron; // the id of the patron also express his index in the patron's array.
                patron.thisPatronId = i;
                return patron.thisPatronId;
            }
            if (patronsLst[i] == patron) {
                return patron.thisPatronId;
            }
        }
        return -1;
    }

    /**Returns true if the given number is an id of a patron in the library, false otherwise.**/
    boolean isPatronIdValid(int patronId) {
        //checking if the id even makes sense, before diving into the iteration
        if (patronId < 0 || patronId > maxPatronNum){
            return false;
    }
        for (int i = 0; i < maxPatronNum; i++) {
        if (patronsLst[i] == null){
            return false;
        }
        else if (patronId == patronsLst[i].thisPatronId) {
            return true;
        }
    }
        return false;
}
    /**Returns the non-negative id number of the given patron if he is registered to this library,
     *  -1 otherwise.**/
    int getPatronId(Patron patron) {
        if (!isPatronIdValid(patron.thisPatronId)) {
            return -1;
        }
        return patron.thisPatronId;
    }

    /**Finds a book by his ID. The function runs on the array that carries the information about the patrons
     * in the library.*/
    Patron getPatronById(int patronId) {
        for (int i = 0; i < maxPatronNum; i++) {
            if (patronsLst[i].thisPatronId == patronId) {
                return patronsLst[i];
            }
        }
        return null;
    }

    /**Marks the book with the given id number as borrowed by the patron with the given patron id,
     * if this book is available,
     * the given patron isn't already borrowing the maximal number of books allowed,
     * and if the patron will enjoy this book.*/
    boolean borrowBook(int bookId, int patronId) {
        //1st step: checks if the book and the patron exists in this library.
        if (!isBookIdValid(bookId) || !isPatronIdValid(patronId)){
            return false;
        }
        // 2nd step: turns the ids into the object itself, and verifies that all the conditions above
        else {
            Book currentBook = getBookById(bookId);
            Patron currentPatron = getPatronById(patronId);
            if (isBookAvailable(bookId) && borrowsCapacityPerPatron[patronId] < maxBorrowedNum
                && currentPatron.willEnjoyBook(currentBook)) {
                currentBook.currentBorrowerId = patronId;
                // The next line updates the number of the borrowed books per patron.
                ++borrowsCapacityPerPatron[patronId];
                return true;
        } else {
                return false;
        }
    }
    }

    /**Return the given book.*/
    void returnBook(int bookId) {
        Book currentBook = getBookById(bookId);
        int borrwerId = currentBook.currentBorrowerId;
        --borrowsCapacityPerPatron[borrwerId]; //updates the n
        currentBook.returnBook();
    }

    /**Suggest the patron with the given id the book he will enjoy the most,
     * out of all available books he will enjoy, if any such exist.*/
    Book suggestBookToPatron(int patronId) {
        if (!isPatronIdValid(patronId)){
            return null;
        }
        Patron currentPatron = getPatronById(patronId);
        for (int i = 0; i < maxBookNum; i++) {
            if (currentPatron.willEnjoyBook(bookLst[i]) & bookLst[i].currentBorrowerId != -1) {
                return bookLst[i];
            }
        }
        return null;
    }
}
