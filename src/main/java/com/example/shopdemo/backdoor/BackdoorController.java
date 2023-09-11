package com.example.shopdemo.backdoor;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequiredArgsConstructor
@RequestMapping("/backdoor")
public class BackdoorController {

    private final JdbcTemplate jdbcTemplate;

    private final PasswordEncoder passwordEncoder;

    @GetMapping("/setup-database")
    @Transactional
    public String setUpDatabase() {
        deleteOrderOptions();
        deleteOrderLineItems();
        deleteOrders();
        deleteCartLineItemOptions();
        deleteCartLineItems();
        deleteCarts();
        deleteImages();
        deleteProductOptionItems();
        deleteProductOptions();
        deleteProducts();
        deleteCategories();
        deleteAccessTokens();
        deleteUsers();

        createUsers();
        createCategories();
        createProducts();
        createProductOptions();
        createProductOptionItems();
        createImages();
        createOrders();
        createOrderLineItems();
        createOrderOptions();

        return "OK!";
    }

    private void deleteOrderOptions() {
        jdbcTemplate.update("DELETE FROM orders");
    }

    private void deleteOrderLineItems() {
        jdbcTemplate.update("DELETE FROM order_line_items");
    }

    private void deleteOrders() {
        jdbcTemplate.update("DELETE FROM order_options");
    }

    private void deleteCartLineItemOptions() {
        jdbcTemplate.update("DELETE FROM cart_line_item_options");
    }

    private void deleteCartLineItems() {
        jdbcTemplate.update("DELETE FROM cart_line_items");
    }

    private void deleteCarts() {
        jdbcTemplate.update("DELETE FROM carts");
    }

    private void deleteImages() {
        jdbcTemplate.update("DELETE FROM images");
    }

    private void deleteProductOptionItems() {
        jdbcTemplate.update("DELETE FROM product_option_items");
    }

    private void deleteProductOptions() {
        jdbcTemplate.update("DELETE FROM product_options");
    }

    private void deleteProducts() {
        jdbcTemplate.update("DELETE FROM products");
    }

    private void deleteCategories() {
        jdbcTemplate.update("DELETE FROM categories");
    }

    private void deleteAccessTokens() {
        jdbcTemplate.update("DELETE FROM access_tokens");
    }

    private void deleteUsers() {
        jdbcTemplate.update("DELETE FROM users");
    }

    private void createUsers() {
        LocalDateTime now = LocalDateTime.now();

        jdbcTemplate.update("""
                        INSERT INTO users (id, email, name, password, role, created_at, updated_at)
                        VALUES (?, ?, ?, ?, ?, ?, ?)
                        """, "0BV000USR0001", "tester@example.com", "테스터",
                passwordEncoder.encode("password"), "ROLE_USER", now, now
        );

        jdbcTemplate.update("""
                        INSERT INTO users (id, email, name, password, role, created_at, updated_at)
                        VALUES (?, ?, ?, ?, ?, ?, ?)
                        """, "0BV000USR0002", "admin@example.com", "관리자",
                passwordEncoder.encode("password"), "ROLE_ADMIN", now, now
        );
    }

    private void createCategories() {
        LocalDateTime now = LocalDateTime.now();

        jdbcTemplate.update("""
                        INSERT INTO categories(id, name, hidden, created_at, updated_at) VALUES (?, ?, ?, ?, ?)
                        """,
                "0BV000CAT0001", "top", false, now, now
        );

        jdbcTemplate.update("""
                        INSERT INTO categories(id, name, hidden, created_at, updated_at) VALUES (?, ?, ?, ?, ?)
                        """,
                "0BV000CAT0002", "outer", false, now, now
        );

        jdbcTemplate.update("""
                        INSERT INTO categories(id, name, hidden, created_at, updated_at) VALUES (?, ?, ?, ?, ?)
                        """,
                "0BV000CAT0003", "HIDDEN", true, now, now
        );
    }

    private void createProducts() {
        LocalDateTime now = LocalDateTime.now();

        jdbcTemplate.update("""
                        INSERT INTO products(id, category_id, name, price, description, created_at, updated_at) 
                        VALUES (?, ?, ?, ?, ?, ?, ?)
                        """,
                "0BV000PRO0001", "0BV000CAT0001", "맨투맨", 128_000L, "편하게 입을 수 있는 맨투맨", now, now
        );

        jdbcTemplate.update("""
                        INSERT INTO products(id, category_id, name, price, description, created_at, updated_at) 
                        VALUES (?, ?, ?, ?, ?, ?, ?)
                        """,
                "0BV000PRO0002", "0BV000CAT0002", "셔츠", 118_000L, "편합니다", now, now
        );
    }

