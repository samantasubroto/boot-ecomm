package com.example.springboot_demo.controllers;

import com.example.springboot_demo.model.*;
import com.example.springboot_demo.model.Price;
import com.example.springboot_demo.model.entity.Address;
import com.example.springboot_demo.model.entity.Customer;
import com.example.springboot_demo.model.entity.Product;
import com.example.springboot_demo.model.entity.User;
import com.example.springboot_demo.model.enums.Currency;
import com.example.springboot_demo.model.enums.StockStatus;
import com.example.springboot_demo.repository.CustomerRepository;
import com.example.springboot_demo.repository.ProductRepository;
import com.example.springboot_demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
public class DataController {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @GetMapping("/product/data")
    public String setProd() {
        List<Product> products = new ArrayList<>();
        products.add(new Product("100000001", "Product 1", "High-quality product", "Top seller", true, new Price(Currency.INR, 198.22, true), "image1.jpg", new Stock(StockStatus.INSTOCK, 55)));
        products.add(new Product("100000002", "Product 2", "Premium product with great reviews", "Highly recommended", true, new Price(Currency.INR, 299.99, true), "image2.jpg", new Stock(StockStatus.INSTOCK, 100)));
        products.add(new Product("100000003", "Product 3", "Affordable and reliable", "Limited stock available", true, new Price(Currency.INR, 129.99, true), "image3.jpg", new Stock(StockStatus.INSTOCK, 25)));
        products.add(new Product("100000004", "Product 4", "Out of stock item", "Currently unavailable", false, new Price(Currency.INR, 112.00, false), "image4.jpg", new Stock(StockStatus.OUTOFSTOCK, 0)));
        products.add(new Product("100000005", "Product 5", "Luxury product for professionals", "Best in category", true, new Price(Currency.INR, 999.99, true), "image5.jpg", new Stock(StockStatus.INSTOCK, 10)));
        products.add(new Product("100000006", "Product 6", "Budget-friendly option", "On sale", true, new Price(Currency.INR, 499.99, true), "image6.jpg", new Stock(StockStatus.INSTOCK, 200)));
        products.add(new Product("100000007", "Product 7", "Top-tier quality product", "High durability", true, new Price(Currency.INR, 699.99, true), "image7.jpg", new Stock(StockStatus.INSTOCK, 50)));
        products.add(new Product("100000008", "Product 8", "Perfect for enthusiasts", "Limited edition", true, new Price(Currency.INR, 899.99, true), "image8.jpg", new Stock(StockStatus.INSTOCK, 30)));
        products.add(new Product("100000009", "Product 9", "No longer available", "Discontinued", false, new Price(Currency.INR, 234.00, false), "image9.jpg", new Stock(StockStatus.OUTOFSTOCK, 0)));
        products.add(new Product("100000010", "Product 10", "Great value for money", "Special deal", true, new Price(Currency.INR, 399.99, true), "image10.jpg", new Stock(StockStatus.INSTOCK, 60)));
        products.add(new Product("100000011", "Product 11", "Reliable and durable", "Customer favorite", true, new Price(Currency.INR, 249.99, true), "image11.jpg", new Stock(StockStatus.INSTOCK, 80)));
        products.add(new Product("100000012", "Product 12", "Compact and powerful", "Highly portable", true, new Price(Currency.INR, 349.99, true), "image12.jpg", new Stock(StockStatus.INSTOCK, 40)));
        products.add(new Product("100000013", "Product 13", "Innovative design", "Award-winning", true, new Price(Currency.INR, 549.99, true), "image13.jpg", new Stock(StockStatus.INSTOCK, 20)));
        products.add(new Product("100000014", "Product 14", "Eco-friendly choice", "Sustainable materials", true, new Price(Currency.INR, 299.99, true), "image14.jpg", new Stock(StockStatus.INSTOCK, 120)));
        products.add(new Product("100000015", "Product 15", "Best seller", "Highly rated by users", true, new Price(Currency.INR, 459.99, true), "image15.jpg", new Stock(StockStatus.INSTOCK, 70)));
        products.add(new Product("100000016", "Product 16", "Premium performance", "Great for professionals", true, new Price(Currency.INR, 799.99, true), "image16.jpg", new Stock(StockStatus.INSTOCK, 35)));
        products.add(new Product("100000017", "Product 17", "Versatile and adaptable", "Multi-purpose use", true, new Price(Currency.INR, 379.99, true), "image17.jpg", new Stock(StockStatus.INSTOCK, 90)));
        products.add(new Product("100000018", "Product 18", "Portable and lightweight", "Perfect for travel", true, new Price(Currency.INR, 199.99, true), "image18.jpg", new Stock(StockStatus.INSTOCK, 150)));
        products.add(new Product("100000019", "Product 19", "Energy-efficient", "Low power consumption", true, new Price(Currency.INR, 289.99, true), "image19.jpg", new Stock(StockStatus.INSTOCK, 65)));
        products.add(new Product("100000020", "Product 20", "Stylish and modern", "Great aesthetics", true, new Price(Currency.INR, 599.99, true), "image20.jpg", new Stock(StockStatus.INSTOCK, 45)));
        products.add(new Product("100000021", "Product 21", "Ultimate comfort", "Ergonomic design", true, new Price(Currency.INR, 259.99, true), "image21.jpg", new Stock(StockStatus.INSTOCK, 75)));
        products.add(new Product("100000022", "Product 22", "Efficient and fast", "Optimized for performance", true, new Price(Currency.INR, 659.99, true), "image22.jpg", new Stock(StockStatus.INSTOCK, 25)));
        products.add(new Product("100000023", "Product 23", "Durable and strong", "Long-lasting quality", true, new Price(Currency.INR, 349.99, true), "image23.jpg", new Stock(StockStatus.INSTOCK, 55)));
        products.add(new Product("100000024", "Product 24", "Innovative technology", "Feature-packed", true, new Price(Currency.INR, 999.99, true), "image24.jpg", new Stock(StockStatus.INSTOCK, 15)));
        products.add(new Product("100000025", "Product 25", "Compact and efficient", "Space-saving", true, new Price(Currency.INR, 219.99, true), "image25.jpg", new Stock(StockStatus.INSTOCK, 110)));
        products.add(new Product("100000026", "Product 26", "Lightweight and durable", "Highly portable", true, new Price(Currency.INR, 329.99, true), "image26.jpg", new Stock(StockStatus.INSTOCK, 130)));
        products.add(new Product("100000027", "Product 27", "Stylish and efficient", "Perfect for modern use", true, new Price(Currency.INR, 439.99, true), "image27.jpg", new Stock(StockStatus.INSTOCK, 90)));
        products.add(new Product("100000028", "Product 28", "Advanced technology", "High-end features", true, new Price(Currency.INR, 859.99, true), "image28.jpg", new Stock(StockStatus.INSTOCK, 30)));
        products.add(new Product("100000029", "Product 29", "Premium quality", "Recommended by experts", true, new Price(Currency.INR, 559.99, true), "image29.jpg", new Stock(StockStatus.INSTOCK, 40)));
        products.add(new Product("100000030", "Product 30", "Reliable performance", "Tested for durability", true, new Price(Currency.INR, 289.99, true), "image30.jpg", new Stock(StockStatus.INSTOCK, 60)));
        products.add(new Product("100000031", "Product 31", "Energy-saving device", "Environmentally friendly", true, new Price(Currency.INR, 319.99, true), "image31.jpg", new Stock(StockStatus.INSTOCK, 100)));
        products.add(new Product("100000032", "Product 32", "Compact powerhouse", "Ideal for small spaces", true, new Price(Currency.INR, 409.99, true), "image32.jpg", new Stock(StockStatus.INSTOCK, 50)));
        products.add(new Product("100000033", "Product 33", "Ergonomic and sleek", "Designed for comfort", true, new Price(Currency.INR, 519.99, true), "image33.jpg", new Stock(StockStatus.INSTOCK, 25)));
        products.add(new Product("100000034", "Product 34", "Innovative features", "Perfect for enthusiasts", true, new Price(Currency.INR, 689.99, true), "image34.jpg", new Stock(StockStatus.INSTOCK, 15)));
        products.add(new Product("100000035", "Product 35", "Best value", "Highly rated by users", true, new Price(Currency.INR, 249.99, true), "image35.jpg", new Stock(StockStatus.INSTOCK, 85)));
        products.add(new Product("100000036", "Product 36", "Luxurious feel", "Top of the line", true, new Price(Currency.INR, 749.99, true), "image36.jpg", new Stock(StockStatus.INSTOCK, 12)));
        products.add(new Product("100000037", "Product 37", "Budget-friendly pick", "Affordable and useful", true, new Price(Currency.INR, 199.99, true), "image37.jpg", new Stock(StockStatus.INSTOCK, 120)));
        products.add(new Product("100000038", "Product 38", "Stylish gadget", "Trendy design", true, new Price(Currency.INR, 339.99, true), "image38.jpg", new Stock(StockStatus.INSTOCK, 45)));
        products.add(new Product("100000039", "Product 39", "Compact and powerful", "Portable convenience", true, new Price(Currency.INR, 469.99, true), "image39.jpg", new Stock(StockStatus.INSTOCK, 35)));
        products.add(new Product("100000040", "Product 40", "Sustainable and green", "Eco-friendly features", true, new Price(Currency.INR, 309.99, true), "image40.jpg", new Stock(StockStatus.INSTOCK, 105)));
        productRepository.saveAll(products);
        return "success";
    }

