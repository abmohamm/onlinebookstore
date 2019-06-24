package com.app.samples.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;

import com.app.samples.dao.UserDao;
import com.app.samples.model.Book;
import com.app.samples.model.Login;
import com.app.samples.model.User;

public class UserServiceImpl implements UserService {

  @Autowired
  public UserDao userDao;

  public void register(User user) {
    userDao.register(user);
  }

  public User validateUser(Login login) {
    return userDao.validateUser(login);
  }

  @Override
  public String addBook(Book book) {
    String result = "";
    if(book != null) {
      result = userDao.addBook(book);      
    }
    return result;
  }

  @Override
  public List<Integer> getBookCategory() {
    List<Integer> list = null;
    try {
      list = userDao.getBookCategory();
    }
    catch(DataAccessException dataAccessException) {
      dataAccessException.printStackTrace();
    }
    return list;
  }

  @Override
  public List<Integer> getBooksIdList() {
    List<Integer> booksIdList = null;
    try {
      booksIdList = userDao.getBookIdList();
    }
    catch(DataAccessException dataAccessException) {
      dataAccessException.printStackTrace();
    }
    return booksIdList;
  }

  @Override
  public String updateBook(Book book) {
    String result = "";
    if(book != null) {
      result = userDao.updateBook(book);
    }
    return result;
  }

  @Override
  public String deleteBook(Book book) {
    String result = null;
    try {
      result = userDao.deleteBook(book);
    }
    catch(DataAccessException exception) {
      exception.printStackTrace();
    }
    return result;
  }

  @Override
  public List<String> getBooks() {
    List<String> list = null;
    try {
      list = userDao.getBooks();
    }
    catch(Exception ex) {
      ex.printStackTrace();
    }
    return list;
  }

  @Override
  public List<String> getBooksBasedOnDepartment(Book book) {
    List<String> booksList = null;
    int bookCategoryId = 0;
    if(book.getBookCategoryId( )!= null && !book.getBookCategoryId().isEmpty()) {
      String arr[] = book.getBookCategoryId().split(" "); 
      bookCategoryId = Integer.parseInt(arr[0]);
    }
    try {
      booksList = userDao.getBooksBasedOnDepartment(bookCategoryId);
    }
    catch(DataAccessException dataAccessException) {
      dataAccessException.printStackTrace();
    }
    return booksList;
  }

  @Override
  public List<Book> searchBasedOnIsbn(int isbn) {
    List<Book> books = null;
    if(!String.valueOf(isbn).isEmpty()) {
      books = userDao.searchBasedOnIsbn(isbn);
    }
    return books;
  }

  @Override
  public List<Integer> getBooksIsbnList() {
    List<Integer> booksListIsbn = null;
    try {
      booksListIsbn = userDao.getBooksIsbnList();
    }
    catch(DataAccessException dataAccessException) {
      dataAccessException.printStackTrace();
    }
    return booksListIsbn;
  }

  @Override
  public List<Book> getBooksBasedOnAuthor(String name) {
    List<Book> books = null;
    if(!name.isEmpty()) {
      books = userDao.getBooksBasedOnAuthor(name);
    }
    return books;
  }

  @Override
  public List<String> getBookAuthorList() {
    List<String> booksAuthorList = null;
    try {
      booksAuthorList = userDao.getBookAuthorList();
    }
    catch(DataAccessException dataAccessException) {
      dataAccessException.printStackTrace();
    }
    return booksAuthorList;
  }

  @Override
  public List<String> getBookTitleList() {
    List<String> booksTitleList = null;
    try {
      booksTitleList = userDao.getBookTitleList();
    }
    catch(DataAccessException dataAccessException) {
      dataAccessException.printStackTrace();
    }
    return booksTitleList;
  }

  @Override
  public List<String> getBookPublisherList() {
    List<String> bookPublishersList = null;
    try {
      bookPublishersList = userDao.getBookPublisherList();
    }
    catch(DataAccessException dataAccessException) {
      dataAccessException.printStackTrace();
    }
    return bookPublishersList;
  }

  @Override
  public List<Book> searchBasedOnAuthor(String author) {
    List<Book> books = null;
    if(!author.isEmpty()) {
      books = userDao.searchBasedOnAuthor(author);
    }
    return books;
  }

  @Override
  public List<Book> searchBasedOnTitle(String title) {
    List<Book> books = null;
    if(!title.isEmpty()) {
      books = userDao.searchBasedOnTitle(title);
    }
    return books;
  }

  @Override
  public List<Book> searchBasedOnId(int id) {
    List<Book> books = null;
    if(id != 0 && !String.valueOf(id).isEmpty()) {
      books = userDao.searchBasedOnId(id);
    }
    return books;
  }

  @Override
  public List<Book> searchBasedOnPublisher(String publisher) {
    List<Book> books = null;
    if(!publisher.isEmpty()) {
      books = userDao.searchBasedOnPublisher(publisher);
    }
    return books;
  }

  @Override
  public List<Book> getTotalBooks() {
    List<Book> books = null;
    books = userDao.getTotalBooks();
    return books;
  }

}
