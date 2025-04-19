package modelsDAO;

import config.ConectionPostgres;
import interfaces.Crud;
import models.Person;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class PersonDAO implements Crud {
    ConectionPostgres db = new ConectionPostgres();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;

    Person person = new Person();

    @Override
    public List<Person> list() {
        ArrayList<Person> persons = new ArrayList<>();
        String sql = "select * from person";

        try {
            con = db.getConection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                Person person = new Person();
                person.setId(rs.getInt("id"));
                person.setName(rs.getString("name"));
                person.setEmail(rs.getString("email"));

                persons.add(person);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return persons;
    }

    @Override
    public Person find(int id) {
        return null;
    }

    @Override
    public boolean create(Person person) {
        return false;
    }

    @Override
    public boolean update(Person person) {
        return false;
    }

    @Override
    public boolean delete(int id) {
        return false;
    }
}
