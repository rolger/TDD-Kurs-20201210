package garbage;

import java.util.List;
import java.util.Objects;

/**
 * Description
 *
 * @author Matthias Mairleitner
 */
public class SearchResultDto {

    private List<Company> foundCompanies;

    public SearchResultDto(List<Company> foundCompanies) {
        this.foundCompanies = Objects.requireNonNull(foundCompanies);
    }

    public List<Company> getCompanies() {
        return this.foundCompanies;
    }
}
