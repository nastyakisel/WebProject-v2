package com.finalproject.onlineapteka.controller;

import java.util.HashMap;
import java.util.Map;

import com.finalproject.onlineapteka.command.Command;
import com.finalproject.onlineapteka.command.impl.AddCommand;
import com.finalproject.onlineapteka.command.impl.AddGoodCommand;
import com.finalproject.onlineapteka.command.impl.AddGoodtoCartCommand;
import com.finalproject.onlineapteka.command.impl.CheckCartCommand;
import com.finalproject.onlineapteka.command.impl.EditCommand;
import com.finalproject.onlineapteka.command.impl.EditGoodCommand;
import com.finalproject.onlineapteka.command.impl.GetAllGoodsCommand;
import com.finalproject.onlineapteka.command.impl.GetGoodsFromCartCommand;
import com.finalproject.onlineapteka.command.impl.GetGoodsByCategoryAdmCommand;
import com.finalproject.onlineapteka.command.impl.GetGoodsByCategoryCommand;
import com.finalproject.onlineapteka.command.impl.GoodDetailsCommand;
import com.finalproject.onlineapteka.command.impl.LocaleCommand;
import com.finalproject.onlineapteka.command.impl.LogOutCommand;
import com.finalproject.onlineapteka.command.impl.LoginCommand;
import com.finalproject.onlineapteka.command.impl.RegistrationCommand;


public class CommandProvider {
	private Map<String, Command> commands = new HashMap<String, Command>();
	
	CommandProvider() {
		commands.put("login", new LoginCommand());
		commands.put("registration", new RegistrationCommand());
		commands.put("logout", new LogOutCommand());
		commands.put("getAllGoods", new GetAllGoodsCommand());
		commands.put("getGoodsByCategory", new GetGoodsByCategoryCommand());
		commands.put("getGoodsByCategoryAdm", new GetGoodsByCategoryAdmCommand());
		commands.put("addGood", new AddGoodCommand());
		commands.put("add", new AddCommand());
		commands.put("goodDetails", new GoodDetailsCommand());
		commands.put("edit", new EditCommand());
		commands.put("ru", new LocaleCommand());
		commands.put("en", new LocaleCommand());
		commands.put("editGood", new EditGoodCommand());
		commands.put("addGoodtoCart", new AddGoodtoCartCommand());
		commands.put("checkCart", new CheckCartCommand());
		commands.put("getFromCart", new GetGoodsFromCartCommand());
		
	}
	
	public Command getCommand(String commandName){
		Command command;
		command = commands.get(commandName);
		return command;
				
	}
}
