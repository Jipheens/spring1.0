package learning.learning.controllers;

import learning.learning.Utilitiess.EntityResponse;
import learning.learning.models.EmployeeModel;
import learning.learning.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin

public class employeeController {
    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/getAll")
      public EntityResponse getEmployees(){
        return employeeService.getEmployees();
    }
    @GetMapping("/getById")
    public  EntityResponse getEmployeeById(@RequestParam Long id ){
        return employeeService.getEmployeeById(id );
    }

    @PostMapping("/add")
    public EntityResponse addEmployee(@RequestBody EmployeeModel employeeModel) {
        return employeeService.addEmployee(employeeModel);
    }

    @PutMapping("/update")
    public EntityResponse updateEmployee(@RequestBody EmployeeModel employeeModel){
        return  employeeService.updateEmployee(employeeModel);
    }

    @DeleteMapping("/delete")
    public  EntityResponse deleteEmployee(@RequestParam Long id){
        return employeeService.deleteEmployee(id);
    }
    }
