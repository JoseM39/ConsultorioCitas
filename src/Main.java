import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import javax.swing.*;

// Clase Doctor
class Doctor {
    int idDoctor;
    String nombre;
    String especialidad;

    public Doctor(int idDoctor, String nombre, String especialidad) {
        this.idDoctor = idDoctor;
        this.nombre = nombre;
        this.especialidad = especialidad;
    }
}

// Clase Paciente
class Paciente {
    int idPaciente;
    String nombre;

    public Paciente(int idPaciente, String nombre) {
        this.idPaciente = idPaciente;
        this.nombre = nombre;
    }
}

// Clase Cita
class Cita {
    int idCita;
    String fechaHora;
    String motivo;
    Doctor doctor;
    Paciente paciente;

    public Cita(int idCita, String fechaHora, String motivo) {
        this.idCita = idCita;
        this.fechaHora = fechaHora;
        this.motivo = motivo;
    }

    public void asignarDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public void asignarPaciente(Paciente paciente) {
        this.paciente = paciente;
    }
}

//Clase adicional para mostrar las citas ya relacionadas
class mostrarCitas {
    public static void mostrar(List<Cita> citas) {
        JOptionPane.showMessageDialog(null, "Recolectando citas...");
        for (Cita cita : citas) {
            if (cita.doctor != null && cita.paciente != null) {
                JOptionPane.showMessageDialog(null, "***\nID Cita: " + cita.idCita + "\nFecha y hora: " + cita.fechaHora + "\nMotivo: " + cita.motivo + "\nDoctor: " + cita.doctor.nombre + "\nEspecialidad: " + cita.doctor.especialidad + "\nPaciente: " + cita.paciente.nombre + "\n\n");
            }
        }
    }
}

// Clase Administrador
class Administrador {
    int idAdmin;
    String usuario;
    String contrasena;

    public Administrador(int idAdmin, String usuario, String contrasena) {
        this.idAdmin = idAdmin;
        this.usuario = usuario;
        this.contrasena = contrasena;
    }

    public boolean validarAcceso(String usuarioIngresado, String contrasenaIngresada) {
        return usuarioIngresado.equals(usuario) && contrasenaIngresada.equals(contrasena);
    }
}

// Clase SistemaCitas
class SistemaCitas {
    List<Doctor> doctores = new ArrayList<>();
    List<Paciente> pacientes = new ArrayList<>();
    List<Cita> citas = new ArrayList<>();
    List<Administrador> administradores = new ArrayList<>();

    // Constructor del sistema
    public SistemaCitas() {
        // Cargar administradores por defecto
        administradores.add(new Administrador(1, "admin", "admin123"));
    }

    public void darDeAltaDoctor(int idDoctor, String nombre, String especialidad) {
        Doctor doctor = new Doctor(idDoctor, nombre, especialidad);
        doctores.add(doctor);
        JOptionPane.showMessageDialog(null,"Doctor dado de alta exitosamente."); //JOptionPane Mensaje
    }

    public void darDeAltaPaciente(int idPaciente, String nombre) {
        Paciente paciente = new Paciente(idPaciente, nombre);
        pacientes.add(paciente);
        JOptionPane.showMessageDialog(null,"Paciente dado de alta exitosamente.");
    }

    public void crearCita(int idCita, String fechaHora, String motivo) {
        Cita cita = new Cita(idCita, fechaHora, motivo);
        citas.add(cita);
        JOptionPane.showMessageDialog(null,"Cita creada exitosamente.");
    }

    public void relacionarCita(int idCita, int idDoctor, int idPaciente) {
        Doctor doctor = buscarDoctor(idDoctor);
        Paciente paciente = buscarPaciente(idPaciente);
        Cita cita = buscarCita(idCita);

        if (doctor != null && paciente != null && cita != null) {
            cita.asignarDoctor(doctor);
            cita.asignarPaciente(paciente);
            JOptionPane.showMessageDialog(null,"Cita relacionada con doctor y paciente exitosamente.");
        } else {
            JOptionPane.showMessageDialog(null,"Error al relacionar cita: IDs no encontrados.");

        }
    }

    public Doctor buscarDoctor(int idDoctor) {
        for (Doctor doctor : doctores) {
            if (doctor.idDoctor == idDoctor) {
                return doctor;
            }
        }
        return null;
    }

