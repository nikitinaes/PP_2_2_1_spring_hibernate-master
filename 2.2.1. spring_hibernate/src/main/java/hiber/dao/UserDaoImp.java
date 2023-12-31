package hiber.dao;

import hiber.model.Car;
import hiber.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {
   @Autowired
   private SessionFactory sessionFactory;

   @Override
   public void addUser(User user) {
      sessionFactory.getCurrentSession().save(user);
   }
   @Override
    public User getUserByCar(String model, int series) {
      TypedQuery<User> query = sessionFactory.openSession().createQuery(
              "from User user where user.car.model = :model and user.car.series = :series");
         query.setParameter("model", model).setParameter("series", series);
         return query.setMaxResults(1).getSingleResult();
      }
   @Override
   @SuppressWarnings("unchecked")
   public List<User> showUsers() {
      TypedQuery<User> query=sessionFactory.getCurrentSession().createQuery("from User");
      return query.getResultList();
   }

}
