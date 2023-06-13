package com.example.block7crudvalidation.clase.persona.aplicacion;

import com.example.block7crudvalidation.clase.estudiante.aplicacion.EstudianteServiceImp;
import com.example.block7crudvalidation.clase.estudiante.controller.dto.EstudianteOutPutSimple;
import com.example.block7crudvalidation.clase.estudiante.dominio.EstudianteEntity;
import com.example.block7crudvalidation.clase.persona.PersonaImpRepositorio;
import com.example.block7crudvalidation.clase.persona.controller.dto.Persona.PersonaInput;
import com.example.block7crudvalidation.clase.persona.controller.dto.Persona.PersonaOutPutFather;
import com.example.block7crudvalidation.clase.persona.controller.dto.Persona.PersonaOutPutFull;
import com.example.block7crudvalidation.clase.persona.controller.dto.Persona.PersonaOutPutSimple;
import com.example.block7crudvalidation.clase.persona.dominio.PersonaEntity;
import com.example.block7crudvalidation.clase.profesor.aplicacion.ProfesorServiceImp;
import com.example.block7crudvalidation.clase.profesor.controller.dto.ProfesorOutputSimple;
import com.example.block7crudvalidation.clase.profesor.dominio.ProfesorEntity;
import com.example.block7crudvalidation.excepciones.EntityNotFoundException;
import com.example.block7crudvalidation.excepciones.UnprocessableEntityException;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.*;

@Component
public class PersonaServiceImpl implements PersonaService {

    @Autowired
    private PersonaImpRepositorio personaRepository;
    @Autowired
    private ProfesorServiceImp profesorServiceImp;
    @Autowired
    private EstudianteServiceImp estudianteServiceImp;

