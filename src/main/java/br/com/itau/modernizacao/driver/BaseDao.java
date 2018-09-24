package br.com.itau.modernizacao.driver;

import java.io.Serializable;

public interface BaseDao<T, PK extends Serializable> {

    T get(PK id);

    boolean exists(PK id);

    void save(T object);

    void update(T object);

    void remove(T object);

    void remove(PK id);
}
