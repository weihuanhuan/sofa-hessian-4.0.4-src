package com.jasonfitch.hessian.io;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectStreamClass;
import java.lang.reflect.Modifier;
import java.lang.reflect.Proxy;

/**
 * Created by JasonFitch on 10/30/2020.
 */
public class ObjectInputStreamWithLoader extends ObjectInputStream {

    public ObjectInputStreamWithLoader() throws IOException, SecurityException {
    }

    public ObjectInputStreamWithLoader(InputStream in) throws IOException {
        super(in);
    }

    /**
     * @see ObjectInputStream#resolveClass(java.io.ObjectStreamClass)
     */
    @Override
    protected Class<?> resolveClass(ObjectStreamClass desc) throws IOException, ClassNotFoundException {
        try {
            ClassLoader classLoader = getClassLoader();
            String descName = desc.getName();
            return Class.forName(descName, false, classLoader);
        } catch (ClassNotFoundException ignoredEx) {
            //try against with super
            return super.resolveClass(desc);
        }
    }

    /**
     * @see ObjectInputStream#resolveProxyClass(java.lang.String[])
     */
    @Override
    protected Class<?> resolveProxyClass(String[] interfaces) throws IOException, ClassNotFoundException {
        ClassLoader classLoader = getClassLoader();
        ClassLoader nonPublicLoader = null;
        boolean hasNonPublicInterface = false;

        // define proxy in class loader of non-public interface(s), if any
        Class<?>[] classObjs = new Class<?>[interfaces.length];
        for (int i = 0; i < interfaces.length; i++) {
            Class<?> cl = Class.forName(interfaces[i], false, classLoader);
            if ((cl.getModifiers() & Modifier.PUBLIC) == 0) {
                if (hasNonPublicInterface) {
                    if (nonPublicLoader != cl.getClassLoader()) {
                        throw new IllegalAccessError("conflicting non-public interface class loaders");
                    }
                } else {
                    nonPublicLoader = cl.getClassLoader();
                    hasNonPublicInterface = true;
                }
            }
            classObjs[i] = cl;
        }

        try {
            return Proxy.getProxyClass(hasNonPublicInterface ? nonPublicLoader : classLoader, classObjs);
        } catch (IllegalArgumentException e) {
            //try against with super
            return super.resolveProxyClass(interfaces);
        }
    }

    /**
     * Using context classloader from current thread.
     */
    private ClassLoader getClassLoader() {
        ClassLoader contextClassLoader = Thread.currentThread().getContextClassLoader();
        return contextClassLoader;
    }
}
