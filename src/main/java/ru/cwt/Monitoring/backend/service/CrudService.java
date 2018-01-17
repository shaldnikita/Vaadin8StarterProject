package ru.cwt.Monitoring.backend.service;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;
import ru.cwt.Monitoring.backend.model.AbstractEntity;


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
