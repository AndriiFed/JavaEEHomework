package com;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PersonController extends HttpServlet {
  private List<Person> personList = new ArrayList(Arrays.asList(new Person("xxx", 12, 1),
      new Person("yyy", 12, 2)));
  private String errorMessage;

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    String action = req.getParameter("action");

    if (action == null) {
      serveDefaultUserAction(req, resp);
      return;
    }

    switch (action) {
      case "addnew":
        serveAddUserAction(req, resp);
        break;

      case "delete":
        serveDeleteUserAction(req, resp);
        break;

      case "update":
        serveUpdateUserAction(req, resp);
        break;

      default:
        serveDefaultUserAction(req, resp);
        break;
    }
  }

  private void serveDefaultUserAction(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    req.setAttribute("persons", personList);
    if (errorMessage != null && !errorMessage.equals("")) {
      req.setAttribute("message", errorMessage);
      errorMessage = "";
    }
    req.getRequestDispatcher("/personsList.jsp").forward(req, resp);
  }

  private void serveAddUserAction(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    Person person = new Person();
    req.setAttribute("person", person);
    req.getRequestDispatcher("/addPerson.jsp").forward(req, resp);
  }

  private void serveDeleteUserAction(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    String id = req.getParameter("id");
    for (Person p : personList) {
      if (p.getId() == Integer.valueOf(id)) {
        personList.remove(p);
        break;
      }
    }
    req.setAttribute("persons", personList);
    req.getRequestDispatcher("/personsList.jsp").forward(req, resp);
  }

  private void serveUpdateUserAction(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    String id = req.getParameter("id");
    for (Person p : personList) {
      if (p.getId() == Integer.valueOf(id)) {
        req.setAttribute("person", p);
        break;
      }
    }
    req.getRequestDispatcher("/addPerson.jsp").forward(req, resp);
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    Person person = Integer.valueOf(req.getParameter("id")) == 0 ? new Person() : getPersonById(req.getParameter("id"));
    if (isNameValid(req.getParameter("name")) &&
        isAgeValid(Integer.valueOf(req.getParameter("age")))) {
      person.setName(req.getParameter("name"));
      person.setAge(Integer.valueOf(req.getParameter("age")));
      if (Integer.valueOf(req.getParameter("id")) == 0) {
        person.setId(personList.size() + 1);
        personList.add(person);
      }
      sendRedirectToUserList(req, resp, "");
      return;
    } else {
      sendRedirectToUserList(req, resp, "Wrong name or age value!");
    }
  }

  private void sendRedirectToUserList(HttpServletRequest req, HttpServletResponse resp, String message) throws IOException {
    req.setAttribute("persons", personList);
    errorMessage = message;
    resp.sendRedirect("persons");
  }

  private Person getPersonById(String id) {
    for (Person p : personList) {
      if (p.getId() == Integer.valueOf(id)) {
        return p;
      }
    }
    return null;
  }

  private boolean isNameValid(String name) {
    return name.matches("[a-zA-Z]+");
  }

  private boolean isAgeValid(int age) {
    return !(age < 1 || age > 100);
  }

}