    @PostMapping("/customer/data")
    public String setUser() {
        List<Customer> customers = new ArrayList<>();

        // Creating Addresses for customers
        List<Address> rohitAddress = List.of(new Address("101", "Sunshine Apartments", "MG Road", "Mumbai", "Maharashtra", "400001", null));
        List<Address> vikasAddress = List.of(new Address("102", "Green Residency", "HSR Layout", "Bangalore", "Karnataka", "560102", null));
        List<Address> shreyasAddress = List.of(new Address("103", "Skyline Towers", "Anna Nagar", "Chennai", "Tamil Nadu", "600040", null));
        List<Address> viratAddress = List.of(new Address("104", "Royal Mansion", "Sector 45", "Delhi", "Delhi", "110001", null));
        List<Address> rainaAddress = List.of(new Address("105", "Elegant Homes", "Salt Lake", "Kolkata", "West Bengal", "700091", null));

        // Creating Customer objects with Address list
        Customer rohit = new Customer("rohit", "sharma", "rohitsharma@gmail.com", "Sharma@123", "9959974149", new HashSet<>(List.of("Customer")), rohitAddress);
        Customer vikas = new Customer("vikas", "sharma", "vikassharma@gmail.com", "Sharma@123", "9348291212", new HashSet<>(List.of("Customer")), vikasAddress);
        Customer shreyas = new Customer("shreyas", "sharma", "shreyassharma@gmail.com", "Sharma@123", "9348291213", new HashSet<>(List.of("Customer")), shreyasAddress);
        Customer virat = new Customer("virat", "sharma", "viratsharma@gmail.com", "Sharma@123", "9348291214", new HashSet<>(List.of("Customer")), viratAddress);
        Customer raina = new Customer("raina", "sharma", "rainasharma@gmail.com", "Sharma@123", "9348291215", new HashSet<>(List.of("Customer")), rainaAddress);

        // Setting each Address's Customer field
        rohit.getAddress().forEach(addr -> addr.setCustomer(rohit));
        vikas.getAddress().forEach(addr -> addr.setCustomer(vikas));
        shreyas.getAddress().forEach(addr -> addr.setCustomer(shreyas));
        virat.getAddress().forEach(addr -> addr.setCustomer(virat));
        raina.getAddress().forEach(addr -> addr.setCustomer(raina));

        // Adding customers to list
        customers.add(rohit);
        customers.add(vikas);
        customers.add(shreyas);
        customers.add(virat);
        customers.add(raina);

        // Save all customers, which will also save addresses due to cascade
        customerRepository.saveAll(customers);

        return "success";
    }


//    @PostMapping("/customer/data")
//    public String setUser() {
//        List<User> users = new ArrayList<>();
//        users.add(new User("rohit","sharma","rohitsharma@gmail.com","Sharma@","9959974149"));
//        userRepository.saveAll(users);
//        return "success";
//    }
}