package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;

import javax.imageio.spi.ServiceRegistry;
import java.util.Properties;

public class Util {
    // реализуйте настройку соеденения с БД
//    private final String url = "jdbc:mysql://localhost:3306/hiber";
  //  private final String name = "root";
  //  private final String password = "rootpassword";
    private static Util instance;

    private Util () {}
    public static Util getInstance() {
        if (instance == null) {
            instance = new Util();
        }
        return instance;
    }
    private SessionFactory factory;

    public SessionFactory getFactory () {
        if (factory == null) {
            try {
              Configuration configuration = new Configuration().addAnnotatedClass(User.class);
              factory = configuration.buildSessionFactory();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return factory;
    }
}
