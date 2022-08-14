package org.battles.battles.school;

import lombok.RequiredArgsConstructor;
import org.battles.battles.exception.exception.CSchoolNameSchoolDomainException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class SchoolService {

    private final SchoolRepository schoolRepository;

    @Transactional
    public School getSchoolId(String schoolName, String schoolDomainName) {
        School school;
        if (schoolRepository.findBySchoolDomainName(schoolDomainName).isPresent()) {
            school = schoolRepository.findBySchoolDomainName(schoolDomainName).get();
            if (!school.getSchoolName().equals(schoolName)) {
                throw new CSchoolNameSchoolDomainException();
            }
        } else {
            if (schoolRepository.findBySchoolName(schoolName).isPresent()) {
                throw new CSchoolNameSchoolDomainException();
            }
            school = School.builder()
                .schoolName(schoolName)
                .schoolDomainName(schoolDomainName)
                .build();
            schoolRepository.save(school);
        }
        return school;
    }

}
