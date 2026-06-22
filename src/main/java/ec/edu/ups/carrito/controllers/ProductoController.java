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
import ec.edu.ups.carrito.view.ListarProductosView;

public class ProductoController {

    private ProductoDAO productoDAO;
    private CrearProductoView crearView;
    private BuscarProductoView buscarView;
    private ActualizarProductoView actualizarView;
    private EliminarProductoView eliminarView;
    private ListarProductosView listarView;

    public ProductoController(CrearProductoView crearView, BuscarProductoView buscarView, ActualizarProductoView actualizarView, EliminarProductoView eliminarView,ListarProductosView listarView, ProductoDAO productoDAO) {

        this.crearView = crearView;
        this.buscarView = buscarView;
        this.actualizarView = actualizarView;
        this.eliminarView = eliminarView;
        this.listarView = listarView;
        this.productoDAO = productoDAO;

        configurarEventos();
    }

    private void configurarEventos() {

        crearView.getBtnAceptar().addActionListener(e -> crearProducto());

        buscarView.getBtnBuscar().addActionListener(e -> buscarProducto());

        eliminarView.getBtnBuscar().addActionListener(e -> buscarProductoEliminar());

        eliminarView.getBtnEliminar().addActionListener(e -> eliminarProducto());

        actualizarView.getBtnBuscar().addActionListener(e -> buscarProductoActualizar());

        actualizarView.getBtnActualizar().addActionListener(e -> actualizarProducto());
    }

    public void crearProducto() {
        int codigo = Integer.parseInt(crearView.getTxtCodigo().getText());
        String nombre = crearView.getTxtNombre().getText();
        double precio = Double.parseDouble(crearView.getTxtPrecio().getText());

        Producto productoRepetido = productoDAO.buscar(codigo);
        
        if(productoRepetido != null){
            crearView.mostrarMensaje("Ya existe un producto con ese código");
            return;
        }
        
        for(Producto producto : productoDAO.listar()){
            if(producto.getNombre().equalsIgnoreCase(nombre)){
                crearView.mostrarMensaje("Ya existe un producto con ese nombre");
                return;
            }
        }
        
        Producto producto = new Producto(codigo, nombre, precio);
        productoDAO.crear(producto);
        listarProductos();

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

    public void buscarProductoActualizar() {
        int codigo = Integer.parseInt(actualizarView.getTxtCodigo().getText());

        Producto producto = productoDAO.buscar(codigo);

        if (producto != null) {
            actualizarView.getTxtNombre().setText(producto.getNombre());
            actualizarView.getTxtPrecio().setText(String.valueOf(producto.getPrecio()));
        } else {
            actualizarView.mostrarMensaje("No existe ese producto");
        }
    }

    public void actualizarProducto() {
        int codigo = Integer.parseInt(actualizarView.getTxtCodigo().getText());
        String nombre = actualizarView.getTxtNombre().getText();
        double precio = Double.parseDouble(actualizarView.getTxtPrecio().getText());

        Producto productoNuevo = new Producto(codigo, nombre, precio);

        productoDAO.actualizar(codigo, productoNuevo);
        listarProductos();

        actualizarView.mostrarMensaje("Producto actualizado");
    }

    public void buscarProductoEliminar() {
        int codigo = Integer.parseInt(eliminarView.getTxtCodigo().getText());

        Producto producto = productoDAO.buscar(codigo);

        if (producto != null) {
            eliminarView.getTxtNombre().setText(producto.getNombre());
            eliminarView.getTxtPrecio().setText(String.valueOf(producto.getPrecio()));
        } else {
            eliminarView.mostrarMensaje("No existe ese producto");
        }
    }

    public void eliminarProducto() {
        int codigo = Integer.parseInt(eliminarView.getTxtCodigo().getText());

        Producto producto = productoDAO.buscar(codigo);

        if (producto != null) {
            productoDAO.eliminar(codigo);
            listarProductos();
            eliminarView.mostrarMensaje("Producto eliminado");
        } else {
            eliminarView.mostrarMensaje("Producto no encontrado");
        }
    }
    public void listarProductos(){
        listarView.CargarDatos(productoDAO.listar());
    }
}
