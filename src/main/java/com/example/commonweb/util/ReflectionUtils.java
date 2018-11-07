package com.example.commonweb.util;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @ClassName: ReflectionUtils.java
 */
public class ReflectionUtils {

    ReflectionUtils() {
    }

    private static final Logger LOGGER = LoggerFactory
            .getLogger(ReflectionUtils.class);

    public static void setFieldValue(Object object, String fieldName,
                                     Object value) {
        Field field = getDeclaredField(object, fieldName);

        if (field == null) {
            throw new IllegalArgumentException("Could not find field ["
                    + fieldName + "] on target [" + object + "]");
        }
        makeAccessible(field);
        try {
            field.set(object, value);
        } catch (IllegalAccessException e) {
            LOGGER.error("", e);
        }
    }

    public static Object getFieldValue(Object object, String fieldName) {
        Field field = getDeclaredField(object, fieldName);

        if (field == null) {
            throw new IllegalArgumentException("Could not find field ["
                    + fieldName + "] on target [" + object + "]");
        }
        makeAccessible(field);

        Object result = null;
        try {
            result = field.get(object);
        } catch (IllegalAccessException e) {
            LOGGER.error("", e);
        }
        return result;
    }

    public static Object invokeMethod(Object object, String methodName,
                                      Class<?>[] parameterTypes, Object[] parameters)
            throws InvocationTargetException {
        Method method = getDeclaredMethod(object, methodName, parameterTypes);
        if (method == null) {
            throw new IllegalArgumentException("Could not find method ["
                    + methodName + "] on target [" + object + "]");
        }
        method.setAccessible(true);
        try {
            return method.invoke(object, parameters);
        } catch (IllegalAccessException e) {
            LOGGER.error("", e);
        }
        return null;
    }

    protected static Field getDeclaredField(Object object, String fieldName) {
        for (Class superClass = object.getClass(); superClass != Object.class; superClass = superClass
                .getSuperclass())
            try {
                return superClass.getDeclaredField(fieldName);
            } catch (NoSuchFieldException e) {
                LOGGER.error("", e);
            }
        return null;
    }

    /**
     * @param object
     * @return
     * @Function: ReflectionUtils::getAllDeclaredField
     * @Description: ��ø�������и�����ֶΰ���˽���ֶ�
     * @version: v1.0.0
     * @author: Administrator
     * @date: 2015��4��3�� ����10:04:07
     * <p>
     * Modification History:
     * Date         Author          Version            Description
     * -------------------------------------------------------------
     */
    public static Field[] getAllDeclaredField(Object object) {
        List<Field> l = new ArrayList<Field>();
        for (Class superClass = object.getClass(); superClass != Object.class; superClass = superClass
                .getSuperclass())
            try {
                Field[] declaredFields = superClass.getDeclaredFields();
                for (Field field : declaredFields) {
                    l.add(field);
                }
            } catch (Exception e) {
                LOGGER.error("", e);
            }
        return l.toArray(new Field[0]);
    }

    protected static void makeAccessible(Field field) {
        if ((!(Modifier.isPublic(field.getModifiers())))
                || (!(Modifier.isPublic(field.getDeclaringClass()
                .getModifiers()))))
            field.setAccessible(true);
    }

    public static Method getDeclaredMethod(Object object, String methodName,
                                           Class<?>[] parameterTypes) {
        for (Class superClass = object.getClass(); superClass != Object.class; superClass = superClass
                .getSuperclass())
            try {
                return superClass.getDeclaredMethod(methodName, parameterTypes);
            } catch (NoSuchMethodException e) {
                LOGGER.error("", e);
            }
        return null;
    }

    public static <T> Class<T> getSuperClassGenricType(Class clazz) {
        return getSuperClassGenricType(clazz, 0);
    }

    public static Class getSuperClassGenricType(Class clazz, int index) {
        Type genType = clazz.getGenericSuperclass();

        if (!(genType instanceof ParameterizedType)) {
            LOGGER.warn(clazz.getSimpleName()
                    + "'s superclass not ParameterizedType");
            return Object.class;
        }

        Type[] params = ((ParameterizedType) genType).getActualTypeArguments();

        if ((index >= params.length) || (index < 0)) {
            LOGGER.warn("Index: " + index + ", Size of "
                    + clazz.getSimpleName() + "'s Parameterized Type: "
                    + params.length);

            return Object.class;
        }
        if (!(params[index] instanceof Class)) {
            LOGGER
                    .warn(clazz.getSimpleName()
                            + " not set the actual class on superclass generic parameter");
            return Object.class;
        }
        return (Class) params[index];
    }

    public static IllegalArgumentException convertToUncheckedException(
            Exception e) {
        if ((e instanceof IllegalAccessException)
                || (e instanceof IllegalArgumentException)
                || (e instanceof NoSuchMethodException)) {
            return new IllegalArgumentException("Refelction Exception.", e);
        }
        return new IllegalArgumentException(e);
    }
}
