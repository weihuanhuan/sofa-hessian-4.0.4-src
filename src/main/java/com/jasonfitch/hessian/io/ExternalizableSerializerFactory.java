package com.jasonfitch.hessian.io;

import com.caucho.hessian.io.Deserializer;
import com.caucho.hessian.io.HessianProtocolException;
import com.caucho.hessian.io.Serializer;
import com.caucho.hessian.io.SerializerFactory;
import java.io.Externalizable;

/**
 * Factory for returning serialization methods, added Externalizability.
 */
public class ExternalizableSerializerFactory extends SerializerFactory {

    @Override
    protected Serializer loadSerializer(Class<?> cl) throws HessianProtocolException {
        return super.loadSerializer(cl);
    }

    @Override
    protected Serializer getDefaultSerializer(Class cl) {
        if (Externalizable.class.isAssignableFrom(cl)) {
            return new ExternalizableSerializer(cl);
            //            return JavaSerializer.create(cl);
        }
        return super.getDefaultSerializer(cl);
    }

    @Override
    protected Deserializer loadDeserializer(Class cl) throws HessianProtocolException {
        return super.loadDeserializer(cl);
    }

    @Override
    protected Deserializer getDefaultDeserializer(Class cl) {
        if (Externalizable.class.isAssignableFrom(cl)) {
            return new ExternalizableDeserializer(cl);
            //            return JavaSerializer.create(cl);
        }
        return super.getDefaultDeserializer(cl);
    }

}
