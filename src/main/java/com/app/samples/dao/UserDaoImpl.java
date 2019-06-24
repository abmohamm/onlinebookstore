package com.app.samples.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;

import com.app.samples.model.Book;
import com.app.samples.model.Login;
import com.app.samples.model.User;

public class UserDaoImpl implements UserDao {

  private static final String AUTHOR = "author";
  private static final String TITLE = "title";
  private static final String PUBLISHER = "publisher";
  private static final String DESCRIPTION = "description";
  private static final String ID = "id";
  private static final String ISBN = "isbn";
  private static final String CATEGORY_ID = "category_id";
  private static final String CATEGORY_NAME = "category_name";

  @Autowired
  DataSource datasource;
  
  @Autowired
  JdbcTemplate jdbcTemplate;

  public void register(User user) {

    String sql = "insert into users values(?,?,?,?,?,?,?)";

    jdbcTemplate.update(sql, new Object[] { user.getUsername(), user.getPassword(), user.getFirstname(),
        user.getLastname(), user.getEmail(), user.getAddress(), user.getPhone() });
  }

  public User validateUser(Login login) {

    String sql = "select * from users where username='" + login.getUsername() + "' and password='" + login.getPassword()
    + "'";

    List<User> users = jdbcTemplate.query(sql, new UserMapper());

    return users.size() > 0 ? users.get(0) : null;
  }

  @Override
  public String addBook(Book book) {
    String result = "";
    int bookCategoryId = 0; 
    if(book != null) {
      if(book.getBookCategoryId() != null && !book.getBookCategoryId().isEmpty()) {
        String arr[] = book.getBookCategoryId().split(" ");
        bookCategoryId = Integer.parseInt(arr[0]);
      }      
    }
    try {
      if(book.getTitle() != null && !book.getTitle().isEmpty() || book.getAuthor() != null && !book.getAuthor().isEmpty() 
          || book.getPublisher() != null && !book.getPublisher().isEmpty() || !String.valueOf(book.getIsbn()).isEmpty() && book.getIsbn() != 0) {
        String sql = "insert into books(ISBN,title,author,publisher,description,category_id) values (?,?,?,?,?,?)";
        int res = jdbcTemplate.update(sql, new Object[] {book.getIsbn(),book.getTitle(),book.getAuthor(),book.getPublisher(),book.getDescription(),bookCategoryId});
        result = "Book Inserted Successfully";
      }
      else {
        result = "Book Not Inserted";
      }
    }
    catch(DataAccessException dataAccessException) {
      dataAccessException.printStackTrace();
    }
    return result;
  }

  public List<Integer> getBookCategory(){
    List<Integer> categoryList= null;    
    String sql = "select id from book_category";
    try {
      categoryList = new ArrayList<Integer>();
      List<Map<String,Object>> recordsList = jdbcTemplate.queryForList(sql);
      if(recordsList != null && !recordsList.isEmpty()){   
        for(Map<String,Object> map : recordsList) {
          categoryList.add((Integer) map.get("ID"));
        }
      }
    }
    catch(DataAccessException dataAccessException) {
      dataAccessException.printStackTrace();
    }
    return categoryList;
  }

  @Override
  public String updateBook(Book book) {
    String sql = "update books set isbn = ?,title = ?,author = ?,publisher = ?, description = ?, category_id = ? where id = ?";
    StringBuilder stringBuilder = new StringBuilder();
    try {
      if(book != null) {
        if(book.getTitle() != null && !book.getTitle().isEmpty() || book.getAuthor() != null && !book.getAuthor().isEmpty() 
            || book.getPublisher() != null && !book.getPublisher().isEmpty() || !String.valueOf(book.getIsbn()).isEmpty() && book.getIsbn() != 0) {
          jdbcTemplate.update(sql, new Object[] {book.getIsbn(),book.getTitle(),book.getAuthor(),book.getPublisher(),book.getDescription(),book.getBookCategory(),book.getId()});
          stringBuilder.append("Book Updated Successfully");
        }
        else {
          stringBuilder.append("Book Not Updated");
        }
      }   
    }
    catch(DataAccessException dataAccessException) {
      dataAccessException.printStackTrace();
    }
    return stringBuilder.toString();
  }

  @Override
  public List<Integer> getBookIdList() {
    List<Integer> booksIdList = null;    
    String sql = "select id from books";
    try {
      booksIdList = new ArrayList<Integer>();
      List<Map<String,Object>> recordsList = jdbcTemplate.queryForList(sql);
      if(recordsList != null && !recordsList.isEmpty()){   
        for(Map<String,Object> map : recordsList) {
          booksIdList.add((Integer) map.get(ID));
        }
      }
    }
    catch(DataAccessException dataAccessException) {
      dataAccessException.printStackTrace();
    }
    return booksIdList;
  }

