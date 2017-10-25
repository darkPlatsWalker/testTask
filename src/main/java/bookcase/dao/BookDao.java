package bookcase.dao;

import bookcase.model.Book;

import java.util.List;

public interface BookDao {
    void addBook(Book book);

    void updateBook(Book book);

    void removeBook(int id);

    void setRead(int id);

    Book getBookById(int id);

    List<Book> showUnread();

    List<Book> searchByYear(int year);

    List<Book> searchByAuthor(String author);

    List<Book> searchByTheme(String theme);

    public Integer getNumberOfPages();

    public List<Book> tenBooks(Integer pageNum);
}
