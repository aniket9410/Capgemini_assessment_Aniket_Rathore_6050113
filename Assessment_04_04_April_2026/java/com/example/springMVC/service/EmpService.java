package com.example.springMVC.service;




import org.springframework.stereotype.Service;

import com.example.springMVC.dto.EmpDto;
import com.example.springMVC.entity.Emp;
import com.example.springMVC.repo.EmpRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmpService {

    private final EmpRepository repo;

    public EmpService(EmpRepository repo) {
        this.repo = repo;
    }

    private EmpDto convertToDto(Emp emp) {
        EmpDto dto = new EmpDto();
        dto.setEmpId(emp.getEmpId());
        dto.setEmpName(emp.getEmpName());
        dto.setEmpSal(emp.getEmpSal());
        dto.setEmpDoj(emp.getEmpDoj());
        dto.setDeptName(emp.getDeptName());
        return dto;
    }

   
    private Emp convertToEntity(EmpDto dto) {
        Emp emp = new Emp();
        emp.setEmpId(dto.getEmpId());
        emp.setEmpName(dto.getEmpName());
        emp.setEmpSal(dto.getEmpSal());
        emp.setEmpDoj(dto.getEmpDoj());
        emp.setDeptName(dto.getDeptName());
        return emp;
    }

    public List<EmpDto> getAll() {
        return repo.findAll()
                .stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    public EmpDto getById(Integer id) {
        return repo.findById(id)
                .map(this::convertToDto)
                .orElse(null);
    }

    public void save(EmpDto dto) {
        repo.save(convertToEntity(dto));
    }

    public void delete(Integer id) {
        repo.deleteById(id);
    }
}
