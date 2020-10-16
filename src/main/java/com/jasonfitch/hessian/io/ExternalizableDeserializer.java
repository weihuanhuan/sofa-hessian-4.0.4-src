package com.jasonfitch.hessian.io;

import com.caucho.hessian.io.AbstractDeserializer;
import com.caucho.hessian.io.AbstractHessianInput;
import com.caucho.hessian.io.AbstractHessianOutput;
import com.caucho.hessian.io.Hessian2Input;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

/**
 * Deserializing a Externalizable Object.
 */
public class ExternalizableDeserializer extends AbstractDeserializer {

    private Class<?> clazz;

    public ExternalizableDeserializer(Class<?> cl) {
        this.clazz = cl;
    }

    /**
     * Get a Class object represent client's class of deserialization.
     *
     * @return a Class object
     */
    @Override
    public Class<?> getType() {
        return clazz;
    }

    /**
     * Adapt V1 version for HessianInput of Hessian.
     *
     * @see AbstractHessianOutput#writeObjectBegin(java.lang.String)
     */
    @Override
    public Object readMap(AbstractHessianInput in) throws IOException {
        Object object = doReadObject(in);
        return object;
    }

    /**
     * Adapt V2 version for Hessian2Input of Hessian.
     */
    @Override
    public Object readObject(AbstractHessianInput in) throws IOException {
        Object object = doReadObject(in);
        return object;
    }

    /**
     * Adapt V2 version for Hessian2Input of Hessian.
     *
     * @see Hessian2Input#readObject()
     */
    @Override
    public Object readObject(AbstractHessianInput in, Object[] fields) throws IOException {
        Object object = doReadObject(in);
        return object;
    }

    /**
     * Deserialize a Externalizable object by reading a Hessian's input source.
     *
     * @param in is Hessian's input source
     * @return a real Externalizable object deserializing from input data
     * @throws IOException throws when an I/O exception of some sort has occurred
     */
    private Object doReadObject(AbstractHessianInput in) throws IOException {
        byte[] bytes = in.readBytes();
        if (bytes == null) {
            return null;
        }

        try (ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bytes);
             ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream)) {
            return objectInputStream.readObject();
        } catch (ClassNotFoundException e) {
            throw new IOException(String.format("Failed to deserializing a externalizable object for %s", clazz), e);
        }
    }
}
