package thymeleaf.repositories;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import thymeleaf.model.Company;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;

import java.util.List;

@Repository
@Transactional
public class CompanyRepositories {
    private final EntityManager entityManager;

    public CompanyRepositories(EntityManagerFactory entityManagerFactory) {
        this.entityManager = entityManagerFactory.createEntityManager();
    }


    public void save(Company company){
        entityManager.getTransaction().begin();
        entityManager.persist(company);
        entityManager.getTransaction().commit();
    }

    public List<Company> getAllCompany(){
        entityManager.getTransaction().begin();

        List<Company>  companyList = entityManager
                .createQuery("select c from Company c", Company.class)
                .getResultList();

        entityManager.getTransaction().commit();
        return companyList;
    }

    public Company findById(Long id) {
        entityManager.getTransaction().begin();
        Company company = entityManager.find(Company.class, id);
        entityManager.getTransaction().commit();
        return company;
    }
 @Transactional
    public void deleteById(Long companyId)  {
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        entityManager.remove(entityManager.find(Company.class, companyId));
        transaction.commit();
    }

    public void updateById(Long id, Company company){
        entityManager.getTransaction().begin();
        Company company1 = entityManager.find(Company.class, id);
        company1.setCompanyName(company.getCompanyName());
        company1.setLocatedCountry(company.getLocatedCountry());
        entityManager.merge(company);
        entityManager.getTransaction().commit();

    }


    @Transactional
    public Company findByCompanyName(Company name){
        return (Company) entityManager.createQuery
                        ("select c from Company c where c.companyName = : name ")
                .setParameter("name", name);
    }


}
