package pl.arturzaczek.school.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.arturzaczek.school.entitis.SchoolClass;
import pl.arturzaczek.school.repositories.SchoolClassRepository;

import java.util.List;

@Service
public class SchoolClassService {

    private SchoolClassRepository schoolClassRepository;

    @Autowired
    public SchoolClassService(SchoolClassRepository schoolClassRepository) {
        this.schoolClassRepository = schoolClassRepository;
    }

    public List<SchoolClass> getAllSC(){
        List <SchoolClass> schoolClassList = schoolClassRepository.findAll();
        return schoolClassList;
    }
}
