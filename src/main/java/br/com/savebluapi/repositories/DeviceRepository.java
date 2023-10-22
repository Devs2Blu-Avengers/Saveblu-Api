package br.com.savebluapi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.savebluapi.models.Device;

public interface DeviceRepository extends JpaRepository<Device, Long> {
    
}
