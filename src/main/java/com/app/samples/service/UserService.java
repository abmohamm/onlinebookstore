package com.app.samples.service;

import java.util.List;

import com.app.samples.model.Book;
import com.app.samples.model.Login;
import com.app.samples.model.User;

public interface UserService {

  void register(User user);

  User validateUser(Login login);

  public List<Book> searchBasedOnId(int id);

  String addBook(Book book);

  String updateBook(Book book);

  public List<Integer> getBookCategory();

  public List<Integer> getBooksIdList();

  public String deleteBook(Book book);

  public List<String> getBooks();

  public List<String> getBooksBasedOnDepartment(Book book);

  public List<Book> searchBasedOnIsbn(int isbn);

  public List<Book> searchBasedOnAuthor(String author);

  public List<Book> searchBasedOnTitle(String title);

  public List<Book> searchBasedOnPublisher(String publisher);

  public List<Integer> getBooksIsbnList();

  public List<Book> getBooksBasedOnAuthor(String name);

  public List<String> getBookAuthorList();

  public List<String> getBookTitleList();

  public List<String> getBookPublisherList();;

  public List<Book> getTotalBooks();
}
