package com.ensi.repositories;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.ensi.entities.Provider;

@Repository
public interface ProviderRepository extends CrudRepository<Provider, Long> {
	
}
