package com.jasonfitch.test;

import com.caucho.hessian.io.Hessian2Input;
import com.caucho.hessian.io.Hessian2Output;
import com.caucho.hessian.io.HessianInput;
import com.caucho.hessian.io.HessianOutput;
import com.jasonfitch.hessian.io.ExternalizableSerializerFactory;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.Serializable;

public class SerializingUtils {

    public static void testHessian(Serializable serializable, ExternalizableSerializerFactory serializerFactory) throws IOException {
        System.out.println("############com.jasonfitch.test.SerializingUtils.testHessian################");
        ByteArrayOutputStream bout = new ByteArrayOutputStream();
        HessianOutput hout = new HessianOutput(bout);
        hout.setSerializerFactory(serializerFactory);

        hout.writeObject(serializable);
        hout.flush();

        byte[] body = bout.toByteArray();
        ByteArrayInputStream input = new ByteArrayInputStream(body, 0, body.length);
        HessianInput hin = new HessianInput(input);
        hin.setSerializerFactory(serializerFactory);

        Serializable copy = (Serializable) hin.readObject();

        compareResult(serializable, copy);
    }

    public static void testHessian2(Serializable serializable, ExternalizableSerializerFactory serializerFactory) throws IOException {
        System.out.println("############com.jasonfitch.test.SerializingUtils.testHessian2################");
        ByteArrayOutputStream bout = new ByteArrayOutputStream();
        Hessian2Output h2out = new Hessian2Output(bout);
        h2out.setSerializerFactory(serializerFactory);

        h2out.writeObject(serializable);
        h2out.flush();

        byte[] body = bout.toByteArray();
        ByteArrayInputStream input = new ByteArrayInputStream(body, 0, body.length);
        Hessian2Input h2in = new Hessian2Input(input);
        h2in.setSerializerFactory(serializerFactory);

        Serializable copy = (Serializable) h2in.readObject();

        compareResult(serializable, copy);
    }

    public static void compareResult(Serializable serializable, Serializable copy) {
        System.out.println();
        if (serializable == null || copy == null) {
            System.out.println(serializable);
            System.out.println(copy);
            System.out.println("serializable == copy:" + (serializable == copy));
        } else {
            System.out.println(serializable.toString());
            System.out.println(copy.toString());
            System.out.println("serializable.toString().equals(copy.toString()):" + serializable.toString().equals(copy.toString()));
        }
    }

}