package com.openuniversity.springjwt.controllers;


import com.openuniversity.springjwt.models.ERole;
import com.openuniversity.springjwt.models.EmployeeDetails;
import com.openuniversity.springjwt.models.Role;
import com.openuniversity.springjwt.models.User;
import com.openuniversity.springjwt.payload.request.EmployeeAddRequest;
import com.openuniversity.springjwt.payload.request.EmployeeEditRequest;
import com.openuniversity.springjwt.payload.response.EmployeeDetailsResponse;
import com.openuniversity.springjwt.payload.response.MessageResponse;
import com.openuniversity.springjwt.repository.EmployeeRepository;
import com.openuniversity.springjwt.repository.RoleRepository;
import com.openuniversity.springjwt.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@Controller
@RequestMapping("/api/employee")
public class EmployeeController {

    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    EmailValidation emailValidation;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    UserRepository userRepository;


    @GetMapping("/all")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> getAllEmployeeDetails() {
        boolean employeesExists = false;
        long count = employeeRepository.count();
        //System.out.println("XXXXXXXXXXXXXXX " + count);
        List <EmployeeDetails> list= new ArrayList<EmployeeDetails>();
        if (count > 0){
            employeesExists = true;
            //employeeDetails = new EmployeeDetails(employeeCode);
        } else {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: No employees have not been registered!"));
        }
        if(employeesExists){

            for(long i = 1; i<= count; i++) {
                list.add(employeeRepository.findEmployeeDetailsById(i).orElse(null));// = employeeRepository.findEmployeeDetailsById(i).orElse(null);
            }
        }
        else{
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: The employee has not been registered!"));
        }

        return new ResponseEntity<List<EmployeeDetails>>(list, HttpStatus.OK);
    }

    @GetMapping("/one/{employeeCode}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> getEmployeeDetails(@PathVariable("employeeCode") String employeeCode) {
        boolean employeeExists = false;
        boolean employeeActive = false;

        EmployeeDetails employeeDetails = new EmployeeDetails();
        if (employeeRepository.existsByEmployeeCode(employeeCode)){
            employeeExists = true;
            employeeDetails = employeeRepository.findEmployeeDetailsByEmployeeCode(employeeCode);
            //employeeDetails = new EmployeeDetails(employeeCode);
        } else {
            if(employeeRepository.existsByEmployeeName(employeeCode)){
                employeeDetails = employeeRepository.findEmployeeDetailsByEmployeeName(employeeCode);
            }
            else{
                return ResponseEntity
                        .badRequest()
                        .body(new MessageResponse("Error: The employee has not been registered!"));
            }
        }

        if(employeeDetails.getEmployeeStatus().equals("Active")){
            employeeActive = true;
        }
        else{
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: The employee is not activated!"));
        }
        if(employeeExists ){
            return ResponseEntity.ok(new EmployeeDetailsResponse(
                    employeeDetails.getEmployeeCode(),employeeDetails.getEmployeeName(),
                    employeeDetails.getEmployeeAddress(), employeeDetails.getEmployeeEmail(),
                    employeeDetails.getEmployeeContactNumber(), employeeDetails.getUser()
            ));
        }
        else{
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: The employee has not been registered!"));
        }
    }


    @PostMapping("/add")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> addEmployee(@RequestBody EmployeeAddRequest employeeAddRequest){
        boolean validEmail = false;

        if (emailValidation.validateEmail(employeeAddRequest.getEmployeeEmail())) {
            validEmail = true;
        }
        else{
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: enter a valid email"));
        }

        if (employeeRepository.existsByEmployeeCode(employeeAddRequest.getEmployeeCode())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Username is already taken!"));
        }

        EmployeeDetails employeeDetails = new EmployeeDetails(employeeAddRequest.getEmployeeCode(),
                employeeAddRequest.getEmployeeName(), employeeAddRequest.getEmployeeAddress(),
                employeeAddRequest.getEmployeeEmail(), employeeAddRequest.getEmployeeContact(), "Active",employeeAddRequest.getUser() );

        if(validEmail){
            employeeRepository.save(employeeDetails);
        }
        return ResponseEntity.ok(new MessageResponse("Employee registered successfully!"));
    }

    @PutMapping("/edit/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> editEmployee(@RequestBody EmployeeEditRequest employeeEditRequest,@PathVariable("id") long id){

        String nameNew = employeeEditRequest.getName();
        String addressNew = employeeEditRequest.getAddress();
        String emailNew = employeeEditRequest.getEmail();
        String contactNew = employeeEditRequest.getContact();

        EmployeeDetails employeeDetails = employeeRepository.findEmployeeDetailsById(id).orElse(null);
        employeeDetails.setEmployeeName(nameNew);
        employeeDetails.setEmployeeAddress(addressNew);
        employeeDetails.setEmployeeEmail(emailNew);
        employeeDetails.setEmployeeContactNumber(contactNew);
        employeeRepository.save(employeeDetails);

        return ResponseEntity.ok(new MessageResponse("Employee has been edited successfully!"));
    }

    @PutMapping("/deactivate")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> deactivateEmployee(@RequestParam long id) {
        EmployeeDetails employeeDetails = employeeRepository.findEmployeeDetailsById(id).orElse(null);
        employeeDetails.setEmployeeStatus("Inactive");
        employeeRepository.save(employeeDetails);

        return ResponseEntity.ok(new MessageResponse("Employee has been deactivated successfully!"));
    }

    @PutMapping("/activate")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> activateEmployee(@RequestParam long id) {
        EmployeeDetails employeeDetails = employeeRepository.findEmployeeDetailsById(id).orElse(null);
        employeeDetails.setEmployeeStatus("Active");
        employeeRepository.save(employeeDetails);

        return ResponseEntity.ok(new MessageResponse("Employee has been activated successfully!"));
    }

    @PutMapping("/changeRole/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> changeRole(@PathVariable("id") long id, @RequestParam String role){
        EmployeeDetails employeeDetails = employeeRepository.findEmployeeDetailsById(id).orElse(null);
        User user = employeeDetails.getUser();
        Set<Role> existingRoles = user.getRoles();

        if(role.equals("Admin")){
            Role role1 = roleRepository.findByName(ERole.ROLE_ADMIN)
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            existingRoles.add(role1);
        }
        if(role.equals("Operator")){
            Role role2 = roleRepository.findByName(ERole.ROLE_OPERATOR)
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            existingRoles.add(role2);
        }

        user.setRoles(existingRoles);
        userRepository.save(user);
        return ResponseEntity.ok(new MessageResponse("Employee has been activated successfully!"));
    }
}
