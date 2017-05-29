import model.Company;
import model.Person;
import model.User;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;

public class ObjectMappingTest {
  public static void main(String[] args) {
    // Log4j settings
    BasicConfigurator.configure();
    Logger.getRootLogger().setLevel(Level.ERROR);

    User user = new User("Jonh Doe", 36, new Company("My Company LTD."));

    Mapper mapper = new DozerBeanMapper();
    Person person = mapper.map(user, Person.class);

    assert (person.getLogin().equals(user.getName())) : "Name not equals Login";
    assert (user.getAge() == person.getAge()) : "User age not equals Person age";
    System.out.println(user);
    System.out.println(person);


  }
}
