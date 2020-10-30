package com.jasonfitch.test.blacklist;

import java.io.IOException;
import java.io.Serializable;
import java.util.Arrays;

/**
 * Created by JasonFitch on 10/30/2020.
 */
public class TestObjectSerializableBlack implements Serializable {

    private TestObjectSerializableDeny[][] denyObject;

    public TestObjectSerializableBlack() {
        System.out.println("new:" + this.getClass().getName());
    }

    public TestObjectSerializableDeny[][] getObject() {
        return denyObject;
    }

    public void setObject(TestObjectSerializableDeny[][] denyObject) {
        this.denyObject = denyObject;
    }

    private void writeObject(java.io.ObjectOutputStream out) throws IOException {
        System.out.println("com.jasonfitch.test.blacklist.TestObjectSerializableBlack.writeObject");
        out.defaultWriteObject();
    }

    private void readObject(java.io.ObjectInputStream in) throws IOException, ClassNotFoundException {
        System.out.println("com.jasonfitch.test.blacklist.TestObjectSerializableBlack.readObject");
        in.defaultReadObject();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TestObjectSerializableBlack)) return false;

        TestObjectSerializableBlack that = (TestObjectSerializableBlack) o;

        return Arrays.deepEquals(denyObject, that.denyObject);
    }

    @Override
    public int hashCode() {
        return Arrays.deepHashCode(denyObject);
    }
}
