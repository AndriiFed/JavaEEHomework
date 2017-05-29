import model.Company;
import model.Person;
import model.User;

import java.lang.reflect.Field;

public class ReflectionMappingTest {
  public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException {

    User user = new User("Jonh Doe", 36, new Company("My Company LTD."));
    Person person = new Person();

    Field userName = user.getClass().getDeclaredField("name");
    Field userAge = user.getClass().getDeclaredField("age");
    Field personLogin = person.getClass().getDeclaredField("login");
    Field personAge = person.getClass().getDeclaredField("age");
    userName.setAccessible(true);
    userAge.setAccessible(true);
    personLogin.setAccessible(true);
    personAge.setAccessible(true);

    personLogin.set(person, (String) userName.get(user));
    personAge.set(person, userAge.getInt(user));

    System.out.println(user);
    System.out.println(person);
    assert (person.getLogin().equals(user.getName())) : "Name not equals Login";
    assert (user.getAge() == person.getAge()) : "User age not equals Person age";

    user.setName("New User");
    user.setAge(99);
    System.out.println(user);
    System.out.println(person);
    assert (!person.getLogin().equals(user.getName())) : "Name and Login have same Name object";
    assert (user.getAge() != person.getAge());


  }
}
