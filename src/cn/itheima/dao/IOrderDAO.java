package cn.itheima.dao;

import cn.itheima.domain.Order;

import java.util.List;

public interface IOrderDAO {

    List<Order> findOrderByCustomerId(Integer id, Integer pageNo, Integer showResultNo);

    Integer getTotalResultNo(Integer id);

    void delOrderById(String orderId);
}
