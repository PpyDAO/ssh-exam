package cn.itheima.dao;

import java.util.List;

import cn.itheima.domain.Customer;

public interface ICustomerDAO {
	List<Customer> findAll();
	
	void addCustomer(Customer customer);

	void delCustomerById(Customer customer);

    Customer findCustomerById(Integer id);
}
