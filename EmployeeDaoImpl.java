package com.websystique.springmvc.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.websystique.springmvc.model.Employee;

@Repository("employeeDao")
public class EmployeeDaoImpl extends AbstractDao<Integer, Employee> implements EmployeeDao {

	public Employee findById(int id) {
		return getByKey(id);
	}

	public void save(Employee employee) {
		persist(employee);
	}
	
	public void deleteEmployeeBySsn(String ssn) {
		Query query = getSession().createSQLQuery("delete from Employee where ssn = :ssn");
		query.setString("ssn", ssn);
		query.executeUpdate();
	}

	@SuppressWarnings("unchecked")
	public List<Employee> findAllEmployees() {
		Criteria criteria = createEntityCriteria();
		return (List<Employee>) criteria.list();
	}

	public Employee findEmployeeByEmployeeId(String employeeId) {
		Criteria criteria = createEntityCriteria();
		criteria.add(Restrictions.eq("employeeId", employeeId));
		return (Employee) criteria.uniqueResult();
	}

/*	public Employee findByIdForLogin(int id) {
		@SuppressWarnings("unused")
		Query query=getSession().createSQLQuery("select from Employee where ssn='+ssn+'");
		
		return null;
	}   */
	
	
	public Employee getFindEmployeeByEmployeeId(String employeeId) {
		/*Employee employee = null;
		//@SuppressWarnings("null")
		Query qry=getSession().createSQLQuery("select from employee where employeeId="+employee.getEmployeeId());
		qry.uniqueResult();
		return employee; */
		
		Criteria criteria=createEntityCriteria();
		criteria.add(Restrictions.eq("employeeId",employeeId));
		return (Employee) criteria.uniqueResult();	
	}

	public boolean authenticateEmployee(String employeeId, String password) {
		//return null;
		Session session = sessionFactory.openSession();
		boolean userFound = false;
		//Query using Hibernate Query Langauge
		String sql_query = "FROM Employee where employeeId=? and password=?";
		Query query = session.createQuery(sql_query);
		query.setParameter(0, employeeId);
		query.setParameter(1, password);
		List list = query.list();
		if((list != null)&&(list.size()>0)){
			userFound = true;
		}
		session.close();
		return userFound;
		
		
	}

	/*public Employee getEmployeeBySsn(String ssn) {
		return null;
	}*/

/*	public Employee findEmployeeByEmployeeId(String employeeId) {
		return null;
	}*/

	

	public Employee getEmployeeBySsn(String ssn) {
		return null;
	}

	public boolean isEmployeeIdUnique(Integer id, String employeeId) {
		return true;
	}

	

	
}
