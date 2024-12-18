package com.alexis.proyecto.gestionusuariosroles.controllers;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.alexis.proyecto.gestionusuariosroles.domain.Rol;
import com.alexis.proyecto.gestionusuariosroles.domain.Usuario;
import com.alexis.proyecto.gestionusuariosroles.enums.Accion;
import com.alexis.proyecto.gestionusuariosroles.repositories.RolRepository;
import com.alexis.proyecto.gestionusuariosroles.repositories.UsuarioRepository;
import com.alexis.proyecto.gestionusuariosroles.services.LogAuditoriaService;
import com.alexis.proyecto.gestionusuariosroles.services.impl.UsuarioServiceImpl;

import jakarta.servlet.http.HttpServletRequest;
/**
 * Controlador para gestionar  usuarios.
 */
@Controller
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioServiceImpl usr;

    @Autowired
    private UsuarioRepository ur;

    @Autowired
    private RolRepository rr;

    @Autowired
    private LogAuditoriaService las;

    /**
     * Muestra la vista de la tarjeta de usuario.
     * 
     * @return Nombre del archivo HTML para la vista de la tarjeta.
     */
    @GetMapping("/card")
    public String getCard() {
        return "card";
    }

    /**
     * Muestra al usuario.
     *
     * @param authentication Información de autenticacion del usuario.
     * @param model          Modelo de la vista.
     * @return Nombre del archivo HTML o redireccion si no tiene permisos.
     */
    @GetMapping("/dashboard")
    public String userDashboard(Authentication authentication, Model model) {
        Map<String, Object> user = usr.getDatosUsuario(authentication);
        model.addAllAttributes(user);
        if (user.containsValue("USER")) {
            return "usuario-dashboard";
        } else {
            return "redirect:/access-denied";
        }
    }

    /**
     * Muestra al administrador.
     *
     * @param authentication Información de autenticacion del administrador.
     * @param model          Modelo de la vista.
     * @return Nombre del archivo HTML o redireccion si no tiene permisos.
     */
    @GetMapping("/admin/dashboard")
    public String adminDashboard(Authentication authentication, Model model) {
        Map<String, Object> admin = usr.getDatosAdmin(authentication);
        if (authentication.getAuthorities().stream()
                .anyMatch(grantedAuthority -> grantedAuthority.getAuthority().equals("admin"))) {
            model.addAttribute("users", usr.getUsuarios());
            model.addAllAttributes(admin);
            return "admin-dashboard";
        } else {
            return "redirect:/access-denied";
        }
    }

    /**
     * Muestra la vista de auditoria de usuario para administradores.
     *
     * @param model Modelo de la vista.
     * @return Nombre del archivo HTML para la vista de auditoria.
     */
    @GetMapping("/admin/auditoria")
    public String userAuditoria(Model model) {
        model.addAttribute("logs", las.getLogsAuditorias());
        return "log-auditoria";
    }

    /**
     * Muestra el formulario para crear un nuevo usuario.
     *
     * @param model Modelo de la vista.
     * @return Nombre del archivo HTML para el formulario de creacion de usuario.
     */
    @GetMapping("/admin/create-user")
    public String showCreateUserForm(Model model) {
        List<Rol> Roles = (List<Rol>) rr.findAll();
        model.addAttribute("roles", Roles);
        model.addAttribute("usuario", new Usuario());

        return "create-user";
    }

    /**
     * Crea un nuevo usuario en el sistema.
     *
     * @param usuario Datos del usuario a crear.
     * @param model   Modelo de la vista.
     * @return Redirige al tablero de administrador despues de la creacion.
     */
    @PostMapping("/admin/create-user")
    public String createUsuario(@ModelAttribute Usuario usuario, Model model) {
        usr.createUsuario(usuario);
        las.registrarAccion("usuarios", Accion.CREATE);
        return "redirect:/usuario/admin/dashboard";
    }
      /**
     * Muestra el formulario para editar un usuario existente.
     *
     * @param idUsuario ID del usuario a editar.
     * @param model Modelo de la vista.
     * @return Nombre del archivo HTML para el formulario de edicion de usuario.
     */
    @GetMapping("/admin/edit-user/{idUsuario}")
    public String showEditUser(@PathVariable int idUsuario, Model model) {
        Usuario user = ur.findById(idUsuario).orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        model.addAttribute("user", user);
        return "edit-user";
    }
     /**
     * Actualiza los datos de un usuario existente.
     *
     * @param idUsuario ID del usuario a actualizar.
     * @param nombre Nuevo nombre del usuario.
     * @param email Nueva dirección de correo electronico del usuario.
     * @return Redirige al tablero de administrador.
     */
    @PatchMapping("/admin/edit-user/{idUsuario}")
    public String actualizarUsuario(@PathVariable int idUsuario, @RequestParam("nombre") String nombre,
            @RequestParam("email") String email) {
        usr.putUsuario(idUsuario, nombre, email);
        las.registrarAccion("usuarios", Accion.UPDATE);
        return "redirect:/usuario/admin/dashboard";
    }

    /**
     * Elimina un usuario del sistema.
     *
     * @param idUsuario ID del usuario a eliminar.
     * @param usuario   Entidad de usuario para eliminar.
     * @return Respuesta HTTP con un mensaje de exito o error.
     */
    @DeleteMapping("/remove")
    public ResponseEntity<?> userDelete(
            @RequestParam(name = "idUsuario", defaultValue = "0", required = false) int idUsuario, Usuario usuario) {
        try {
            usr.deleteUsuario(idUsuario);
            las.registrarAccion("usuarios", Accion.DELETE);
            return ResponseEntity.ok("El cliente se borro exitosamente");

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuario no encontrado con nombre: " + e);
        }
    }

    /**
     * Cierra la sesion del usuario.
     *
     * @param request Informacion de la solicitud HTTP.
     * @return Redirige a la vista de login despues de cerrar la sesion.
     */
    @GetMapping("/logout")
    public String logout(HttpServletRequest request) {
        request.getSession().invalidate();
        return "redirect:/login";
    }
}
