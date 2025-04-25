package com.docker.java.jsf.controller;

import com.docker.java.jsf.model.Producto;
import com.docker.java.jsf.service.ProductoService;
import jakarta.annotation.PostConstruct;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import org.primefaces.PrimeFaces;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Named
@ViewScoped
public class ProductoController implements Serializable {

    private static final long serialVersionUID = 1L;

    @Inject
    private ProductoService productoService;

    private List<Producto> productos;
    private Producto productoSeleccionado;
    private List<Producto> productosFiltrados;
    private String terminoBusqueda;
    private String viewMode = "table"; // Valores posibles: "table", "grid"
    private List<String> categorias;

    @PostConstruct
    public void init() {
        this.productos = productoService.getProductos();
        this.productoSeleccionado = new Producto();
        this.productosFiltrados = new ArrayList<>();
        this.categorias = Arrays.asList("Electrónicos", "Ropa", "Libros", "Hogar", "Juguetes", "Deportes", "Alimentos", "Otros");
    }

    public void cargarProductos() {
        this.productos = productoService.getProductos();
    }

    public void prepararNuevoProducto() {
        this.productoSeleccionado = new Producto();
    }

    public String getViewMode() {
        return viewMode;
    }
    
    public void setViewMode(String viewMode) {
        this.viewMode = viewMode;
    }

    public void guardarProducto() {
        try {
            if (this.productoSeleccionado.getId() == null) {
                productoService.agregarProducto(this.productoSeleccionado);
                FacesContext.getCurrentInstance().addMessage(null, 
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Producto Agregado", "El producto ha sido agregado correctamente."));
            } else {
                productoService.actualizarProducto(this.productoSeleccionado);
                FacesContext.getCurrentInstance().addMessage(null, 
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Producto Actualizado", "El producto ha sido actualizado correctamente."));
            }
            
            this.productos = productoService.getProductos();
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, 
                new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Ocurrió un error al guardar el producto: " + e.getMessage()));
        }
    }

    public void eliminarProducto() {
        try {
            productoService.eliminarProducto(this.productoSeleccionado.getId());
            this.productos = productoService.getProductos();
            FacesContext.getCurrentInstance().addMessage(null, 
                new FacesMessage(FacesMessage.SEVERITY_INFO, "Producto Eliminado", "El producto ha sido eliminado correctamente."));
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, 
                new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Ocurrió un error al eliminar el producto: " + e.getMessage()));
        }
    }

    public void buscarProductos() {
        if (terminoBusqueda != null && !terminoBusqueda.trim().isEmpty()) {
            this.productos = productoService.buscarProductos(this.terminoBusqueda);
            if (this.productos.isEmpty()) {
                FacesContext.getCurrentInstance().addMessage(null, 
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Sin resultados", "No se encontraron productos que coincidan con: " + this.terminoBusqueda));
            }
        } else {
            this.productos = productoService.getProductos();
        }
    }

    public List<Producto> getProductos() {
        return productos;
    }

    public void setProductos(List<Producto> productos) {
        this.productos = productos;
    }

    public Producto getProductoSeleccionado() {
        return productoSeleccionado;
    }

    public void setProductoSeleccionado(Producto productoSeleccionado) {
        this.productoSeleccionado = productoSeleccionado;
    }

    public List<Producto> getProductosFiltrados() {
        return productosFiltrados;
    }

    public void setProductosFiltrados(List<Producto> productosFiltrados) {
        this.productosFiltrados = productosFiltrados;
    }

    public String getTerminoBusqueda() {
        return terminoBusqueda;
    }

    public void setTerminoBusqueda(String terminoBusqueda) {
        this.terminoBusqueda = terminoBusqueda;
    }
    
    public List<String> getCategorias() {
        return categorias;
    }
} 