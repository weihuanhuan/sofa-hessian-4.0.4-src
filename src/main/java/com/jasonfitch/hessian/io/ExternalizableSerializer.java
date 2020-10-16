package com.jasonfitch.hessian.io;

import com.caucho.hessian.io.AbstractHessianOutput;
import com.caucho.hessian.io.AbstractSerializer;
import com.caucho.hessian.io.Hessian2Input;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

/**
 * Serializing a Externalizable object.
 */
public class ExternalizableSerializer extends AbstractSerializer {

    private Class<?> clazz;

    public ExternalizableSerializer(Class<?> cl) {
        this.clazz = cl;
    }

    /**
     * Get a Class object represent client's class of serialization.
     *
     * @param obj that is ignored
     * @return a Class object
     */
    @Override
    protected Class<?> getClass(Object obj) {
        return clazz;
    }

    /**
     * Control process of writeObject for all version of Hessian's output source.
     */
    @Override
    public void writeObject(Object obj, AbstractHessianOutput out) throws IOException {
        super.writeObject(obj, out);
    }

    /**
     * Adapt V1 version for HessianInput of Hessian.
     *
     * @see AbstractHessianOutput#writeObjectBegin(java.lang.String)
     */
    @Override
    protected void writeObject10(Object obj, AbstractHessianOutput out) throws IOException {
        writeInstance(obj, out);
        out.writeMapEnd();
    }

    /**
     * Adapt V2 version for Hessian2Input of Hessian.
     *
     * @see Hessian2Input#readObjectDefinition(java.lang.Class)
     */
    @Override
    protected void writeDefinition20(Class<?> cl, AbstractHessianOutput out) throws IOException {
        out.writeClassFieldLength(0);
    }

    /**
     * Serialize a Externalizable object into Hessian's output source.
     *
     * @param obj is going to be Serialized.
     * @param out is Hessian's output source
     * @throws IOException throws when an I/O exception of some sort has occurred
     */
    @Override
    protected void writeInstance(Object obj, AbstractHessianOutput out) throws IOException {
        try (ByteArrayOutputStream arrayOutputStream = new ByteArrayOutputStream();
             ObjectOutputStream objectOutputStream = new ObjectOutputStream(arrayOutputStream)) {
            objectOutputStream.writeObject(obj);
            out.writeBytes(arrayOutputStream.toByteArray());
            out.writeObjectEnd();
        }
    }
}
