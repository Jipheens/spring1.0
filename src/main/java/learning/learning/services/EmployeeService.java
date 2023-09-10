package learning.learning.services;

import learning.learning.Utilitiess.EntityResponse;
import learning.learning.models.EmployeeModel;
import learning.learning.repos.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service

public class EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;

    public EntityResponse addEmployee (EmployeeModel employeeModel) {
        EntityResponse response = new EntityResponse();

        try {
                response.setMessages(HttpStatus.OK.getReasonPhrase());
                response.setStatusCode(HttpStatus.OK.value());
                response.setEntity(employeeRepository.save(employeeModel));


        } catch (Exception e) {
            response.setMessages(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase());
            response.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
        }
        return response;
    }

    public EntityResponse getEmployees() {
        EntityResponse response=new EntityResponse();
        try {
            Iterable<EmployeeModel> existingEmloyees= employeeRepository.findAll();
            response.setMessages(HttpStatus.OK.getReasonPhrase());
            response.setStatusCode(HttpStatus.OK.value());
            response.setEntity(existingEmloyees);
        }
        catch (Exception ex){
            response.setMessages(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase());
            response.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
        }
        return response;
    }

    public EntityResponse getEmployeeById(Long id) {
      EntityResponse response= new EntityResponse();
      try {

          Optional<EmployeeModel> existingEmployee = employeeRepository.findById(id) ;

          response.setMessages(HttpStatus.OK.getReasonPhrase());
          response.setStatusCode(HttpStatus.OK.value());
          response.setEntity(existingEmployee);
      }
      catch (Exception ex){
          response.setMessages(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase());
          response.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
      }
        return response;
    }

    public EntityResponse updateEmployee(EmployeeModel employeeModel) {
        EntityResponse response = new EntityResponse();
        try {
            EmployeeModel existingEmployee = employeeRepository.findById(employeeModel.getId()).orElse(null);
            if (existingEmployee != null) {
                // Update the fields of the existingEmployee with the new data from employeeModel
                existingEmployee.setFName(employeeModel.getFName());
                existingEmployee.setAge(employeeModel.getAge());
                existingEmployee.setLName(employeeModel.getLName());
                existingEmployee.setId(employeeModel.getId());
                existingEmployee.setDepartment(employeeModel.getDepartment());
                existingEmployee.setDob(employeeModel.getDob());

                employeeRepository.save(existingEmployee);

                response.setMessages("Employee updated successfully");
                response.setStatusCode(HttpStatus.OK.value());
                response.setEntity(existingEmployee);
            } else {
                response.setMessages("Employee not found");
                response.setStatusCode(HttpStatus.NOT_FOUND.value());
            }
        } catch (Exception e) {
            response.setMessages("Error updating employee: " + e.getMessage());
            response.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
        }
        return response;
    }

}
