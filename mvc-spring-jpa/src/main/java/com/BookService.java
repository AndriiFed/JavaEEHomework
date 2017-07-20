package com;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("bookService")
public class BookService {
  @Autowired
  private BookDao bookDao;

  public List getAllUsers() {
    return bookDao.getAllBooks();
  }
}
