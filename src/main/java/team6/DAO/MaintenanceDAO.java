package team6.DAO;

import team6.entities.Maintenance;
import team6.entities.Vehicle;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.time.LocalDate;
import java.util.List;

public class MaintenanceDAO {


    private final EntityManager em;

    public MaintenanceDAO(EntityManager em) {
        this.em = em;
    }

    public void save(Maintenance maintenance) {
        EntityTransaction transaction = em.getTransaction();

        transaction.begin();
        em.persist(maintenance);
        transaction.commit();

        System.out.println("Complimenti " + maintenance.getTypeMaintenance()+ " è stato creato con successo!");
    }

    public Maintenance findById(long id) {
        return em.find(Maintenance.class, id);
    }

    public void findAndDeleteById(long id) {
        Maintenance maintenance = this.findById(id);

        if (maintenance != null) {
            EntityTransaction transaction = em.getTransaction();

            transaction.begin();
            em.remove(maintenance);
            transaction.commit();

            System.out.println("Complimenti hai eliminato con successo il maintenance " + maintenance.getTypeMaintenance() );
        } else {
            System.out.println("Ops! non ho trovato nessun maintenance tramite il codice ID che mi hai fornito");
        }
    }

    public void setDateOfEndMaintenance(Maintenance maintenance, LocalDate date) {
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        maintenance.setDateOfEndMaintenance(date);
        transaction.commit();
        System.out.println("Il mezzo può tornare sulla tratta");
    }

public List<Maintenance> getMaintencanceList (Vehicle vehicle){

        return vehicle.getMaintenanceList();

}




}
