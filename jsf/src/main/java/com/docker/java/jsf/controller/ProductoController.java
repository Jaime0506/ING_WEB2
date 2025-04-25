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
import java.util.List;

@Named
@ViewScoped
public class ProductoController implements Serializable {

    private static final long serialVersionUID = 1L;

    @Inject
    private ProductoService productoService;

    private List<Producto> productos;
    private Producto productoSeleccionado;
    private List<Producto> productosSeleccionados;
    private String terminoBusqueda;

    @PostConstruct
    public void init() {
        this.productos = productoService.getProductos();
        this.productoSeleccionado = new Producto();
    }

    public void cargarProductos() {
        this.productos = productoService.getProductos();
    }

    public void abrirNuevo() {
        this.productoSeleccionado = new Producto();
    }

    public void guardarProducto() {
        if (this.productoSeleccionado.getId() == null) {
            productoService.agregarProducto(this.productoSeleccionado);
            FacesContext.getCurrentInstance().addMessage(null, 
                new FacesMessage("Producto Agregado", "El producto ha sido agregado correctamente."));
        } else {
            productoService.actualizarProducto(this.productoSeleccionado);
            FacesContext.getCurrentInstance().addMessage(null, 
                new FacesMessage("Producto Actualizado", "El producto ha sido actualizado correctamente."));
        }
        
        this.productos = productoService.getProductos();
        PrimeFaces.current().executeScript("PF('productoDialog').hide()");
    }

    public void eliminarProducto() {
        productoService.eliminarProducto(this.productoSeleccionado.getId());
        this.productoSeleccionado = null;
        this.productos = productoService.getProductos();
        FacesContext.getCurrentInstance().addMessage(null, 
            new FacesMessage("Producto Eliminado", "El producto ha sido eliminado correctamente."));
        PrimeFaces.current().ajax().update("form:messages", "form:dt-productos");
    }

    public void eliminarProductosSeleccionados() {
        for (Producto producto : this.productosSeleccionados) {
            productoService.eliminarProducto(producto.getId());
        }
        this.productosSeleccionados = null;
        this.productos = productoService.getProductos();
        FacesContext.getCurrentInstance().addMessage(null, 
            new FacesMessage("Productos Eliminados", "Los productos seleccionados han sido eliminados."));
        PrimeFaces.current().ajax().update("form:messages", "form:dt-productos");
        PrimeFaces.current().executeScript("PF('dtProductos').clearFilters()");
    }

    public void buscarProductos() {
        this.productos = productoService.buscarProductos(this.terminoBusqueda);
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

    public List<Producto> getProductosSeleccionados() {
        return productosSeleccionados;
    }

    public void setProductosSeleccionados(List<Producto> productosSeleccionados) {
        this.productosSeleccionados = productosSeleccionados;
    }

    public String getTerminoBusqueda() {
        return terminoBusqueda;
    }

    public void setTerminoBusqueda(String terminoBusqueda) {
        this.terminoBusqueda = terminoBusqueda;
    }
} 