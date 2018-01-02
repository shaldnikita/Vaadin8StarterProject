package ru.shaldnikita.Tags.backend.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.shaldnikita.Tags.backend.model.AbstractEntity;


import java.util.List;
import java.util.Optional;


public abstract class CrudService<T extends AbstractEntity> {

	protected abstract JpaRepository<T, Long> getRepository();

	public T save(T entity) {
		return getRepository().save(entity);
	}

    @Transactional
	public void delete(long id) {
	    T t = load(id);
	    t.setDel(true);

        save(t);
	}

	public T load(long id) {
		return getRepository().findOne(id);
	}

	public abstract long countAnyMatching(Optional<String> filter);

}
