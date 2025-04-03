package com.catalogo.productos.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;

import com.catalogo.productos.modelo.Producto;

public interface ProductoRepositorio extends JpaRepository<Producto, Integer>{

}
