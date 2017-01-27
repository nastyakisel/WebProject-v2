package com.finalproject.onlineapteka.utils;

import java.util.ArrayList;
import java.util.List;

import com.finalproject.onlineapteka.bean.ErrorBean;

public class ValidationUtils {
	public static List<ErrorBean> validateInput(List<String> inputList) {
		List<ErrorBean> errors = new ArrayList<>();
		for (int i = 0; i < inputList.size(); i++) {
			if (inputList.get(i).isEmpty()) {
				ErrorBean emptyInput = new ErrorBean("emptyField");
				errors.add(emptyInput);
			}
		}
		return errors;
	}
}
