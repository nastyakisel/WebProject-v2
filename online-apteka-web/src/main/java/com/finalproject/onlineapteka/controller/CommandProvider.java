package com.finalproject.onlineapteka.controller;

import java.util.HashMap;
import java.util.Map;

import com.finalproject.onlineapteka.command.Command;
import com.finalproject.onlineapteka.command.impl.AddCommand;
import com.finalproject.onlineapteka.command.impl.GetCategoriesCommand;
import com.finalproject.onlineapteka.command.impl.GetRecipeDetailsCommand;
import com.finalproject.onlineapteka.command.impl.ProlongRecipeCommand;
import com.finalproject.onlineapteka.command.impl.AddDrugCommand;
import com.finalproject.onlineapteka.command.impl.AddDrugToCartCommand;
import com.finalproject.onlineapteka.command.impl.AssignRecipeCommand;
import com.finalproject.onlineapteka.command.impl.CheckCartCommand;
import com.finalproject.onlineapteka.command.impl.CreateRecipeCommand;
import com.finalproject.onlineapteka.command.impl.EditCommand;
import com.finalproject.onlineapteka.command.impl.EditDrugCommand;
import com.finalproject.onlineapteka.command.impl.GetAllDrugsCommand;
import com.finalproject.onlineapteka.command.impl.GetDrugsFromCartCommand;
import com.finalproject.onlineapteka.command.impl.GetDrugsByCategoryAdmCommand;
import com.finalproject.onlineapteka.command.impl.GetDrugsByCategoryCommand;
import com.finalproject.onlineapteka.command.impl.GetOrderDetailsCommand;
import com.finalproject.onlineapteka.command.impl.GetOrdersCommand;
import com.finalproject.onlineapteka.command.impl.DrugDetailsCommand;
import com.finalproject.onlineapteka.command.impl.LocaleCommand;
import com.finalproject.onlineapteka.command.impl.LogOutCommand;
import com.finalproject.onlineapteka.command.impl.LoginCommand;
import com.finalproject.onlineapteka.command.impl.MakeOrderCommand;
import com.finalproject.onlineapteka.command.impl.RegistrationCommand;
import com.finalproject.onlineapteka.command.impl.SearchDrugsCommand;


public class CommandProvider {
	private Map<String, Command> commands = new HashMap<String, Command>();
	
	CommandProvider() {
		commands.put("login", new LoginCommand());
		commands.put("registration", new RegistrationCommand());
		commands.put("logout", new LogOutCommand());
		commands.put("getAllGoods", new GetAllDrugsCommand());
		commands.put("getGoodsByCategory", new GetDrugsByCategoryCommand());
		commands.put("getGoodsByCategoryAdm", new GetDrugsByCategoryAdmCommand());
		commands.put("addGood", new AddDrugCommand());
		commands.put("add", new AddCommand());
		commands.put("goodDetails", new DrugDetailsCommand());
		commands.put("edit", new EditCommand());
		commands.put("ru", new LocaleCommand());
		commands.put("en", new LocaleCommand());
		commands.put("editGood", new EditDrugCommand());
		commands.put("addGoodtoCart", new AddDrugToCartCommand());
		commands.put("checkCart", new CheckCartCommand());
		commands.put("getFromCart", new GetDrugsFromCartCommand());
		commands.put("makeOrder", new MakeOrderCommand());
		commands.put("getOrders", new GetOrdersCommand());
		commands.put("orderDetails", new GetOrderDetailsCommand());
		commands.put("assignRecipe", new AssignRecipeCommand());
		commands.put("createRecipe", new CreateRecipeCommand());
		commands.put("prolongRecipe", new ProlongRecipeCommand()); 
		commands.put("recipeDetails", new GetRecipeDetailsCommand());
		commands.put("searchDrugs", new SearchDrugsCommand());
		commands.put("getCategories", new GetCategoriesCommand());
			
	}
	
	public Command getCommand(String commandName){
		Command command;
		command = commands.get(commandName);
		return command;
				
	}
}
