package com.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.entity.Orders;
import com.repository.OrderRepository;

@Service
public class OrderService {


	@Autowired
	OrderRepository orderRepository;
	
	public List<Orders> viewAllOrders(){
		return orderRepository.findAll();	
	}
	
	public Orders findOrderById(int oid){
		Optional<Orders> op = orderRepository.findById(oid);
		if(op.isPresent()) {
			Orders orderitem = op.get();
			return orderitem;
		}else {
			return null;
		}
	}
	
	public String placeOrder(Orders orders){
		Optional<Orders> oder = orderRepository.findById(orders.getOid());
		if(oder.isPresent()) {
			return "Nothing carted, Food Orders details already exists!";
		}else {
			orderRepository.save(orders);
			return "Food ordered successfully!";
		}		
	}
	
	public String updateOrder(Orders orders) {
		Optional<Orders> oder = orderRepository.findById(orders.getOid());
		if(oder.isPresent()) {
			Orders ochange = oder.get();
			ochange.setOid(orders.getOid());
			ochange.setCname(orders.getCname());
			ochange.setLocation(orders.getLocation());
			ochange.setContactno(orders.getContactno());
			ochange.setQty(orders.getQty());
			ochange.setQty(orders.getFid());
			ochange.setQty(orders.getPrice());
			ochange.setStatus(orders.getStatus());
			orderRepository.saveAndFlush(ochange);
			return "Food Orders updated successfuly!";
			
		}else {
			return "Food Orders details not found!";
		}
	}
	
	public String deleteOrder(int oid){
		Optional<Orders> food = orderRepository.findById(oid);
		if(food.isPresent()) {
			orderRepository.deleteById(oid);
			return "Food Orders cancelled successfully!";
			
		}else {
			return "Food Orders details not found!";
		}
	}
	
	public List<Object> findByOrder(int oid){
		return orderRepository.findByOrder(oid);
	}
	
	public List<java.util.Map<String, Object>> findMyOrders(int oid){
		return orderRepository.findMyOrders(oid);
	}
	
	public List<java.util.Map<String, Object>> findCustomerOrder(int contactno){
		return orderRepository.findCustomerOrder(contactno);
	}
	
	public String cancelCustomerOrder(int contactno){
		Optional<Orders> order = orderRepository.cancelCustomerOrder(contactno);
		if(order.isPresent()) {
			orderRepository.deleteById(contactno);
			return "Customer Order cancelled successfully!";
			
		}else {
			return "Cudtomer Order details not found!";
		}
	}
}
