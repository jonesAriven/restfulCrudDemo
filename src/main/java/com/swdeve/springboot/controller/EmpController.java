package com.swdeve.springboot.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.swdeve.springboot.dao.DepartmentDao;
import com.swdeve.springboot.dao.EmployeeDao;
import com.swdeve.springboot.entities.Employee;

@Controller
public class EmpController {
	private    Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired // 有@Autowired 注解就不需要get，set方法了
	EmployeeDao employeeDao;

	@Autowired
	DepartmentDao departmentDao;

	/**
	 *  查询所有员工
	 *  restful风格uri：请求地址：/emps，请求方式：get
	 * @param model
	 * @return
	 */
	@GetMapping("/emps")
	public String list(Model model) {
//		 // 日志的级别；
//		 // 由低到高 trace<debug<info<warn<error
//		 // 可以调整输出的日志级别；日志就只会在这个级别以以后的高级别生效
//		 logger.trace("这是trace日志...");
//		 logger.debug("这是debug日志...");
//		 // SpringBoot默认给我们使用的是info级别的，没有指定级别的就用SpringBoot默认规定的级别；root级别
//		 logger.info("这是info日志...");
//		 logger.warn("这是warn日志...");
//		 logger.error("这是error日志...");
		model.addAttribute("emps", employeeDao.getAll());
		 // thymeleaf默认就会拼串
        // classpath:/templates/xxxx.html
		return "emp/list";
	}

	/**
	 * 跳转到员工新增页面
	 * restful风格uri：请求地址：/emp，请求方式：get
	 * @param model
	 * @return
	 */
	@GetMapping("/emp")
	public String toAddEmpPage(Model model) {
		model.addAttribute("depts", departmentDao.getDepartments());
		return "emp/edit";
	}

	/**
	 * 新增员工
	 * restful风格uri：请求地址：/emp，请求方式：post
	 * @param emp
	 * @return
	 */
	@PostMapping("/emp")
	public String addEmp(Employee emp) {
		logger.info("添加员工的信息为：" + emp.toString());
		employeeDao.save(emp);
		// redirect: 表示重定向到一个地址  /代表当前项目路径   重定向是客户端完成的，可以看到访问地址的变化
        // forward: 表示转发到一个地址   转发是服务端完成的，在浏览器端看不到访问地址的变化
		return "redirect:/emps";//重定向到查询员工列表页面
	}

	/**
	 * 跳转到员工修改页面,查询某个员工的信息进行会写
	 * restful风格uri：请求地址：/emp/{id}，请求方式：get
	 * @param id
	 * @param model
	 * @return
	 */
	@GetMapping("/emp/{id}")
	public String toUpdateEmpPage(@PathVariable("id") Integer id,Model model) {
		logger.info("修改员工的id为：" + id);
		Employee emp = employeeDao.get(id);
		model.addAttribute("emp", emp);
		model.addAttribute("depts", departmentDao.getDepartments());
		logger.info("修改的员工信息为：" + emp.toString());
		return "emp/edit";
	}

	/**
	 * 修改员工
	 * restful风格uri：请求地址：/emp/{id}，请求方式：put
	 * @param emp
	 * @return
	 */
	@PutMapping("/emp")
	public String updateEmp(Employee emp) {
		logger.info("需要修改的员工信息为：" + emp.toString());
		employeeDao.save(emp);
		return "redirect:/emps";//重定向到查询员工列表页面
	}

	/**
	 * 删除员工
	 * restful风格uri：请求地址：/emp/{id}，请求方式：delete
	 * @return
	 */
	@DeleteMapping("/emp/{id}")
	public String deleteEmp(@PathVariable("id") Integer id) {
		logger.info("需要修改的员工id为：" + id);
		employeeDao.delete(id);
		return "redirect:/emps";//重定向到查询员工列表页面
	}
}