  @Override
  public String deleteBook(Book book) {
    StringBuilder stringBuilder = null;
    String sql = "delete from books where id=?";
    try {
      if(book.getId() != 0 && !String.valueOf(book.getId()).isEmpty()) {
        stringBuilder = new StringBuilder();
        jdbcTemplate.update(sql,new Object[] {book.getId()});
        stringBuilder.append("Book Deleted Successfully");      
      }
    }
    catch(DataAccessException exception) {
      exception.printStackTrace();
    }
    return stringBuilder.toString();
  }

  @Override
  public List<String> getBooks() {
    List<String> bookList = new ArrayList();
    String sql = "select * from book_category";   
    try {
      List<Map<String, Object>> map = jdbcTemplate.queryForList(sql);     
      for(Map<String,Object> books : map) {
        int id = (int) books.get(ID);
        String name = (String) books.get(CATEGORY_NAME);
        bookList.add(id+" for "+name);
      }
    }
    catch(DataAccessException dataAccessException) {
      dataAccessException.printStackTrace();
    }
    return bookList;
  }

  public List<Integer> getBooksIsbnList() {
    List<Integer> booksIsbnList = new ArrayList();
    String sql = "select *  from books";   
    try {
      List<Map<String, Object>> map = jdbcTemplate.queryForList(sql);     
      for(Map<String,Object> books : map) {
        int isbn = (int)books.get(ISBN);
        booksIsbnList.add(isbn);
      }
    }
    catch(DataAccessException dataAccessException) {
      dataAccessException.printStackTrace();
    }
    return booksIsbnList;
  }

  @Override
  public List<String> getBooksBasedOnDepartment(int categoryId) {
    List<String> booksList = new ArrayList<String>();
    String sql = "select title from books where category_id = "+categoryId;
    try {
      List<Map<String,Object>> map = jdbcTemplate.queryForList(sql);
      for(Map<String,Object> books : map ) {
        String book = (String)books.get(TITLE);
        booksList.add(book);
      }
    }
    catch(DataAccessException dataAccessException) {
      dataAccessException.printStackTrace();
    }
    return booksList;
  }
  
  public List<Book> searchBasedOnId(int id){
    List<Book> books = new ArrayList<Book>();
    Book b = null;
    List<Map<String,Object>> map = null;
    if(!String.valueOf(id).isEmpty()) {
      String sql = "select * from books where isbn = "+id;
      map = jdbcTemplate.queryForList(sql);
      for(Map<String,Object> book : map) {
        b = new Book();
        b.setAuthor((String)book.get(AUTHOR));
        b.setTitle((String)book.get(TITLE));
        b.setPublisher((String)book.get(PUBLISHER));
        b.setBookCategory((int)book.get(CATEGORY_ID));
        b.setDescription((String)book.get(DESCRIPTION));
        b.setIsbn((int)book.get(ISBN));
        b.setId((int)book.get(ID));
        books.add(b);       
      }
    }
    return books;
    
    
  }

  @Override
  public List<Book> searchBasedOnIsbn(int isbn) {
    List<Book> books = new ArrayList<Book>();
    Book b = null;
    List<Map<String,Object>> map = null;
    if(!String.valueOf(isbn).isEmpty()) {
      String sql = "select * from books where isbn = "+isbn;
      map = jdbcTemplate.queryForList(sql);
      for(Map<String,Object> book : map) {
        b = new Book();
        b.setAuthor((String)book.get(AUTHOR));
        b.setTitle((String)book.get(TITLE));
        b.setPublisher((String)book.get(PUBLISHER));
        b.setBookCategory((int)book.get(CATEGORY_ID));
        b.setDescription((String)book.get(DESCRIPTION));
        b.setIsbn((int)book.get(ISBN));
        b.setId((int)book.get(ID));
        books.add(b);       
      }
    }
    return books;
  }

  @Override
  public List<Book> getBooksBasedOnAuthor(String name) {
    List<Book> books = new ArrayList<Book>();
    Book b = null;
    List<Map<String,Object>> map = null;
    if(!name.isEmpty()) {
      String sql = "select * from books where author = '"+name+"'";
      map = jdbcTemplate.queryForList(sql);
      for(Map<String,Object> book : map) {
        b = new Book();
        b.setAuthor((String)book.get(AUTHOR));
        b.setTitle((String)book.get(TITLE));
        b.setPublisher((String)book.get(PUBLISHER));
        b.setDescription((String)book.get(DESCRIPTION));
        b.setIsbn((int)book.get(ISBN));
        b.setId((int)book.get(ID));
        books.add(b);       
      }
    }
    return books;
  }

  @Override
  public List<String> getBookAuthorList() {
    List<String> booksAuthorList = null;    
    String sql = "select Author from books";
    try {
      booksAuthorList = new ArrayList<String>();
      List<Map<String,Object>> recordsList = jdbcTemplate.queryForList(sql);
      if(recordsList != null && !recordsList.isEmpty()){   
        for(Map<String,Object> map : recordsList) {
          booksAuthorList.add((String) map.get(AUTHOR));
        }
      }
    }
    catch(DataAccessException dataAccessException) {
      dataAccessException.printStackTrace();
    }
    return booksAuthorList;
  }

