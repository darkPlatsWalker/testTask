package bookcase.service;

import bookcase.model.Book;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface BookService {
    public void addBook(Book book);

    public void updateBook(Book book);

    public void removeBook(int id);

    public void setRead(int id);

    public Book getBookById(int id);

    List<Book> showUnread();

    List<Book> searchByYear(int year);

    List<Book> searchByAuthor(String author);

    List<Book> searchByTheme(String theme);

    public Integer getNumberOfPages();

    public List<Book> tenBooks(Integer pageNum);
}
