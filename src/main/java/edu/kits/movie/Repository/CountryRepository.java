package edu.kits.movie.Repository;

import edu.kits.movie.Domain.Country;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CountryRepository extends JpaRepository<Country, Integer> {
}