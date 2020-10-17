package com.jasonfitch.test;

import java.io.IOException;
import java.io.Serializable;

public class TestObjectSerializable implements Serializable {

    private Throwable _value;

    public TestObjectSerializable() {
    }

    public TestObjectSerializable(Throwable value) {
        _value = value;
    }

    public Object getValue() {
        return _value;
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
        if (!(o instanceof TestObjectSerializable))
            return false;

        TestObjectSerializable obj = (TestObjectSerializable) o;

        if (_value == null && _value == null) {
            return true;
        }

        if (_value != null && obj._value != null) {
            return _value.toString().equals(obj._value.toString());
        }

        return false;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.getClass().getName());
        sb.append("[");
        if (_value == null) {
            sb.append("_value==null");
        } else {
            sb.append(_value);
            sb.append("$$$");
            if (_value.getCause() == null) {
                sb.append("_value.getCause()==null");
            } else {
                sb.append(_value.getCause());
            }
        }
        sb.append("]");
        return sb.toString();
    }

}
