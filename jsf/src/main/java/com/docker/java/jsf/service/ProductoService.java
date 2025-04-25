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
        agregarProducto(new Producto(null, "Laptop HP", "Laptop HP Pavilion 15.6\" con procesador Intel Core i5, 8GB RAM y 512GB SSD. Ideal para trabajo y estudio.", new BigDecimal("799.99"), 10, "Electrónicos", "https://m.media-amazon.com/images/I/71RD3PUKyYL.jpg"));
        agregarProducto(new Producto(null, "Smartphone Samsung", "Samsung Galaxy S21 con pantalla AMOLED de 6.2\", 128GB de almacenamiento y cámara triple de 64MP.", new BigDecimal("699.99"), 15, "Electrónicos", "https://m.media-amazon.com/images/I/61jYjeuNUnL.jpg"));
        agregarProducto(new Producto(null, "Audífonos Sony", "Sony WH-1000XM4 - Audífonos inalámbricos con cancelación de ruido y hasta 30 horas de batería.", new BigDecimal("349.99"), 8, "Electrónicos", "https://m.media-amazon.com/images/I/71o8Q5XJS5L.jpg"));
        agregarProducto(new Producto(null, "Zapatillas Nike", "Nike Air Max 270 - Zapatillas deportivas con cámara de aire visible y mesh transpirable.", new BigDecimal("150.00"), 12, "Ropa", "https://m.media-amazon.com/images/I/71oEKkghg-L.jpg"));
        agregarProducto(new Producto(null, "Libro Clean Code", "Clean Code: A Handbook of Agile Software Craftsmanship por Robert C. Martin. Aprende las mejores prácticas de programación.", new BigDecimal("45.99"), 20, "Libros", "https://m.media-amazon.com/images/I/41xShlnTZTL.jpg"));
        agregarProducto(new Producto(null, "Monitor Gaming", "Monitor gaming de 27\" con resolución QHD, 165Hz y tiempo de respuesta de 1ms para una experiencia de juego inmersiva.", new BigDecimal("299.99"), 5, "Electrónicos", "https://m.media-amazon.com/images/I/81tZzR17UyL.jpg"));
        agregarProducto(new Producto(null, "Teclado Mecánico", "Teclado mecánico para gaming con RGB, switches Cherry MX y estructura de aluminio para mayor durabilidad.", new BigDecimal("89.99"), 18, "Electrónicos", "https://m.media-amazon.com/images/I/71cngLX2xuL.jpg"));
        agregarProducto(new Producto(null, "Cámara DSLR", "Cámara réflex digital con sensor CMOS de 24.1MP, grabación de video 4K y conectividad WiFi/Bluetooth.", new BigDecimal("649.99"), 6, "Electrónicos", "https://m.media-amazon.com/images/I/71EWRyqzw0L.jpg"));
        agregarProducto(new Producto(null, "Reloj Inteligente", "Smartwatch con monitoreo de actividad física, ritmo cardíaco, notificaciones y batería de larga duración.", new BigDecimal("199.99"), 14, "Electrónicos", "https://m.media-amazon.com/images/I/71HqY1W5+3L.jpg"));
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