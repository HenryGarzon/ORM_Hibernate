package orm_hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class ORM_Listado {

    public static void main(String[] args) {
        // Crear SessionFactory
        try (SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml") // Cargar configuración de Hibernate
                .addAnnotatedClass(Artista.class) // Registrar la entidad
                .buildSessionFactory()) {

            // Iniciar sesión
            try (Session session = factory.openSession()) {
                session.beginTransaction(); // Iniciar transacción

                // 🔹 Obtener la lista completa de artistas
                List<Artista> artistas = session.createQuery("from Artista", Artista.class).getResultList();

                // 🔹 Mostrar los resultados
                if (!artistas.isEmpty()) {
                    System.out.println("Lista de Artistas:");
                    for (Artista artista : artistas) {
                        System.out.println(artista);
                    }
                } else {
                    System.out.println("No hay artistas registrados en la base de datos.");
                }

                session.getTransaction().commit(); // Confirmar transacción
                System.out.println("Listado finalizado correctamente");

            } catch (Exception e) {
                System.err.println("Error en la consulta: " + e.getMessage());
                e.printStackTrace();
            } finally {
                factory.close(); // Cerrar la fábrica de sesiones
            }

        } catch (Exception e) {
            System.err.println("Error al inicializar Hibernate: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
