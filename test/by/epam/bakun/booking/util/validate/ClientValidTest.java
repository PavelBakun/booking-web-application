package by.epam.bakun.booking.util.validate;

import org.junit.Assert;
import org.junit.Test;

public class ClientValidTest {
    @Test
    public void isClientValid() {
        ClientValid clientValid = new ClientValid("Admkim", "Abcd1234", "m@r.ru");
        Assert.assertTrue(clientValid.isClientValid());


        clientValid = new ClientValid("Admkim", "bcd1234", "m@r.ru");
        Assert.assertFalse(clientValid.isClientValid());
    }
}