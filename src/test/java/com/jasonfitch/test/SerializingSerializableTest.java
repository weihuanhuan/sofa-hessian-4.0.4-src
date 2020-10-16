package com.jasonfitch.test;

import com.jasonfitch.hessian.io.ExternalizableSerializerFactory;
import org.junit.Test;

import java.io.IOException;

/**
 * Created by qiwei.lqw on 2016/8/24.
 */
public class SerializingSerializableTest {

    @Test()
    public void testAll() throws IOException {
        serializingSerializableTest();
    }

    @Test
    public void serializingSerializableTest() throws IOException {
        IOException ioException = new IOException("wrapper ex", new RuntimeException("caused ex"));
        TestObjectSerializable testObject = new TestObjectSerializable(ioException);
        ExternalizableSerializerFactory serializerFactory = new ExternalizableSerializerFactory();
        SerializingUtils.testHessian(testObject, serializerFactory);
        SerializingUtils.testHessian2(testObject, serializerFactory);
    }

}