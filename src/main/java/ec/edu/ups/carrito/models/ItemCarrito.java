/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.ups.carrito.models;

import java.util.Objects;

/**
 *
 * @author USER
 */
public class ItemCarrito {
    private Producto producto;
    private int cantidad;

    public ItemCarrito() {
    }

    public ItemCarrito(Producto producto, int cantidad) {
        this.producto = producto;
        this.cantidad = cantidad;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
    public double calcularSubtotal(){
        return producto.getPrecio() * cantidad;
    }

    @Override
    public String toString() {
        return "ItemCarrito{" + "producto=" + producto + ", cantidad=" + cantidad + '}';
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 29 * hash + Objects.hashCode(this.producto);
        hash = 29 * hash + this.cantidad;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final ItemCarrito other = (ItemCarrito) obj;
        if (this.cantidad != other.cantidad) {
            return false;
        }
        return Objects.equals(this.producto, other.producto);
    }
    
    
}
