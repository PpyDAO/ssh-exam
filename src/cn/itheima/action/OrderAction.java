package cn.itheima.action;

import cn.itheima.domain.Customer;
import cn.itheima.domain.Order;
import cn.itheima.domain.PageBean;
import cn.itheima.service.ICustomerService;
import cn.itheima.service.IOrderService;
import com.alibaba.fastjson.JSONObject;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Namespace("/order")
@ParentPackage("json-default")
public class OrderAction extends ActionSupport implements ModelDriven<PageBean<Order>> {
    private PageBean<Order> orderPageBean;
    private Integer id;
    private Integer showResultNo;
    private Integer pageNo;
    private String orderId;

    @Override
    public PageBean<Order> getModel() {
        return orderPageBean;
    }

    public void setShowResultNo(Integer showResultNo) {
        this.showResultNo = showResultNo;
    }

    public void setPageNo(Integer pageNo) {
        this.pageNo = pageNo;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    @Autowired
    private IOrderService orderService;

    @Action(value = "findOrderByCustomerId", results = {@Result(name = "success", type = "json"), @Result(name = "input", location = "error.jsp")})
    public String findOrderByCustomerId() {
        orderPageBean = orderService.findOrderByCustomerId(id, pageNo, showResultNo);
        return SUCCESS;
    }

    @Action(value = "delOrder")
    public void delOrder() {
        orderService.delOrderById(orderId);
    }





}
