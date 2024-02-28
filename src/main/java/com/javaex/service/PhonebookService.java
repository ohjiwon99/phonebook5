package com.javaex.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaex.dao.PhonebookDao;
import com.javaex.vo.PersonVo;

@Service
public class PhonebookService {
	
	// 필드
	//메모리에 올려줘
	@Autowired
	private PhonebookDao phonebookDao;

	// 생성자

	// 메소드gs

	// 메소드 일반
	
	// 등록  exeWrite --> 전화번호부에 새로운 연락처를 등록하는 기능
	public int exeWrite(PersonVo personVo) {
		System.out.println("PhonebookService.exeWrite()");

		// 비지니스로직X
		//PhonebookDao phonebookDao = new PhonebookDao();

		int count = phonebookDao.personInsert(personVo);

		return count;
	}
	
	
	
	//리스트 exeLis --> 전체 연락처 리스트를 조회하는 기능
	public List<PersonVo> exeList() {
		System.out.println("PhonebookService.exeList()");

		//PhonebookDao phonebookDao = new PhonebookDao();
		List<PersonVo> personList = phonebookDao.personSelect();

		return personList;
	}

	

	//삭제 exeDelete --> 메서드는 지정된 번호의 연락처를 삭제하는 기능
	public int exeDelete(int no) {
		System.out.println("PhonebookService.exeDelete()");

		//PhonebookDao phonebookDao = new PhonebookDao();
		int count = phonebookDao.personDelete(no);

		return count;
	}

	
	//수정등록  exeModifyForm --> 메서드는 수정할 연락처의 정보를 조회하는 기능
	public PersonVo exeModifyForm(int no) {
		System.out.println("PhonebookService.exeModifyForm()");

		//PhonebookDao phonebookDao = new PhonebookDao();
		PersonVo personVo = phonebookDao.personSelectOne(no);

		return personVo;
	}

	
	//수정 exeModify --> 메서드는 연락처 정보를 수정하여 업데이트하는 기능
	public int exeModify(PersonVo personVo) {
		System.out.println("PhonebookService.exeModify()");

		//PhonebookDao phonebookDao = new PhonebookDao();
		int count = phonebookDao.personUpdate(personVo);

		return count;
	}

}