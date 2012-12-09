package com.xyz.rcp.firstapplication.tableviewer.commands;

import java.util.List;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;

import com.xyz.rcp.firstapplication.model.ModelProvider;
import com.xyz.rcp.firstapplication.model.Person;

public class Print extends AbstractHandler {
	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		List<Person> personList = ModelProvider.INSTANCE.getPersons();
		for (Person p : personList) {
			System.out.println(p);
		}
		return null;
	}
}
