package test.java.be.pxl.ja.exercise_7_4;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import main.java.be.pxl.ja.exercise_7_4.CartItem;
import java.util.List;
import java.util.Arrays;
import java.math.BigDecimal;

public class CartItemTests {
    private List<CartItem> cart;

    @Test
    public void checkout_shouldReturn245() {
        cart = Arrays.asList(
                    new CartItem("shoes", new BigDecimal(50)),
                    new CartItem("shoes", new BigDecimal(50)),
                    new CartItem("shoes", new BigDecimal(100)),
                    new CartItem("hat", new BigDecimal(20)),
                    new CartItem("hat", new BigDecimal(30)),
                    new CartItem("shirt", new BigDecimal(45))
                    );

        BigDecimal price = CartItem.checkout(cart);

        assertEquals(245, price.intValue());
    }

    @Test
    public void checkout_shouldReturn195() {
        cart = Arrays.asList(
                    new CartItem("shoes", new BigDecimal(50)),
                    new CartItem("shoes", new BigDecimal(50)),
                    new CartItem("hat", new BigDecimal(50)),
                    new CartItem("shirt", new BigDecimal(45))
                    );

        BigDecimal price = CartItem.checkout(cart);

        assertEquals(195, price.intValue());
    }
}
