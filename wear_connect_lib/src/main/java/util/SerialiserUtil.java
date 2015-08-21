package util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * Created by Kuzlo on 20.08.2015.
 */
public class SerialiserUtil {

    public static byte[] serialize(Object obj) {
        ByteArrayOutputStream b = new ByteArrayOutputStream();
        ObjectOutputStream o = null;
        try {
            o = new ObjectOutputStream(b);
            o.writeObject(obj);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return b.toByteArray();
    }

    public static Object deserialize(byte[] bytes) {
        ByteArrayInputStream b = new ByteArrayInputStream(bytes);
        ObjectInputStream o;
        try {
            o = new ObjectInputStream(b);
            return o.readObject();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}
