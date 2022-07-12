package com.yety.todoapp.mapper;

public interface IMapper <In, Out>{
    public Out map(In in);
}