  @Override
  public List<String> getBookTitleList() {
    List<String> booksTitleList = null;    
    String sql = "select Title from books";
    try {
      booksTitleList = new ArrayList<String>();
      List<Map<String,Object>> recordsList = jdbcTemplate.queryForList(sql);
      if(recordsList != null && !recordsList.isEmpty()){   
        for(Map<String,Object> map : recordsList) {
          booksTitleList.add((String) map.get(TITLE));
        }
      }
    }
    catch(DataAccessException dataAccessException) {
      dataAccessException.printStackTrace();
    }
    return booksTitleList;
  }

  @Override
  public List<String> getBookPublisherList() {
    List<String> bookPublishersList = null;    
    String sql = "select Publisher from books";
    try {
      bookPublishersList = new ArrayList<String>();
      List<Map<String,Object>> recordsList = jdbcTemplate.queryForList(sql);
      if(recordsList != null && !recordsList.isEmpty()){   
        for(Map<String,Object> map : recordsList) {
          bookPublishersList.add((String) map.get(PUBLISHER));
        }
      }
    }
    catch(DataAccessException dataAccessException) {
      dataAccessException.printStackTrace();
    }
    return bookPublishersList;
  }

  @Override
  public List<Book> searchBasedOnAuthor(String author) {
    List<Book> books = new ArrayList<Book>();
    Book b = null;
    List<Map<String,Object>> map = null;
    if(!author.isEmpty()) {
      String sql = "select * from books where author = '"+author+"'";
      map = jdbcTemplate.queryForList(sql);
      for(Map<String,Object> book : map) {
        b = new Book();
        b.setAuthor((String)book.get(AUTHOR));
        b.setTitle((String)book.get(TITLE));
        b.setPublisher((String)book.get(PUBLISHER));
        b.setBookCategory((int)book.get(CATEGORY_ID));
        b.setDescription((String)book.get(DESCRIPTION));
        b.setIsbn((int)book.get(ISBN));
        b.setId((int)book.get(ID));
        books.add(b);       
      }
    }
    return books;
  }

  @Override
  public List<Book> searchBasedOnTitle(String title) {
    List<Book> books = new ArrayList<Book>();
    Book b = null;
    List<Map<String,Object>> map = null;
    if(!title.isEmpty()) {
      String sql = "select * from books where title = '"+title+"'";
      map = jdbcTemplate.queryForList(sql);
      for(Map<String,Object> book : map) {
        b = new Book();
        b.setAuthor((String)book.get(AUTHOR));
        b.setTitle((String)book.get(TITLE));
        b.setPublisher((String)book.get(PUBLISHER));
        b.setBookCategory((int)book.get(CATEGORY_ID));
        b.setDescription((String)book.get(DESCRIPTION));
        b.setIsbn((int)book.get(ISBN));
        b.setId((int)book.get(ID));
        books.add(b);       
      }
    }
    return books;
  }

  @Override
  public List<Book> searchBasedOnPublisher(String publisher) {
    List<Book> books = new ArrayList<Book>();
    Book b = null;
    List<Map<String,Object>> map = null;
    if(!publisher.isEmpty()) {
      String sql = "select * from books where publisher = '"+publisher+"'";
      map = jdbcTemplate.queryForList(sql);
      for(Map<String,Object> book : map) {
        b = new Book();
        b.setAuthor((String)book.get(AUTHOR));
        b.setTitle((String)book.get(TITLE));
        b.setPublisher((String)book.get(PUBLISHER));
        b.setBookCategory((int)book.get(CATEGORY_ID));
        b.setDescription((String)book.get(DESCRIPTION));
        b.setIsbn((int)book.get(ISBN));
        b.setId((int)book.get(ID));
        books.add(b);       
      }
    }
    return books;
  }

  @Override
  public List<Book> getTotalBooks() {
    List<Book> books = new ArrayList<Book>();
    Book b = null;
    List<Map<String,Object>> map = null;
      String sql = "select * from books";
      map = jdbcTemplate.queryForList(sql);
      for(Map<String,Object> book : map) {
        b = new Book();
        b.setAuthor((String)book.get(AUTHOR));
        b.setTitle((String)book.get(TITLE));
        b.setPublisher((String)book.get(PUBLISHER));
        b.setBookCategory((int)book.get(CATEGORY_ID));
        b.setDescription((String)book.get(DESCRIPTION));
        b.setIsbn((int)book.get(ISBN));
        b.setId((int)book.get(ID));
        books.add(b);       
      }
      return books;
  }  
}
class UserMapper implements RowMapper<User> {

  public User mapRow(ResultSet rs, int arg1) throws SQLException {
    User user = new User();

    user.setUsername(rs.getString("username"));
    user.setPassword(rs.getString("password"));
    user.setFirstname(rs.getString("firstname"));
    user.setLastname(rs.getString("lastname"));
    user.setEmail(rs.getString("email"));
    user.setAddress(rs.getString("address"));
    user.setPhone(rs.getInt("phone"));

    return user;
  }
}