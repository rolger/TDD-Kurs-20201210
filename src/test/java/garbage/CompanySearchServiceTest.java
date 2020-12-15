package garbage;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import java.util.Collections;
import java.util.List;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class CompanySearchServiceTest {

    @Mock
    private GarbageSearchParser parser;
    @Mock
    private CompanyRepository repo;

    @InjectMocks
    private CompanySearchService sut;

    @Test
    void shouldReturnEmptyResultDto_whenParserReturnsEmptyList() {
        when(parser.parse(anyString())).thenReturn(Collections.emptyList());

        SearchResultDto result = sut.search("");

        Assertions.assertThat(result.getCompanies()).isEmpty();
    }

    @Test
    void shouldReturnEmptyResultDto_whenUsingNullInput() {
        when(parser.parse(null)).thenReturn(Collections.emptyList());

        SearchResultDto result = sut.search(null);

        Assertions.assertThat(result.getCompanies()).isEmpty();
    }

    @Test
    void shouldReturnResultDtoWithCompany_whenCompanyFound() {
        String input = "ball";
        when(parser.parse(input)).thenReturn(Collections.singletonList("BALL"));

        when(repo.findBy("BALL")).thenReturn(List.of(new Company("A")));
        SearchResultDto result = sut.search(input);

        Assertions.assertThat(result.getCompanies()).hasSize(1);
    }

    @Test
    void shouldReturnResultDtoWithMultipleCompany_whenCompaniesFound() {
        String input = "ball,glas";
        when(parser.parse(input)).thenReturn(List.of("BALL","GLAS"));

        when(repo.findBy("GLAS")).thenReturn(List.of(new Company("A")));
        when(repo.findBy("BALL")).thenReturn(List.of(new Company("B")));

        SearchResultDto result = sut.search(input);

        Assertions.assertThat(result.getCompanies()).hasSize(2);
    }

    @Test
    void shouldReturnUniqueListOfCompanies() {
        String input = "ball,glas";
        when(parser.parse(input)).thenReturn(List.of("BALL","GLAS"));

        when(repo.findBy("GLAS")).thenReturn(List.of(new Company("A")));
        when(repo.findBy("BALL")).thenReturn(List.of(new Company("A"), new Company("B")));

        SearchResultDto result = sut.search(input);

        Assertions.assertThat(result.getCompanies()).hasSize(2);
    }
}
