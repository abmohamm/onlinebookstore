package com.app.samples.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;

import com.app.samples.model.Book;
import com.app.samples.service.UserService;


@WebServlet("/ShoppingController")
public class ShoppingController extends HttpServlet{

  @Autowired
  UserService userService;

  private static final long serialVersionUID = 1L;

  /* (non-Javadoc)
   * @see javax.servlet.http.HttpServlet#doGet(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
   */
  public void doGet(HttpServletRequest httpServletRequest,HttpServletResponse httpServletResponse) {
    try {
    String action = httpServletRequest.getParameter("action");
    List<Book> books = null;
    HttpSession session = httpServletRequest.getSession();
    if(action.equalsIgnoreCase("ordernow")) {
      String str = httpServletRequest.getParameter("id");
      int id = Integer.parseInt(str);
      if(String.valueOf(id) != null) {
        books = userService.searchBasedOnId(id);
      }
      session.setAttribute("cart", books);
      httpServletRequest.getRequestDispatcher("cart.jsp").forward(httpServletRequest, httpServletResponse);
      } 
    }
    catch (ServletException | IOException e) {
      e.printStackTrace();
    }

  }

}
