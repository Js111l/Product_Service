package com.ecom.productservice.service;

import java.util.List;

public interface BaseService<T, ID> {
  List<T> getAll();

  T getById(ID id);

  T deleteById(ID id);

  T delete(T object);

  T create(T object);

  T update(T object);

}
