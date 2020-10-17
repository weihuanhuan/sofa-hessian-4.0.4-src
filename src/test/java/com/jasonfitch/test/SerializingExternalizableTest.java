package com.jasonfitch.test;

import com.jasonfitch.hessian.io.ExternalizableSerializerFactory;
import org.junit.Test;

import java.io.IOException;

/**
 * Created by qiwei.lqw on 2016/8/24.
 */
public class SerializingExternalizableTest {

    @Test()
    public void testAll() throws IOException {
        serializingExternalizableTest();

        serializingExternalizableNullTest();

        serializingNullTest();
    }

    @Test
    public void serializingExternalizableTest() throws IOException {
        IOException ioException = new IOException("wrapper ex", new RuntimeException("caused ex"));
        TestObjectExternalizableTransient testObject = new TestObjectExternalizableTransient(ioException);
        ExternalizableSerializerFactory serializerFactory = new ExternalizableSerializerFactory();
        SerializingUtils.testHessian(testObject, serializerFactory);
        SerializingUtils.testHessian2(testObject, serializerFactory);
    }

    @Test
    public void serializingExternalizableNullTest() throws IOException {
        TestObjectExternalizableTransient testObject = new TestObjectExternalizableTransient(null);
        ExternalizableSerializerFactory serializerFactory = new ExternalizableSerializerFactory();
        SerializingUtils.testHessian(testObject, serializerFactory);
        SerializingUtils.testHessian2(testObject, serializerFactory);
    }

    @Test
    public void serializingNullTest() throws IOException {
        TestObjectExternalizableTransient testObject = null;
        ExternalizableSerializerFactory serializerFactory = new ExternalizableSerializerFactory();
        SerializingUtils.testHessian(testObject, serializerFactory);
        SerializingUtils.testHessian2(testObject, serializerFactory);
    }


}