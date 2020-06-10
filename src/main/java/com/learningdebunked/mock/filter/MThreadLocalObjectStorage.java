package com.learningdebunked.mock.filter;

/**
 * @author Kapil
 * @created 09/06/2020 - 2:46 PM
 * @project mock
 */
public class MThreadLocalObjectStorage {

    static private ThreadLocal threadLocal = new ThreadLocal<MRequest>();

    public static ThreadLocal<MRequest> getThreadLocal() {
        return threadLocal;
    }
}
