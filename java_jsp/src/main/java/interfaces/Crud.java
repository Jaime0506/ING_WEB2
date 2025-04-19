package interfaces;

import models.Person;

import java.util.List;

public interface Crud {
    public List<Person> list();
    public Person find(int id);
    public boolean create(Person person);
    public boolean update(Person person);
    public boolean delete(int id);
}
