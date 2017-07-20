package com;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("authorService")
public class AuthorService {
  @Autowired
  private AuthorDao authorDao;

  public List getAllAuthors() {
    return authorDao.getAllAuthors();
  }
}