    public Paciente buscarPaciente(int idPaciente) {
        for (Paciente paciente : pacientes) {
            if (paciente.idPaciente == idPaciente) {
                return paciente;
            }
        }
        return null;
    }

    public Cita buscarCita(int idCita) {
        for (Cita cita : citas) {
            if (cita.idCita == idCita) {
                return cita;
            }
        }
        return null;
    }

    public boolean validarAccesoAdmin(String usuario, String contrasena) {
        for (Administrador admin : administradores) {
            if (admin.validarAcceso(usuario, contrasena)) {
                return true;
            }
        }
        return false;
    }

    public void mostrarCitasRelacionadas() {
        mostrarCitas.mostrar(citas);
    }
}

// Clase principal
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        SistemaCitas sistema = new SistemaCitas();
        boolean salir = false;

        while (!salir) {
            JOptionPane.showMessageDialog(null,"Bienvenido al Sistema de Administración de Citas");
            JOptionPane.showMessageDialog(null,"Selecciona una de las siguientes opciones escribiendo el numero.");
            int opcion = Integer.parseInt(JOptionPane.showInputDialog("1. Iniciar sesión como Administrador.\n2. Salir."));

            if (opcion == 1) {
                String usuario = JOptionPane.showInputDialog("Usuario:");
                String contrasena = JOptionPane.showInputDialog("Contraseña:");

                if (sistema.validarAccesoAdmin(usuario, contrasena)) {
                    JOptionPane.showMessageDialog(null,"Acceso Concedido.");
                    boolean salirAdmin = false;

                    while (!salirAdmin) {
                        JOptionPane.showMessageDialog(null,"Selecciona una de las siguientes opciones escribiendo el numero.");
                        int opcionAdmin = Integer.parseInt(JOptionPane.showInputDialog("1. Dar de alta doctor\n2. Dar de alta paciente\n3. Crear cita\n4. Relacionar cita\n5. Mostrar citas\n6. Salir"));

                        switch (opcionAdmin) {
                            case 1:
                                int idDoctor = Integer.parseInt(JOptionPane.showInputDialog("Ingrese ID del doctor:"));
                                String nombreDoctor = JOptionPane.showInputDialog("Ingrese nombre del doctor:");
                                String especialidadDoctor = JOptionPane.showInputDialog("Ingrese especialidad del doctor:");
                                sistema.darDeAltaDoctor(idDoctor, nombreDoctor, especialidadDoctor);
                                break;
                            case 2:
                                int idPaciente = Integer.parseInt(JOptionPane.showInputDialog("Ingrese ID del paciente:"));
                                String nombrePaciente = JOptionPane.showInputDialog("Ingrese nombre del paciente:");
                                sistema.darDeAltaPaciente(idPaciente, nombrePaciente);
                                break;
                            case 3:
                                int idCita = Integer.parseInt(JOptionPane.showInputDialog("Ingrese ID de la cita:"));
                                String fechaHora = JOptionPane.showInputDialog("Ingrese fecha y hora.\nFormato:(YYYY-MM-DD HH:MM):");
                                String motivo = JOptionPane.showInputDialog("Ingrese el motivo del cita:");
                                sistema.crearCita(idCita, fechaHora, motivo);
                                break;
                            case 4:
                                idCita = Integer.parseInt(JOptionPane.showInputDialog("Ingrese ID de la cita:"));
                                idDoctor = Integer.parseInt(JOptionPane.showInputDialog("Ingrese ID del doctor:"));
                                idPaciente = Integer.parseInt(JOptionPane.showInputDialog("Ingrese ID del paciente:"));
                                sistema.relacionarCita(idCita, idDoctor, idPaciente);
                                break;
                            case 5:
                                sistema.mostrarCitasRelacionadas();
                                break;
                            case 6:
                                salirAdmin = true;
                                JOptionPane.showMessageDialog(null,"Cerrando sesión...");
                                break;
                            default:
                                JOptionPane.showMessageDialog(null,"Opción Invalida.");
                                break;
                        }
                    }
                } else {
                    JOptionPane.showMessageDialog(null,"Acceso denegado\nIntentalo otra vez.");
                }
            } else if (opcion == 2) {
                salir = true;
                JOptionPane.showMessageDialog(null,"Saliendo del sistema...");
            } else {
                JOptionPane.showMessageDialog(null,"Opción Invalida.");
            }
        }
        scanner.close();
    }
}