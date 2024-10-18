package com.example.E_Commerce_MicroServices.services;



import com.example.E_Commerce_MicroServices.models.Admin;
import com.example.E_Commerce_MicroServices.repositories.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminService {

    @Autowired
    private AdminRepository adminRepository;

    public Admin createAdmin(Admin admin) {
        return adminRepository.save(admin);
    }

    public List<Admin> getAllAdmins() {
        return adminRepository.findAll();
    }

    public Admin getAdminById(int id) {
        return adminRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Admin not found with ID: " + id));
    }

    public void deleteAdmin(int id) {
        adminRepository.deleteById(id);
    }
}
