package ru.is_lab1.repository;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;

import java.util.List;
import java.util.Optional;

@ApplicationScoped
@Transactional
public interface Repository<T> {
    public T save(T t);
    public T update(T t);
    public Optional<T> findById(Long id);
    public List<T> findAll();
    public boolean delete(Long id);
    public List<T> findPage(int page, int size);
}
