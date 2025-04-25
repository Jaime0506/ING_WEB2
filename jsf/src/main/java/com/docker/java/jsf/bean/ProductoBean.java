package com.docker.java.jsf.bean;

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
import java.math.BigDecimal;
import java.util.List;

@Named
@ViewScoped
public class ProductoBean implements Serializable {

    private static final long serialVersionUID = 1L;

    @Inject
    private ProductoService productoService;

    private List<Producto> productos;
    private Producto productoSeleccionado;
    private List<Producto> productosFiltrados;
    private String terminoBusqueda;

    @PostConstruct
    public void init() {
        this.productos = productoService.listarProductos();
    }

    public void prepararNuevoProducto() {
        this.productoSeleccionado = new Producto();
        this.productoSeleccionado.setPrecio(BigDecimal.ZERO);
        this.productoSeleccionado.setStock(0);
    }

    public void guardarProducto() {
        try {
            this.productoService.guardarProducto(this.productoSeleccionado);
            this.productos = productoService.listarProductos();
            FacesContext.getCurrentInstance().addMessage(null, 
                new FacesMessage(FacesMessage.SEVERITY_INFO, "Producto Guardado", "El producto ha sido guardado exitosamente."));

            PrimeFaces.current().executeScript("PF('gestionProductoDialog').hide()");
            PrimeFaces.current().ajax().update("form:messages", "form:dt-productos");
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, 
                new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Ocurrió un error al guardar el producto."));
        }
    }

    public void eliminarProducto() {
        try {
            this.productoService.eliminarProducto(this.productoSeleccionado.getId());
            this.productos = productoService.listarProductos();
            this.productoSeleccionado = null;
            FacesContext.getCurrentInstance().addMessage(null, 
                new FacesMessage(FacesMessage.SEVERITY_INFO, "Producto Eliminado", "El producto ha sido eliminado exitosamente."));
            PrimeFaces.current().ajax().update("form:messages", "form:dt-productos");
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, 
                new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Ocurrió un error al eliminar el producto."));
        }
    }

    public void buscarProductos() {
        if (this.terminoBusqueda != null && !this.terminoBusqueda.isEmpty()) {
            this.productos = productoService.buscarProductos(
                this.terminoBusqueda, null, null, null, null, null, null);
        } else {
            this.productos = productoService.listarProductos();
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
} 