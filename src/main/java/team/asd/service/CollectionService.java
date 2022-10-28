package team.asd.service;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.ArrayUtils;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class CollectionService implements IsCollectionService {
	@Override
	public List<Object> unmodifiableList(Object... objects) {
		if (ArrayUtils.isEmpty(objects)) {
			throw new UnsupportedOperationException();
		}
		return Arrays.stream(objects)
				.collect(Collectors.toUnmodifiableList());

	}

	@Override
	public List<Object> immutableList(Object... objects) {
		if (ArrayUtils.isEmpty(objects)) {
			throw new UnsupportedOperationException();
		}
		return List.of(objects);
	}

	@Override
	public Set<Object> retrieveObjectsThatPresentInBothLists(Set<Object> firstSet, Set<Object> secondSet) {
		if (CollectionUtils.isEmpty(firstSet) || CollectionUtils.isEmpty(secondSet)) {
			return Collections.emptySet();
		}
		return new HashSet<>(CollectionUtils.intersection(firstSet, secondSet));
	}
}
