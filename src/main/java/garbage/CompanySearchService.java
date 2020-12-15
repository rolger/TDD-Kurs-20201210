package garbage;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Description
 *
 * @author Matthias Mairleitner
 */
public class CompanySearchService {
    private final CompanyRepository repository;
    private final GarbageSearchParser parser;

    public CompanySearchService(CompanyRepository repository, GarbageSearchParser parser) {
        this.repository = repository;
        this.parser = parser;
    }

    public SearchResultDto search(String input) {
        final List<String> keywords = this.parser.parse(input);

        final List<Company> foundCompanies = keywords.stream()
                .map(this.repository::findBy)
                .flatMap(List::stream)
                .distinct()
                .collect(Collectors.toList());

        return new SearchResultDto(foundCompanies);
    }
}
