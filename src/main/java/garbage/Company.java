package garbage;

import java.util.ArrayList;
import java.util.List;

public class Company {
    private final String name;
    private final List<String> garbages;

    public Company(String name) {
        this.name = name;
        this.garbages = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void addGarbage(String garbage) {
        garbages.add(garbage);
    }

    public List<String> getGarbages() {
        return new ArrayList<>(garbages);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        Company company = (Company) o;

        if (!name.equals(company.name))
            return false;
        return garbages.equals(company.garbages);
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }
}
