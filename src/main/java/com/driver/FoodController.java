package com.driver;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/food")
public class FoodController {
    private Menu menu;
    private Order currentOrder;

    public FoodController() {
        menu = new Menu();
        currentOrder = new Order();

        // Add some sample menu items
        menu.addMenuItem(new Food(1, "Pizza", 12.99));
        menu.addMenuItem(new Food(2, "Burger", 8.99));
        menu.addMenuItem(new Food(3, "Pasta", 10.49));
    }

    @GetMapping("/menu")
    public List<Food> getMenu() {
        return menu.getMenuItems();
    }

    @PostMapping("/order/{itemId}")
    public void placeOrder(@PathVariable int itemId) {
        Food foodItem = menu.getMenuItemById(itemId);
        if (foodItem != null) {
            currentOrder.addItem(foodItem);
        }
    }

    @GetMapping("/order")
    public Order getCurrentOrder() {
        return currentOrder;
    }

    @GetMapping("/order/total")
    public double getTotalBill() {
        return currentOrder.getTotalBill();
    }
}