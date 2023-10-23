package br.com.savebluapi.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import br.com.savebluapi.models.Device;
import br.com.savebluapi.models.dtos.DeviceDTO;
import br.com.savebluapi.repositories.DeviceRepository;
import jakarta.validation.ConstraintViolationException;

@Service
public class DeviceService {
    
    @Autowired
    DeviceRepository repository;

    @Autowired
    ModelMapper mapper;

    public List<DeviceDTO> listAll() {
        return repository.findAll().stream()
            .map(specie -> mapper.map(specie, DeviceDTO.class))
            .collect(Collectors.toList());        
    }

    public DeviceDTO findById(Long id) throws Exception {
        
        if (id == null || id < 1) {
            throw new IllegalArgumentException("ID inválido!");
        }

        Optional<Device> optional = repository.findById(id);
        if (optional.isPresent()) {
            return mapper.map(optional.get(), DeviceDTO.class);
        } else {
            throw new Exception("Device não encontrado");
        }
    }

    public Long create(DeviceDTO newObjectDTO) throws Exception {

        try {
            Device entity = mapper.map(newObjectDTO, Device.class);
            Device created = repository.save(entity);
            return created.getId();
        } catch(ConstraintViolationException | DataIntegrityViolationException e){
            throw new Exception("Dados informados violam restrições no BD.");
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("Um erro ocorreu!");
        }
    }

    public void deleteById(Long id) throws Exception{
        try{
            repository.deleteById(id);
        }catch(ConstraintViolationException | DataIntegrityViolationException e){
            throw new Exception("Não é possível deletar o device com o ID informado.");
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("Um erro ocorreu!");
        }
    }

    public Long update(DeviceDTO newObjectDTO) throws Exception {

        Long id = newObjectDTO.getId();
        Optional<Device> optional = repository.findById(id);
        if (optional.isPresent()) {
            try{
                Device entity = optional.get();
                mapper.map(newObjectDTO, entity);
                repository.save(entity);
                return entity.getId();
            }catch(ConstraintViolationException | DataIntegrityViolationException e){
                throw new Exception("Dados informados violam restrições no BD.");
            } catch (Exception e) {
                e.printStackTrace();
                throw new Exception("Um erro ocorreu!");
            }
        } else {
            throw new Exception("Um erro ocorreu!");
        }
    }
}
