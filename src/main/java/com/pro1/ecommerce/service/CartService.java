package com.pro1.ecommerce.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.pro1.ecommerce.dto.CartItemResponse;
import com.pro1.ecommerce.dto.CartRequest;
import com.pro1.ecommerce.dto.CartResponse;
import com.pro1.ecommerce.entity.Cart;
import com.pro1.ecommerce.entity.CartItem;
import com.pro1.ecommerce.entity.Product;
import com.pro1.ecommerce.entity.User;
import com.pro1.ecommerce.repository.CartItemRepository;
import com.pro1.ecommerce.repository.CartRepository;
import com.pro1.ecommerce.repository.ProductRepository;
import com.pro1.ecommerce.repository.UserRepository;
import com.pro1.ecommerce.exception.ResourceNotFoundException;
@Service
public class CartService {

    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;

    public CartService(CartRepository cartRepository,
                       CartItemRepository cartItemRepository,
                       ProductRepository productRepository,
                       UserRepository userRepository) {

        this.cartRepository = cartRepository;
        this.cartItemRepository = cartItemRepository;
        this.productRepository = productRepository;
        this.userRepository = userRepository;
    }

    // Get Logged-in User
    private User getCurrentUser() {

        Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();

        String email = authentication.getName();

        return userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User Not Found"));
    }

    // Add Product to Cart
    public String addToCart(CartRequest request) {

        User user = getCurrentUser();

        Cart cart = cartRepository.findByUser(user)
                .orElseGet(() -> {
                    Cart newCart = new Cart();
                    newCart.setUser(user);
                    return cartRepository.save(newCart);
                });

        Product product = productRepository.findById(request.getProductId())
                .orElseThrow(() ->
                        new ResourceNotFoundException("Product Not Found"));

        CartItem cartItem = cartItemRepository
                .findByCartAndProduct(cart, product)
                .orElse(null);

        if (cartItem != null) {

            cartItem.setQuantity(
                    cartItem.getQuantity() + request.getQuantity());

        } else {

            cartItem = new CartItem();
            cartItem.setCart(cart);
            cartItem.setProduct(product);
            cartItem.setQuantity(request.getQuantity());
        }

        cartItemRepository.save(cartItem);

        return "Product Added To Cart";
    }

    // Get Cart
    public CartResponse getCart() {

        User user = getCurrentUser();

        Cart cart = cartRepository.findByUser(user)
                .orElse(null);

        if (cart == null) {
            return null;
        }

        List<CartItemResponse> items = new ArrayList<>();

        double totalAmount = 0;

        for (CartItem cartItem : cart.getCartItems()) {

            double subtotal =
                    cartItem.getProduct().getPrice() * cartItem.getQuantity();

            totalAmount += subtotal;

            CartItemResponse item = new CartItemResponse();

            item.setProductId(cartItem.getProduct().getId());
            item.setProductName(cartItem.getProduct().getName());
            item.setPrice(cartItem.getProduct().getPrice());
            item.setQuantity(cartItem.getQuantity());
            item.setSubtotal(subtotal);

            items.add(item);
        }

        CartResponse response = new CartResponse();
        response.setCartId(cart.getId());
        response.setItems(items);
        response.setTotalAmount(totalAmount);

        return response;
    }

    // Remove Item
    public String removeFromCart(Long cartItemId) {

    	CartItem cartItem = cartItemRepository.findById(cartItemId)
    	        .orElseThrow(() ->
    	                new ResourceNotFoundException("Cart Item Not Found"));

        cartItemRepository.delete(cartItem);

        return "Item Removed Successfully";
    }

    // Clear Cart
    public String clearCart() {

        Cart cart = cartRepository.findByUser(getCurrentUser())
                .orElse(null);

        if (cart == null) {
            return "Cart is Empty";
        }

        cartItemRepository.deleteAll(cart.getCartItems());

        cart.getCartItems().clear();

        cartRepository.save(cart);

        return "Cart Cleared Successfully";
    }
}