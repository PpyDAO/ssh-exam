package cn.itheima.domain;

import com.alibaba.fastjson.annotation.JSONField;
import org.apache.struts2.json.annotations.JSON;
import org.apache.struts2.json.annotations.JSONFieldBridge;
import org.hibernate.annotations.Cascade;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

@Entity
@Table(name="t_customer")
public class Customer {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	private String cusName;
	private String cusPhone;
	private String cusImgSrc;
	
	@OneToMany(targetEntity=Order.class, mappedBy="customer")
    @Cascade(org.hibernate.annotations.CascadeType.DELETE)
	private Set<Order> orders = new HashSet<Order>();

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getCusName() {
		return cusName;
	}
	public void setCusName(String cusName) {
		this.cusName = cusName;
	}
	public String getCusPhone() {
		return cusPhone;
	}
	public void setCusPhone(String cusPhone) {
		this.cusPhone = cusPhone;
	}

    @JSON(serialize = false)
    public Set<Order> getOrders() {
		return orders;
	}
    public void setOrders(Set<Order> orders) {
		this.orders = orders;
	}
	public String getCusImgSrc() {
		return cusImgSrc;
	}
	public void setCusImgSrc(String cusImgSrc) {
		this.cusImgSrc = cusImgSrc;
	}

}
