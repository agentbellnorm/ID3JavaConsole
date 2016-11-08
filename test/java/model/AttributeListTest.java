package model;

import org.junit.*;

/**
 * Created by Morgan on 2016-11-08.
 */
public class AttributeListTest {

    AttributeList attributes;
    private final String ATTRIBUTE_1 = "attr1";
    private final String ATTRIBUTE_2 = "attr2";

    @Before
    public void before() {
        attributes = new AttributeList();
        attributes.add(ATTRIBUTE_1);
        attributes.add(ATTRIBUTE_2);
    }

    @Test
    public void subtract() throws Exception {
        AttributeList toRemove = new AttributeList();
        toRemove.add(ATTRIBUTE_2);
        Assert.assertFalse(attributes.subtract(toRemove).contains(ATTRIBUTE_2));
        Assert.assertTrue(attributes.contains(ATTRIBUTE_1) && attributes.contains(ATTRIBUTE_2));
    }
}
