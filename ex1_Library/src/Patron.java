/**
 * This class represents a library patron that has a name
 * and assigns values to different literary aspects of books.
 */
class Patron {
    final String patronFirstName;

    final String patronLastName;
    int comicTendency;
    int dramaticTendency;
    int educationalTendency;
    int patronEnjoymentThreshold;
    int thisPatronId = -1;

    /*----=  Constructors  =-----*/
    /**
     @param patronFirstName - The first name of the patron.
     @param patronLastName - The last name of the patron.
     @param comicTendency - The weight the patron assigns to the comic aspects of books.
     @param dramaticTendency - The weight the patron assigns to the dramatic aspects of books.
     @param educationalTendency - The weight the patron assigns to the educational aspects of books.
     @param patronEnjoymentThreshold - The minimal literary value a book must have for this patron to enjoy it.
     */

    Patron(String patronFirstName,
           String patronLastName,
           int comicTendency,
           int dramaticTendency,
           int educationalTendency,
           int patronEnjoymentThreshold) {
        this.patronFirstName = patronFirstName;
        this.patronLastName = patronLastName;
        this.comicTendency = comicTendency;
        this.dramaticTendency = dramaticTendency;
        this.educationalTendency = educationalTendency;
        this.patronEnjoymentThreshold = patronEnjoymentThreshold;
    }
    /**Returns a string representation of the patron, which is a sequence of its first and last name,
     * separated by a single white space.
     * For example, if the patron's first name is "Ricky" and his last name is "Bobby",
     * this method will return the String "Ricky Bobby".
     * Returns:
     * the String representation of this patron.*/
    String stringRepresentation() {
        return patronFirstName + " " + patronLastName;
    }

    /**Returns the literary value this patron assigns to the given book.
     Parameters:
     book - The book to asses.
     Returns:
     the literary value this patron assigns to the given book.*/
    int getBookScore(Book book) {
        int sumComic = book.bookComicValue * comicTendency;
        int sumDrama = book.bookDramaticValue * dramaticTendency;
        int sumEdu = book.bookEducationalValue * educationalTendency;
        return sumComic + sumDrama + sumEdu;
    }
    /**Returns true of this patron will enjoy the given book, false otherwise.
     Parameters:
     book - The book to asses.
     Returns:
     true of this patron will enjoy the given book, false otherwise.*/
    boolean willEnjoyBook(Book book) {
        return getBookScore(book) > patronEnjoymentThreshold;
    }
}



