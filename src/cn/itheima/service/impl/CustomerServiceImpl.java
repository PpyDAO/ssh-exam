package cn.itheima.service.impl;

import cn.itheima.dao.ICustomerDAO;
import cn.itheima.domain.Customer;
import cn.itheima.service.ICustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(rollbackFor = Exception.class)
public class CustomerServiceImpl implements ICustomerService {
	@Autowired
	private ICustomerDAO customerDAO;

	@Override
	public List<Customer> findAll() {
		return customerDAO.findAll();
	}

	@Override
	public void addCustomer(Customer customer) {
		customerDAO.addCustomer(customer);
	}

	@Override
	public void delCustomerById(Customer customer) {
		customerDAO.delCustomerById(customer);
    }

    @Override
    public Customer findCustomerById(Integer id) {
        return customerDAO.findCustomerById(id);
    }

}
