package bookcase.dao;

import bookcase.model.Book;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import org.hibernate.query.Query;
import java.util.ArrayList;
import java.util.List;

@Repository
public class BookDaoImpl implements BookDao {
    private static final Logger logger = LoggerFactory.getLogger(BookDaoImpl.class);

    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void addBook(Book book) {
        Session session = this.sessionFactory.getCurrentSession();
        session.persist(book);
        logger.info("Book successfully saved. Book details: " + book);
    }

    @Override
    public void updateBook(Book book) {
        Session session = this.sessionFactory.getCurrentSession();
        session.update(book);
        logger.info("Book successfully update. Book details: " + book);
    }

    @Override
    public void setRead(int id) {
        Session session = this.sessionFactory.getCurrentSession();
        Book book = (Book) session.load(Book.class, id);
        if (!book.isRead()) {
            book.setRead(true);
            session.update(book);
            logger.info("Book is fully read now. Book details: " + book);
        }
        else logger.info("Book was already read, nothing changed");
    }

    @Override
    public void removeBook(int id) {
        Session session = this.sessionFactory.getCurrentSession();
        Book book = (Book) session.load(Book.class, id);

        if(book!=null){
            session.delete(book);
        }
        logger.info("Book successfully removed. Book details: " + book);
    }

    @Override
    public Book getBookById(int id) {
        Session session = this.sessionFactory.getCurrentSession();
        Book book = (Book) session.load(Book.class, id);
        logger.info("Book successfully loaded. Book details: " + book);

        return book;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Book> showUnread() {
        Session session = this.sessionFactory.getCurrentSession();
        List<Book> bookList = session.createQuery("from Book where readAlready = false").list();

        logger.info("unread books: ");
        for(Book book: bookList){
            logger.info("            " + book);
        }
        logger.info("----------------------------------");

        return bookList;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Book> searchByYear(int year) {
        Session session = this.sessionFactory.getCurrentSession();
        List<Book> bookList = session.createQuery("from Book where printYear = " + year).list();

        logger.info("Books print in " + year);
        for(Book book: bookList){
            logger.info("            " + book);
        }
        logger.info("----------------------------------");

        return bookList;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Book> searchByAuthor(String author) {
        Session session = this.sessionFactory.getCurrentSession();
        String qu = String.format("from Book where lower(author) like '%s'", "%" + author.toLowerCase() + "%");
        List<Book> bookList = session.createQuery(qu).list();

        logger.info("Books of " + author);
        for(Book book: bookList){
            logger.info("            " + book);
        }
        logger.info("----------------------------------");

        return bookList;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Book> searchByTheme(String theme) {
        Session session = this.sessionFactory.getCurrentSession();
        String th = theme.toLowerCase();
        String queryString = String.format("from Book where (lower(title) like '%s') or (lower(description) like '%s')", "%"+th+"%", "%"+th+"%");
        List<Book> bookList = session.createQuery(queryString).list();

        logger.info("Books about " + theme);
        for(Book book: bookList){
            logger.info("            " + book);
        }
        logger.info("----------------------------------");

        return bookList;
    }

    @Override
    public Integer getNumberOfPages() {
        Session session = this.sessionFactory.getCurrentSession();
        Integer rows =  (int)(long)(Long)session.createQuery("SELECT COUNT (*) FROM Book").list().get(0);
        Integer pageCount = rows/10;
        if (rows%10 != 0) pageCount++;
        return  pageCount;
    }

    @Override
    public List<Book> tenBooks(Integer pageNum) {
        Session session = this.sessionFactory.getCurrentSession();
        Query query = session.createQuery("from Book");
        query.setFirstResult(pageNum * 10);
        query.setMaxResults(10);
        List<Book> bookList = query.list();

        logger.info("Books page " + pageNum);
        for(Book book: bookList){
            logger.info("            " + book);
        }
        logger.info("----------------------------------");

        return bookList;
    }
}