    @Override
    public PersonaOutPutSimple buscarPersonaID(@PathVariable Long id) {
        PersonaEntity personaEntity;
        Optional<PersonaEntity> optionalEntity = personaRepository.findById(id);

        if (id == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

        if (optionalEntity.isPresent()) {
            return optionalEntity.get().parsePersonaOutputSimple();
        } else {
            throw new EntityNotFoundException("No se ha encontrado la persona con el id: " + id);
        }

    }


    @Override
    public List<PersonaOutPutFather> listarPersonas(@RequestParam("outputType") String outputType) {
        List<PersonaEntity> listaPersonas = personaRepository.findAll();
        List<PersonaOutPutFather> personaOutPutFathers = new ArrayList<>();

        for (PersonaEntity persona : listaPersonas) {
            if (outputType.equalsIgnoreCase("full")) {
                personaOutPutFathers.add(persona.parsePersonaOutputFull());
            } else {
                personaOutPutFathers.add(persona.parsePersonaOutputSimple());
            }
        }

        return personaOutPutFathers;
    }

    public List<PersonaOutPutFather> buscarPersonas(String user, String name, String surname, Date fechaCreacion, String ordenarPor) {
        List<PersonaEntity> personas = personaRepository.findAll();

        List<PersonaEntity> resultados = new ArrayList<>();

        for (PersonaEntity persona : personas) {
            boolean cumpleCriterios = false;

            if (user != null && persona.getUsuario().equals(user)) {
                cumpleCriterios = true;
            }
            if (name != null && persona.getName().equals(name)) {
                cumpleCriterios = true;
            }
            if (surname != null && persona.getSurname().equals(surname)) {
                cumpleCriterios = true;
            }
            if (fechaCreacion != null && persona.getCreated_date().compareTo(fechaCreacion) >= 0) {
                cumpleCriterios = true;
            }

            if (cumpleCriterios) {
                resultados.add(persona);
            }
        }


        if ("user".equals(ordenarPor)) {
            resultados.sort(Comparator.comparing(PersonaEntity::getUsuario));
        } else if ("name".equals(ordenarPor)) {
            resultados.sort(Comparator.comparing(PersonaEntity::getName));
        }


        return convertirAPersonaOutput(resultados);
    }

    public List<String> listarPersonasPagina(String outputType, int pagina, int tamanoPagina) {
        List<String> hoja = crearHoja(personaRepository.findAll(), outputType);
        int totalAtributos = hoja.size();
        int totalPaginas = (int) Math.ceil((double) totalAtributos / tamanoPagina);
        int inicio = (pagina - 1) * tamanoPagina;
        int fin = Math.min(inicio + tamanoPagina, totalAtributos);

        if (pagina <= 0 || pagina > totalPaginas) {
            return new ArrayList<>(); // Página inválida, retornar una lista vacía
        }

        return hoja.subList(inicio, fin);
    }

    private List<String> crearHoja(List<PersonaEntity> personas, String outputType) {
        List<String> hoja = new ArrayList<>();
        PersonaOutPutSimple personaOutPutSimple;
        PersonaOutPutFull personaOutPutFull;

        for (PersonaEntity persona : personas) {

            if ("simple".equals(outputType)) {
                personaOutPutSimple = persona.parsePersonaOutputSimple();
                hoja.add("id: " + personaOutPutSimple.getId());
                hoja.add("usuario: " + personaOutPutSimple.getUsuario());
                hoja.add("name: " + personaOutPutSimple.getName());
                hoja.add("surname: " + personaOutPutSimple.getSurname());
                hoja.add("company_email: " + personaOutPutSimple.getCompany_email());
                hoja.add("personal_email: " + personaOutPutSimple.getPersonal_email());
                hoja.add("city: " + personaOutPutSimple.getCity());
                hoja.add("active: " + personaOutPutSimple.getActive());
                hoja.add("created_date: " + personaOutPutSimple.getCreated_date());
                hoja.add("imagen_url: " + personaOutPutSimple.getImagen_url());
                hoja.add("termination_date: " + personaOutPutSimple.getTermination_date());
            } else {
                personaOutPutFull = persona.parsePersonaOutputFull();
                hoja.add("id: " + personaOutPutFull.getId());
                hoja.add("usuario: " + personaOutPutFull.getUsuario());
                hoja.add("name: " + personaOutPutFull.getName());
                hoja.add("surname: " + personaOutPutFull.getSurname());
                hoja.add("company_email: " + personaOutPutFull.getCompany_email());
                hoja.add("personal_email: " + personaOutPutFull.getPersonal_email());
                hoja.add("city: " + personaOutPutFull.getCity());
                hoja.add("active: " + personaOutPutFull.getActive());
                hoja.add("created_date: " + personaOutPutFull.getCreated_date());
                hoja.add("imagen_url: " + personaOutPutFull.getImagen_url());
                hoja.add("termination_date: " + personaOutPutFull.getTermination_date());

                // Agregar información adicional del profesor si está presente
                if (personaOutPutFull.getProfesorOutputSimple() != null) {
                    ProfesorOutputSimple profesorOutputSimple = personaOutPutFull.getProfesorOutputSimple();
                    hoja.add("profesor id: " + profesorOutputSimple.getIdProfesor());
                    hoja.add("profesor name: " + profesorOutputSimple.getBranch());
                }

                // Agregar información adicional del estudiante si está presente
                if (personaOutPutFull.getEstudianteOutPutSimple() != null) {
                    EstudianteOutPutSimple estudianteOutPutSimple = personaOutPutFull.getEstudianteOutPutSimple();
                    hoja.add("estudiante id: " + estudianteOutPutSimple.getIdEstudiante());
                    hoja.add("estudiante name: " + estudianteOutPutSimple.getIdEstudiante());
                    hoja.add("estudiante surname: " + estudianteOutPutSimple.getComents());
                    hoja.add("estudiante surname: " + estudianteOutPutSimple.getNum_hours_week());
                }
            }
        }

        return hoja;
    }


    public List<PersonaOutPutFather> convertirAPersonaOutput(List<PersonaEntity> personas) {
        List<PersonaOutPutFather> personasOutput = new ArrayList<>();

        for (PersonaEntity persona : personas) {
            personasOutput.add(persona.parsePersonaOutputFull());
        }

        return personasOutput;
    }


    @Override
    public PersonaOutPutSimple addPersona(@RequestBody PersonaInput personaInput) throws Exception {
        PersonaEntity p;
        Optional<PersonaInput> personaOptional = Optional.ofNullable(personaInput);

        if (personaOptional.isPresent()) {
            if (personaInput.getUsuario().length() >= 10 && personaInput.getUsuario().length() <= 6) {
                throw new UnprocessableEntityException("Longitud de usuario no puede ser superior a 10 caracteres");
            } else if (personaInput.getPassword() == null) {
                throw new UnprocessableEntityException("Longitud de password no puede ser inferior a 8 caracteres");
            } else if (personaInput.getCompany_email() == null) {
                throw new UnprocessableEntityException("El email de la empresa no puede contener el nombre de usuario");
            } else if (personaInput.getPersonal_email() == null) {
                throw new UnprocessableEntityException("El email personal no puede contener el nombre de usuario");
            } else if (personaInput.getPersonal_email().contains(personaInput.getCompany_email())) {
                throw new UnprocessableEntityException("El email personal no puede contener el email de la empresa");
            } else if (personaInput.getCity() == null) {
                throw new UnprocessableEntityException("Longitud de ciudad no puede ser inferior a 3 caracteres");
            } else if (personaInput.getActive() == null) {
                throw new UnprocessableEntityException("El campo active no puede ser nulo");
            } else {
                p = new PersonaEntity(personaInput);
                personaRepository.save(p);
                return p.parsePersonaOutputSimple();
            }
        } else {
            throw new UnprocessableEntityException("Usuario no puede ser nulo");
        }
    }

    @Override
    public void deletePersonaById(Long id) {
        Optional<PersonaEntity> op1 = personaRepository.findById(id);
        PersonaEntity persona;
        ProfesorEntity profesorEntity;
        EstudianteEntity estudianteEntity;

        if (op1.isPresent()) {
            persona = op1.get();
            profesorEntity = persona.getProfesorEntity();
            estudianteEntity = persona.getEstudianteEntity();

            if (profesorEntity != null) {
                profesorServiceImp.deletePersonaById(profesorEntity.getId_profesor());
            } else if (estudianteEntity != null) {
                estudianteServiceImp.borrarEstudiante(estudianteEntity.getId_estudiante());
            }
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        persona.setEstudianteEntity(null);
        persona.setProfesorEntity(null);
        personaRepository.save(persona);
        personaRepository.delete(persona);
    }

    @Override
    public PersonaOutPutSimple updatePersona(@PathVariable Long id, @RequestBody PersonaInput personaData) {
        Optional<PersonaEntity> personaOptional = personaRepository.findById(id);
        PersonaEntity persona;

        if (personaOptional.isPresent()) {
            persona = new PersonaEntity(personaData);
            persona.setId(id);
            personaRepository.save(persona);
            return persona.parsePersonaOutputSimple();

        } else {
            throw new UnprocessableEntityException("El usuario que esta buscando para la actualizacion no existe");
        }
    }

    public PersonaOutPutSimple getPersonaPorNombre(@PathVariable String nombre) {
        List<PersonaEntity> listaPersonas = personaRepository.findAll();
        PersonaOutPutSimple p = null;

        for (PersonaEntity persona : listaPersonas) {
            if (persona.getName().equals(nombre)) {
                return persona.parsePersonaOutputSimple();
            }
        }
        throw new UnprocessableEntityException("No existe ningun usuario con ese nombre");
    }
}
