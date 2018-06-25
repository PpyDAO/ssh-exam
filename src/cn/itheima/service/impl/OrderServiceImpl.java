package cn.itheima.service.impl;

import cn.itheima.dao.IOrderDAO;
import cn.itheima.domain.Order;
import cn.itheima.domain.PageBean;
import cn.itheima.service.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(rollbackFor = Exception.class)
public class OrderServiceImpl implements IOrderService {
    @Autowired
    private IOrderDAO orderDAO;

    @Override
    public PageBean<Order> findOrderByCustomerId(Integer id, Integer pageNo, Integer showResultNo) {
        PageBean<Order> orderPageBean = new PageBean<>();
        Integer totalResultNo = orderDAO.getTotalResultNo(id);
        int totalPageNo = (int) Math.ceil(totalResultNo * 1.0 / showResultNo);
        List<Order> orders = orderDAO.findOrderByCustomerId(id, pageNo, showResultNo);

        orderPageBean.setTotalPageNo(totalPageNo);
        orderPageBean.setContent(orders);

        return orderPageBean;
    }

    @Override
    public void delOrderById(String orderId) {
        orderDAO.delOrderById(orderId);
    }
}
