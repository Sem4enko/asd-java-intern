package team.asd.service;

import lombok.NonNull;
import team.asd.entities.IsPerson;

import java.util.*;
import java.util.stream.Collectors;

public class Person implements IsPersonService {
    @Override
    public @NonNull List<IsPerson> collectPersonsWithNameStartsWith(List<IsPerson> personList, String prefix) {
        if (personList == null || personList.isEmpty())
            return new ArrayList<>();
        if (prefix == null || prefix.isEmpty())
            return personList;
        return personList.stream()
                .filter(p -> p != null && p.getName() != null && p.getName().startsWith(prefix))
                .collect(Collectors.toList());
    }

    @Override
    public @NonNull Map<Integer, List<IsPerson>> collectPersonsByAge(List<IsPerson> personList) {
        if (personList == null) {
            return new HashMap<>();
        }
        return personList.stream()
                .filter(n -> n != null && n.getAge() != null && n.getAge() >= 0)
                .sorted(Comparator.comparing(IsPerson::getAge))
                .collect(Collectors.groupingBy(IsPerson::getAge));

    }

    @Override
    public @NonNull Double calculateAverageAge(List<IsPerson> personList) {
        if (personList == null) {
            return 0.0d;
        }
        return personList.stream()
                .filter(n -> n != null && n.getAge() != null && n.getAge() >= 0)
                .mapToDouble(IsPerson::getAge)
                .average().getAsDouble();
    }

    @Override
    public @NonNull IntSummaryStatistics sumAndCountAge(List<IsPerson> personList) {
        if (personList == null)
            return new IntSummaryStatistics();
        return personList.stream()
                .filter(p -> p != null && p.getAge() != null && p.getAge() >= 0)
                .mapToInt(IsPerson::getAge)
                .summaryStatistics();
    }
}
