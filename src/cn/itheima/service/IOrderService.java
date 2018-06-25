package cn.itheima.service;

import cn.itheima.domain.Order;
import cn.itheima.domain.PageBean;

import java.util.List;

public interface IOrderService {

    PageBean<Order> findOrderByCustomerId(Integer id, Integer pageNo, Integer showResultNo);

    void delOrderById(String orderId);
}
