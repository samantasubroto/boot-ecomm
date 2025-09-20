package com.example.springboot_demo.service.order.impl;

import com.example.springboot_demo.model.entity.Cart;
import com.example.springboot_demo.model.entity.Customer;
import com.example.springboot_demo.model.entity.Order;
import com.example.springboot_demo.model.entity.OrderItem;
import com.example.springboot_demo.model.enums.OrderStatus;
import com.example.springboot_demo.repository.CartRepository;
import com.example.springboot_demo.repository.OrderRepository;
import com.example.springboot_demo.service.cart.CartService;
import com.example.springboot_demo.service.order.OrderService;
import com.example.springboot_demo.service.payment.PaymentGateways;
import com.example.springboot_demo.service.user.UserService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private UserService userService;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private CartService cartService;

    @Autowired
    private PaymentGateways paymentGateways;

    @Override
    @Transactional
    public Order placeOrder(final String customerid) {
        Cart cart = cartService.getUserCart(customerid);
        Customer customer = userService.getCustomerByEmail(customerid);
        if (cart != null && cart.getCartItems().isEmpty()) {
            throw new RuntimeException("Cart is empty");
        }

        Order order = new Order();
        order.setCustomer(cart.getCustomer());
        order.setOrderNumber(generateOrderNumber());
        order.setStatus(OrderStatus.PENDING);
        order.setTotalPrice(cart.getCartTotal());
        //Give customer more flexiblity to choose address that he/she wanna use.
        if (customer.getAddress() != null) {
            order.setShippingAddress(customer.getAddress().get(0));
        }

        List<OrderItem> orderItems = cart.getCartItems().stream()
                .map(cartItem -> {
                    OrderItem orderItem = new OrderItem();
                    orderItem.setOrder(order);
                    orderItem.setProduct(cartItem.getProduct());
                    orderItem.setQuantity(cartItem.getQuantity());
                    orderItem.setPriceAtPurchase(cartItem.getProduct().getPrice().getValue());
                    return orderItem;
                }).collect(Collectors.toList());

        order.setOrderItems(orderItems);
        this.paymentGateways.createPayment(order);
        orderRepository.save(order);

        cart.getCartItems().clear();
        cart.setCartTotal(0.0);
        cartService.saveCart(cart);

        return order;
    }

    @Override
    @Transactional
    public List<Order> getOrders(final String customerId) {
        Customer customer = userService.getCustomerByEmail(customerId);
        if (customer != null) {
            return orderRepository.fetchAllOrders(customerId);
        }
        return null;
    }

    private String generateOrderNumber() {
        return String.format("%010d", new Random().nextInt(1_000_000_000));
    }
}
