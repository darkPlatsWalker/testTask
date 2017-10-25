package bookcase.service;

import bookcase.dao.BookDao;
import bookcase.model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {
    private BookDao bookDao;

    @Autowired
    public void setBookDao(BookDao bookDao) {
        this.bookDao = bookDao;
    }

    @Override
    @Transactional
    public void addBook(Book book) {
        this.bookDao.addBook(book);
    }

    @Override
    @Transactional
    public void updateBook(Book book) {
        this.bookDao.updateBook(book);
    }

    @Override
    @Transactional
    public void setRead(int id) {
        this.bookDao.setRead(id);
    }

    @Override
    @Transactional
    public void removeBook(int id) {
        this.bookDao.removeBook(id);
    }

    @Override
    @Transactional
    public Book getBookById(int id) {
        return this.bookDao.getBookById(id);
    }

    @Override
    @Transactional
    public List<Book> showUnread() {
        return bookDao.showUnread();
    }

    @Override
    @Transactional
    public List<Book> searchByYear(int year) {
        return bookDao.searchByYear(year);
    }

    @Override
    @Transactional
    public List<Book> searchByAuthor(String author) {
        return bookDao.searchByAuthor(author);
    }

    @Override
    @Transactional
    public List<Book> searchByTheme(String theme) {
        return bookDao.searchByTheme(theme);
    }

    @Override
    @Transactional
    public Integer getNumberOfPages() {
        return this.bookDao.getNumberOfPages();
    }

    @Override
    @Transactional
    public List<Book> tenBooks(Integer pageNum) {
        return this.bookDao.tenBooks(pageNum);
    }
}
