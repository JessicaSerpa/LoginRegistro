
package modelo;


public class Empleado {
    private String emp_codigo;
    private String emp_nombre;
    private String emp_correo;
    private String emp_pswd;

    public Empleado() {
    }

    public String getEmp_codigo() {
        if (emp_codigo != null) {
            int atIndex = emp_codigo.indexOf('@');
            if (atIndex != -1) { // Verifica si se encontró el carácter '@'
                return emp_codigo.substring(0, atIndex);
            } else {
                // Si no se encuentra el carácter '@', devuelve la cadena original
                return emp_codigo;
            }
        } else {
            // Si emp_codigo es nulo, devuelve nulo
            return null;
        }
    }

    public void setEmp_codigo(String emp_codigo) {
        this.emp_codigo = emp_codigo;
    }

    public String getEmp_nombre() {
        return emp_nombre;
    }

    public void setEmp_nombre(String emp_nombre) {
        this.emp_nombre = emp_nombre;
    }

    public String getEmp_correo() {
        return emp_correo;
    }

    public void setEmp_correo(String emp_correo) {
        this.emp_correo = emp_correo;
    }

    public String getEmp_pswd() {
        return emp_pswd;
    }

    public void setEmp_pswd(String emp_pswd) {
        this.emp_pswd = emp_pswd;
    }
    
}
