package cn.itheima.service;

import java.util.List;

import cn.itheima.domain.Customer;

public interface ICustomerService {
	List<Customer> findAll();

	void addCustomer(Customer customer);

	void delCustomerById(Customer customer);

    Customer findCustomerById(Integer id);
}
