package com.tfg.kerzenstudio.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tfg.kerzenstudio.enums.Tipo;
import com.tfg.kerzenstudio.model.Producto;
import com.tfg.kerzenstudio.services.CarritoService;
import com.tfg.kerzenstudio.services.PedidoService;
import com.tfg.kerzenstudio.services.ProductoService;
import com.tfg.kerzenstudio.services.UsuarioService;

@Controller
public class AppController {

	@Autowired
	private ProductoService productoservice;
	
	@Autowired
	private CarritoService carritoservice;
	
	@Autowired
	private PedidoService pedidoservice;
	
	@Autowired
	private UsuarioService usuarioservice;
	
	
	
	@RequestMapping("/")
	public String viewInicioPage(Model model) {
		List<Producto> productos = productoservice.listAll();
		model.addAttribute("productos", productos);
        return "index";
    }
	@RequestMapping("/mujer")
	public String viewMujerPage(Model model) {
		List<Producto> productosmujer = productoservice.findProductoTipo(Tipo.MUJER);
		model.addAttribute("productosmujer", productosmujer);
        return "mujer";
    }
	@RequestMapping("/hombre")
	public String viewHombrePage(Model model) {
		List<Producto> productoshombre = productoservice.findProductoTipo(Tipo.HOMBRE);
		model.addAttribute("productoshombre", productoshombre);
        return "hombre";
    }
	@RequestMapping("/accesorios")
	public String viewAccesoriosPage(Model model) {
		List<Producto> productosaccesorio = productoservice.findProductoTipo(Tipo.ACCESORIO);
		model.addAttribute("productosaccesorio", productosaccesorio);
        return "accesorios";
    }
	@RequestMapping("/contacto")
	public String viewContactoPage(Model model) {
        return "contacto";
    }
	
}
