package edu.kits.movie.Repository;

import edu.kits.movie.Domain.Writer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WriterRepository extends JpaRepository<Writer, Integer> {
}