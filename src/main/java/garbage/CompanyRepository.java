package garbage;

import java.util.List;

public interface CompanyRepository {
    List<Company> findBy(String keyword);

    void saveNewCompany(String name, List<String> garbage);
}
