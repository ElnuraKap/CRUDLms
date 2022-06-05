package thymeleaf.service;

import javassist.NotFoundException;
import org.springframework.stereotype.Service;
import thymeleaf.model.Company;
import thymeleaf.repositories.CompanyRepositories;

import java.util.List;
import java.util.Objects;

@Service
public class CompanyService {

    private final CompanyRepositories companyRepositories;

    public CompanyService(CompanyRepositories companyRepositories) {
        this.companyRepositories = companyRepositories;
    }

    public List<Company> getAllCompany (){
        return companyRepositories.getAllCompany();

    }

    public void saveCompany(Company company){
        companyRepositories.save(company);
    }

    public Company findById(Long id) {
        return companyRepositories.findById(id);
    }

    public void deleteById(Long id) {
        companyRepositories.deleteById(id);
    }

    public void updateById(Company newCompany,Long id){
        Company company = companyRepositories.findById(id);
        String currentName = company.getCompanyName();
        String newCompanyName = newCompany.getCompanyName();

        if (!Objects.equals(currentName,newCompanyName)){
            company.setCompanyName(newCompanyName);
        }

        String currentLocated = company.getLocatedCountry();
        String newLocated = newCompany.getLocatedCountry();

        if(!Objects.equals(currentLocated,newLocated)){
            company.setLocatedCountry(newLocated);
        }
        companyRepositories.updateById(id,newCompany);

    }


}
