package com.techouts.add;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

public class EmployeeDao
{
   JdbcTemplate Template;
   
   public void setTemplate(JdbcTemplate jdbcTemplate)
   {
	   this.Template=jdbcTemplate;
	   
   }
   public int save(Employee employee)
   {
	   String sql="insert into ravindradb.emp1 values("+employee.getId()+",'"+employee.getName()+"',"+employee.getSalary()+", '"+employee.getLocation()+"')";
	   return Template.update(sql);
   }
   public int update(Employee employee)
   {
	   String sql="update ravindradb.emp1 set name='"+employee.getName()+"', salary="+employee.getSalary()+" where id="+employee.getId()+"";   
	   return Template.update(sql);
   }
   public int delete(int id){    
	    String sql="delete from ravindradb.emp1 where id="+id+"";    
	    return Template.update(sql);    
	}    
	@SuppressWarnings("deprecation")
	public Employee getEmpById(int id){    
	    String sql="select * from ravindradb.emp1 where id=?";    
	    return Template.queryForObject(sql, new Object[]{id},new BeanPropertyRowMapper<Employee>(Employee.class));    
	}    
	public List<Employee> getEmployees(){    
	    return Template.query("select * from ravindradb.emp1",new RowMapper<Employee>(){    
	        public Employee mapRow(ResultSet rs, int row) throws SQLException {    
	            Employee e=new Employee();    
	            e.setId(rs.getInt(1));    
	            e.setName(rs.getString(2)); 
	            e.setSalary(rs.getDouble(3));
	            e.setLocation(rs.getString(4));   
	          //  System.out.println(e.getName());
	            return e;    
	        }

			 
	    });  
	}
}
