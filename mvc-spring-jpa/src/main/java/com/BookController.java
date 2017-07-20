package com;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/books")
public class BookController {

  @Autowired
  private BookService bookService;

  @RequestMapping(value = "/list", method = RequestMethod.GET)
  public @ResponseBody
  List getBooks() {
    return bookService.getAllUsers();
  }

}
