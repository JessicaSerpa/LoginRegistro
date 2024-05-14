import dao.DaoEmpleados;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import modelo.Empleado;

@WebServlet("/Login")
public class Login extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private DaoEmpleados daoEmpleados = new DaoEmpleados();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        String action = request.getParameter("action");

        if (action != null && action.equals("login")) {
            // Manejar solicitud de inicio de sesión
            String email = request.getParameter("email");
            String password = request.getParameter("password");

            // Aquí deberías validar las credenciales en tu base de datos
            Empleado empleado = daoEmpleados.obtenerEmpleadoPorCorreo(email);
            if (empleado != null && empleado.getEmp_pswd().equals(password)) {
                HttpSession session = request.getSession();
                session.setAttribute("usuario", empleado);
                response.sendRedirect("newjsp.jsp");
            } else {
                // Credenciales inválidas, redirigir al usuario de vuelta al formulario de inicio de sesión con un mensaje de error
                String errorMessage = "Usuario o contraseña incorrectos";
                request.setAttribute("error", errorMessage);
                request.getRequestDispatcher("login.jsp").forward(request, response);
            }
        } else if (action != null && action.equals("registrarse")) {
            // Manejar solicitud de registro
            String name = request.getParameter("name");
            String email = request.getParameter("email");
            String password = request.getParameter("password");

            // Crear un nuevo empleado con los datos proporcionados
            Empleado nuevoEmpleado = new Empleado();
            
            nuevoEmpleado.setEmp_codigo(email);
            nuevoEmpleado.setEmp_nombre(name);
            nuevoEmpleado.setEmp_correo(email);
            nuevoEmpleado.setEmp_pswd(password);

            // Guardar el nuevo empleado en la base de datos
            daoEmpleados.registrarEmpleado(nuevoEmpleado);

            // Establecer sesión para el nuevo usuario y redirigirlo a la página de inicio
            HttpSession session = request.getSession();
            session.setAttribute("usuario", nuevoEmpleado);
            String successMessage = "Registrado exitosamente";
            request.setAttribute("success", successMessage);
            request.getRequestDispatcher("login.jsp").forward(request, response);
        } else {
            // Si no se proporciona ninguna acción, redirige al usuario a la página de inicio
            response.sendRedirect("login.jsp");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }
}
