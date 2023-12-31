package learning.learning.repos;

import learning.learning.models.EmployeeModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<EmployeeModel,Long> {

    @Query("SELECT e FROM EmployeeModel e WHERE e.deletedFla = :deletedFla")
    Iterable<EmployeeModel> findByDeleteflag(Character deletedFla);
}
