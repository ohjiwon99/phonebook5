package com.javaex.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.javaex.dao.PhonebookDao;
import com.javaex.service.PhonebookService;
import com.javaex.vo.PersonVo;

/*전화번호부 애플리케이션의 요청을 처리하는 컨트롤러,
   주요 기능-->( 등록, 수정, 삭제, 리스트 조회)
    
   컨트롤러 클래스에서는 PhonebookService와 PhonebookDao를 사용하여 
   비즈니스 로직과 데이터 액세스를 처리한다.
   
   @Autowired를 사용하여 필요한 서비스를 자동으로 연결하고, 
   Model을 사용하여 뷰로 데이터를 전달한다.*/

@Controller
public class PhonebookController {

	// 필드
	//메모리에 올려줘
	@Autowired
	private PhonebookService phonebookService;

	// 생성자

	// 메소드gs

	// 메소드 일반
	
	//등록 리다이렉트
	/************************************************************
	 * list --> list 메서드를 통해 전체 연락처 리스트를 조회
	 **********************************************************/

	@RequestMapping(value = "/phone/list", method = { RequestMethod.GET, RequestMethod.POST })
	public String list(Model model) {// Model에서attribute에 담아야할때만 써준다. model은 데이터, view는 화면이다.
		System.out.println("PhonebookController.list()");

		//자동연결@Autowired
		//PhonebookService phonebookService = new PhonebookService();
		List<PersonVo> personList = phonebookService.exeList();

		// PhonebookDao phonebookDao= new PhonebookDao();

		// List<PersonVo> personList=phonebookDao.personSelect();
		//System.out.println(personList);

		model.addAttribute("pList", personList);// 별명, 진짜 주소

		return "list";
	}

	/***********************************
	 * 등록2 묶어서 받는거
	 *********************************/
	// http://localhost:8080/phonebook5(여기까지
	// 공통주소/phone/write?name=오지원&hp=010&company=02
	@RequestMapping(value = "/phone/write2", method = { RequestMethod.GET, RequestMethod.POST })
	public String write2(@ModelAttribute PersonVo personVo) {
		System.out.println("PhonebookController.write2()");

		System.out.println(personVo.toString());

		// 서비스를 메모리에 올리고

		// 서비스의 메소드 사용
		//PhonebookService phonebookService = new PhonebookService();
		//phonebookService.exeWrite(personVo);

		// dao를 메모리에 올린다
		//PhonebookDao phonebookDao = new PhonebookDao();

		// dao.personInt(vo) 저장
		//phonebookDao.personInsert(personVo);
		phonebookService.exeWrite(personVo);

		return "redirect:/phone/list";
	}
	
	/***********************************
	 * 수정폼
	 *********************************/
	@RequestMapping(value = "/phone/modifyform", method = { RequestMethod.GET, RequestMethod.POST })
	public String modifyForm(@RequestParam(value = "no") int no, Model model) {
		System.out.println("PhonebookController.modifyForm()");
		System.out.println(no);

		//PhonebookService phonebookService = new PhonebookService();
		PersonVo personVo = phonebookService.exeModifyForm(no);

		model.addAttribute("personVo", personVo);
		return "modifyForm";
	}

	/**************************************************************
	 * 수정 --> modify 메서드와 modifyForm 메서드를 통해 연락처의 정보를 수정
	 **************************************************************/
	@RequestMapping(value = "/phone/modify", method = { RequestMethod.GET, RequestMethod.POST })
	public String modify(@ModelAttribute PersonVo personVo) {
		System.out.println("PhonebookController.modify()");
		System.out.println(personVo);
		
		phonebookService.exeModify(personVo);

		//PhonebookService phonebookService = new PhonebookService();
		
		return "redirect:/phone/list";
	}
	
	/*******************************************************
	 * 삭제--> delete 메서드를 통해 전화번호부에서 선택한 연락처를 삭제
	 *******************************************************/
	// 삭제
	@RequestMapping(value = "/phone/delete", method = { RequestMethod.GET, RequestMethod.POST })
	public String delete(@RequestParam("no") int no) {//@RequestParam어노테이션을 사용하여 요청 파라미터인 "no"값을 가져오고, 이 값을 int타입의 변수 no에 저장한다.
		System.out.println("PhonebookController.delete()");

		System.out.println(no);

		//PhonebookService phonebookService = new PhonebookService();
		phonebookService.exeDelete(no);

		return "redirect:/phone/list";
	}



	
	
	
	
	
	
	
	
	
	
	
	/************************************************************
	 * 등록폼 --> writeForm 메서드를 통해 연락처를 등록하는 폼을 조회가능
	 ***********************************************************/

	// localhost:8080/phonebook5/phone/writeForm
	@RequestMapping(value = "/phone/writeForm", method = { RequestMethod.POST, RequestMethod.GET })
	public String writeForm() {
		System.out.println("PhonebookController.writeForm()");

		return "writeForm";
	}

	/************************************************************
	 * 등록 --> write 메서드와 write2 메서드를 통해 새로운 연락처 정보를 등록
	 *************************************************************/
	// http://localhost:8080/phonebook5/phone/write?name=오지원&hp=010&company=02
	@RequestMapping(value = "/phone/write", method = { RequestMethod.GET, RequestMethod.POST })
	public String write(@RequestParam(value = "name") String name, @RequestParam(value = "hp") String hp,
			@RequestParam(value = "company") String company) {
		System.out.println("PhonebookController.write()");

		System.out.println(name);
		System.out.println(hp);
		System.out.println(company);

		// vo로 묶는다
		PersonVo personVo = new PersonVo(name, hp, company);

		// dao메모리에 올린다
		//PhonebookDao phonebookDao = new PhonebookDao();

		// dao.personInsert(vo)저장한다.
		//phonebookDao.personInsert(personVo);
		phonebookService.exeWrite(personVo);

		// 리스트로 리다이렉트
		return "redirect:/phone/list";

	}
	



}
