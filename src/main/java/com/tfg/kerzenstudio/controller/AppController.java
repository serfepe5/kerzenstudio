package com.tfg.kerzenstudio.controller;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.tfg.kerzenstudio.enums.Rol;
import com.tfg.kerzenstudio.enums.Tipo;
import com.tfg.kerzenstudio.model.Pedido;
import com.tfg.kerzenstudio.model.Producto;
import com.tfg.kerzenstudio.model.Usuario;
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

	// CONTROL DE USUARIOS
	@RequestMapping("/")
	public String viewHomePage(HttpServletRequest request) {

		if (request.isUserInRole(Rol.ADMINISTRADOR.name())) {
			return "redirect:/administrador";
		} else if (request.isUserInRole(Rol.REGISTRADO.name())) {
			return "redirect:/index";
		} else {
			return "redirect:/index";
		}
	}

	@RequestMapping("/acceso")
	public String acceso(HttpServletRequest request) {
		if (request.isUserInRole(Rol.ADMINISTRADOR.name())) {
			return "redirect:/administrador";
		} else {
			return "redirect:/index";
		}
	}

	@RequestMapping("/iniciarSesion")
	public String inicioSesion(Model model) {
		Usuario u = new Usuario();
		model.addAttribute("usuario", u);
		return "inicioSesion";
	}

	@RequestMapping("/registrarse")
	public String viewRegistrarse(Model model) {
		Usuario u = new Usuario();
		model.addAttribute("usuario", u);

		return "/registrarse";
	}

	@RequestMapping(value = "/guardarregistrarse", method = RequestMethod.POST)
	public String saveUsuario(@ModelAttribute("usuario") Usuario usuario) {
		usuario.setRol(Rol.REGISTRADO);
		usuarioservice.save(usuario);

		return "redirect:/";
	}

	// PAGINAS USUARIO INVITADO
	@RequestMapping("/index")
	public String viewIndexPage(Model model) {
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
	
	@RequestMapping("/politica")
	public String viewPoliticaPage(Model model) {
		return "politica";
	}

////////////////////////////////////////////////USUARIO ADMINISTRADOR//////////////////////////////////////////////////////
	@RequestMapping("/administrador")
	public String viewAdmin(Model model) {
		List<Producto> productos = productoservice.listAll();
		model.addAttribute("productos", productos);

		return "administrador";
	}

	// CONTROL DE PRODUCTOS
	// crear producto
	@RequestMapping("/nuevoproducto")
	public String showNuevoProducto(Model model) {
		Producto p = new Producto();
		model.addAttribute("producto", p);

		return "nuevoproducto";
	}

	// guardar producto
	@RequestMapping(value = "/guardarproducto", method = RequestMethod.POST)
	public ModelAndView guardarProducto(@ModelAttribute("producto") Producto producto) {
		productoservice.save(producto);
		ModelAndView mav = new ModelAndView("/administrador");
		List<Producto> productos = productoservice.listAll();
		mav.addObject("productos", productos);
		return mav;
	}

	// modificarproducto
	@RequestMapping("/modificarproducto/{id}")
	public ModelAndView showModificarProducto(@PathVariable(name = "id") Long id) {
		ModelAndView mav = new ModelAndView("modificarproducto");
		Producto producto = productoservice.findProductos(id);
		mav.addObject("producto", producto);

		return mav;
	}

	// borrar producto
	@RequestMapping("/borrarproducto/{id}")
	public String borrarProducto(@PathVariable(name = "id") Long id) {
		productoservice.delete(id);
		return "redirect:/";
	}

	//////////////////////////////////////////////// USUARIO
	//////////////////////////////////////////////// REGISTRADO//////////////////////////////////////////////////////

	// vista carrito
	@RequestMapping("/carrito")
	public String viewCarritoPage(Model model) {
		Map<Producto, Integer> productoscarrito = carritoservice.getCarrito();
		model.addAttribute("productoscarrito", productoscarrito);
		Float precioTotal = carritoservice.precioTotalCarrito();
		model.addAttribute("precioTotal", precioTotal);
		
		return "carrito";
	}

	// aniadir producto a carrito
	@RequestMapping("/aniadirproductocarrito/{id}")
	public String viewAniadirProductoACarrito(@PathVariable(name = "id") Long id) {
		carritoservice.addCarrito(id);
		return "redirect:/carrito";
	}

	// borrar producto de carrito
	@RequestMapping("/eliminarproductocarrito/{id}")
	public String viewBorrarProductoACarrito(@PathVariable(name = "id") Long id) {
		carritoservice.removeItemCarrito(id);
		return "redirect:/carrito";
	}

	// borrar  carrito
	@RequestMapping("/borrarcarrito")
	public String viewBorrarCarrito() {
		carritoservice.vaciarCarrito();
		return "redirect:/carrito";
	}
	
	// vista carrito
		@RequestMapping("/pedido")
		public String viewPedidoPage(Model model) {
			Map<Producto, Integer> productoscarrito = carritoservice.getCarrito();
			Pedido pedido = pedidoservice.crearPedido(productoscarrito);
			model.addAttribute("pedido", pedido);
			return "pedido";
		}
		
		// vista carrito
		@RequestMapping("/verpedidos")
		public String viewPedidosPage(Model model) {
			List<Pedido> pedidos = pedidoservice.findPedidoUser();
			model.addAttribute("pedidos", pedidos);
			return "verpedidos";
		}
	
	
}
