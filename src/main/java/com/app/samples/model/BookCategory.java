package com.app.samples.model;

public class BookCategory {
  
  private String categoryName;
  private int id;
  
  public BookCategory() {
    super();
  }
  public BookCategory(String categoryName, int id) {
    super();
    this.categoryName = categoryName;
    this.id = id;
  }

  public String getCategoryName() {
    return categoryName;
  }
  public void setCategoryName(String categoryName) {
    this.categoryName = categoryName;
  }
  public int getId() {
    return id;
  }
  public void setId(int id) {
    this.id = id;
  }
  @Override
  public String toString() {
    return "BookCategory [categoryName=" + categoryName + ", id=" + id + "]";
  }
  
  
  
  

}