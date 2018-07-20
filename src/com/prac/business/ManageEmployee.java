package com.prac.business;

import java.util.Iterator;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.prac.model.Employee;

public class ManageEmployee {

	 private static SessionFactory factory; 
	   public static void main(String[] args) {
	      
	      try {
	         factory = new Configuration().configure().buildSessionFactory();
	      } catch (Throwable ex) { 
	         System.err.println("Failed to create sessionFactory object." + ex);
	         throw new ExceptionInInitializerError(ex); 
	      }
	      
	      ManageEmployee ME = new ManageEmployee();

	      /* Add few employee records in database */
	      Integer empID1 = ME.addEmployee("ankur", "ankur@practice", "1000");
	      Integer empID2 = ME.addEmployee("uzma", "uzma@practice", "5000");
	      Integer empID3 = ME.addEmployee("tapan", "tapan@practice", "10000");

	      /* List down all the employees */
	      ME.listEmployees();

	      /* Update employee's records */
	    // int empID1 = 71;
	   // ME.updateEmployee(empID1, "ibrahim123@prac.com");

	      /* Delete an employee from the database */
	     //ME.deleteEmployee(empID1);

	      /* List down new list of the employees */
	     System.out.println("Updated table view");
	     ME.listEmployees();
	   }
	   
	   /* Method to CREATE an employee in the database */
	   public Integer addEmployee(String name, String mailId, String contactNumber){
	      Session session = factory.openSession();
	      Transaction tx = null;
	      Integer employeeID = null;
	      try {
	         
	    	 
	    	 tx = session.beginTransaction();
	         Employee employee = new Employee(name, mailId, contactNumber);
	         System.out.println("Adding employee [ " + employee.getEmpName() + " ]"); 
	         employeeID = (Integer) session.save(employee); 
	         tx.commit();
	      } catch (HibernateException e) {
	         if (tx!=null) tx.rollback();
	         e.printStackTrace(); 
	      } finally {
	         session.close(); 
	      }
	      return employeeID;
	   }
	   
	   /* Method to  READ all the employees */
	   public void listEmployees( ){
	      Session session = factory.openSession();
	      Transaction tx = null;
	      
	      try {
	         tx = session.beginTransaction();
	         List employees = session.createQuery("FROM Employee").list(); 
	         System.out.println("================================================");
	         for (Iterator iterator = employees.iterator(); iterator.hasNext();){
	            Employee employee = (Employee) iterator.next(); 
	            System.out.print("Name: " + employee.getEmpName()); 
	            System.out.print("MailID: " + employee.getEmpMailId()); 
	            System.out.println("ContactNumber: " + employee.getEmpContactNumber()); 
	         }
	         System.out.println("================================================");
	         tx.commit();
	      } catch (HibernateException e) {
	         if (tx!=null) tx.rollback();
	         e.printStackTrace(); 
	      } finally {
	         session.close(); 
	      }
	   }
	   
	   /* Method to UPDATE salary for an employee */
	   public void updateEmployee(Integer EmployeeID, String mailId ){
	      Session session = factory.openSession();
	      Transaction tx = null;
	      
	      try {
	         tx = session.beginTransaction();
	         Employee employee = (Employee)session.get(Employee.class, EmployeeID); 
	         employee.setEmpMailId(mailId);;
			 session.update(employee); 
	         tx.commit();
	      } catch (HibernateException e) {
	         if (tx!=null) tx.rollback();
	         e.printStackTrace(); 
	      } finally {
	         session.close(); 
	      }
	   }
	   
	   /* Method to DELETE an employee from the records */
	   public  void deleteEmployee(Integer EmployeeID){
	      Session session = factory.openSession();
	      Transaction tx = null;
	      
	      try {
	         tx = session.beginTransaction();
	         Employee employee = (Employee)session.get(Employee.class, EmployeeID); 
	         session.delete(employee); 
	         tx.commit();
	      } catch (HibernateException e) {
	         if (tx!=null) tx.rollback();
	         e.printStackTrace(); 
	      } finally {
	         session.close(); 
	      }
	   }

}
