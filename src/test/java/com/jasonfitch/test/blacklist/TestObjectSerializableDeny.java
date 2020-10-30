package com.jasonfitch.test.blacklist;

import java.io.IOException;
import java.io.Serializable;

/**
 * Created by JasonFitch on 10/30/2020.
 */
public class TestObjectSerializableDeny implements Serializable {

    private String object;

    public TestObjectSerializableDeny() {
        System.out.println("new:" + this.getClass().getName());
    }

    private void writeObject(java.io.ObjectOutputStream out) throws IOException {
        System.out.println("com.jasonfitch.test.blacklist.TestObjectSerializableDeny.writeObject");
        out.defaultWriteObject();
    }

    private void readObject(java.io.ObjectInputStream in) throws IOException, ClassNotFoundException {
        System.out.println("com.jasonfitch.test.blacklist.TestObjectSerializableDeny.readObject");
        in.defaultReadObject();
    }

    public String getObject() {
        return object;
    }

    public void setObject(String object) {
        this.object = object;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TestObjectSerializableDeny)) return false;

        TestObjectSerializableDeny that = (TestObjectSerializableDeny) o;

        return object != null ? object.equals(that.object) : that.object == null;
    }

    @Override
    public int hashCode() {
        return object != null ? object.hashCode() : 0;
    }
}
