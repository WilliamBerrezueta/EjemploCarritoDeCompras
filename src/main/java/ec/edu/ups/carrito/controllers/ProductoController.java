/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.ups.carrito.controllers;

import ec.edu.ups.carrito.dao.ProductoDAO;
import ec.edu.ups.carrito.models.Producto;
import ec.edu.ups.carrito.view.CrearProductoView;
import ec.edu.ups.carrito.view.BuscarProductoView;
import ec.edu.ups.carrito.view.ActualizarProductoView;
import ec.edu.ups.carrito.view.EliminarProductoView;

public class ProductoController {

    private ProductoDAO productoDAO;
    private CrearProductoView crearView;
    private BuscarProductoView buscarView;
    private ActualizarProductoView actualizarView;
    private EliminarProductoView eliminarView;

    public ProductoController(CrearProductoView crearView,
                              BuscarProductoView buscarView,
                              ActualizarProductoView actualizarView,
                              EliminarProductoView eliminarView,
                              ProductoDAO productoDAO) {

        this.crearView = crearView;
        this.buscarView = buscarView;
        this.actualizarView = actualizarView;
        this.eliminarView = eliminarView;
        this.productoDAO = productoDAO;

        configurarEventos();
    }

    private void configurarEventos() {

        crearView.getBtnAceptar().addActionListener(e -> crearProducto());

        buscarView.getBtnBuscar().addActionListener(e -> buscarProducto());

        actualizarView.getBtnActualizar().addActionListener(e -> actualizarProducto());

        eliminarView.getBtnEliminar().addActionListener(e -> eliminarProducto());
    }

    public void crearProducto() {
        int codigo = Integer.parseInt(crearView.getTxtCodigo().getText());
        String nombre = crearView.getTxtNombre().getText();
        double precio = Double.parseDouble(crearView.getTxtPrecio().getText());

        Producto producto = new Producto(codigo, nombre, precio);
        productoDAO.crear(producto);

        crearView.mostrarMensaje("Producto creado correctamente");
    }

    public void buscarProducto() {
        int codigo = Integer.parseInt(buscarView.getTxtCodigo().getText());

        Producto producto = productoDAO.buscar(codigo);

        if (producto != null) {
            buscarView.getTxtNombre().setText(producto.getNombre());
            buscarView.getTxtPrecio().setText(String.valueOf(producto.getPrecio()));
        } else {
            buscarView.mostrarMensaje("Producto no encontrado");
        }
    }

    public void actualizarProducto() {
        int codigo = Integer.parseInt(actualizarView.getTxtCodigo().getText());
        String nombre = actualizarView.getTxtNombre().getText();
        double precio = Double.parseDouble(actualizarView.getTxtPrecio().getText());

        Producto productoBuscado = productoDAO.buscar(codigo);

        if (productoBuscado != null) {
            Producto productoNuevo = new Producto(codigo, nombre, precio);
            productoDAO.actualizar(codigo, productoNuevo);
            actualizarView.mostrarMensaje("Producto actualizado");
        } else {
            actualizarView.mostrarMensaje("No existe ese producto");
        }
    }

    public void eliminarProducto() {
        int codigo = Integer.parseInt(eliminarView.getTxtCodigo().getText());

        Producto producto = productoDAO.buscar(codigo);

        if (producto != null) {
            productoDAO.eliminar(codigo);
            eliminarView.mostrarMensaje("Producto eliminado");
        } else {
            eliminarView.mostrarMensaje("Producto no encontrado");
        }
    }
}