    private void createProductOptions() {
        LocalDateTime now = LocalDateTime.now();

        jdbcTemplate.update("""
                        INSERT INTO product_options(id, product_id, name, created_at, updated_at) 
                        VALUES (?, ?, ?, ?, ?)
                        """,
                "0BV000OPT0001", "0BV000PRO0001", "색상", now, now
        );

        jdbcTemplate.update("""
                        INSERT INTO product_options(id, product_id, name, created_at, updated_at) 
                        VALUES (?, ?, ?, ?, ?)
                        """,
                "0BV000OPT0002", "0BV000PRO0001", "사이즈", now, now
        );
    }

    private void createProductOptionItems() {
        LocalDateTime now = LocalDateTime.now();

        jdbcTemplate.update("""
                        INSERT INTO product_option_items(id, product_option_id, name, created_at, updated_at) 
                        VALUES (?, ?, ?, ?, ?)
                        """,
                "0BV000ITEM0001", "0BV000OPT0001", "Black", now, now
        );

        jdbcTemplate.update("""
                        INSERT INTO product_option_items(id, product_option_id, name, created_at, updated_at) 
                        VALUES (?, ?, ?, ?, ?)
                        """,
                "0BV000ITEM0002", "0BV000OPT0001", "White", now, now
        );

        jdbcTemplate.update("""
                        INSERT INTO product_option_items(id, product_option_id, name, created_at, updated_at) 
                        VALUES (?, ?, ?, ?, ?)
                        """,
                "0BV000ITEM0003", "0BV000OPT0002", "S", now, now
        );

        jdbcTemplate.update("""
                        INSERT INTO product_option_items(id, product_option_id, name, created_at, updated_at) 
                        VALUES (?, ?, ?, ?, ?)
                        """,
                "0BV000ITEM0004", "0BV000OPT0002", "M", now, now
        );

        jdbcTemplate.update("""
                        INSERT INTO product_option_items(id, product_option_id, name, created_at, updated_at) 
                        VALUES (?, ?, ?, ?, ?)
                        """,
                "0BV000ITEM0005", "0BV000OPT0002", "L", now, now
        );
    }

    private void createImages() {
        LocalDateTime now = LocalDateTime.now();

        jdbcTemplate.update("""
                        INSERT INTO images(id, product_id, url, created_at, updated_at) 
                        VALUES (?, ?, ?, ?, ?)
                        """,
                "0BV000IMG0001", "0BV000PRO0001",
                "https://ahastudio.github.io/my-image-assets/images/cbcl-products/01.jpg", now, now
        );

        jdbcTemplate.update("""
                        INSERT INTO images(id, product_id, url, created_at, updated_at) 
                        VALUES (?, ?, ?, ?, ?)
                        """,
                "0BV000IMG0002", "0BV000PRO0002",
                "https://ahastudio.github.io/my-image-assets/images/cbcl-products/02.jpg", now, now
        );
    }

    private void createOrders() {
        LocalDateTime now = LocalDateTime.now();

        jdbcTemplate.update("""
                        INSERT INTO orders(id, user_id, total_price, receiver_name, address1, address2, postal_code,
                            phone_number, payment_merchant_id, payment_transaction_id,
                            status, ordered_at, created_at, updated_at)
                        VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)
                        """,
                "0BV00ODR0001", "0BV00USR0001", 246_000, "테스터", "상원12길 34", "ㅇㅇㅇ호", "04790",
                "010-1234-5678", "PaymentMerchantID", "PaymentTransactionID",
                "PAID", now, now, now
        );
    }

    private void createOrderLineItems() {
        LocalDateTime now = LocalDateTime.now();

        jdbcTemplate.update("""
                        INSERT INTO order_line_items(id, order_id, product_id, product_name, unit_price, quantity,
                            total_price, created_at, updated_at)
                        VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)
                        """,
                "0BV00OLI0001", "0BV00ODR0001", "0BV00PRO0001", "맨투맨", 128_000, 1,
                128_000, now, now
        );

        jdbcTemplate.update("""
                        INSERT INTO order_line_items(id, order_id, product_id, product_name, unit_price, quantity,
                            total_price, created_at, updated_at)
                        VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)
                        """,
                "0BV00OLI0002", "0BV00ODR0001", "0BV00PRO0002", "셔츠", 118_000, 1,
                118_000, now, now
        );
    }

    private void createOrderOptions() {
        LocalDateTime now = LocalDateTime.now();

        jdbcTemplate.update("""
                        INSERT INTO order_options(id, order_line_item_id, product_option_id, name, product_option_item_id,
                            product_option_item_name, created_at, updated_at)
                        VALUES (?, ?, ?, ?, ?, ?, ?, ?)
                        """, "0BV00OOP0001", "0BV00OLI0001", "0BV00OPT0001", "색상", "0BV00ITEM002",
                "White", now, now
        );

        jdbcTemplate.update("""
                        INSERT INTO order_options(id, order_line_item_id, product_option_id, name, product_option_item_id,
                            product_option_item_name, created_at, updated_at)
                        VALUES (?, ?, ?, ?, ?, ?, ?, ?)
                        """, "0BV00OOP0002", "0BV00OLI0001", "0BV00OPT0002", "사이즈", "0BV00ITEM005",
                "L", now, now
        );
    }

}
