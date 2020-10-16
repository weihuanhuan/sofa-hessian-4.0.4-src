package com.jasonfitch.test;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

public class TestObjectExternalizableTransient implements Externalizable {
    private transient Throwable _value;

    public TestObjectExternalizableTransient() {
    }

    public TestObjectExternalizableTransient(Throwable value) {
        _value = value;
    }

    public Object getValue() {
        return _value;
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        System.out.println("com.jasonfitch.test.TestObjectExternalizableTransient.writeExternal");
        out.writeObject(_value);
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        System.out.println("com.jasonfitch.test.TestObjectExternalizableTransient.readExternal");
        _value = (Throwable) in.readObject();
    }

    private void writeObject(java.io.ObjectOutputStream out) throws IOException {
        System.out.println("com.jasonfitch.test.TestObjectExternalizableTransient.writeObject");
        out.defaultWriteObject();
    }

    private void readObject(java.io.ObjectInputStream in) throws IOException, ClassNotFoundException {
        System.out.println("com.jasonfitch.test.TestObjectExternalizableTransient.readObject");
        in.defaultReadObject();
    }

    public int hashCode() {
        if (_value != null)
            return _value.hashCode();
        else
            return 0;
    }

    public boolean equals(Object o) {
        if (!(o instanceof TestObjectExternalizableTransient))
            return false;

        TestObjectExternalizableTransient obj = (TestObjectExternalizableTransient) o;

        if (_value == null && _value == null) {
            return true;
        }

        if (_value != null && obj._value != null) {
            return _value.toString().equals(obj._value.toString());
        }

        return false;
    }

    public String toString() {
        return getClass().getName() + "[" + _value + "$$$" + _value.getCause() + "]";
    }

}
