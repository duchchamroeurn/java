package com.sangkhim.spring.security;

import java.security.SecureRandom;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 
 * @author Sangkhim
 * 
 * http://localhost:8080/springmvc/oauth/token?client_id=my-trusted-client&grant_type=password&username=admin&password=password
 * http://localhost:8080/springmvc/oauth/token?client_id=my-trusted-client&grant_type=refresh_token&refresh_token=
 * http://localhost:8080/springmvc/employee/list?access_token=
 * http://localhost:8080/springmvc/employee/htmllist?access_token=
 *
 */

@Controller
@RequestMapping("/employee")
public class SecurityOauth2RestController {
    static Set Employees;

    static {
        Employees = new HashSet();
        Employee foobar = null;
        for (int i = 0; i < 10; i++) {
            double sal = new SecureRandom().nextInt(400)*500;
            foobar = new Employee(i, "Employee " + i, sal );
            Employees.add(foobar);
        }
    }

    @RequestMapping(value = "/{employeeId}", method = RequestMethod.GET, headers = "Accept=application/json", produces = {"application/json"})
    @ResponseBody
    public Employee getEmployee(@PathVariable int employeeId) {
        Iterator X = Employees.iterator();
        while (X.hasNext()) {
            Employee f = (Employee) X.next();
            if (f.getId() == employeeId) return f;
        }
        return null;
    }

    @RequestMapping(value = "/htmllist", method = RequestMethod.GET, headers = "Accept=text/html", produces = {"text/html"})
    @ResponseBody
    public String getEmployeeListHTML() {
        String retVal = "<html><body><table border=1>";
        Iterator X = Employees.iterator();
        while (X.hasNext()) {
            Employee f = (Employee) X.next();
            retVal += "<tr><td>" + f.getId() + "</td><td>" + f.getName() + "</td><td>" + f.getSalary() + "</td></tr>";
        }
        retVal += "</table></body></html>";

        return retVal;
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET, headers = "Accept=application/json", produces = {"application/json"})
    @ResponseBody
    public Set getEmployeeList() {
        return Employees;
    }

    @RequestMapping(method = RequestMethod.GET, headers = "Accept=application/json", produces = {"application/json"})
    @ResponseBody
    public Set getEmployees() {
        return Employees;
    }   

    @RequestMapping(method = RequestMethod.POST, headers = "Accept=application/json", produces = {"application/json"}, consumes = {"application/json"})
    @ResponseBody
    public boolean createEmployee(@RequestBody Employee employee) {
        return Employees.add(employee);
    }

    @RequestMapping(value = "/{employeeId}", method = RequestMethod.PUT, headers = "Accept=application/json", produces = {"application/json"}, consumes = {"application/json"})
    @ResponseBody
    public Employee editEmployee(@RequestBody Employee foobar, @PathVariable int employeeId) {
        Iterator X = Employees.iterator();
        while (X.hasNext()) {
            Employee f = (Employee) X.next();
            if (employeeId == f.getId()) {
                f.setId(foobar.getId());
                f.setName(foobar.getName());
                return f;
            }
        }
        return null;
    }

    @RequestMapping(value = "/{employeeId}", method = RequestMethod.DELETE, headers = "Accept=application/json", produces = {"application/json"})
    @ResponseBody
    public boolean deleteEmployee(@PathVariable int employeeId) {
        System.out.println("Delete call.");
        Iterator fooIterator = Employees.iterator();
        while (fooIterator.hasNext()) {
            Employee foobar = (Employee) fooIterator.next();
            System.out.println(foobar);
            if (foobar.getId() == employeeId) {
                fooIterator.remove();
                return true;
            }
        }
        return false;
    }
    
}