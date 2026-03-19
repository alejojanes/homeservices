package org.app.service;

import org.app.dto.WorkerProfileRequest;
import org.app.entity.User;
import org.app.entity.WorkerProfile;
import org.app.repository.UserRepository;
import org.app.repository.WorkerProfileRepository;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import java.util.List;

@ApplicationScoped
public class WorkerProfileService {

    @Inject
    WorkerProfileRepository workerRepository;

    @Inject
    UserRepository userRepository;

    // ✅ Obtener todos los workers
    public List<WorkerProfile> getAll(){
        return workerRepository.listAll();
    }

    // ✅ Obtener por ID
    public WorkerProfile getById(Long id){
        WorkerProfile worker = workerRepository.findById(id);

        if(worker == null){
            throw new RuntimeException("WorkerProfile not found");
        }

        return worker;
    }

    // ✅ Obtener por userId
    public WorkerProfile getByUserId(Long userId){
        WorkerProfile worker = workerRepository
                .find("user.id", userId)
                .firstResult();

        if(worker == null){
            throw new RuntimeException("WorkerProfile not found for user");
        }

        return worker;
    }

    // 🔥 Crear WorkerProfile (con validaciones clave)
    @Transactional
    public WorkerProfile create(WorkerProfileRequest request){

        // 1️⃣ Validar que el usuario exista
        User user = userRepository.findById(request.userId);

        if(user == null){
            throw new RuntimeException("User not found");
        }

        // 2️⃣ Validar que no tenga ya perfil
        WorkerProfile existing = workerRepository
                .find("user.id", request.userId)
                .firstResult();

        if(existing != null){
            throw new RuntimeException("User already has a worker profile");
        }

        // 3️⃣ Crear el perfil
        WorkerProfile worker = new WorkerProfile();
        worker.user = user;
        worker.description = request.description;
        worker.experienceYears = request.experienceYears;
        worker.verified = false;
        worker.rating = 0.0;

        workerRepository.persist(worker);

        return worker;
    }

    // ✅ Eliminar
    @Transactional
    public void delete(Long id){

        WorkerProfile worker = workerRepository.findById(id);

        if(worker == null){
            throw new RuntimeException("WorkerProfile not found");
        }

        workerRepository.delete(worker);
    }

    // 🔄 (Opcional pero recomendado) Actualizar
    @Transactional
    public WorkerProfile update(Long id, WorkerProfileRequest request){

        WorkerProfile worker = workerRepository.findById(id);

        if(worker == null){
            throw new RuntimeException("WorkerProfile not found");
        }

        // ⚠️ No cambiamos el user (regla de negocio)
        worker.description = request.description;
        worker.experienceYears = request.experienceYears;

        return worker;
    }
}