package cn.itheima.action;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import cn.itheima.domain.Customer;
import cn.itheima.service.ICustomerService;

@Namespace("/customer")
@ParentPackage("struts-default")
public class CustomerAction extends ActionSupport {
	private Integer id;
	
	@Autowired
	private ICustomerService customerService;
	
	@Action(value="findAll", results= {@Result(name="success", location="/customerList.jsp"), @Result(name="input", location="error.jsp")})
	public String findAll() {
		List<Customer> cusList = customerService.findAll();
		ActionContext.getContext().getValueStack().set("cusList", cusList);
		return SUCCESS;
	}
	
	@Action(value="delCustomer", results= {@Result(name="success", location="findAll", type="redirectAction"), @Result(name="input", location="error.jsp")})
	public String delCustomer() {
        Customer customer = customerService.findCustomerById(id);
        File file = new File("C:\\Users\\83470\\Desktop\\image\\"+customer.getCusImgSrc().substring(customer.getCusImgSrc().lastIndexOf('/')));
        file.delete();
        customerService.delCustomerById(customer);
		return SUCCESS;
	}

    public void setId(Integer id) {
        this.id = id;
    }
}
