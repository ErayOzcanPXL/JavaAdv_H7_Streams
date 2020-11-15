package main.java.be.pxl.ja.exercise_7_4;

import java.util.List;
import java.math.BigDecimal;
import java.util.stream.Collectors;

public class CartItem {
    private String name;
    private BigDecimal price;

    public CartItem(String name, BigDecimal price) {
        setName(name);
        setPrice(price);
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return this.price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public static BigDecimal checkout(List<CartItem> cart) {
        BigDecimal priceOfShoes = calculatePriceOfShoes(cart, new BigDecimal(100.0), new BigDecimal(20));
        BigDecimal priceOfHats = calculatePriceOfHats(cart, 2, new BigDecimal(50.0), new BigDecimal(10.0));

        BigDecimal priceOfRemaining = cart.stream()
            .filter(cartItem -> !cartItem.getName().equals("shoes") && !cartItem.getName().equals("hat"))
            .map(CartItem::getPrice)
            .reduce(BigDecimal.ZERO, BigDecimal::add);

        return priceOfRemaining.add(priceOfShoes.add(priceOfHats));
    }

    @Override
    public String toString() {
        return this.name;
    }

    private static BigDecimal calculatePriceOfShoes(List<CartItem> cart, BigDecimal discountBarier, BigDecimal discountPercentage) {
        BigDecimal price = cart.stream()
            .filter(cartItem -> "shoes".equals(cartItem.getName()))
            .map(CartItem::getPrice)
            .reduce(BigDecimal.ZERO, BigDecimal::add);

        if (price.compareTo(discountBarier) > 0) {
            price = price.subtract(price.multiply(discountPercentage.divide(new BigDecimal(100))));
        }

        return price;
    }

    private static BigDecimal calculatePriceOfHats(List<CartItem> cart, int minimumAmount, BigDecimal discountBarier, BigDecimal discountAmount) {
        List<BigDecimal> pricesOfHats = cart.stream()
            .filter(cartItem -> "hat".equals(cartItem.getName()))
            .map(CartItem::getPrice)
            .collect(Collectors.toList());

        BigDecimal totalPrice = pricesOfHats.stream()
            .reduce(BigDecimal.ZERO, BigDecimal::add);

        if (pricesOfHats.size() >= minimumAmount && totalPrice.compareTo(discountBarier) >= 0) {
            totalPrice = totalPrice.subtract(discountAmount);
        }

        return totalPrice;
    }
}
