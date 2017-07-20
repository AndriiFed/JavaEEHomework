package com;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/authors")
public class AuthorController {

  @Autowired
  private AuthorService authorService;

  @RequestMapping(value = "/list", method = RequestMethod.GET)
  public @ResponseBody
  List getAuthors() {
    return authorService.getAllAuthors();
  }

}
