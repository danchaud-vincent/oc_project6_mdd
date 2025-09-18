package com.orion.mdd.mapper;

import java.util.List;

public interface EntityMapper<D, E> {

    List<D> toDto(List<E> entityList);

}
