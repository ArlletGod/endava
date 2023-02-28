package md.fin.homefinance;

import md.fin.homefinance.model.Item;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

public class ItemTest {

    @Test
    public void newItemShouldHaveZeroSum() {
        Item v1 = new Item();
        Assert.assertEquals(0, v1.getSum(), 1e-9);
    }


}