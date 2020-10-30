package com.jasonfitch.test.blacklist;

import com.caucho.hessian.io.ClassFactory;
import com.caucho.hessian.io.SerializerFactory;
import com.jasonfitch.hessian.io.ObjectInputStreamWithLoader;
import com.jasonfitch.test.SerializingUtils;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import org.junit.Test;

/**
 * Created by JasonFitch on 10/30/2020.
 */
public class SerializerFactoryBlackListTest {

    @Test
    public void testAll() throws IOException, ClassNotFoundException {

        denySerializableTest();

        objectInputStreamTest();

    }

    @Test
    public void denySerializableTest() throws IOException {
        ClassLoader classLoader = getClassLoader();
        SerializerFactory serializerFactory = new SerializerFactory(classLoader);

        ClassFactory classFactory = serializerFactory.getClassFactory();
        classFactory.setWhitelist(false);
        classFactory.allow(TestObjectSerializableBlack.class.getName());
        classFactory.deny(TestObjectSerializableDeny.class.getName());

        Serializable testObject = new TestObjectSerializableBlack();

        SerializingUtils.testHessian2(testObject, serializerFactory);
    }

    @Test
    public void objectInputStreamTest() throws IOException, ClassNotFoundException {
        System.out.println("############ com.jasonfitch.test.blacklist.SerializerFactoryBlackListTest.objectInputStreamTest ################");
        Serializable testObject = new TestObjectSerializableBlack();

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ObjectOutputStream outputStream = new ObjectOutputStream(byteArrayOutputStream);
        outputStream.writeObject(testObject);

        byte[] bytes = byteArrayOutputStream.toByteArray();
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bytes);
        ObjectInputStream inputStream = new ObjectInputStreamWithLoader(byteArrayInputStream);

        Object copyObject = inputStream.readObject();

        boolean equals = copyObject.equals(testObject);
        System.out.println("copyObject.equals(testObject):" + equals);
    }

    private ClassLoader getClassLoader() {
        ClassLoader contextClassLoader = Thread.currentThread().getContextClassLoader();
        return contextClassLoader;
    }


}
