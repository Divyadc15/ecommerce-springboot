package com.pro1.ecommerce.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.pro1.ecommerce.dto.OrderItemResponse;
import com.pro1.ecommerce.dto.OrderResponse;
import com.pro1.ecommerce.entity.Cart;
import com.pro1.ecommerce.entity.CartItem;
import com.pro1.ecommerce.entity.Order;
import com.pro1.ecommerce.entity.OrderItem;
import com.pro1.ecommerce.entity.User;
import com.pro1.ecommerce.exception.ResourceNotFoundException;
import com.pro1.ecommerce.repository.CartItemRepository;
import com.pro1.ecommerce.repository.CartRepository;
import com.pro1.ecommerce.repository.OrderItemRepository;
import com.pro1.ecommerce.repository.OrderRepository;
import com.pro1.ecommerce.repository.UserRepository;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;
    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;
    private final UserRepository userRepository;

    public OrderService(OrderRepository orderRepository,
                        OrderItemRepository orderItemRepository,
                        CartRepository cartRepository,
                        CartItemRepository cartItemRepository,
                        UserRepository userRepository) {

        this.orderRepository = orderRepository;
        this.orderItemRepository = orderItemRepository;
        this.cartRepository = cartRepository;
        this.cartItemRepository = cartItemRepository;
        this.userRepository = userRepository;
    }

    private User getCurrentUser() {

        Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();

        String email = authentication.getName();

        return userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User Not Found"));
    }
    public String placeOrder() {

        User user = getCurrentUser();

        Cart cart = cartRepository.findByUser(user)
                .orElse(null);

        if (cart == null || cart.getCartItems().isEmpty()) {
            return "Cart is Empty";
        }

        Order order = new Order();
        order.setUser(user);
        order.setOrderDate(java.time.LocalDateTime.now());
        order.setStatus("PLACED");

        double totalAmount = 0;

        List<OrderItem> orderItems = new ArrayList<>();

        for (CartItem cartItem : cart.getCartItems()) {

            OrderItem orderItem = new OrderItem();

            orderItem.setOrder(order);
            orderItem.setProduct(cartItem.getProduct());
            orderItem.setQuantity(cartItem.getQuantity());
            orderItem.setPrice(cartItem.getProduct().getPrice());

            totalAmount +=
                    cartItem.getProduct().getPrice() * cartItem.getQuantity();

            orderItems.add(orderItem);
        }

        order.setTotalAmount(totalAmount);
        order.setOrderItems(orderItems);

        orderRepository.save(order);

        cartItemRepository.deleteAll(cart.getCartItems());

        cart.getCartItems().clear();

        cartRepository.save(cart);

        return "Order Placed Successfully";
    }
    public List<OrderResponse> getMyOrders() {

        User user = getCurrentUser();

        List<Order> orders = orderRepository.findByUser(user);

        List<OrderResponse> responseList = new ArrayList<>();

        for (Order order : orders) {

            OrderResponse response = new OrderResponse();

            response.setOrderId(order.getId());
            response.setOrderDate(order.getOrderDate());
            response.setStatus(order.getStatus());
            response.setTotalAmount(order.getTotalAmount());

            List<OrderItemResponse> itemResponses = new ArrayList<>();

            for (OrderItem item : order.getOrderItems()) {

                OrderItemResponse itemResponse = new OrderItemResponse();

                itemResponse.setProductId(item.getProduct().getId());
                itemResponse.setProductName(item.getProduct().getName());
                itemResponse.setPrice(item.getPrice());
                itemResponse.setQuantity(item.getQuantity());

                itemResponse.setSubtotal(
                        item.getPrice() * item.getQuantity());

                itemResponses.add(itemResponse);
            }

            response.setItems(itemResponses);

            responseList.add(response);
        }

        return responseList;
    }
    public OrderResponse getOrderById(Long id) {

        User user = getCurrentUser();

        Order order = orderRepository.findByIdAndUser(id, user)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Order Not Found"));

        OrderResponse response = new OrderResponse();

        response.setOrderId(order.getId());
        response.setOrderDate(order.getOrderDate());
        response.setStatus(order.getStatus());
        response.setTotalAmount(order.getTotalAmount());

        List<OrderItemResponse> itemResponses = new ArrayList<>();

        for (OrderItem item : order.getOrderItems()) {

            OrderItemResponse itemResponse = new OrderItemResponse();

            itemResponse.setProductId(item.getProduct().getId());
            itemResponse.setProductName(item.getProduct().getName());
            itemResponse.setPrice(item.getPrice());
            itemResponse.setQuantity(item.getQuantity());

            itemResponse.setSubtotal(
                    item.getPrice() * item.getQuantity());

            itemResponses.add(itemResponse);
        }

        response.setItems(itemResponses);

        return response;
    }
    public String cancelOrder(Long id) {

        User user = getCurrentUser();

        Order order = orderRepository.findByIdAndUser(id, user)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Order Not Found"));

        if ("CANCELLED".equals(order.getStatus())) {
            return "Order Already Cancelled";
        }

        order.setStatus("CANCELLED");

        orderRepository.save(order);

        return "Order Cancelled Successfully";
    }

}