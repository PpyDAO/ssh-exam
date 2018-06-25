package cn.itheima.dao.impl;

import cn.itheima.dao.IOrderDAO;
import cn.itheima.domain.Order;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class OrderDAOImpl extends HibernateDaoSupport implements IOrderDAO {
    @Autowired
    public void setSupperSessionFactory(SessionFactory sessionFactory){
        super.setSessionFactory(sessionFactory);
    }

    @Override
    public List<Order> findOrderByCustomerId(Integer id, Integer pageNo, Integer showResultNo) {
        Session currentSession = getSessionFactory().getCurrentSession();
        Query query = currentSession.createQuery("from Order o where o.customer.id = ?");
        query.setParameter(0, id);
        query.setFirstResult((pageNo-1)*showResultNo);
        query.setMaxResults(showResultNo);
        List<Order> list = query.list();

        return list;
    }

    @Override
    public Integer getTotalResultNo(Integer id) {
        Long totalResultNo = (Long) getHibernateTemplate().find("select count(o.id) from Order o where o.customer.id = ?", id).get(0);
        return totalResultNo.intValue();
    }

    @Override
    public void delOrderById(String orderId) {
        Order order = getHibernateTemplate().get(Order.class, orderId);
        getHibernateTemplate().delete(order);
    }
}
