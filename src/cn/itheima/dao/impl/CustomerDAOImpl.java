package cn.itheima.dao.impl;

import java.io.File;
import java.net.URL;
import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import cn.itheima.dao.ICustomerDAO;
import cn.itheima.domain.Customer;

@Repository
public class CustomerDAOImpl extends HibernateDaoSupport implements ICustomerDAO {
	@Autowired
	public void setSupperSessionFactory(SessionFactory sessionFactory) {
		setSessionFactory(sessionFactory);
	}
	
	@Override
	public List<Customer> findAll() {
		return (List<Customer>) getHibernateTemplate().find("from Customer");
	}

	@Override
	public void addCustomer(Customer customer) {
		getHibernateTemplate().save(customer);
	}

	@Override
	public void delCustomerById(Customer customer) {
        getHibernateTemplate().delete(customer);
	}

    @Override
    public Customer findCustomerById(Integer id) {
        return getHibernateTemplate().get(Customer.class, id);
    }

}
