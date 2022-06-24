package peaksoft.restcrudlms.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import peaksoft.restcrudlms.entities.Group;


public interface GroupRepository extends JpaRepository<Group, Long> {
}