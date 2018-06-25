package cn.itheima.test;

import cn.itheima.domain.Customer;
import cn.itheima.domain.Order;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.math.BigDecimal;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:applicationContext.xml" })
public class TestApp {
    @Autowired
    private SessionFactory sessionFactory;

	@Test
	public void test1() {
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        Customer customer = session.get(Customer.class, 24);

        Order order = new Order();

        order.setCustomer(customer);
        order.setOrderNum("123456789");
        order.setPrice(BigDecimal.valueOf(123.12));
        order.setReceiverInfo("纽约");

        session.save(order);

        session.getTransaction().commit();

    }


	
	
	
	

}
