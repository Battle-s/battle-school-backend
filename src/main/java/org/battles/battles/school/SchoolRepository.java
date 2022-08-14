package org.battles.battles.school;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SchoolRepository extends JpaRepository<School, Long> {

    Optional<School> findBySchoolDomainName(String domainName);

    Optional<School> findBySchoolName(String schoolName);

    Optional<School> findBySchoolNameAndSchoolDomainName(String schoolName,String domain);
}
