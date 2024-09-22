package com.andrew.messenger.mapper;


public interface Mapper<F, T> {

    T map(F from);

    default T map(F fromObject, T toObject) {
        return toObject;
    }
}
