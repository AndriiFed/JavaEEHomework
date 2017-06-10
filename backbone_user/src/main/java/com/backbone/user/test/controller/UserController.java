package com.backbone.user.test.controller;

import com.backbone.user.test.entity.User;
import com.backbone.user.test.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.inject.Inject;
import java.util.List;

@Controller
@RequestMapping("/rest/users")
public class UserController {
  @Inject
  protected UserRepository userRepository;

  @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
  public
  @ResponseBody
  boolean deleteUser(@PathVariable("id") Integer id) {
    userRepository.delete(id);
    return true;
  }

  public static void main(String[] args) {
    System.out.println(System.getProperty("java.library.path"));
  }

  @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
  public
  @ResponseBody
  User delete(@PathVariable("id") Integer id) {
    userRepository.delete(id);
    userRepository.flush();
    return new User();
  }


  @RequestMapping(value = "/", method = RequestMethod.GET)
  public
  @ResponseBody
  List<User> findAll() {
    List<User> users = userRepository.findAll();
    return users;
  }

  @RequestMapping(value = "/{id}", method = RequestMethod.GET)
  public
  @ResponseBody
  User findById(@PathVariable("id") Integer id) {
    User user = userRepository.findOne(id);
    return user;
  }

  @RequestMapping(value = "/", method = RequestMethod.POST)
  public
  @ResponseBody
  User create(@RequestBody User user) throws UserExeption {
    checkUserFields(user);
    userRepository.saveAndFlush(user);
    return user;
  }

  @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
  public
  @ResponseBody
  User update(@RequestBody User user) throws UserExeption {
    checkUserFields(user);
    userRepository.saveAndFlush(user);
    return user;
  }

  private void checkUserFields(@RequestBody User user) throws UserExeption {
    if (user.getName() == null || user.getName().equals("")) {
      throw new UserExeption("Name should not be empty");
    }
    if (user.getAge() == null || user.getAge() < 1 || user.getAge() > 100) {
      throw new UserExeption("Age should be 1..100");
    }
    if (user.getEmail() == null || user.getEmail().equals("")) {
      throw new UserExeption("Email should not be empty");
    }

    List<User> users = userRepository.findAll();
    for (User tempUser : users) {
      if (tempUser.getName().equals(user.getName()) &&
              tempUser.getAge().equals(user.getAge()) &&
              !tempUser.getId().equals(user.getId())) {
        throw new UserExeption("User is already presented in DB");
      }
    }
  }

  @ExceptionHandler(Exception.class)
  @ResponseBody
  @ResponseStatus(value = HttpStatus.BAD_REQUEST)
  public String handleException(Exception e) {
    return e.getMessage();
  }
}

