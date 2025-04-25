package com.docker.java.jsf.service;

import com.docker.java.jsf.model.Producto;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Named;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

@Named
@ApplicationScoped
public class ProductoService {

    private List<Producto> productos;
    private AtomicLong contadorId = new AtomicLong(1);

    @PostConstruct
    public void init() {
        productos = new ArrayList<>();
        // Agregar algunos productos de ejemplo
        agregarProducto(new Producto(null, "Laptop HP", "Laptop HP Pavilion 15.6\"", new BigDecimal("799.99"), 10, "Electrónicos", "https://m.media-amazon.com/images/I/71RD3PUKyYL.jpg"));
        agregarProducto(new Producto(null, "Smartphone Samsung", "Samsung Galaxy S21", new BigDecimal("699.99"), 15, "Electrónicos", "https://m.media-amazon.com/images/I/61jYjeuNUnL.jpg"));
        agregarProducto(new Producto(null, "Audífonos Sony", "Sony WH-1000XM4", new BigDecimal("349.99"), 8, "Electrónicos", "https://m.media-amazon.com/images/I/71o8Q5XJS5L.jpg"));
        agregarProducto(new Producto(null, "Zapatillas Nike", "Nike Air Max 270", new BigDecimal("150.00"), 12, "Ropa", "https://m.media-amazon.com/images/I/71oEKkghg-L.jpg"));
        agregarProducto(new Producto(null, "Libro Clean Code", "Robert C. Martin", new BigDecimal("45.99"), 20, "Libros", "https://m.media-amazon.com/images/I/41xShlnTZTL.jpg"));
    }

    public List<Producto> getProductos() {
        return new ArrayList<>(productos);
    }

    public Producto getProductoPorId(Long id) {
        return productos.stream()
                .filter(p -> p.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    public Producto agregarProducto(Producto producto) {
        if (producto.getId() == null) {
            producto.setId(contadorId.getAndIncrement());
        }
        productos.add(producto);
        return producto;
    }

    public Producto actualizarProducto(Producto producto) {
        int index = -1;
        for (int i = 0; i < productos.size(); i++) {
            if (productos.get(i).getId().equals(producto.getId())) {
                index = i;
                break;
            }
        }
        if (index >= 0) {
            productos.set(index, producto);
            return producto;
        }
        return null;
    }

    public boolean eliminarProducto(Long id) {
        return productos.removeIf(p -> p.getId().equals(id));
    }

    public List<Producto> buscarProductos(String query) {
        if (query == null || query.isEmpty()) {
            return getProductos();
        }
        
        String queryLower = query.toLowerCase();
        return productos.stream()
                .filter(p -> p.getNombre().toLowerCase().contains(queryLower) || 
                             p.getDescripcion().toLowerCase().contains(queryLower) || 
                             p.getCategoria().toLowerCase().contains(queryLower))
                .collect(Collectors.toList());
    }
} 