package team6;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Application {
    private static final EntityManagerFactory emf= Persistence.createEntityManagerFactory("DB_traporto_pubblico");
    public static EntityManager em =  emf.createEntityManager();
    public static void main(String[] args) {
        System.out.println("Hello World!");
    }
}
