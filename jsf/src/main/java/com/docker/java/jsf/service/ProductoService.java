package com.docker.java.jsf.service;

import java.util.List;
import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

import com.docker.java.jsf.model.Producto;
import com.docker.java.jsf.repository.ProductoRepository;

@Service
public class ProductoService {

    @Autowired
    private ProductoRepository productoRepository;

    @PersistenceContext
    private EntityManager entityManager;

    public List<Producto> listarProductos() {
        return productoRepository.findAll();
    }

    public Optional<Producto> obtenerProductoPorId(Long id) {
        return productoRepository.findById(id);
    }

    @Transactional
    public Producto guardarProducto(Producto producto) {
        return productoRepository.save(producto);
    }

    @Transactional
    public void eliminarProducto(Long id) {
        productoRepository.deleteById(id);
    }

    public List<Producto> buscarProductos(String nombre, String descripcion, 
                                        Double precioMin, Double precioMax,
                                        Integer stockMin, Integer stockMax,
                                        String categoria) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Producto> query = cb.createQuery(Producto.class);
        Root<Producto> root = query.from(Producto.class);
        
        List<Predicate> predicates = new ArrayList<>();
        
        if (nombre != null && !nombre.isEmpty()) {
            predicates.add(cb.like(cb.lower(root.get("nombre")), 
                                 "%" + nombre.toLowerCase() + "%"));
        }
        
        if (descripcion != null && !descripcion.isEmpty()) {
            predicates.add(cb.like(cb.lower(root.get("descripcion")), 
                                 "%" + descripcion.toLowerCase() + "%"));
        }
        
        if (precioMin != null) {
            predicates.add(cb.greaterThanOrEqualTo(root.get("precio"), precioMin));
        }
        
        if (precioMax != null) {
            predicates.add(cb.lessThanOrEqualTo(root.get("precio"), precioMax));
        }
        
        if (stockMin != null) {
            predicates.add(cb.greaterThanOrEqualTo(root.get("stock"), stockMin));
        }
        
        if (stockMax != null) {
            predicates.add(cb.lessThanOrEqualTo(root.get("stock"), stockMax));
        }
        
        if (categoria != null && !categoria.isEmpty()) {
            predicates.add(cb.equal(root.get("categoria"), categoria));
        }
        
        query.where(predicates.toArray(new Predicate[0]));
        
        TypedQuery<Producto> typedQuery = entityManager.createQuery(query);
        return typedQuery.getResultList();
    }
} 