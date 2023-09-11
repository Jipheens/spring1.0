package learning.learning.services;

import learning.learning.Utilitiess.EntityResponse;
import learning.learning.Utilitiess.UserRequestContacts;
import learning.learning.models.EmployeeModel;
import learning.learning.repos.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
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
            Iterable<EmployeeModel> existingEmloyees= employeeRepository.findByDeleteflag('N');
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

          Iterable<EmployeeModel> existingEmployee = employeeRepository.findByDeleteflag('N');
          if (existingEmployee!=null) {

              response.setMessages(HttpStatus.OK.getReasonPhrase());
              response.setStatusCode(HttpStatus.OK.value());
              response.setEntity(existingEmployee);
          }
          else {
              response.setMessages("Employee not found");

          }

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

    public EntityResponse<EmployeeModel> deleteEmployee(Long id) {
        EntityResponse response=new EntityResponse();
        try {
            Optional<EmployeeModel> existingEmployee = employeeRepository.findById(id);

            if (existingEmployee != null) {
                EmployeeModel employeeModel = existingEmployee.get();

                employeeModel.setDeletedBy(UserRequestContacts.UserRequestContext.getCurrentUser());
                employeeModel.setDeletedFla('Y');
                employeeModel.setDeletedTime(LocalDateTime.now());

                EmployeeModel deletedEmployed = employeeRepository.save(employeeModel);

                response.setMessages("Employee record Deleted successfully");
                response.setStatusCode(HttpStatus.OK.value());

            }

        }
        catch (Exception ex){
            response.setMessages("Error Deleting record: " + ex.getMessage());
            response.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
        }
return response;
    }
}
