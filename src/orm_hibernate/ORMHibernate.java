package orm_hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class ORMHibernate {

    public static void main(String[] args) {

        // Crear SessionFactory
        try (SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Artista.class)
                .buildSessionFactory()) {
            // Iniciar sesión            
            try (Session session = factory.openSession()) {

                session.beginTransaction();//Se da comienzo a la transacción
                // Crear objeto Artista
                Artista artista = new Artista("Angel", "Montana");

                // Guardar en la base de datos
                session.save(artista);

                // Confirmar transacción
                session.getTransaction().commit();

                System.out.println("El registro fue almacenado correctamente");
            } catch (Exception e) {
                System.err.println("Error en la transacción: " + e.getMessage());
                e.printStackTrace();
            } finally {
                factory.close();
            }

        } catch (Exception e) {
            System.err.println("Error al inicializar Hibernate: " + e.getMessage());
            e.printStackTrace();
        }

    }

}
