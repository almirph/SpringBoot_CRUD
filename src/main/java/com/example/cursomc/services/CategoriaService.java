package com.example.cursomc.services;

import com.example.cursomc.domain.Categoria;
import com.example.cursomc.dto.CategoriaDTO;
import com.example.cursomc.repositories.CategoriaRepository;
import com.example.cursomc.services.exceptions.DataIntegrityException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepository repo;

    public Categoria find (Integer id) {
        Optional <Categoria> obj = repo.findById(id);
        return obj.orElse(null);
    }

    public Categoria insert(Categoria obj) {
        obj.setId(null);
        return repo.save(obj);
    }

    public Categoria update (Categoria obj) {
        Categoria newObj = find(obj.getId());
        updateData(newObj, obj);
        find(obj.getId());
        return repo.save(obj);
    }

    public void delete(Integer id) {
        find(id);
        try {
            repo.deleteById(id);
        }
        catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("Não é possível incluir uma categoria que não tem produtos");
        }
    }

    public List<Categoria> findAll() {
        return repo.findAll();
    }

    public Page<Categoria> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
        PageRequest pageRequest = PageRequest.of(page, linesPerPage, Sort.Direction.valueOf(direction), orderBy);
        return repo.findAll(pageRequest);
    }

    public Page<Categoria> findPage2(Pageable pageable) {
        return repo.findAll(pageable);
    }

    public Categoria fromDTO(CategoriaDTO objDto) {
        return new Categoria(objDto.getId(), objDto.getNome());
    }

    private void updateData(Categoria newObj, Categoria obj) {
        newObj.setNome(newObj.getNome());
    }
}
