package cn.itheima.action;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import cn.itheima.domain.Customer;
import cn.itheima.service.ICustomerService;

@Namespace("/customer")
@ParentPackage("struts-default")
public class AddCustomerAction extends ActionSupport implements ModelDriven<Customer>{
	private Customer customer = new Customer();
	
	private File cusImg;
	private String cusImgFileName;
	
	public void setCusImg(File cusImg) {
		this.cusImg = cusImg;
	}

	public void setCusImgFileName(String cusImgFileName) {
		this.cusImgFileName = cusImgFileName;
	}

	@Autowired
	private ICustomerService customerService;
	
	@Action(value="addCustomer", results= {@Result(name="success", location="findAll", type="redirectAction"), @Result(name="input", location="error.jsp")})
	public String addCustomer() {
		String uuidFileName = UUID.nameUUIDFromBytes(cusImgFileName.getBytes()).toString()+cusImgFileName.substring(cusImgFileName.lastIndexOf('.'));
		File destFile = new File("C:\\Users\\83470\\Desktop\\image\\", uuidFileName);
		try {
			FileUtils.copyFile(cusImg, destFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
		customer.setCusImgSrc("/pic/"+uuidFileName);
		customerService.addCustomer(customer);
		return SUCCESS;
	}

	@Override
	public Customer getModel() {
		return customer;
	}
	
}
