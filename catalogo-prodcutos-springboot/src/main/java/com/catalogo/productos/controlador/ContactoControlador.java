package com.catalogo.productos.controlador;

import java.util.List;

import com.catalogo.productos.modelo.Producto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.catalogo.productos.repositorio.ProductoRepositorio;

@Controller
public class ContactoControlador {

	@Autowired
	private ProductoRepositorio productoRepositorio;
	
	@GetMapping({"/",""})
	public String verPaginaDeInicio(Model modelo) {
		List<Producto> productos = productoRepositorio.findAll();
		modelo.addAttribute("productos", productos);
		return "index";
	}
	
	@GetMapping("/nuevo")
	public String mostrarFormularioDeRegistrarProducto(Model modelo) {
		modelo.addAttribute("producto", new Producto());
		return "nuevo";
	}
	
	@PostMapping("/nuevo")
	public String guardarProducto(@Validated Producto producto, BindingResult bindingResult, RedirectAttributes redirect, Model modelo) {
		if(bindingResult.hasErrors()) {
			modelo.addAttribute("producto", producto);
			return "nuevo";
		}
		
		productoRepositorio.save(producto);
		redirect.addFlashAttribute("msgExito", "El producto ha sido agregado con exito");
		return "redirect:/";
	}
	

	@GetMapping("/{id}/editar")
	public String mostrarFormularioDeEditarProducto(@PathVariable Integer id, Model modelo) {
		Producto producto = productoRepositorio.getById(id);
		modelo.addAttribute("producto", producto);
		return "nuevo";
	}
	
	@PostMapping("/{id}/editar")
	public String actualizarProducto(@PathVariable Integer id, @Validated Producto producto, BindingResult bindingResult, RedirectAttributes redirect, Model modelo) {
		Producto productoDB = productoRepositorio.getById(id);
		if(bindingResult.hasErrors()) {
			modelo.addAttribute("producto", producto);
			return "nuevo";
		}
		
		productoDB.setNombre(producto.getNombre());
		productoDB.setCantidad(producto.getCantidad());
		productoDB.setPrecio(producto.getPrecio());
		productoDB.setFecha(producto.getFecha());
		
		
		productoRepositorio.save(productoDB);
		redirect.addFlashAttribute("msgExito", "El producto ha sido actualizado correctamente");
		return "redirect:/";
	}
	
	@PostMapping("/{id}/eliminar")
	public String eliminarContacto(@PathVariable Integer id,RedirectAttributes redirect) {
		Producto producto = productoRepositorio.getById(id);
		productoRepositorio.delete(producto);
		redirect.addFlashAttribute("msgExito", "El producto ha sido eliminado correctamente");
		return "redirect:/";
	}
}
