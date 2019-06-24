package com.app.samples.controller;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.app.samples.model.Book;
import com.app.samples.service.UserService;

@Controller
public class BookController {

  @Autowired
  UserService userService;

  @RequestMapping(value="/addbook",method=RequestMethod.GET)
  public ModelAndView addBook(HttpServletRequest httpServletRequest,HttpServletResponse httpServletResponse) {
    ModelAndView mav = new ModelAndView("addbook");
    mav.addObject("addbook", new Book());
    List<String> categoryList = userService.getBooks();
    mav.addObject("categoryList",categoryList);
    return mav;    
  }

  @RequestMapping(value="/getbook",method=RequestMethod.GET)
  public ModelAndView getBooks(HttpServletRequest httpServletRequest,HttpServletResponse httpServletResponse) {
    ModelAndView mav = new ModelAndView("getbook");
    mav.addObject("getbook",new Book());
    List<Book> totalBooks = userService.getTotalBooks();
    if(totalBooks.size()<=5) {
      ModelAndView mav1 = new ModelAndView("success");
      mav1.addObject("message", "Only few books available in store");
      return mav1;
    }
    else {
      Set<String> booksCategorySet = new HashSet<String>(userService.getBooks());
      mav.addObject("booksCategorySet", booksCategorySet);
      Set<Integer> booksListIsbnSet = new HashSet<Integer>(userService.getBooksIsbnList()); 
      mav.addObject("booksListIsbnSet", booksListIsbnSet);
      Set<String> bookAuthorSet = new HashSet<String>(userService.getBookAuthorList());
      mav.addObject("bookAuthorSet", bookAuthorSet);    
      Set<String> bookTitleSet = new HashSet<String>(userService.getBookTitleList());
      mav.addObject("bookTitleSet",bookTitleSet);
      Set<String> bookPublisherSet = new HashSet<String>(userService.getBookPublisherList());
      mav.addObject("bookPublisherSet",bookPublisherSet);
    }
    return mav;
  }

  @RequestMapping(value="/searchBook",method=RequestMethod.POST)
  public ModelAndView getBook(HttpServletRequest httpServletRequest,HttpServletResponse httpServletResponse,@ModelAttribute("getbook") Book book) {
    ModelAndView mav = new ModelAndView("bookslist");
    try {
      List<String> booksList = userService.getBooksBasedOnDepartment(book);
      mav.addObject("booksList", booksList);
      if(book != null) {
        if(!String.valueOf(book.getIsbn()).isEmpty()) {
          mav.addObject("isbn", book.getIsbn());
          List<Book> booksListIsbn = userService.searchBasedOnIsbn(book.getIsbn());
          mav.addObject("booksListIsbn", booksListIsbn);
        }
        if(!book.getAuthor().isEmpty() && book.getAuthor() != null) {
          mav.addObject("author",book.getAuthor());
          List<Book> booksAuthorList = userService.searchBasedOnAuthor(book.getAuthor());
          mav.addObject("booksAuthorList", booksAuthorList);
        }
        if(!book.getTitle().isEmpty() && book.getTitle() != null) {
          mav.addObject("title",book.getTitle());
          List<Book> booksTitleList = userService.searchBasedOnTitle(book.getTitle());
          mav.addObject("booksTitleList", booksTitleList);
        }
        if(!book.getPublisher().isEmpty() && book.getPublisher() != null) {
          mav.addObject("publisher", book.getPublisher());
          List<Book> bookPublishersList = userService.searchBasedOnPublisher(book.getPublisher());
          mav.addObject("bookPublishersList", bookPublishersList);
        }
      }
    }
    catch(DataAccessException dataAccessException) {
      dataAccessException.printStackTrace();
    }
    return mav;
  }

  @RequestMapping(value = "/addBookProcess", method=RequestMethod.POST)
  public ModelAndView addBookProcess(HttpServletRequest httpServletRequest,HttpServletResponse httpServletResponse,@ModelAttribute("addbook") Book book){
    ModelAndView mav = new ModelAndView("success");
    try {    
      String result = userService.addBook(book);
      mav.addObject("message", result);
    }
    catch (DataAccessException exception) {
      exception.printStackTrace();
    }return mav;
  }

  @RequestMapping(value = "/deleteBookProcess", method=RequestMethod.POST)
  public ModelAndView deleteBookProcess(HttpServletRequest httpServletRequest,HttpServletResponse httpServletResponse,@ModelAttribute("deletebook") Book book){
    ModelAndView mav = new ModelAndView("success");
    try {    
      String result = userService.deleteBook(book);
      mav.addObject("message", result);
    }
    catch (DataAccessException exception) {
      exception.printStackTrace(); 
    }
    return mav;
  }


  @RequestMapping(value = "/getBookProcess", method=RequestMethod.POST)
  public ModelAndView getBookProcess(HttpServletRequest httpServletRequest,HttpServletResponse httpServletResponse,@ModelAttribute("getbook") Book book){
    ModelAndView mav = new ModelAndView("success");
    try {    
      String result = userService.deleteBook(book);
      mav.addObject("message", result);
    }
    catch (DataAccessException exception) {
      exception.printStackTrace();
    }return mav; 
  }

  @RequestMapping(value="/updatebook",method=RequestMethod.GET)
  public ModelAndView updateBook(HttpServletRequest httpServletRequest,HttpServletResponse httpServletResponse) {
    ModelAndView mav = new ModelAndView("updatebook");
    mav.addObject("updatebook", new Book());
    List<Integer> booksIdList = userService.getBooksIdList();
    mav.addObject("booksIdList", booksIdList);
    List<Integer> categoryList = userService.getBookCategory();
    mav.addObject("categoryList",categoryList);
    return mav;    
  }

  @RequestMapping(value="/deletebook",method=RequestMethod.GET )
  public ModelAndView deleteBook(HttpServletRequest httpServletRequest,HttpServletResponse httpServletResponse) {
    ModelAndView mav = new ModelAndView("deletebook");
    mav.addObject("deletebook", new Book());
    List<Integer> booksIdList = userService.getBooksIdList();
    mav.addObject("booksIdList", booksIdList);
    return mav;
  }

  @RequestMapping(value="/updateBookProcess",method=RequestMethod.POST)
  public ModelAndView updateBookProcess(HttpServletRequest httpServletRequest,HttpServletResponse httpServletResponse,@ModelAttribute("updatebook") Book book) {
    ModelAndView mav = new ModelAndView("success");
    try {
      String result = userService.updateBook(book);
      mav.addObject("message",result);
    }
    catch(DataAccessException dataAccessException) {
      dataAccessException.printStackTrace();
    }
    return mav;
  }
}


